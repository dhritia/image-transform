import java.io.IOException;

import model.ImageObject;
import model.Model;

/**
 * MockImageModel conatins mock implementation of ImageModel setup for testing.
 */
public class MockImageModel implements Model {

  private StringBuilder log;

  public MockImageModel(StringBuilder log) {
    this.log = log;
  }


  @Override
  public ImageObject getImage(String name) {
    return null;
  }

  @Override
  public void putImage(String name, ImageObject im) {
    log.append("Input: " + name + "\n");
  }

  @Override
  public boolean doesExist(String name) {
    return true;
  }

  @Override
  public void createChannel(String name, String newName, char c) {
    log.append("Input: " + name + " " + newName + " " + c + "\n");
  }

  @Override
  public void createGreyScale(String name, String newName, int p, char type) {
    log.append("Input: " + name + " " + newName + " " + type + " " + p + "\n");
  }


  @Override
  public void flipHorizontal(String name, String newName) {
    log.append("Input: " + name + " " + newName + "\n");
  }

  @Override
  public void flipVertical(String name, String newName) {
    log.append("Input: " + name + " " + newName + "\n");
  }

  @Override
  public void brighten(float bFactor, String name, String newName) {
    log.append("Input: " + (bFactor) + " " + name + " " + newName + "\n");
  }

  @Override
  public void darken(float dFactor, String name, String newName) {
    log.append("Input: " + dFactor + " " + name + " " + newName + "\n");
  }

  @Override
  public void rgbCombine(String red, String green, String blue, String newName) {
    log.append("Input: " + red + " " + green + " " + blue + " " + newName + "\n");
  }

  @Override
  public void rgbSplit(String red, String green, String blue, String name) {
    log.append("Input: " + red + " " + green + " " + blue + " " + name + "\n");
  }

  @Override
  public void blur(String name, String newName, int p) {
    log.append("Input: " + name + " " + newName + " " + p + "\n");
  }

  @Override
  public void sharpen(String name, String newName, int p) {
    log.append("Input: " + name + " " + newName + " " + p + "\n");
  }

  @Override
  public void sepia(String name, String newName, int p) {
    log.append("Input: " + name + " " + newName + " " + p + "\n");
  }

  @Override
  public void compress(String name, String newName, int p) {
    log.append("Input: " + name + " " + newName + " " + p + "\n");
  }

  @Override
  public void histogram(String name, String newName) throws IOException {
    log.append("Input: " + name + " " + newName + "\n");
  }

  @Override
  public void colorCorrect(String name, String newName, int p) {
    log.append("Input: " + name + " " + newName + " " + p + "\n");
  }

  @Override
  public void levelsAdjust(String name, String newName, int b, int m, int w, int p) {
    log.append("Input: " + name + " " + newName + " " + b + " " + m + " " + w + " " + p + "\n");
  }

}
