package view;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import controllergui.Features;

/**
 * Class that takes care of displaying the image manipulations to the user via a GUI.
 */
public class ImageView extends JFrame implements ActionListener, ItemListener,
        ListSelectionListener, IView {

  private JPanel combomenuPanel;
  private JLabel saveLabel;
  private JComboBox<String> combobox;
  private JLabel imageLabel;
  private JLabel histLabel;

  private JButton loadButton;
  private JButton saveButton;
  private JButton menuButton;
  private JButton compressButton;
  private JButton levelAdjustButton;
  private JFormattedTextField field;
  private JFormattedTextField bField;
  private JFormattedTextField mField;
  private JFormattedTextField wField;
  private JSlider percent;
  private JSlider percentLevels;

  /**
   * Constructor for the view class that initialises the necessary panels, comboboxes, fields,
   * button and layouts.
   */
  public ImageView() {
    super();
    setTitle("Image Manipulations");
    setSize(1500, 650);

    JPanel mainPanel;
    JScrollPane mainScrollPane;
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainScrollPane = new JScrollPane(mainPanel);
    this.add(mainScrollPane);

    JPanel imagePanel;
    imagePanel = new JPanel();
    imagePanel.setPreferredSize(new Dimension(900, 600));

    JPanel histPanel;
    histPanel = new JPanel();
    histPanel.setPreferredSize(new Dimension(300, 600));
    mainPanel.add(imagePanel, BorderLayout.LINE_START);
    mainPanel.add(histPanel, BorderLayout.CENTER);

    imageLabel = new JLabel();
    imageLabel.setBorder(BorderFactory.createTitledBorder("Image"));
    imageLabel.setHorizontalAlignment(JLabel.CENTER);

    histLabel = new JLabel();
    histLabel.setBorder(BorderFactory.createTitledBorder("Histogram of Image"));
    histLabel.setHorizontalAlignment(JLabel.CENTER);

    JScrollPane histScrollPane;
    JScrollPane imageScrollPane;
    imageScrollPane = new JScrollPane(imageLabel);
    histScrollPane = new JScrollPane(histLabel);

    imageScrollPane.setPreferredSize(new Dimension(900, 600));
    histScrollPane.setPreferredSize(new Dimension(300, 600));
    imagePanel.add(imageScrollPane, BorderLayout.CENTER);
    histPanel.add(histScrollPane, BorderLayout.CENTER);

    JPanel menuPanel;
    menuPanel = new JPanel();
    mainPanel.add(menuPanel, BorderLayout.LINE_END);
    menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));

    JPanel loadPanel;
    loadPanel = new JPanel();
    loadPanel.setPreferredSize(new Dimension(250, 100));
    loadButton = new JButton("Load Image");
    loadPanel.add(loadButton);
    menuPanel.add(loadPanel);

    JPanel comboboxPanel;
    comboboxPanel = new JPanel();
    comboboxPanel.setPreferredSize(new Dimension(200, 300));
    comboboxPanel.setLayout(new FlowLayout());
    menuPanel.add(comboboxPanel);

    JLabel comboboxDisplay;
    comboboxDisplay = new JLabel("Choose your image manipulation");
    comboboxPanel.add(comboboxDisplay);
    String[] options = { "Visualize R Component", "Visualize G Component", "Visualize B Component",
        "Flip Vertically", "Flip Horizontally", "Blur", "Sharpen", "Convert to GreyScale",
        "Convert to Sepia", "Compress", "Color Correct", "Adjust Levels" };
    combobox = new JComboBox<String>(options);
    combobox.setEnabled(false);
    comboboxPanel.add(combobox);

    menuButton = new JButton("Execute");
    compressButton = new JButton("Execute");
    levelAdjustButton = new JButton("Execute");

    combomenuPanel = new JPanel();
    combomenuPanel.setLayout(new BoxLayout(combomenuPanel, BoxLayout.PAGE_AXIS));
    combomenuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    comboboxPanel.add(combomenuPanel);

    JPanel savePanel;
    savePanel = new JPanel();
    savePanel.setPreferredSize(new Dimension(250, 100));
    menuPanel.add(savePanel);
    saveButton = new JButton("Save Image");
    saveButton.setEnabled(false);

    saveLabel = new JLabel();
    savePanel.add(saveButton);
    savePanel.add(saveLabel);

    percent = new JSlider(0, 100);
    percent.setMajorTickSpacing(10);
    percent.setPaintTicks(true);
    percent.setPaintLabels(true);
    percentLevels = new JSlider(0, 100);
    percentLevels.setMajorTickSpacing(10);
    percentLevels.setPaintTicks(true);
    percentLevels.setPaintLabels(true);
    percentLevels.setValue(0);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // No action.
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    // No action.
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // No action.
  }

  @Override
  public void addFeatures(Features f) {
    loadButton.addActionListener(evt -> {
      try {
        f.loadImg();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    combobox.addActionListener(evt -> {
      try {
        f.setMenu((String) combobox.getSelectedItem());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    menuButton.addActionListener(evt -> {
      try {
        f.execute((String) combobox.getSelectedItem());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    compressButton.addActionListener(evt -> {
      try {
        f.execute("Compress", field.getText());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    levelAdjustButton.addActionListener(evt -> {
      try {
        f.execute(bField.getText(), mField.getText(), wField.getText());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    saveButton.addActionListener(evt -> {
      try {
        f.saveImg();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    percent.addChangeListener(evt -> {
      try {
        f.executeSplit((String) combobox.getSelectedItem(), String.valueOf(percent.getValue()));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    percentLevels.addChangeListener(evt -> {
      try {
        f.executeSplit(bField.getText(),
                mField.getText(), wField.getText(), String.valueOf(percentLevels.getValue()));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Override
  public void showErrorMessage(String title, String message) {
    JOptionPane.showMessageDialog(ImageView.this, message, title,
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void enableSaveButton() {
    saveButton.setEnabled(true);
    saveLabel.setText("Image Loaded Successfully!");
    combobox.setEnabled(true);
  }

  @Override
  public void setSaveLabel(String message) {
    saveLabel.setText(message);
  }

  @Override
  public String openFileExplorer(String action) {
    File f;
    f = null;
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG & PPM Images", "jpg", "png", "ppm", "jpeg");
    fchooser.setFileFilter(filter);
    if (action.equals("load")) {
      int retvalue = fchooser.showOpenDialog(ImageView.this);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        f = fchooser.getSelectedFile();
      }
    } else {
      int retvalue = fchooser.showSaveDialog(ImageView.this);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        f = fchooser.getSelectedFile();
      }
    }
    if (f != null) {
      return f.getAbsolutePath();
    }
    return null;
  }

  @Override
  public void setImages(BufferedImage image, BufferedImage histogram) {
    imageLabel.removeAll();
    imageLabel.revalidate();
    imageLabel.repaint();
    histLabel.removeAll();
    histLabel.revalidate();
    histLabel.repaint();
    imageLabel.setIcon(new ImageIcon(image));
    histLabel.setIcon(new ImageIcon(histogram));
  }

  @Override
  public void clearPanel() {
    combomenuPanel.removeAll();
    combomenuPanel.revalidate();
    combomenuPanel.repaint();
  }

  @Override
  public void addExecuteButton() {
    combomenuPanel.add(menuButton);
  }


  /**
   * Foramts the number to be within a range.
   * @return formatted number.
   */
  private NumberFormatter returnFormatter() {
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format) {
      @Override
      public Object stringToValue(String text) throws ParseException {
        if (text.length() == 0) {
          return null;
        }
        return super.stringToValue(text);
      }
    };
    formatter.setValueClass(Integer.class);
    formatter.setAllowsInvalid(false);
    return formatter;
  }

  @Override
  public void addSplitField() {
    combomenuPanel.add(new JLabel("Operation Preview"));
    combomenuPanel.add(new JLabel("Select Split Percentage:"));
    percent.setValue(0);
    combomenuPanel.add(percent);
    combomenuPanel.add(menuButton);
  }

  @Override
  public void addCompressField() {
    combomenuPanel.add(new JLabel("Enter Compression Ratio:"));
    field = new JFormattedTextField(returnFormatter());
    combomenuPanel.add(field);
    combomenuPanel.add(compressButton);
  }

  @Override
  public void addLevelAdjustFields() {
    combomenuPanel.add(new JLabel("Enter Black Value:"));
    bField = new JFormattedTextField(returnFormatter());
    combomenuPanel.add(bField);
    combomenuPanel.add(new JLabel("Enter Mid Value:"));
    mField = new JFormattedTextField(returnFormatter());
    combomenuPanel.add(mField);
    combomenuPanel.add(new JLabel("Enter White Value:"));
    wField = new JFormattedTextField(returnFormatter());
    combomenuPanel.add(wField);
    combomenuPanel.add(new JLabel("Operation Preview"));
    combomenuPanel.add(new JLabel("Select Split Percentage:"));
    combomenuPanel.add(percentLevels);
    combomenuPanel.add(levelAdjustButton);
  }
}
