package edu.school21.renderer;

import edu.school21.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    private final PreProcessor preProcessor;

    RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void renderMsg(String str) {
        System.out.println(preProcessor.process(str));
    }
}
