package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * RGBCombine class calls model method to combine the three greyscale images into a single image
 * that gets its red, green and blue components from the three images respectively.
 */
public class RGBCombine implements ImageCommands {
  String name;
  String red;
  String green;
  String blue;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public RGBCombine(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    this.name = s[0];
    this.red = s[1];
    this.green = s[2];
    this.blue = s[3];
    if (s.length > 4) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  @Override
  public void goCommands(Model m) {
    if (!m.doesExist(this.red) || !m.doesExist(this.green) || !m.doesExist(this.blue)) {
      throw new IllegalArgumentException();
    }
    m.rgbCombine(this.red, this.green, this.blue, this.name);
  }
}
