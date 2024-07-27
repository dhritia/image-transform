import java.awt.image.BufferedImage;

import controllergui.Features;
import view.IView;

/**
 * This class represents a mock oof the View class.
 * It was created to test the controller.
 */
public class MockImageView implements IView {

  private StringBuilder log;

  public MockImageView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void addFeatures(Features f) {
    log.append("Log: Features added\n");
  }

  @Override
  public void showErrorMessage(String title, String message) {
    log.append("Error message:" + "\nTitle: " + title + "\nMessage:" + message + "\n");
  }

  @Override
  public void enableSaveButton() {
    log.append("Log: Save button enabled\n");
  }

  @Override
  public void setSaveLabel(String message) {
    log.append("Log: Save label set to: " + message + "\n");
  }

  @Override
  public String openFileExplorer(String action) {
    log.append("Log: File explorer called to perform " + action + " action" + "\n");
    return null;
  }

  @Override
  public void setImages(BufferedImage image, BufferedImage histogram) {
    log.append("Log: Image and its histogram set" + "\n");
  }

  @Override
  public void clearPanel() {
    log.append("Log: Menu Panel Cleared\n");
  }

  @Override
  public void addExecuteButton() {
    log.append("Log: Execute button added\n");
  }


  @Override
  public void addSplitField() {
    log.append("Log: Split field added\n");
  }

  @Override
  public void addCompressField() {
    log.append("Log: Compress field added\n");
  }

  @Override
  public void addLevelAdjustFields() {
    log.append("Log: black, mid and white field added\n");
  }
}
