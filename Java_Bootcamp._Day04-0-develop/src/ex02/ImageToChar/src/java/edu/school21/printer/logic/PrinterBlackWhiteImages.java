package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
public class PrinterBlackWhiteImages {
    private String white_symbol;
    private String black_symbol;
    private String imageName;

    public PrinterBlackWhiteImages(Args args, String imageName) {
        this.white_symbol = args.getWhite();
        this.black_symbol = args.getBlack();
        this.imageName = imageName;
    }
    public void start() throws IOException {
        System.out.println("KU");
        ColoredPrinter printer = new ColoredPrinter();
        System.out.println("KU");
        BufferedImage image = ImageIO.read(getClass().getResource(imageName));
        System.out.println("KU");
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);

                if (pixel == Color.WHITE.getRGB()) {
                    printer.print(" ", Ansi.Attribute.NONE,
                            Ansi.FColor.NONE, Ansi.BColor.valueOf(white_symbol));
                } else if (pixel == Color.BLACK.getRGB()) {
                    printer.print(" ", Ansi.Attribute.NONE,
                            Ansi.FColor.NONE, Ansi.BColor.valueOf(black_symbol));
                }
            }
            System.out.print("\n");
        }
    }
    public String getWhite_symbol() {
        return white_symbol;
    }
    public String getBlack_symbol() {
        return black_symbol;
    }
    public String getImageName() {
        return imageName;
    }
    public void setWhite_symbol(String white_symbol) {
        this.white_symbol = white_symbol;
    }
    public void setBlack_symbol(String black_symbol) {
        this.black_symbol = black_symbol;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
