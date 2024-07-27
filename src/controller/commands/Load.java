package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import controller.ImageCommands;
import model.ImageObject;
import model.ImageObjectRGB;
import model.Model;

/**
 * Load class loads the image.
 */
public class Load implements ImageCommands {
  String path;
  String name;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public Load(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    this.path = s[0];
    this.name = s[1];
    if (s.length > 2) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  /**
   * Reads the PPM file.
   *
   * @param filename ppm file to be read
   * @return the ppm image
   * @throws IOException if there are any errors in reading the ppm file.
   */
  private ImageObject readPPM(String filename) throws IOException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
      return null;
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt();

    float[][] r = new float[height][width];
    float[][] g = new float[height][width];
    float[][] b = new float[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        r[i][j] = sc.nextInt();
        g[i][j] = sc.nextInt();
        b[i][j] = sc.nextInt();
      }
    }
    ImageObject im;
    im = new ImageObjectRGB(r, g, b, width, height);
    return im;
  }

  @Override
  public void goCommands(Model m) throws IOException {
    ImageObject im;
    File imageFile;
    BufferedImage image;
    image = null;
    if (path.substring(path.lastIndexOf('.')).equals(".ppm")) {
      im = readPPM(this.path);
    } else {
      try {
        imageFile = new File(path);
        image = ImageIO.read(imageFile);

      } catch (IOException e) {
        System.out.println("File " + path + " not found! Try again.");
        return;
      }
      float[][] r = new float[image.getHeight()][image.getWidth()];
      float[][] g = new float[image.getHeight()][image.getWidth()];
      float[][] b = new float[image.getHeight()][image.getWidth()];
      for (int i = 0; i < image.getWidth(); i++) {
        for (int j = 0; j < image.getHeight(); j++) {
          int p = image.getRGB(i, j);
          r[j][i] = (p >> 16) & 0xFF;
          g[j][i] = (p >> 8) & 0xFF;
          b[j][i] = p & 0xFF;
        }
      }
      im = new ImageObjectRGB(r, g, b, image.getWidth(), image.getHeight());
    }
    if (im != null) {
      m.putImage(this.name, im);
    }
  }
}
