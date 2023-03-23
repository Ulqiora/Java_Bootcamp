package edu.school21.printer;

import edu.school21.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private String prefix;
    private final Renderer renderer;
    public PrinterWithPrefixImpl(Renderer renderer){
        this.renderer=renderer;
    }
    @Override
    public void print(String s) {
        renderer.renderMsg(prefix + s);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
