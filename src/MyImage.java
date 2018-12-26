import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO; 

class MyImage {
    public static String imgPath = "../img/tree.jpg";

    // Reading an image to return as a BI
    public static BufferedImage read(File inFile) throws IOException {
        BufferedImage img = ImageIO.read(inFile);
        System.out.println("Reading complete.");
        return img;
    }

    // Darkening an image
    public static BufferedImage darken(BufferedImage img, double factor) throws IOException {
        int col = img.getWidth();
        int row = img.getHeight();
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                Color currPixel = new Color(img.getRGB(c, r));
                int red = (int) (currPixel.getRed() * factor) % 255;
                int green = (int) (currPixel.getGreen() * factor) % 255;
                int blue = (int) (currPixel.getBlue() * factor) % 255;
                Color total = new Color(red, green, blue);
                img.setRGB(c, r, total.getRGB());
            }
        }
        return img;
    }

    public static BufferedImage flipHorizontally(BufferedImage img) throws IOException {
        int col = img.getWidth();
        int row = img.getHeight();
        for (int r = 0; r < row; r++) {
            for (int c = 0, c2 = col - 1; c < col / 2; c++, c2--) {
                Color currPixel = new Color(img.getRGB(c, r));
                Color oppoPixel = new Color(img.getRGB(c2, r));
                img.setRGB(c2, r, currPixel.getRGB());
                img.setRGB(c, r, oppoPixel.getRGB());
            }
        }
        return img;
    }

    // Converting BI to black & white
    public static BufferedImage blackAndWhite(BufferedImage img) throws IOException {
        int col = img.getWidth();
        int row = img.getHeight();
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                Color currPixel = new Color(img.getRGB(c, r));
                int red = currPixel.getRed();
                int green = currPixel.getGreen();
                int blue = currPixel.getBlue();
                int avg = (red + blue + green) / 3;
                Color total = new Color(avg, avg, avg);
                img.setRGB(c, r, total.getRGB());
            }
        }
        return img;
    }
    public static void main(String args[]) throws IOException {
        File inFile = new File(imgPath);
        BufferedImage img = read(inFile);
        img = flipHorizontally(img);
        ImageIO.write(img, "jpg", inFile);
    }
}
