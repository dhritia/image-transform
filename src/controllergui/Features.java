package controllergui;

import java.io.IOException;

import view.IView;

/**
 * Features has methods that control the various high-level features and abilities of the view.
 */
public interface Features {

  /**
   * Sets the GUI window.
   *
   * @param view view object to set up the GUI.
   */
  void setView(IView view);

  /**
   * Loads image selected via GUI and displays the same.
   *
   * @throws IOException when image mentioned in path is not found.
   */
  void loadImg() throws IOException;

  /**
   * Saves image currently displayed on the GUI.
   *
   * @throws IOException when image path is invalid.
   */
  void saveImg() throws IOException;


  /**
   * Displays the image on GUI.
   *
   * @throws IOException when histogram creation fails.
   */
  void displayImage() throws IOException;

  /**
   * Sets the menu for the selected transformation option.
   *
   * @param option option selected via GUI.
   */
  void setMenu(String option) throws IOException;

  /**
   * Executes the image transformation selected by calling model method.
   *
   * @param option image transformation option selected.
   * @throws IOException if transformation fails.
   */
  void execute(String option) throws IOException;

  /**
   * Executes the levels adjust operation by calling model.
   *
   * @param b black value.
   * @param m mid value.
   * @param w white value.
   * @throws IOException if levels adjust fails.
   */
  void execute(String b, String m, String w) throws IOException;

  /**
   * Executes compression calling the model.
   *
   * @param option option selected i.e compression
   * @param p      compression ratio.
   * @throws IOException if compression fails.
   */
  void execute(String option, String p) throws IOException;

  /**
   * Executes split operation.
   *
   * @param option  image transformation option selected.
   * @param percent percentage of split.
   * @throws IOException if split fails.
   */
  void executeSplit(String option, String percent) throws IOException;

  /**
   * Executes the levels adjust  with split operation by calling model.
   *
   * @param b       black value.
   * @param m       mid value.
   * @param w       white value.
   * @param percent split percentage.
   * @throws IOException if levels adjust with split fails.
   */
  void executeSplit(String b, String m, String w, String percent) throws IOException;
}
