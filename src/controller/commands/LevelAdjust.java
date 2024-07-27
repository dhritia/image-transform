package controller.commands;

import controller.ImageCommands;
import model.Model;

/**
 * LevelAdjust class calls model method to level adjust the image.
 */
public class LevelAdjust implements ImageCommands {
  private String name;
  private String newName;
  private int p;
  private int b;
  private int m;
  private int w;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public LevelAdjust(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    this.p = 100;
    this.b = Integer.parseInt(s[0]);
    this.m = Integer.parseInt(s[1]);
    this.w = Integer.parseInt(s[2]);
    if (b < 0 || b > 255 || m < 0 || m > 255 || w < 0 || w > 255) {
      throw new IllegalArgumentException();
    }
    if (b > m || b > w || m > w) {
      throw new IllegalArgumentException();
    }
    this.name = s[3];
    this.newName = s[4];
    if (s.length > 5) {
      if (s.length == 7) {
        this.p = Integer.parseInt(s[6]);
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
    m.levelsAdjust(this.name, this.newName, this.b, this.m, this.w, this.p);
  }
}
