package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * Darken class calls model method to darkens image.
 */
public class Darken implements ImageCommands {
  private String name;
  private String newName;
  private float d;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public Darken(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    d = Float.parseFloat(s[0]);
    this.name = s[1];
    this.newName = s[2];
    if (s.length > 3) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  /**
   * Call the model's darken() to darken image.
   *
   * @param m model.
   */
  @Override
  public void goCommands(Model m) {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.darken(this.d, this.name, this.newName);
  }
}
