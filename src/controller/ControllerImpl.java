package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.ColorCorrect;
import controller.commands.Compress;
import controller.commands.Darken;
import controller.commands.GreyScale;
import controller.commands.Histogram;
import controller.commands.HorizontalFlip;
import controller.commands.LevelAdjust;
import controller.commands.Load;
import controller.commands.RGBCombine;
import controller.commands.RGBComponent;
import controller.commands.RGBSplit;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.VerticalFlip;
import model.Model;


/**
 * This class represents the controller of the application.
 * It takes input from the user and calls appropriate model methods
 * to perform operations on images.
 */
public class ControllerImpl implements Controller {

  final Readable in;
  final Appendable out;
  Map<String, Function<Scanner, ImageCommands>> commands = new HashMap<>();
  private Model model;

  /**
   * Constructs a new controller object.
   */
  public ControllerImpl(Readable in, Appendable out, Model model) {
    this.model = model;
    this.in = in;
    this.out = out;
    commands.put("load", s -> new Load(s.nextLine()));
    commands.put("save", s -> new Save(s.nextLine()));
    commands.put("red-component", s -> new RGBComponent(s.nextLine(), 'r'));
    commands.put("green-component", s -> new RGBComponent(s.nextLine(), 'g'));
    commands.put("blue-component", s -> new RGBComponent(s.nextLine(), 'b'));
    commands.put("luma-component", s -> new GreyScale(s.nextLine(), 'l'));
    commands.put("value-component", s -> new GreyScale(s.nextLine(), 'v'));
    commands.put("intensity-component", s -> new GreyScale(s.nextLine(), 'i'));
    commands.put("horizontal-flip", s -> new HorizontalFlip(s.nextLine()));
    commands.put("vertical-flip", s -> new VerticalFlip(s.nextLine()));
    commands.put("brighten", s -> new Brighten(s.nextLine()));
    commands.put("darken", s -> new Darken(s.nextLine()));
    commands.put("rgb-split", s -> new RGBSplit(s.nextLine()));
    commands.put("rgb-combine", s -> new RGBCombine(s.nextLine()));
    commands.put("blur", s -> new Blur(s.nextLine()));
    commands.put("sharpen", s -> new Sharpen(s.nextLine()));
    commands.put("sepia", s -> new Sepia(s.nextLine()));
    commands.put("compress", s -> new Compress(s.nextLine()));
    commands.put("histogram", s -> new Histogram(s.nextLine()));
    commands.put("color-correct", s -> new ColorCorrect(s.nextLine()));
    commands.put("levels-adjust", s -> new LevelAdjust(s.nextLine()));
  }

  /**
   * Runs the image manipulation commands.
   *
   * @param str str contains the r=command to be performed.
   * @param sc  scanner
   * @throws IOException if user input is incorrect.
   */
  private void runCommands(String str, Scanner sc) throws IOException {
    ImageCommands i;
    try {
      Function<Scanner, ImageCommands> cmd = commands.getOrDefault(str, null);
      if (cmd == null) {
        sc.nextLine();
        throw new ArrayIndexOutOfBoundsException();
      }
      i = cmd.apply(sc);
      i.goCommands(model);
    } catch (FileNotFoundException e) {
      this.out.append("File/Folder not found. Try again\n");
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      this.out.append("Command syntax is wrong. Try again\n");
    } catch (IllegalArgumentException e) {
      this.out.append("Image provided in the command not found. Try again\n");
    }
  }

  @Override
  public void getCommands() throws IOException {
    Scanner sc = new Scanner(this.in);
    String str;
    while (true) {
      str = sc.next();
      if (str.equals("quit")) {
        break;
      }
      if (str.equals("run")) {
        getCommands(sc.nextLine());
        continue;
      }
      runCommands(str, sc);
    }
  }

  @Override
  public void getCommands(String filename) throws IOException {
    filename = filename.trim();
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      this.out.append("File " + filename + " not found. Try again\n");
      return;
    }
    String s;
    while (sc.hasNextLine()) {
      s = sc.next();
      if (s.equals("quit")) {
        break;
      }
      runCommands(s, sc);
    }
  }
}
