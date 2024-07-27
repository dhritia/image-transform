package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * Compress class calls model method to compress image.
 */
public class Compress implements ImageCommands {
  private String name;
  private String newName;
  private int p;

  /**
   * Constructor for compressing the image.
   *
   * @param command input parameters to the command.
   */
  public Compress(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    p = Integer.parseInt(s[0]);
    if (p < 0 || p > 100) {
      throw new NumberFormatException("Percentage value not valid. ");
    }
    this.name = s[1];
    this.newName = s[2];
    if (s.length > 3) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  /**
   * Call the model's compress() to compress image.
   *
   * @param m model.
   */
  @Override
  public void goCommands(Model m) {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.compress(this.name, this.newName, this.p);
  }
}
