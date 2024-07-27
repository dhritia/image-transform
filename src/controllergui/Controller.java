package controllergui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.ImageObject;
import model.ImageObjectRGB;
import model.Model;
import view.IView;

/**
 * This class represents the controller of the application.
 * It takes input from the GUI and calls appropriate model methods
 * to perform operations on images.
 */
public class Controller implements Features {

  Map<String, Runnable> commands = new HashMap<>();
  Map<String, Runnable> modelCall = new HashMap<>();
  private Model m;
  private IView view;
  private int percent;
  private int b;
  private int mid;
  private int w;
  private String imgType;

  /**
   * Constructs a new controller object.
   */
  public Controller(Model m) {

    commands.put("Blur", () -> addSplitAndExecute());
    commands.put("Sharpen", () -> addSplitAndExecute());
    commands.put("Convert to Sepia", () -> addSplitAndExecute());
    commands.put("Convert to GreyScale", () -> addSplitAndExecute());
    commands.put("Color Correct", () -> addSplitAndExecute());
    commands.put("Compress", () -> addCompressAndExecute());
    commands.put("Visualize R Component", () -> addExecute());
    commands.put("Visualize G Component", () -> addExecute());
    commands.put("Visualize B Component", () -> addExecute());
    commands.put("Flip Vertically", () -> addExecute());
    commands.put("Flip Horizontally", () -> addExecute());
    commands.put("Adjust Levels", () -> addForLevelsAdjust());


    modelCall.put("Blur", () -> m.blur("image", imgType, percent));
    modelCall.put("Sharpen", () -> m.sharpen("image", imgType, percent));
    modelCall.put("Convert to Sepia", () -> m.sepia("image", imgType, percent));
    modelCall.put("Convert to GreyScale", () -> m.createGreyScale("image", imgType, percent, 'l'));
    modelCall.put("Color Correct", () -> m.colorCorrect("image", imgType, percent));
    modelCall.put("Compress", () -> m.compress("image", "image", percent));
    modelCall.put("Visualize R Component", () -> m.createChannel("image", "image", 'r'));
    modelCall.put("Visualize G Component", () -> m.createChannel("image", "image", 'g'));
    modelCall.put("Visualize B Component", () -> m.createChannel("image", "image", 'b'));
    modelCall.put("Flip Vertically", () -> m.flipVertical("image", "image"));
    modelCall.put("Flip Horizontally", () -> m.flipHorizontal("image", "image"));
    modelCall.put("Adjust Levels", () -> m.levelsAdjust("image", imgType, b, mid, w, percent));
    this.m = m;
  }

  @Override
  public void setView(IView view) {
    this.view = view;
    view.addFeatures(this);
  }

