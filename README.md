# Image Manipulation and Enhancement

## Package

1. model - This package holds all the classes and interfaces needed to perform image manipulation
   and enhancements. <br />
2. controller - This package holds all the classes and interfaces needed to perform controller
   operations of communicating with the model to implement image manipulations via command line and
   using script file. <br />
3. controllergui - This package holds all the classes and interfaces needed to perform controller
   operations of communicating with the model, to implement image manipulations via the GUI. <br />
4. view - This package holds all the class and interface need to display the results on the GUI.

## Program Runner

### Classes:

1. ProgramRunner - This class is where the execution of the program starts. It creates a Controller
   object and calls the appropriate Controller method to process the command or file passed in by
   the user. It also provides a GUI to perform image manipulations. <br />

## Controller

### Interfaces:

1. Controller - This interface represents a controller. Controller manages the input, output and
   which functionality to implement from the model. <br />
2. ImageCommands - ImageCommands is a controller interface to execute the commands of the
   model. <br />
3. Features - Features interface has methods that control the various high-level features and abilities of the
   view. <br />

### Classes:

1. ControllerImpl - This class takes input from the user and decides which method of the ImageModel
   to be called. <br />

Below classes present in 'commands' folder. Implemented following command design pattern: <br />

2. Blur - Blur class calls model method to blur image. <br />
3. Brighten - Brighten class calls model method to brighten image. <br />
4. ColorCorrect - ColorCorrect class calls model method to color correct image. <br />
5. Compress - Compress class calls model method to compress image. <br />
6. Darken - Darken class calls model method to darkens image. <br />
7. GreyScale - Greyscale class calls model method to greyscale image - value, intensity, luma.<br />
8. Histogram - Histogram class calls model method to create image histogram. <br />
9. HorizontalFlip - HorizontalFlip class calls model method to horizontally flip image. <br />
10. LevelAdjust - LevelAdjust class calls model method to level adjust the image. <br />
11. Load - Load class loads the image. <br />
12. RGBCombine - RGBCombine class calls model method to combine the three greyscale images into a
    single image that gets its red, green and blue components from the three images
    respectively.<br />
13. RGBComponent - RGBComponent class calls model method to create an image with the red,
    green or blue of the image. <br />
14. RGBSplit - RGBSplit class calls model method to split image into R, G and B channels. <br />
15. Save - Save class saves the image. <br />
16. Sepia - Sepia class calls model method to create sepia of the image. <br />
17. Sharpen - Sharpen class calls model method to sharpen the image. <br />
18. VerticalFlip - VerticalFlip class calls model method to flip the image vertically. <br />

The controllergui package contains the below Controller class associated with the GUI
operations: <br />

19. Controller - This class takes input from the GUI and decides which method of the ImageModel to
    be called. <br />

## Model

### Interfaces:

1. Model - The Image interface represent a model. It manages the implementation for the image
   manipulations and enhancements.
   An image can be either greyscale or RGB format.  <br />
2. ImageObject - This interface represents a single image. <br />

### Classes

1. ImageModel - This class implements all the functionality needed for image manipulation and
   enhancement. This class implements the Image interface and has implementation associated to all
   the methods defined in the interface. <br />
2. ImageObjectRGB - This class implements the ImageObject interface, and represents an image in RGB
   format. An RGB image consists of 3 channels: Red , Green, and Blue.

## View

### Interfaces:

1. IView - The interface for the view class. This interface represents the GUI using which user 
can perform image manipulations.

### Classes

1. ImageView - Class that takes care of taking inputs and displaying the results to the user via a GUI.

## Test:

### Classes

1. MockImageModel - This class is a mock implementation of Image interface, created specifically to
   test CLI and GUI controller in isolation. <br />
2. ControllerTest - ControllerTest class contains controller tests. <br />
3. ModelTest - ModelTest class contains model tests. <br />
4. MockImageView - This class is a mock implementation of the View interface, created specifically to
      test the GUI controller in isolation. <br />
5. GUIControllerTest - GUIControllerTest class contains tests for the GUI controller. <br />

## Image Citation:

https://pixabay.com/photos/flowers-pink-flowers-small-flowers-6515538/ <br />


## Submission Contents:

1. src folder <br />
2. test folder <br />
3. res folder - source image (flowers.jpg), sample image of GUI (GUIScreenshot.png),
JAR file (Assignment5.jar) <br />
6. README.md <br />
7. USEME.md <br />

## Instructions to run a script using this application: <br />

Note: Under the res folder, we have placed a script.txt file which contains a list of commands.
Running the script should run multiple image manipulations on the flower.jpeg file, also placed
under the res folder. <br />

1. Run the ProgramRunner class. <br />
2. In the command-line, run the command "run script.txt" <br />
3. If you wish to try commands manually, you can do so by typing the command on the command line.
   <br />
4. Enter the command "quit" to terminate the program.

## Instructions to run JAR file: <br />

1. Place the script.txt file in the same folder as JAR file.
2. In this folder create a subfolder called 'res'. Copy the flowers.jpg file into the 'res' folder.
3. Open your command line terminal. Go to the folder where the JAR file is. Run the below
   command: <br />
   To run script file: <br />
   **java -jar Assignment5.jar -f path-of-script-file** <br /><br />

   To type the commands and execute it one line at a time. <br />
   **java -jar Assignment5.jar -text** <br /><br />

   To open the program as a graphical user interface. <br />
   **java -jar Assignment5.jar** <br />
   The resulting images will be stored where the user decides to save it via the GUI 'save' option.

## Justifications for design change:

We are following the model-view-controller design, with the following
enhancements: <br />

1. Added one more controller to take care of the delegation of tasks for GUI. <br />
2. Added a view package to display the program as a GUI. <br />