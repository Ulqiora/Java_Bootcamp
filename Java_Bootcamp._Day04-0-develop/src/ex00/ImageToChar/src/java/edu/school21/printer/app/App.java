package edu.school21.printer.app;

import edu.school21.printer.logic.PrinterBlackWhiteImages;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        checkArgs(args);
        try {
            PrinterBlackWhiteImages logic = new PrinterBlackWhiteImages(args[0].charAt(0), args[1].charAt(0), args[2]);
            logic.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkArgs(String[] args) {
        if (args.length != 3)
            printErrorAndExit("Specify 3 input parameters: 2 characters and full path to the image");
        if (args[0].length() != 1 || args[1].length() != 1)
            printErrorAndExit("First and second parameters must be a single characters");
    }

    public static void printErrorAndExit(String msg) {
        System.err.println(msg);
        System.exit(-1);
    }

}