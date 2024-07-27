import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

import controller.Controller;
import controller.ControllerImpl;
import model.ImageModel;
import model.ImageObject;
import model.ImageObjectRGB;
import model.Model;

import static org.junit.Assert.assertEquals;

/**
 * ControllerTest class contains controller tests.
 */
public class ControllerTest {

  //New Tests

  @Test
  public void newTestController() throws IOException {
    StringBuffer out;
    out = new StringBuffer();
    Reader in;
    StringBuilder log;
    log = new StringBuilder();
    in = new StringReader("run res/controllertest2.txt\nquit");
    Controller c = new ControllerImpl(in, out, new MockImageModel(log));
    c.getCommands();
    String input;
    input = "Input: f\n" +
            "Input: f f-comp 30\n" +
            "Input: f f-hist\n" +
            "Input: f f-color-cor 100\n" +
            "Input: f f-lvl-adj 30 80 160 100\n" +
            "Input: f f-color-cor-split 50\n" +
            "Input: f f-lvl-adj-split 30 80 160 75\n" +
            "Input: f f-blur-split 30\n" +
            "Input: f f-sharpen-split 60\n" +
            "Input: f f-sepia-split 35\n" +
            "Input: f f-value-split v 25\n" +
            "Input: f f-luma-split l 45\n" +
            "Input: f f-intensity-split i 90\n";
    assertEquals(input, log.toString());
  }

  @Test
  public void testNewExceptions() throws IOException {
    StringBuffer out;
    out = new StringBuffer();
    Reader in;
    String output;
    in = new StringReader("load res/flowers.jpg f\n" +
            "compress 80 man man\n" +
            "compress 110 f f\n" +
            "compress f 110 f\n" +
            "color-correct man f\n" +
            "colr-correct man f\n" +
            "color-correct man f split 115\n" +
            "levels-adjust 100 50 10 f f\n" +
            "levels-adjust 10 30 265 f f\n" +
            "levels-adjust -100 30 245 f f\n" +
            "levels-adjust 10 30 215 f f split -10\n" +
            "blur f f split -10\n" +
            "sharpen f f split -20\n" +
            "sepia f f spill 40\n" +
            "value-component f f split\n" +
            "luma-component f f 200\n" +
            "intensity-component f f split split\n" +
            "histogram man mna\n" +
            "quit");
    Controller c = new ControllerImpl(in, out, new ImageModel());
    c.getCommands();
    output = "Image provided in the command not found. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Image provided in the command not found. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Image provided in the command not found. Try again\n" +
            "Image provided in the command not found. Try again\n" +
            "Image provided in the command not found. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Image provided in the command not found. Try again\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testSavePPM() throws IOException {
    Model m;
    m = new ImageModel();
    float[][] r = {{1f, 2f, 3f}, {4f, 5f, 6f}, {7f, 8f, 9f}};
    float[][] g = {{1f, 2f, 3f}, {4f, 5f, 6f}, {7f, 8f, 9f}};
    float[][] b = {{1f, 2f, 3f}, {4f, 5f, 6f}, {7f, 8f, 9f}};
    ImageObject im;
    im = new ImageObjectRGB(r, g, b, 3, 3);
    m.putImage("ppmimg", im);
    StringBuffer out;
    out = new StringBuffer();
    Reader in;
    in = new StringReader("save res/test.ppm ppmimg\nquit");
    Controller c = new ControllerImpl(in, System.out, m);
    c.getCommands();
    Scanner sc;
    sc = new Scanner(new FileInputStream("res/test.ppm"));
    assertEquals("P3", sc.next());
    assertEquals(3, sc.nextInt());
    assertEquals(3, sc.nextInt());
    assertEquals(255, sc.nextInt());
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], sc.nextInt(), 0.001f);
        assertEquals(g[i][j], sc.nextInt(), 0.001f);
        assertEquals(b[i][j], sc.nextInt(), 0.001f);
      }
    }
  }

  /**
   * Tests the controller in isolation using a mock model.
   */
  @Test
  public void testController() throws Exception {
    StringBuffer out;
    out = new StringBuffer();
    Reader in;
    StringBuilder log;
    log = new StringBuilder();
    in = new StringReader("run res/controllertest.txt\nquit");
    Controller c = new ControllerImpl(in, out, new MockImageModel(log));
    c.getCommands();
    String input;
    input = "Input: f\n" +
            "Input: f f-red r\n" +
            "Input: f f-blue b\n" +
            "Input: f f-green g\n" +
            "Input: f f-value v 100\n" +
            "Input: f f-luma l 100\n" +
            "Input: f f-intensity i 100\n" +
            "Input: f f-hflip\n" +
            "Input: f f-vflip\n" +
            "Input: 50.0 f f-bright\n" +
            "Input: 50.0 f f-dark\n" +
            "Input: f-red f-green f-blue f\n" +
            "Input: f-red f-green f-blue f-new\n" +
            "Input: f f-blur 100\n" +
            "Input: f f-sharp 100\n" +
            "Input: f f-sepia 100\n";
    assertEquals(input, log.toString());
  }

  /**
   * Tests whether the controller and model work correctly together.
   */
  @Test
  public void runScript() throws Exception {
    StringBuffer out;
    out = new StringBuffer();
    Reader in;
    in = new StringReader("run res/controllertest.txt\n" +
            "quit");
    Controller c = new ControllerImpl(in, out, new ImageModel());
    c.getCommands();
    assertEquals("", out.toString());
  }

  /**
   * Tests whether the controller handles erroneous input correctly or not.
   */
  @Test
  public void testExceptions() throws Exception {
    StringBuffer out;
    out = new StringBuffer();
    Reader in;
    String output;
    in = new StringReader("load manhattan-small.png\n" +
            "run text.txt\n" +
            "load Assignment4/res/flowers.jpeg man\n" +
            "supia man man\n" +
            "brighten man 10 man\n" +
            "blur man man man\n" +
            "horizontal-flip mana man-flip\n" +
            "quit");
    Controller c = new ControllerImpl(in, out, new ImageModel());
    c.getCommands();
    output = "Command syntax is wrong. Try again\n" +
            "File text.txt not found. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Command syntax is wrong. Try again\n" +
            "Image provided in the command not found. Try again\n";
    assertEquals(output, out.toString());
  }
}
