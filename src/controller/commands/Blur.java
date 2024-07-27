package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * Blur class calls model method to blur image.
 */
public class Blur implements ImageCommands {
  private String name;
  private String newName;
  private int p;

  /**
   * Constructor for reading the command.
   *
   * @param command input parameters to the command.
   */
  public Blur(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    this.p = 100;
    this.name = s[0];
    this.newName = s[1];
    if (s.length > 2) {
      if (s.length == 4) {
        this.p = Integer.parseInt(s[3]);
        if (p < 0 || p > 100) {
          throw new NumberFormatException("Percentage value not valid. ");
        }
      } else {
        throw new ArrayIndexOutOfBoundsException();
      }
    }
  }

  /**
   * Call the model's blur() to blur image.
   *
   * @param m model.
   */
  @Override
  public void goCommands(Model m) {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.blur(this.name, this.newName, this.p);
  }
}
