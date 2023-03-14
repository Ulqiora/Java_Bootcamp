package edu.school21.printer.logic;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrinterBlackWhiteImages {
    private char white_symbol;
    private char black_symbol;
    private String imageName;

    public PrinterBlackWhiteImages(char white_symbol, char black_symbol, String imageName) {
        this.white_symbol = white_symbol;
        this.black_symbol = black_symbol;
        this.imageName = imageName;
    }
    public void start() throws IOException {
        BufferedImage image = ImageIO.read(new File(imageName));
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);

                if (pixel == Color.WHITE.getRGB()) {
                    System.out.print(white_symbol);
                } else if (pixel == Color.BLACK.getRGB()) {
                    System.out.print(black_symbol);
                }
            }
            System.out.print("\n");
        }
    }
    public char getWhite_symbol() {
        return white_symbol;
    }
    public char getBlack_symbol() {
        return black_symbol;
    }
    public String getImageName() {
        return imageName;
    }
    public void setWhite_symbol(char white_symbol) {
        this.white_symbol = white_symbol;
    }
    public void setBlack_symbol(char black_symbol) {
        this.black_symbol = black_symbol;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
