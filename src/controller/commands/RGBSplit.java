package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * RGBSplit class calls model method to split image into R, G and B channels.
 */
public class RGBSplit implements ImageCommands {
  String name;
  String red;
  String green;
  String blue;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public RGBSplit(String command) {
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
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.rgbSplit(this.red, this.green, this.blue, this.name);
  }
}
