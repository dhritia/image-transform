package model;

import java.io.IOException;

/**
 * This interface represent a 2D array representation of an image.
 * An image can be either greyscale or RGB format.
 */
public interface Model {


  /**
   * Get the image from the hashmap.
   *
   * @param name Name of the image.
   * @return the Image object.
   */
  ImageObject getImage(String name);

  /**
   * Add the image to the hashmap.
   *
   * @param name Image name.
   * @param im   Image object.
   */
  void putImage(String name, ImageObject im);

  /**
   * Checks if image file exists.
   *
   * @param name of the image.
   * @return true if the file exists.
   */
  boolean doesExist(String name);

  /**
   * Gets the image related to a particular channel.
   *
   * @param name    Name of the image.
   * @param newName New name of the image
   * @param c       c defines the channel.
   */
  void createChannel(String name, String newName, char c);


  /**
   * Creates image with maximum value of the three components for each pixel.
   *
   * @param name    of the image.
   * @param newName name of the image created with maximum value of the three components
   *                for each pixel.
   * @param p       placement of splitting line.
   * @param type    type defines whether its value, luma or intensity.
   */
  void createGreyScale(String name, String newName, int p, char type);

  /**
   * Flip an image horizontally to create a new image.
   *
   * @param name    name of the image to be flipped.
   * @param newName name of the flipped image.
   */
  void flipHorizontal(String name, String newName);

  /**
   * Flip an image vertically to create a new image.
   *
   * @param name    name of the image to be flipped.
   * @param newName name of the flipped image.
   */
  void flipVertical(String name, String newName);

  /**
   * Brighten the image by the given increment to create a new image.
   *
   * @param bFactor increment factor for brighten.
   * @param name    name of the image to be brightened.
   * @param newName name of the brightened image.
   */
  void brighten(float bFactor, String name, String newName);

  /**
   * Darken the image by the given increment to create a new image.
   *
   * @param dFactor decrement factor for darken.
   * @param name    name of the image to be darkened.
   * @param newName name of the darkened image.
   */
  void darken(float dFactor, String name, String newName);

  /**
   * Combine the three greyscale images into a single image that gets its red, green and blue.
   * components from the three images respectively.
   *
   * @param red     Red component of the image.
   * @param green   Green component of the image.
   * @param blue    Blue component of the image.
   * @param newName Combined image.
   */
  void rgbCombine(String red, String green, String blue, String newName);

  /**
   * Splits the given image into three images containing its red, green and blue components.
   *
   * @param red   component of the image after split.
   * @param green component of the image after split.
   * @param blue  component of the image after split.
   * @param name  image to be split.
   */
  void rgbSplit(String red, String green, String blue, String name);

  /**
   * Blur the given image and store the result in another image.
   *
   * @param name    image to be blurred.
   * @param newName blurred image.
   * @param p       placement of splitting line.
   */
  void blur(String name, String newName, int p);

  /**
   * Sharpen the given image and store the result in another image.
   *
   * @param name    image to be sharpened.
   * @param newName sharpened image.
   * @param p       placement of splitting line.
   */
  void sharpen(String name, String newName, int p);

  /**
   * Produce a sepia-toned version of the given image and store the result in another image.
   *
   * @param name    image to be sepia toned.
   * @param newName sepia toned image.
   * @param p       placement of splitting line.
   */
  void sepia(String name, String newName, int p);

  /**
   * Compress the image by the given percentage.
   *
   * @param name    name of the image.
   * @param newName image after compression.
   * @param p       percentage of compression.
   */
  void compress(String name, String newName, int p);

  /**
   * Produces an image that represents histogram of an image.
   *
   * @param name    Name of the image.
   * @param newName Name of the histogram image.
   * @throws IOException throws exception.
   */
  void histogram(String name, String newName) throws IOException;

  /**
   * Color-correct an image by aligning the meaningful peaks of its histogram.
   *
   * @param name    name of the image.
   * @param newName name of the image after color correct.
   * @param p       placement of splitting line.
   */
  void colorCorrect(String name, String newName, int p);

  /**
   * Adjust levels of an image.
   *
   * @param name    name of the image.
   * @param newName name of the level adjusted image.
   * @param b       black value.
   * @param m       mid value.
   * @param w       white value.
   * @param p       placement of splitting line.
   */
  void levelsAdjust(String name, String newName, int b, int m, int w, int p);

}
