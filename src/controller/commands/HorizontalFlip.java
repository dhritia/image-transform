package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * HorizontalFlip class calls model method to horizontally flip image.
 */
public class HorizontalFlip implements ImageCommands {
  private String name;
  private String newName;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public HorizontalFlip(String command) {
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
    m.flipHorizontal(this.name, this.newName);
  }
}
