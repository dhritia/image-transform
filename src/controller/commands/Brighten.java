package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * Brighten class calls model method to brighten image.
 */
public class Brighten implements ImageCommands {
  private String name;
  private String newName;
  private float b;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public Brighten(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    b = Float.parseFloat(s[0]);
    this.name = s[1];
    this.newName = s[2];
    if (s.length > 3) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  /**
   * Call the model's brighten() to blur image.
   *
   * @param m model.
   */
  @Override
  public void goCommands(Model m) {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.brighten(b, this.name, this.newName);
  }
}
