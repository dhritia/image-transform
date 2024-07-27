package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents the model which implements functionality related to image.
 */
public class ImageModel implements Model {

  private Map<String, ImageObject> imgMap;


  /**
   * Constructor of ImageModel. Creates a hash map to store the images.
   */
  public ImageModel() {
    imgMap = new HashMap<String, ImageObject>();
  }

  @Override
  public ImageObject getImage(String name) {
    return imgMap.get(name);
  }

  @Override
  public void putImage(String name, ImageObject im) {
    imgMap.put(name, im);
  }

  @Override
  public boolean doesExist(String name) {
    return imgMap.get(name) != null;
  }


  @Override
  public void createChannel(String name, String newName, char c) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] f;
    if (c == 'r') {
      f = imgMap.get(name).getR();
      imgMap.put(newName, new ImageObjectRGB(f, new float[height][width], new float[height][width],
              width, height));
    } else if (c == 'g') {
      f = imgMap.get(name).getG();
      imgMap.put(newName, new ImageObjectRGB(new float[height][width], f, new float[height][width],
              width, height));
    } else {
      f = imgMap.get(name).getB();
      imgMap.put(newName, new ImageObjectRGB(new float[height][width], new float[height][width], f,
              width, height));
    }
  }

  @Override
  public void createGreyScale(String name, String newName, int p, char type) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] imgR = new float[height][width];
    float[][] imgG = new float[height][width];
    float[][] imgB = new float[height][width];
    float[][] r = imgMap.get(name).getR();
    float[][] g = imgMap.get(name).getG();
    float[][] b = imgMap.get(name).getB();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        if (type == 'v') {
          imgR[i][j] = Math.max(Math.max(r[i][j], g[i][j]), (b[i][j]));
        } else if (type == 'l') {
          imgR[i][j] = (float) (0.2126 * r[i][j] + 0.7152 * g[i][j] + 0.0722 * b[i][j]);
        } else {
          imgR[i][j] = (r[i][j] + g[i][j] + b[i][j]) / 3;
        }
        imgG[i][j] = imgR[i][j];
        imgB[i][j] = imgR[i][j];
      }
      if (p < 100) {
        imgR[i][(int) (width * p * 0.01)] = 0f;
        imgG[i][(int) (width * p * 0.01)] = 0f;
        imgB[i][(int) (width * p * 0.01)] = 0f;
      }
      for (int j = (int) (width * p * 0.01) + 1; j < width; j++) {
        imgR[i][j] = r[i][j];
        imgG[i][j] = g[i][j];
        imgB[i][j] = b[i][j];
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }

  @Override
  public void flipHorizontal(String name, String newName) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] imgR = new float[height][width];
    float[][] imgG = new float[height][width];
    float[][] imgB = new float[height][width];
    float[][] r = imgMap.get(name).getR();
    float[][] g = imgMap.get(name).getG();
    float[][] b = imgMap.get(name).getB();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        imgR[i][j] = r[i][width - j - 1];
        imgG[i][j] = g[i][width - j - 1];
        imgB[i][j] = b[i][width - j - 1];
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }

  @Override
  public void flipVertical(String name, String newName) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] imgR = new float[height][width];
    float[][] imgG = new float[height][width];
    float[][] imgB = new float[height][width];
    float[][] r = imgMap.get(name).getR();
    float[][] g = imgMap.get(name).getG();
    float[][] b = imgMap.get(name).getB();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        imgR[i][j] = r[height - i - 1][j];
        imgG[i][j] = g[height - i - 1][j];
        imgB[i][j] = b[height - i - 1][j];
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }

  @Override
  public void brighten(float bFactor, String name, String newName) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] imgR = new float[height][width];
    float[][] imgG = new float[height][width];
    float[][] imgB = new float[height][width];
    float[][] r = imgMap.get(name).getR();
    float[][] g = imgMap.get(name).getG();
    float[][] b = imgMap.get(name).getB();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        imgR[i][j] = Math.max(0f, Math.min(bFactor + r[i][j], 255f));
        imgG[i][j] = Math.max(0f, Math.min(bFactor + g[i][j], 255f));
        imgB[i][j] = Math.max(0f, Math.min(bFactor + b[i][j], 255f));
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }

  @Override
  public void darken(float dFactor, String name, String newName) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] imgR = new float[height][width];
    float[][] imgG = new float[height][width];
    float[][] imgB = new float[height][width];
    float[][] r = imgMap.get(name).getR();
    float[][] g = imgMap.get(name).getG();
    float[][] b = imgMap.get(name).getB();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        imgR[i][j] = Math.max(0, -dFactor + r[i][j]);
        imgG[i][j] = Math.max(0, -dFactor + g[i][j]);
        imgB[i][j] = Math.max(0, -dFactor + b[i][j]);
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }

  @Override
  public void rgbCombine(String red, String green, String blue, String newName) {
    float[][] r = imgMap.get(red).getR();
    float[][] g = imgMap.get(green).getG();
    float[][] b = imgMap.get(blue).getB();
    imgMap.put(newName, new ImageObjectRGB(r, g, b, imgMap.get(red).getWidth(),
            imgMap.get(red).getHeight()));
  }

  @Override
  public void rgbSplit(String red, String green, String blue, String name) {
    this.createChannel(name, red, 'r');
    this.createChannel(name, green, 'g');
    this.createChannel(name, blue, 'b');
  }

  /**
   * Applying the gaussian filter to a pixel.
   *
   * @param i     i position of array.
   * @param j     j position of array.
   * @param index of array.
   * @param r     red component array.
   * @param g     green component array.
   * @param b     blue component array.
   * @param width width of array.
   * @return the gaussian filter applied R, G and B.
   */
  private float[] gaussian(int i, int j, int index, float[][] r, float[][] g, float[][] b,
                           int width) {
    float[][] gauss = {
            {0.0625f, 0.125f, 0.0625f},
            {0.125f, 0.25f, 0.125f},
            {0.0625f, 0.125f, 0.0625f}
    };
    float[] sum = new float[3];
    if (j > 0) {
      sum[0] += r[i][j - 1] * gauss[i - index + 1][0];
      sum[1] += g[i][j - 1] * gauss[i - index + 1][0];
      sum[2] += b[i][j - 1] * gauss[i - index + 1][0];
    }
    sum[0] += r[i][j] * gauss[i - index + 1][1];
    sum[1] += g[i][j] * gauss[i - index + 1][1];
    sum[2] += b[i][j] * gauss[i - index + 1][1];
    if (j < width - 1) {
      sum[0] += r[i][j + 1] * gauss[i - index + 1][2];
      sum[1] += g[i][j + 1] * gauss[i - index + 1][2];
      sum[2] += b[i][j + 1] * gauss[i - index + 1][2];
    }
    return sum;
  }

  @Override
  public void blur(String name, String newName, int p) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float sumR;
    float sumG;
    float sumB;
    float[] sum;
    float[][] imgR = new float[height][width];
    float[][] imgG = new float[height][width];
    float[][] imgB = new float[height][width];
    float[][] r = imgMap.get(name).getR();
    float[][] g = imgMap.get(name).getG();
    float[][] b = imgMap.get(name).getB();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        sumR = 0.0f;
        sumG = 0.0f;
        sumB = 0.0f;
        if (i > 0) {
          sum = gaussian(i - 1, j, i, r, g, b, width);
          sumR += sum[0];
          sumG += sum[1];
          sumB += sum[2];
        }
        sum = gaussian(i, j, i, r, g, b, width);
        sumR += sum[0];
        sumG += sum[1];
        sumB += sum[2];
        if (i < height - 1) {
          sum = gaussian(i + 1, j, i, r, g, b, width);
          sumR += sum[0];
          sumG += sum[1];
          sumB += sum[2];
        }
        imgR[i][j] = Math.max(0f, Math.min(255f, sumR));
        imgG[i][j] = Math.max(0f, Math.min(255f, sumG));
        imgB[i][j] = Math.max(0f, Math.min(255f, sumB));
      }
      if (p < 100) {
        imgR[i][(int) (width * p * 0.01)] = 0f;
        imgG[i][(int) (width * p * 0.01)] = 0f;
        imgB[i][(int) (width * p * 0.01)] = 0f;
      }
      for (int j = (int) (width * p * 0.01) + 1; j < width; j++) {
        imgR[i][j] = r[i][j];
        imgG[i][j] = g[i][j];
        imgB[i][j] = b[i][j];
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }

  /**
   * Applying the sharpen filter to a pixel.
   *
   * @param i     i position of array.
   * @param j     j position of array.
   * @param index of array.
   * @param r     red component array.
   * @param g     green component array.
   * @param b     blue component array.
   * @param width width of array.
   * @return the sharpen filter applied R, G and B.
   */
  private float[] sFilter(int i, int j, int index, float[][] r, float[][] g, float[][] b,
                          int width) {
    float[][] kernel = {
            {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f},
            {-0.125f, 0.25f, 0.25f, 0.25f, -0.125f},
            {-0.125f, 0.25f, 1.0f, 0.25f, -0.125f},
            {-0.125f, 0.25f, 0.25f, 0.25f, -0.125f},
            {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f}
    };
    float[] sum = new float[3];
    if (j > 1) {
      sum[0] += r[i][j - 2] * kernel[i - index + 2][0];
      sum[1] += g[i][j - 2] * kernel[i - index + 2][0];
      sum[2] += b[i][j - 2] * kernel[i - index + 2][0];
    }
    if (j > 0) {
      sum[0] += r[i][j - 1] * kernel[i - index + 2][1];
      sum[1] += g[i][j - 1] * kernel[i - index + 2][1];
      sum[2] += b[i][j - 1] * kernel[i - index + 2][1];
    }
    sum[0] += r[i][j] * kernel[i - index + 2][2];
    sum[1] += g[i][j] * kernel[i - index + 2][2];
    sum[2] += b[i][j] * kernel[i - index + 2][2];
    if (j < width - 1) {
      sum[0] += r[i][j + 1] * kernel[i - index + 2][3];
      sum[1] += g[i][j + 1] * kernel[i - index + 2][3];
      sum[2] += b[i][j + 1] * kernel[i - index + 2][3];
    }
    if (j < width - 2) {
      sum[0] += r[i][j + 2] * kernel[i - index + 2][4];
      sum[1] += g[i][j + 2] * kernel[i - index + 2][4];
      sum[2] += b[i][j + 2] * kernel[i - index + 2][4];
    }
    return sum;
  }

  @Override
  public void sharpen(String name, String newName, int p) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float sumR;
    float sumG;
    float sumB;
    float[] sum;
    float[][] imgR = new float[height][width];
    float[][] imgG = new float[height][width];
    float[][] imgB = new float[height][width];
    float[][] r = imgMap.get(name).getR();
    float[][] g = imgMap.get(name).getG();
    float[][] b = imgMap.get(name).getB();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        sumR = 0.0f;
        sumG = 0.0f;
        sumB = 0.0f;
        if (i > 1) {
          sum = sFilter(i - 2, j, i, r, g, b, width);
          sumR += sum[0];
          sumG += sum[1];
          sumB += sum[2];
        }
        if (i > 0) {
          sum = sFilter(i - 1, j, i, r, g, b, width);
          sumR += sum[0];
          sumG += sum[1];
          sumB += sum[2];
        }
        sum = sFilter(i, j, i, r, g, b, width);
        sumR += sum[0];
        sumG += sum[1];
        sumB += sum[2];
        if (i < height - 1) {
          sum = sFilter(i + 1, j, i, r, g, b, width);
          sumR += sum[0];
          sumG += sum[1];
          sumB += sum[2];
        }
        if (i < height - 2) {
          sum = sFilter(i + 2, j, i, r, g, b, width);
          sumR += sum[0];
          sumG += sum[1];
          sumB += sum[2];
        }
        imgR[i][j] = Math.max(0f, Math.min(255f, sumR));
        imgG[i][j] = Math.max(0f, Math.min(255f, sumG));
        imgB[i][j] = Math.max(0f, Math.min(255f, sumB));
      }
      if (p < 100) {
        imgR[i][(int) (width * p * 0.01)] = 0f;
        imgG[i][(int) (width * p * 0.01)] = 0f;
        imgB[i][(int) (width * p * 0.01)] = 0f;
      }
      for (int j = (int) (width * p * 0.01) + 1; j < width; j++) {
        imgR[i][j] = r[i][j];
        imgG[i][j] = g[i][j];
        imgB[i][j] = b[i][j];
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }

  @Override
  public void sepia(String name, String newName, int p) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] imgR = new float[height][width];
    float[][] imgG = new float[height][width];
    float[][] imgB = new float[height][width];
    float[][] r = imgMap.get(name).getR();
    float[][] g = imgMap.get(name).getG();
    float[][] b = imgMap.get(name).getB();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        imgR[i][j] = Math.min(0.393f * r[i][j] + 0.769f * g[i][j]
                + 0.189f * b[i][j], 255f);
        imgG[i][j] = Math.min(0.349f * r[i][j] + 0.686f * g[i][j]
                + 0.168f * b[i][j], 255f);
        imgB[i][j] = Math.min(0.272f * r[i][j] + 0.534f * g[i][j]
                + 0.131f * b[i][j], 255f);
      }
      if (p < 100) {
        imgR[i][(int) (width * p * 0.01)] = 0f;
        imgG[i][(int) (width * p * 0.01)] = 0f;
        imgB[i][(int) (width * p * 0.01)] = 0f;
      }
      for (int j = (int) (width * p * 0.01) + 1; j < width; j++) {
        imgR[i][j] = r[i][j];
        imgG[i][j] = g[i][j];
        imgB[i][j] = b[i][j];
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }


  /**
   * Perform Haar Wavelet Transform on the image.
   *
   * @param f is a channel of the image.
   * @return the channel after the transform.
   */
  float[][] transform(float[][] f) {
    int n;
    int ind;
    int k;
    float[] inv = new float[f.length];
    n = (int) (Math.log(f.length) / Math.log(2));
    ind = 1;
    while (n > 0) {
      k = (int) Math.pow(2, n - 1);
      for (int i = 0; i < f.length; i++) {
        for (int j = 0; j < (f.length / (int) Math.pow(2, ind)); j++) {
          inv[j] = (f[i][j * 2] + f[i][j * 2 + 1]) / (float) Math.sqrt(2);
          inv[j + k] = (f[i][j * 2] - f[i][j * 2 + 1]) / (float) Math.sqrt(2);
        }
        for (int j = 0; j < k * 2; j++) {
          f[i][j] = inv[j];
        }
      }
      for (int i = 0; i < f.length; i++) {
        for (int j = 0; j < (f.length / (int) Math.pow(2, ind)); j++) {
          inv[j] = (f[j * 2][i] + f[j * 2 + 1][i]) / (float) Math.sqrt(2);
          inv[j + k] = (f[j * 2][i] - f[j * 2 + 1][i]) / (float) Math.sqrt(2);
        }
        for (int j = 0; j < k * 2; j++) {
          f[j][i] = inv[j];
        }
      }
      n--;
      ind++;
    }
    return f;
  }

  /**
   * Add R,G,B values to a sorted set.
   *
   * @param r R channel
   * @param g G channel
   * @param b B channel
   * @return the sorted set.
   */
  private Set getSet(float[][] r, float[][] g, float[][] b) {
    Set<Float> s = new TreeSet<>();
    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r.length; j++) {
        s.add(Math.abs(r[i][j]));
        s.add(Math.abs(g[i][j]));
        s.add(Math.abs(b[i][j]));
      }
    }
    return s;
  }

  /**
   * Calculate the threshold value.
   *
   * @param s sorted set of channel values
   * @param p compression ratio
   * @return threshold values
   */
  private float thresholdVal(TreeSet s, int p) {
    float th;
    th = 0f;
    int pos;
    pos = (int) Math.round((s.size() * p) / 100.0);
    for (Object x : s) {
      th = (Float) x;
      pos--;
      if (pos == 0) {
        break;
      }
    }
    return th;
  }

  /**
   * Set the threshold values.
   *
   * @param f  channel of the image
   * @param th threshold
   * @return channel after setting threshold.
   */
  private float[][] threshold(float[][] f, float th) {
    for (int i = 0; i < f.length; i++) {
      for (int j = 0; j < f.length; j++) {
        if (Math.abs(f[i][j]) <= th) {
          f[i][j] = 0f;
        }
      }
    }
    return f;
  }

  /**
   * Performs inverse haar transfer.
   *
   * @param f channel of the image.
   * @return channel array after inverse transform.
   */
  private float[][] invTransform(float[][] f) {
    int n;
    int k;
    int ind;
    float[] inv = new float[f.length];
    n = 1;
    while (n <= (int) (Math.log(f.length) / Math.log(2))) {
      k = (int) Math.pow(2, n - 1);
      for (int i = 0; i < f.length; i++) {
        ind = 0;
        for (int j = 0; j < k; j++) {
          inv[ind++] = (f[j][i] + f[j + k][i]) / (float) Math.sqrt(2);
          inv[ind++] = (f[j][i] - f[j + k][i]) / (float) Math.sqrt(2);
        }
        for (int j = 0; j < k * 2; j++) {
          f[j][i] = inv[j];
        }
      }
      for (int i = 0; i < f.length; i++) {
        ind = 0;
        for (int j = 0; j < k; j++) {
          inv[ind++] = (f[i][j] + f[i][j + k]) / (float) Math.sqrt(2);
          inv[ind++] = (f[i][j] - f[i][j + k]) / (float) Math.sqrt(2);
        }
        for (int j = 0; j < k * 2; j++) {
          f[i][j] = inv[j];
        }
      }
      n++;
    }
    return f;
  }

  @Override
  public void compress(String name, String newName, int p) {
    int width;
    int height;
    ImageObject im = imgMap.get(name);
    width = im.getWidth();
    height = im.getHeight();
    im.addPadding();
    float[][] r = im.getR();
    float[][] g = im.getG();
    float[][] b = im.getB();
    im.removePadding(width, height);
    r = transform(r);
    g = transform(g);
    b = transform(b);
    float th;
    if (p > 0) {
      Set<Float> s;
      s = getSet(r, g, b);
      th = thresholdVal((TreeSet) s, p);
      r = threshold(r, th);
      g = threshold(g, th);
      b = threshold(b, th);
    }
    r = invTransform(r);
    g = invTransform(g);
    b = invTransform(b);
    ImageObject img = new ImageObjectRGB(r, g, b, im.getWidth(), im.getHeight());
    img.removePadding(width, height);
    imgMap.put(newName, img);
  }

  /**
   * Connect the dots to draw histogram.
   *
   * @param max   maximum frequency of all the channels
   * @param color color of the channel
   * @param g     Graphics2D object used to create lines
   * @param arr   channel array
   */
  private void drawLines(int max, Color color, Graphics2D g, int[] arr) {
    int prev;
    g.setColor(color);
    prev = (int) ((double) arr[0] / max * 256);
    g.drawLine(0, 0, 1, prev);
    for (int i = 1; i < 256; i++) {
      int y = (int) ((double) arr[i] / max * 256);
      g.drawLine(i, prev, i + 1, y);
      prev = y;
    }
  }

  @Override
  public void histogram(String name, String newName) {
    int height;
    int width;
    int max = 0;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] imgR = imgMap.get(name).getR();
    float[][] imgG = imgMap.get(name).getG();
    float[][] imgB = imgMap.get(name).getB();
    int[] r = new int[256];
    int[] g = new int[256];
    int[] b = new int[256];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        r[(int) imgR[i][j]]++;
        g[(int) imgG[i][j]]++;
        b[(int) imgB[i][j]]++;
      }
    }
    BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
    Graphics2D gd = image.createGraphics();
    gd.setColor(Color.WHITE);
    gd.fillRect(0, 0, 256, 256);
    for (int i = 0; i < 256; i++) {
      if (r[i] > max) {
        max = r[i];
      }
      if (g[i] > max) {
        max = g[i];
      }
      if (b[i] > max) {
        max = b[i];
      }
    }
    gd.setColor(Color.LIGHT_GRAY);
    for (int i = 0; i < 16; i++) {
      gd.drawLine(0, i * 16, 255, i * 16);
      gd.drawLine(i * 16, 0, i * 16, 255);
    }
    gd.drawLine(0, 255, 255, 255);
    gd.drawLine(255, 0, 255, 255);
    drawLines(max, Color.RED, gd, r);
    drawLines(max, Color.GREEN, gd, g);
    drawLines(max, Color.BLUE, gd, b);
    float[][] histr = new float[image.getHeight()][image.getWidth()];
    float[][] histg = new float[image.getHeight()][image.getWidth()];
    float[][] histb = new float[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        int p = image.getRGB(i, j);
        histr[256 - j - 1][i] = (p >> 16) & 0xFF;
        histg[256 - j - 1][i] = (p >> 8) & 0xFF;
        histb[256 - j - 1][i] = p & 0xFF;
      }
    }
    ImageObject im = new ImageObjectRGB(histr, histg, histb, image.getWidth(), image.getHeight());
    imgMap.put(newName, im);
  }

  @Override
  public void colorCorrect(String name, String newName, int p) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] imgR = imgMap.get(name).getR();
    float[][] imgG = imgMap.get(name).getG();
    float[][] imgB = imgMap.get(name).getB();
    int[] r = new int[256];
    int[] g = new int[256];
    int[] b = new int[256];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        r[(int) imgR[i][j]]++;
        g[(int) imgG[i][j]]++;
        b[(int) imgB[i][j]]++;
      }
    }
    int indR = 10;
    int indG = 10;
    int indB = 10;
    for (int i = 11; i < 246; i++) {
      if (r[indR] < r[i]) {
        indR = i;
      }
      if (g[indG] < g[i]) {
        indG = i;
      }
      if (b[indB] < b[i]) {
        indB = i;
      }
    }
    int avg = (indR + indG + indB) / 3;
    int offsetR = avg - indR;
    int offsetG = avg - indG;
    int offsetB = avg - indB;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        imgR[i][j] = Math.round(Math.min(255f, Math.max(imgR[i][j] + offsetR, 0f)));
        imgG[i][j] = Math.round(Math.min(255f, Math.max(imgG[i][j] + offsetG, 0f)));
        imgB[i][j] = Math.round(Math.min(255f, Math.max(imgB[i][j] + offsetB, 0f)));
      }
      if (p < 100) {
        imgR[i][(int) (width * p * 0.01)] = 0f;
        imgG[i][(int) (width * p * 0.01)] = 0f;
        imgB[i][(int) (width * p * 0.01)] = 0f;
      }
      for (int j = (int) (width * p * 0.01) + 1; j < width; j++) {
        imgR[i][j] = imgR[i][j];
        imgG[i][j] = imgG[i][j];
        imgB[i][j] = imgB[i][j];
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }

  @Override
  public void levelsAdjust(String name, String newName, int b, int m, int w, int p) {
    int height;
    int width;
    height = imgMap.get(name).getHeight();
    width = imgMap.get(name).getWidth();
    float[][] imgR = imgMap.get(name).getR();
    float[][] imgG = imgMap.get(name).getG();
    float[][] imgB = imgMap.get(name).getB();
    int aA = -b * (128 - 255) + 128 * w - 255 * m;
    int bA = b * b * (128 - 255) + 255 * m * m - 128 * w * w;
    int cA = b * b * (255 * m - 128 * w) - b * (255 * m * m - 128 * w * w);
    int aAA = b * b * (m - w) - b * (m * m - w * w) + w * m * m - m * w * w;
    float a = (float) aA / aAA;
    float bl = (float) bA / aAA;
    float c = (float) cA / aAA;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        imgR[i][j] = Math.min(255f, Math.max(a * imgR[i][j] * imgR[i][j]
                + bl * imgR[i][j] + c, 0f));
        imgG[i][j] = Math.min(255f, Math.max(a * imgG[i][j] * imgG[i][j]
                + bl * imgG[i][j] + c, 0f));
        imgB[i][j] = Math.min(255f, Math.max(a * imgB[i][j] * imgB[i][j]
                + bl * imgB[i][j] + c, 0f));
      }
      if (p < 100) {
        imgR[i][(int) (width * p * 0.01)] = 0f;
        imgG[i][(int) (width * p * 0.01)] = 0f;
        imgB[i][(int) (width * p * 0.01)] = 0f;
      }
      for (int j = (int) (width * p * 0.01) + 1; j < width; j++) {
        imgR[i][j] = imgR[i][j];
        imgG[i][j] = imgG[i][j];
        imgB[i][j] = imgB[i][j];
      }
    }
    imgMap.put(newName, new ImageObjectRGB(imgR, imgG, imgB, width, height));
  }

}

