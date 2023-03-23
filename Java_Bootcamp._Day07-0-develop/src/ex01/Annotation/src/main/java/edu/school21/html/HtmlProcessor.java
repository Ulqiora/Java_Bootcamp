package edu.school21;


import edu.school21.html.annotations.HtmlForm;
import edu.school21.html.annotations.HtmlInput;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.FileWriter;
import java.util.Set;

@SupportedAnnotationTypes({"edu.school21.annotations.HtmlForm", "edu.school21.annotations.HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);

        for (Element e : elements) {
            createHtml(e);
        }

        return false;
    }

    private void createHtml(Element element) {
        final String FORM_CREATER_FORMATED = "<form action = \"%s\" method = \"%s\">\n";
        final String INPUT_FORMATED = "\t<input type = \"%s\" name = \"%s\" placeholder = \"%s\">\n";
        final String SUBMIT_BUTTON = "\t<input type = \"submit\" value = \"Send\">\n";
        final String FORM_CLOSER = "</form>\n";

        HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
        HtmlInput htmlInput;

        try (FileWriter fileWriter = new FileWriter("target/classes/" + htmlForm.fileName())) {
            fileWriter.write(String.format(FORM_CREATER_FORMATED, htmlForm.action(), htmlForm.method()));

            for (Element e : element.getEnclosedElements()) {
                htmlInput = e.getAnnotation(HtmlInput.class);
                if (htmlInput != null)
                    fileWriter.write(String.format(INPUT_FORMATED, htmlInput.type(), htmlInput.name(), htmlInput.placeholder()));
            }

            fileWriter.write(SUBMIT_BUTTON);
            fileWriter.write(FORM_CLOSER);

            fileWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}