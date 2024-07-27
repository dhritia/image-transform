package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * RGBComponent class calls model method to create an image with the red, green or blue of the
 * image.
 */
public class RGBComponent implements ImageCommands {

  private String name;
  private String newName;
  private char channel;

  /**
   * Constructor for reading the command.
   *
   * @param command input parameters to the command.
   * @param c       r,g,b
   */
  public RGBComponent(String command, char c) {
    String[] s;
    s = command.trim().split(" ", 0);
    this.name = s[0];
    this.newName = s[1];
    this.channel = c;
    if (s.length > 2) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  @Override
  public void goCommands(Model m) {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.createChannel(this.name, this.newName, this.channel);
  }
}
