import java.io.IOException;
import java.io.InputStreamReader;

import controller.Controller;
import controller.ControllerImpl;
import controllergui.Features;
import model.ImageModel;
import model.Model;
import view.IView;
import view.ImageView;

/**
 * This class is where the implementation of the program starts.
 */
public class ProgramRunner {

  /**
   * Main method where program execution starts.
   *
   * @param args arguments to the main method.
   * @throws IOException if there is failure in the getCommands().
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      IView view = new ImageView();
      Model m = new ImageModel();
      Features c = new controllergui.Controller(m);
      c.setView(view);
    } else {
      Controller c = new ControllerImpl(new InputStreamReader(System.in), System.out,
              new ImageModel());
      if (args.length == 1) {
        c.getCommands();
      } else {
        c.getCommands(args[1]);
      }
    }
  }
}