  private void readPPM(File f) throws FileNotFoundException {
    Scanner sc;
    sc = new Scanner(f);
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: Plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt();

    float[][] r = new float[height][width];
    float[][] g = new float[height][width];
    float[][] b = new float[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        r[i][j] = sc.nextInt();
        g[i][j] = sc.nextInt();
        b[i][j] = sc.nextInt();
      }
    }
    ImageObject im;
    im = new ImageObjectRGB(r, g, b, width, height);
    m.putImage("image", im);
  }

  private void bufferedImgToImgObject(File f, String extension) throws IOException {
    if (extension.equals("ppm")) {
      readPPM(f);
      return;
    }
    BufferedImage image;
    ImageObject im;
    image = ImageIO.read(f);
    float[][] r = new float[image.getHeight()][image.getWidth()];
    float[][] g = new float[image.getHeight()][image.getWidth()];
    float[][] b = new float[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        int p = image.getRGB(i, j);
        r[j][i] = (p >> 16) & 0xFF;
        g[j][i] = (p >> 8) & 0xFF;
        b[j][i] = p & 0xFF;
      }
    }
    im = new ImageObjectRGB(r, g, b, image.getWidth(), image.getHeight());
    m.putImage("image", im);
  }

  private BufferedImage toBufferedImg(ImageObject im) {
    BufferedImage image;
    int rgbColor;
    float[][] r = im.getR();
    float[][] g = im.getG();
    float[][] b = im.getB();
    image = new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < im.getWidth(); i++) {
      for (int j = 0; j < im.getHeight(); j++) {
        rgbColor = ((int) r[j][i] << 16) | ((int) g[j][i] << 8) | ((int) b[j][i]);
        image.setRGB(i, j, rgbColor);
      }
    }
    return image;
  }

  @Override
  public void loadImg() throws IOException {
    String path;
    path = view.openFileExplorer("load");
    if (path == null) {
      return;
    }
    File f = new File(path);
    this.imgType = "image";
    bufferedImgToImgObject(f, f.getName().substring(f.getName().lastIndexOf('.') + 1));
    view.enableSaveButton();
    this.displayImage();
  }

  /**
   * Saves PPM file in the path specified.
   * @param path path where the file gets saved.
   * @param r Red channel.
   * @param g Green Channel.
   * @param b Blue Channel.
   * @throws IOException if save of PPM file fails.
   */
  private void savePPM(String path, float[][] r, float[][] g, float[][] b) throws IOException {
    FileWriter writer = new FileWriter(path);
    writer.write("P3\n");
    writer.write(r[0].length + " " + r.length + "\n");
    writer.write("255\n");
    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r[0].length; j++) {
        writer.write((int) r[i][j] + "\n" + (int) g[i][j] + "\n" + (int) b[i][j] + "\n");
      }
    }
    writer.flush();
    writer.close();
  }

  @Override
  public void saveImg() throws IOException {
    String path;
    path = view.openFileExplorer("save");
    if (path == null) {
      return;
    }
    int rgbColor;
    BufferedImage image;
    ImageObject im = m.getImage("image");
    float[][] r = im.getR();
    float[][] g = im.getG();
    float[][] b = im.getB();
    String ext = path.substring(path.lastIndexOf(".") + 1);
    if (ext.equals("ppm")) {
      savePPM(path, r, g, b);
    }
    if (!ext.equals("jpg") && !ext.equals("jpeg") && !ext.equals("png") && !ext.equals("ppm")) {
      view.showErrorMessage("Error Saving Image", "Unsupported Format: Image "
              + "can only be saved in JPG, PNG and PPM formats");
      return;
    }
    image = new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < im.getWidth(); i++) {
      for (int j = 0; j < im.getHeight(); j++) {
        rgbColor = ((int) r[j][i] << 16) | ((int) g[j][i] << 8) | ((int) b[j][i]);
        image.setRGB(i, j, rgbColor);
      }
    }
    if (Files.exists(Paths.get(path.substring(0, path.lastIndexOf('/'))))) {
      File outputFile = new File(path);
      ImageIO.write(image, path.substring(path.lastIndexOf(".") + 1), outputFile);
    }
    view.setSaveLabel("Image Saved Successfully!");
  }


  @Override
  public void displayImage() throws IOException {
    BufferedImage im = null;
    BufferedImage hist = null;
    if (m.getImage(this.imgType) != null) {
      im = toBufferedImg(m.getImage(this.imgType));
      m.histogram(this.imgType, "histogram");
      hist = toBufferedImg(m.getImage("histogram"));
    }
    view.setImages(im, hist);
  }

  @Override
  public void setMenu(String option) throws IOException {
    this.imgType = "image";
    this.displayImage();
    commands.get(option).run();
  }

  @Override
  public void execute(String option) throws IOException {
    this.percent = 100;
    this.imgType = "image";
    modelCall.get(option).run();
    this.displayImage();
    view.setSaveLabel("Current image not saved");
  }

  @Override
  public void execute(String b, String m, String w) throws IOException {
    try {
      this.percent = 100;
      this.b = Integer.parseInt(b);
      this.mid = Integer.parseInt(m);
      this.w = Integer.parseInt(w);
      if (this.b > this.mid || this.b > this.w || this.mid > this.w) {
        view.showErrorMessage("Invalid Values", "Values "
                + "for black, mid and white have to be in ascending order");
        return;
      }
      if (this.b < 0 || this.b > 255 || this.mid < 0 || this.mid > 255
              || this.w < 0 || this.w > 255) {
        view.showErrorMessage("Invalid Values", "Values "
                + "for black, mid and white have to in the range 0-255");
        return;
      }
      this.imgType = "image";
      modelCall.get("Adjust Levels").run();
      this.displayImage();
      view.setSaveLabel("Current image not saved");
    }
    catch (NumberFormatException e) {
      view.showErrorMessage("Values not entered",
              "Enter values for black, mid and white before pre-viewing");
    }
  }

  @Override
  public void execute(String option, String p) throws IOException {
    try {
      this.percent = Integer.parseInt(p);
      if (this.percent < 0 || this.percent > 100) {
        view.showErrorMessage("Invalid Values",
                "Compression ratio value must be between 0-100");
        return;
      }
      this.imgType = "image";
      modelCall.get(option).run();
      this.displayImage();
      view.setSaveLabel("Current image not saved");
    }
    catch (NumberFormatException e) {
      view.showErrorMessage("Invalid Value",
              "Compression ratio value must be between 0-100");
    }
  }

  @Override
  public void executeSplit(String option, String p) throws IOException {
    this.percent = Integer.parseInt(p);
    this.imgType = "imagesplit";
    modelCall.get(option).run();
    this.displayImage();
  }

  @Override
  public void executeSplit(String b, String m, String w, String p) throws IOException {
    this.percent = Integer.parseInt(p);
    try {
      this.b = Integer.parseInt(b);
      this.mid = Integer.parseInt(m);
      this.w = Integer.parseInt(w);
      if (this.b > this.mid || this.b > this.w || this.mid > this.w) {
        view.showErrorMessage("Invalid Values", "Values "
                + "for black, mid and white have to be in ascending order");
        return;
      }
      this.imgType = "imagesplit";
      modelCall.get("Adjust Levels").run();
      this.displayImage();
    }
    catch (NumberFormatException e) {
      view.showErrorMessage("Values not entered",
              "Enter values for black, mid and white before pre-viewing");
    }
  }

  /**
   * Adds an execute button to the GUI.
   */
  private void addExecute() {
    view.clearPanel();
    view.addExecuteButton();
  }

  /**
   * Adds split field to the GUI.
   */
  private void addSplitAndExecute() {
    view.clearPanel();
    view.addSplitField();
  }

  /**
   * Adds compression ratio field to the GUI.
   */
  private void addCompressAndExecute() {
    view.clearPanel();
    view.addCompressField();
  }

  /**
   * Adds Level adjust fields such as b,m,w to the GUI.
   */
  private void addForLevelsAdjust() {
    view.clearPanel();
    view.addLevelAdjustFields();
  }


}
