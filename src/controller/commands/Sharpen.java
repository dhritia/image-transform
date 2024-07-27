package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * Sharpen class calls model method to sharpen the image.
 */
public class Sharpen implements ImageCommands {
  private String name;
  private String newName;
  private int p;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public Sharpen(String command) {
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

  @Override
  public void goCommands(Model m) {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.sharpen(this.name, this.newName, this.p);
  }
}
