package model;

/**
 * This interface implements an Image object.
 */
public interface ImageObject {
  /**
   * Gets the red component value.
   *
   * @return the red channel.
   */
  float[][] getR();

  /**
   * Gets the green component value.
   *
   * @return the green channel.
   */
  float[][] getG();

  /**
   * Gets the blue component value.
   *
   * @return the blue channel.
   */
  float[][] getB();

  /**
   * Gets the width of the array.
   *
   * @return the array width.
   */
  int getWidth();

  /**
   * Gets the height of the array.
   *
   * @return the array height.
   */
  int getHeight();

  /**
   * Add padding to the image.
   */
  void addPadding();

  /**
   * Remove padding from the image.
   *
   * @param width  width of the array.
   * @param height height of the array.
   */
  void removePadding(int width, int height);
}
