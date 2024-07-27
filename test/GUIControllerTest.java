import org.junit.Test;

import java.io.IOException;

import controllergui.Controller;
import controllergui.Features;

import static org.junit.Assert.assertEquals;

/**
 * Testing if controller works correctly GUI and model.
 */
public class GUIControllerTest {


  /**
   * Tests the setView, loaadImg, saveImg, displayImage, and setMenu methods in controller.
   *
   * @throws IOException if any method fails.
   */
  @Test
  public void testControllerMethods() throws IOException {
    StringBuilder log;
    log = new StringBuilder();
    String input;
    Features c = new Controller(new MockImageModel(log));
    c.setView(new MockImageView(log));
    c.loadImg();
    c.saveImg();
    c.displayImage();
    c.setMenu("Visualize R Component");
    c.setMenu("Sharpen");
    c.setMenu("Compress");
    c.setMenu("Adjust Levels");
    input = "Log: Features added\n"
            + "Log: File explorer called to perform load action\n"
            + "Log: File explorer called to perform save action\n"
            + "Log: Image and its histogram set\n"
            + "Log: Image and its histogram set\n"
            + "Log: Menu Panel Cleared\n"
            + "Log: Execute button added\n"
            + "Log: Image and its histogram set\n"
            + "Log: Menu Panel Cleared\n"
            + "Log: Split field added\n"
            + "Log: Image and its histogram set\n"
            + "Log: Menu Panel Cleared\n"
            + "Log: Compress field added\n"
            + "Log: Image and its histogram set\n"
            + "Log: Menu Panel Cleared\n"
            + "Log: black, mid and white field added\n";
    assertEquals(input, log.toString());
  }

  /**
   * Tests the execute methods in controller.
   *
   * @throws IOException if any method fails.
   */
  @Test
  public void testExecuteMethods() throws IOException {
    StringBuilder log;
    log = new StringBuilder();
    String input;
    Features c = new Controller(new MockImageModel(log));
    c.setView(new MockImageView(log));
    c.execute("Visualize R Component");
    c.execute("Flip Vertically");
    c.execute("Convert to Sepia");
    c.execute("Compress", "80");
    c.execute("Compress", "180");
    c.execute("10", "80", "200");
    c.execute("80", "50", "10");
    c.execute("80", "50", "310");
    input = "Log: Features added\n"
            + "Input: image image r\n"
            + "Log: Image and its histogram set\n"
            + "Log: Save label set to: Current image not saved\n"
            + "Input: image image\n"
            + "Log: Image and its histogram set\n"
            + "Log: Save label set to: Current image not saved\n"
            + "Input: image image 100\n"
            + "Log: Image and its histogram set\n"
            + "Log: Save label set to: Current image not saved\n"
            + "Input: image image 80\n"
            + "Log: Image and its histogram set\n"
            + "Log: Save label set to: Current image not saved\n"
            + "Error message:\n"
            + "Title: Invalid Values\n"
            + "Message:Compression ratio value must be between 0-100\n"
            + "Input: image image 10 80 200 100\n"
            + "Log: Image and its histogram set\n"
            + "Log: Save label set to: Current image not saved\n"
            + "Error message:\n"
            + "Title: Invalid Values\n"
            + "Message:Values for black, mid and white have to be in ascending order\n"
            + "Error message:\n"
            + "Title: Invalid Values\n"
            + "Message:Values for black, mid and white have to be in ascending order\n";
    assertEquals(input, log.toString());
  }

  /**
   * Tests the executeSplit methodsin the controller.
   *
   * @throws IOException if any method fails.
   */
  @Test
  public void testExecuteSplitMethods() throws IOException {
    StringBuilder log;
    log = new StringBuilder();
    String input;
    Features c = new Controller(new MockImageModel(log));
    c.setView(new MockImageView(log));
    c.executeSplit("Blur", "50");
    c.executeSplit("20", "60", "120", "90");
    c.executeSplit("120", "60", "120", "90");
    c.executeSplit("20", "60", "320", "90");
    input = "Log: Features added\n"
            + "Input: image imagesplit 50\n"
            + "Log: Image and its histogram set\n"
            + "Input: image imagesplit 20 60 120 90\n"
            + "Log: Image and its histogram set\n"
            + "Error message:\n"
            + "Title: Invalid Values\n"
            + "Message:Values for black, mid and white have to be in ascending order\n"
            + "Input: image imagesplit 20 60 320 90\n"
            + "Log: Image and its histogram set\n";
    assertEquals(input, log.toString());
  }
}
