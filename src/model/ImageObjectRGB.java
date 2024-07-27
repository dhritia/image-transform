package model;

/**
 * This class represents an image in RGB format.
 * An RGB image consists of 3 channels:
 * Red , Green, and Blue.
 */
public class ImageObjectRGB implements ImageObject {

  private float[][] r;
  private float[][] g;
  private float[][] b;
  private int width;
  private int height;

  /**
   * Constructor to create an RGB image.
   *
   * @param r      red component of the image.
   * @param g      green component of the image.
   * @param b      blue component of the image.
   * @param width  width of the array.
   * @param height height of the array.
   */
  public ImageObjectRGB(float[][] r, float[][] g, float[][] b, int width, int height) {
    this.r = r;
    this.g = g;
    this.b = b;
    this.width = width;
    this.height = height;
  }

  @Override
  public float[][] getR() {
    float[][] red = new float[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        red[i][j] = this.r[i][j];
      }
    }
    return red;
  }

  @Override
  public float[][] getG() {
    float[][] green = new float[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        green[i][j] = this.g[i][j];
      }
    }
    return green;
  }

  @Override
  public float[][] getB() {
    float[][] blue = new float[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        blue[i][j] = this.b[i][j];
      }
    }
    return blue;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void addPadding() {
    if (this.width == this.height && Math.ceil(Math.log(this.width) / Math.log(2))
            == Math.floor(Math.log(this.width) / Math.log(2))) {
      return;
    }
    float[][] imgR;
    float[][] imgG;
    float[][] imgB;
    int w;
    if (this.width > this.height) {
      w = (int) Math.pow(2, Math.ceil(Math.log(this.width) / Math.log(2)));
    } else {
      w = (int) Math.pow(2, Math.ceil(Math.log(this.height) / Math.log(2)));
    }
    imgR = new float[w][w];
    imgG = new float[w][w];
    imgB = new float[w][w];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        imgR[i][j] = this.r[i][j];
        imgG[i][j] = this.g[i][j];
        imgB[i][j] = this.b[i][j];
      }
    }
    this.r = imgR;
    this.g = imgG;
    this.b = imgB;
    this.width = w;
    this.height = w;
  }

  @Override
  public void removePadding(int width, int height) {
    float[][] imgR = new float[height][width];
    float[][] imgG = new float[height][width];
    float[][] imgB = new float[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        imgR[i][j] = Math.min(255f, Math.max(this.r[i][j], 0f));
        imgG[i][j] = Math.min(255f, Math.max(this.g[i][j], 0f));
        imgB[i][j] = Math.min(255f, Math.max(this.b[i][j], 0f));
      }
    }
    this.width = width;
    this.height = height;
    this.r = imgR;
    this.g = imgG;
    this.b = imgB;
  }

}
