package controller.commands;

import java.io.IOException;

import controller.ImageCommands;
import model.Model;


/**
 * Greyscale class calls model method to greyscale image.
 */
public class GreyScale implements ImageCommands {

  private String name;
  private String newName;
  private int p;
  private char type;

  /**
   * Constructor for parsing the command.
   *
   * @param command input parameters to the command.
   */
  public GreyScale(String command, char type) {
    String[] s;
    s = command.trim().split(" ", 0);
    this.p = 100;
    this.name = s[0];
    this.newName = s[1];
    this.type = type;
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
  public void goCommands(Model m) throws IOException {
    if (!m.doesExist(this.name)) {
      throw new IllegalArgumentException();
    }
    m.createGreyScale(this.name, this.newName, this.p, this.type);
  }
}
