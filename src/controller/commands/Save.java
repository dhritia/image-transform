package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import controller.ImageCommands;
import model.ImageObject;
import model.Model;

/**
 * Save class saves the image.
 */
public class Save implements ImageCommands {

  private String path;
  private String name;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public Save(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    this.path = s[0];
    this.name = s[1];
    if (s.length > 2) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  private void savePPM(String path, float[][] r, float[][] g, float[][] b) throws IOException {
    FileWriter writer = new FileWriter(path);
    writer.write("P3\n");
    writer.write(r[0].length + " " + r.length + "\n");
    writer.write("255\n");
    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r[0].length; j++) {
        writer.write((int) r[i][j] + "\n" + (int) g[i][j] + "\n" + (int) b[i][j] + "\n");
      }
    }
    writer.flush();
    writer.close();
  }

  @Override
  public void goCommands(Model m) throws IOException, FileNotFoundException {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    int rgbcolor;
    BufferedImage image;
    ImageObject im = m.getImage(this.name);
    float[][] r = im.getR();
    float[][] g = im.getG();
    float[][] b = im.getB();
    if (path.substring(path.lastIndexOf(".") + 1).equals("ppm")) {
      savePPM(path, r, g, b);
    }
    image = new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < im.getWidth(); i++) {
      for (int j = 0; j < im.getHeight(); j++) {
        rgbcolor = ((int) r[j][i] << 16) | ((int) g[j][i] << 8) | ((int) b[j][i]);
        image.setRGB(i, j, rgbcolor);
      }
    }
    if (Files.exists(Paths.get(path.substring(0, path.lastIndexOf('/'))))) {
      File outputFile = new File(path);
      ImageIO.write(image, path.substring(path.lastIndexOf(".") + 1), outputFile);
    } else {
      System.out.println(path);
      throw new FileNotFoundException();
    }
  }
}
