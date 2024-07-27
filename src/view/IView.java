package view;

import java.awt.image.BufferedImage;

import controllergui.Features;

/**
 * Interface for the view. This interface represents the GUI using which user can perform image
 * manipulations.
 */
public interface IView {

  /**
   * Adds listenerts to all the JButtons and JSliders on the GUI.
   *
   * @param f object using which features can be called.
   */
  void addFeatures(Features f);

  /**
   * Shows error message dialog box on GUI.
   *
   * @param title   Title of the dialog box.
   * @param message Error message to be displayed.
   */
  void showErrorMessage(String title, String message);

  /**
   * Enables the Save Button on GUI after image has been loaded.
   */
  void enableSaveButton();

  /**
   * Sets a message on screen depending on the operation performed on the image.
   *
   * @param message Message to be displayed.
   */
  void setSaveLabel(String message);

  /**
   * Opens file explorer to allow user to load or save image.
   *
   * @param action action can either be load or save.
   * @return File path.
   */
  String openFileExplorer(String action);

  /**
   * Displays image and histogram for GUI.
   *
   * @param image     image set.
   * @param histogram histogram set.
   */
  void setImages(BufferedImage image, BufferedImage histogram);

  /**
   * Clears the existing selected data on the panel.
   */
  void clearPanel();

  /**
   * Adds execute button on GUI.
   */
  void addExecuteButton();


  /**
   * Add Split field on GUI.
   */
  void addSplitField();

  /**
   * Adds compression ratio field on GUI.
   */
  void addCompressField();

  /**
   * Add fields needed for level adjust on GUI.
   */
  void addLevelAdjustFields();
}
