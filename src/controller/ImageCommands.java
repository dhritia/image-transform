package controller;

import java.io.IOException;

import model.Model;

/**
 * ImageCommands is a controller interface to execute the commands
 * of the model.
 */
public interface ImageCommands {

  /**
   * method that executes the commands of the model.
   *
   * @param m model.
   * @throws IOException when file path does not exist.
   */
  void goCommands(Model m) throws IOException;
}
