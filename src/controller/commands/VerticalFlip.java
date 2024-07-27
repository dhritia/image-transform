package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * VerticalFlip class calls model method to flip the image vertically.
 */
public class VerticalFlip implements ImageCommands {
  private String name;
  private String newName;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public VerticalFlip(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    this.name = s[0];
    this.newName = s[1];
    if (s.length > 2) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  @Override
  public void goCommands(Model m) {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.flipVertical(this.name, this.newName);
  }
}
