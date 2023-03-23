package edu.school21;

import edu.school21.preprocessor.*;
import edu.school21.renderer.*;
import edu.school21.printer.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        standardTest();
        BeanedTest();
    }
    public static void standardTest(){
        System.out.println("Демонстрация результата создания класса Printer обычным способом:");
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix ("Prefix ");
        printer.print ("Hello!");
    }
    public static void BeanedTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = applicationContext.getBean("printerWithPrefixStdUpp", Printer.class);
        System.out.println("printerWithPrefixStdUpp Test:");
        printer.print("Hello!");
        printer = applicationContext.getBean("printerWithPrefixStdLow", Printer.class);
        System.out.println("printerWithPrefixStdLow Test:");
        printer.print("Hello!");
        printer = applicationContext.getBean("printerWithPrefixErrUpp", Printer.class);
        System.out.println("printerWithPrefixErrUpp Test:");
        printer.print("Hello!");
        printer = applicationContext.getBean("printerWithPrefixErrLow", Printer.class);
        System.out.println("printerWithPrefixErrLow Test:");
        printer.print("Hello!");
        printer = applicationContext.getBean("printerDateTimeStdUpp", Printer.class);
        System.out.println("printerDateTimeStdUpp Test:");
        printer.print("Hello!");
        printer = applicationContext.getBean("printerDateTimeStdLow", Printer.class);
        System.out.println("printerDateTimeStdLow Test:");
        printer.print("Hello!");
        printer = applicationContext.getBean("printerDateTimeErrUpp", Printer.class);
        System.out.println("printerDateTimeErrUpp Test:");
        printer.print("Hello!");
        printer = applicationContext.getBean("printerDateTimeErrLow", Printer.class);
        System.out.println("printerDateTimeErrLow Test:");
        printer.print("Hello!");
    }
}
