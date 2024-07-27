package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.Model;

/**
 * Histogram class calls model method to create image histogram.
 */
public class Histogram implements ImageCommands {
  private String name;
  private String newName;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public Histogram(String command) {
    String[] s;
    s = command.trim().split(" ", 0);
    this.name = s[0];
    this.newName = s[1];
    if (s.length > 2) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  @Override
  public void goCommands(Model m) throws IOException {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.histogram(this.name, this.newName);
  }
}
