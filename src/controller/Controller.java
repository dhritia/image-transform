package controller;

import java.io.IOException;

/**
 * This interface represents a controller. Controller manages the
 * input, output and which functionality to implement from the model.
 */
public interface Controller {

  /**
   * getCommands reads the command typed in by the user.
   *
   * @throws IOException if command read fails.
   */
  void getCommands() throws IOException;

  /**
   * getCommands reads the file entered by the user and parses through each command.
   *
   * @param filename of the file that contains commands.
   * @throws IOException if file read fails.
   */
  void getCommands(String filename) throws IOException;
}
