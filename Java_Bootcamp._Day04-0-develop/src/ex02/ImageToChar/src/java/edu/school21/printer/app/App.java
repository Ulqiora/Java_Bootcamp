package edu.school21.printer.app;

import edu.school21.printer.logic.PrinterBlackWhiteImages;
import edu.school21.printer.logic.Args;
import com.beust.jcommander.JCommander;

public class App {
    public static void main(String[] args) {
        try {
            Args jArgs = new Args();
            JCommander jCommander = new JCommander(jArgs);
            jCommander.parse(args);
            PrinterBlackWhiteImages logic = new PrinterBlackWhiteImages(jArgs, "/resources/it.bmp");
            logic.start();
        } catch (Exception e) {
            System.out.println(e.getMessage()+"main");
        }
    }
}