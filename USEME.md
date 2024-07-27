# Script Image Manipulation Commands Supported

## load:

**Command format:** <br />
load image-path image-name
<br />**Summary:**  <br />
Loads an image from the specified path and refer it to henceforth in the
program by the given image name. <br />
**Example:** <br />
load res/flowers.jpg flower

## save:

**Command format:** <br />
save image-path image-name
<br />**Summary:**  <br />
Saves the image with the given name to the specified path which
includes the name of the file.<br />
**Example:** <br />
save res/compression30.jpeg f-comp

## split:

<br />**Summary:**  <br />
Provides ability to specify a vertical line to generate a split view of operations. The operations
that are supported are blur, sharpen, sepia, greyscale, color correction and levels adjustment.
<br />
**Example:** <br />
Examples have been shown in blur, sharpen, sepia, greyscale, color correction and levels adjustment
below.

## red-component:

**Command format:** <br />
red-component image-name dest-image-name
<br />**Summary:**  <br />
Create an image with the red-component of the
image with the given name, and refer to it henceforth in the program by the given destination name.
<br />
**Example:** <br />
red-component f f-red

## green-component:

**Command format:** <br />
green-component image-name dest-image-name
<br />**Summary:**  <br />
Create an image with the green-component of the
image with the given name, and refer to it henceforth in the program by the given destination name.
<br />
**Example:** <br />
green-component f f-green

## blue-component:

**Command format:** <br />
blue-component image-name dest-image-name
<br />**Summary:**  <br />
Create an image with the blue-component of the
image with the given name, and refer to it henceforth in the program by the given destination name.
<br />
**Example:** <br />
blue-component f f-blue

## horizontal-flip:

**Command format:** <br />
horizontal-flip image-name dest-image-name
<br />**Summary:**  <br />
Flips an image horizontally to create a new image,
referred to henceforth by the given destination name. <br />
**Example:** <br />
horizontal-flip f f-hflip

## vertical-flip:

**Command format:** <br />
vertical-flip image-name dest-image-name
<br />**Summary:**  <br />
Flips an image vertically to create a new image,
referred to henceforth by the given destination name. <br />
**Example:** <br />
vertical-flip f f-vflip

## brighten:

**Command format:** <br />
brighten increment image-name dest-image-name
<br />**Summary:**  <br />
Brightens the image by the given increment to
create a new image, referred to henceforth by the given destination name. The increment is positive.
<br />
**Example:** <br />
brighten 50 f f-bright

## darken:

**Command format:** <br />
darken increment image-name dest-image-name
<br />**Summary:**  <br />
Darkens the image by the given increment to
create a new image, referred to henceforth by the given destination name. The increment is negative.
<br />
**Example:** <br />
darken 50 f f-dark

## value-component:

**Command format:** <br />
value-component image-name dest-image-name
<br />**Summary:**  <br />
The maximum value of the three components for each pixel
<br />
**Example:** <br />
value-component f f-value <br />
value-component f f-value-split split 25

## intensity-component:

**Command format:** <br />
intensity-component image-name dest-image-name
<br />**Summary:**  <br />
The average of the three components for each pixel.
<br />
**Example:** <br />
intensity-component f f-intensity <br />
intensity-component f f-intensity-split split 90

## luma-component:

**Command format:** <br />
luma-component image-name dest-image-name
<br />**Summary:**  <br />
The weighted sum .
<br />
**Example:** <br />
luma-component f f-luma <br />
luma-component f f-luma-split split 45

## rgb-split:

**Command format:** <br />
rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue
<br />**Summary:**  <br />
Splits the given image into three images containing its red, green and blue components respectively.
These would be the same images that would be individually produced with the red-component,
green-component and blue-component commands. <br />
**Example:** <br />
rgb-split f f-red f-green f-blue

## rgb-combine:

**Command format:** <br />
rgb-combine image-name red-image green-image blue-image
<br />**Summary:**  <br />
Combines the three images that are individually red, green and blue into a single image that gets
its red, green and blue components from the three images respectively. <br />
**Example:** <br />
rgb-combine f-new f-red f-green f-blue

## blur:

**Command format:** <br />
blur image-name dest-image-name
<br />**Summary:**  <br />
Blurs the given image and stores the result in another image
with the given name. <br />
**Example:** <br />
blur f f-blur <br />
blur f f-blur-split split 30

## sharpen:

**Command format:** <br />
sharpen image-name dest-image-name
<br />**Summary:**  <br />
Sharpens the given image and stores the result in another
image with the given name. <br />
**Example:** <br />
sharpen f f-sharp <br />
sharpen f f-sharpen-split split 60

## sepia:

**Command format:** <br />
sepia image-name dest-image-name
<br />**Summary:**  <br />
Produces a sepia-toned version of the given image and stors
the result in another image with the given name. <br />
**Example:** <br />
sepia f f-sepia <br />
sepia f f-sepia-split split 35

## run:

**Command format:** <br />
run script-file
<br />**Summary:**  <br />
Load and run the script commands in the specified file. <br />
**Example:** <br />
run script.txt

## compress:

**Command format:** <br />
compress percentage image-name dest-image-name
<br />**Summary:**  <br />
Creates a compression version of an image.
Percentages between 0 and 100 are considered valid. <br />
**Example:** <br />
compress 30 f f-comp

## histogram:

**Command format:** <br />
histogram image-name dest-image-name
<br />**Summary:**  <br />
Produces an image that represents the histogram of a
given image. <br />
**Example:** <br />
histogram f historg

## color-correct:

**Command format:** <br />
color-correct image-name dest-image-name
<br />**Summary:**  <br />
Color-corrects an image by aligning the meaningful peaks
of its histogram. <br />
**Example:** <br />
color-correct f f-color-cor <br />
color-correct f f-color-cor-split split 50

## levels-adjust:

**Command format:** <br />
levels-adjust b m w image-name dest-image-name
<br />**Summary:**  <br />
Adjusts levels of an image where b, m and w
are the three relevant black, mid and white values respectively. These values should be ascending
in that order, and should be within 0 and 255 for this command to work correctly. <br />
**Example:** <br />
levels-adjust 30 80 160 f f-lvl-adj  <br />
levels-adjust 30 80 160 f f-lvl-adj-split split 60

## Note:

1. 'load' should be the first command performed before doing any image manipulation. <br />
2. 'save' will fail if path file doesn't exist. <br />
3. For all the commands, the source image should exist.

# GUI image manipulations supported

## Load Image:

Clicking on the 'Load Image' button opens the File Explorer. The user can select the image to be
loaded and click on 'Open'. This will display the image selected and the histogram of the
image on the GUI. Once the image is loaded, the GUI will show the message 
'Image Loaded Successfully!'<br />

## Save Image:

Clicking on the 'Save Image' button opens the File Explorer. The user can select where the image
has to be saved and click on 'Save'. This will save the image in that path. The user can save 
the images only in JPG, PNG, and PPM formats. Once the image is saved,
the GUI will show the message 'Image Saved Successfully!' <br />

## Image manipulations dropdown:

Note: Running any of the below operations will show the message 'Current Image not Saved' to let the
user
know that the image has not been saved and they can do so by using the 'Save Image' button on the GUI.

### Visualize R Component:

Click on the option 'Visualize R Component' and click 'Execute' button.
This will display Red channel of the image. <br />

### Visualize G Component:

Click on the option 'Visualize G Component' and click 'Execute' button.
This will display Green channel of the image. <br />

### Visualize B Component:

Click on the option 'Visualize G Component' and click 'Execute' button.
This will display Blue channel of the image. <br />

### Flip Vertically:

Click on the option 'Flip Vertically' in the drop-down and click 'Execute'
button. This will display a vertically flipped image on GUI. <br />

### Flip Horizontally:

Click on the option 'Flip Horizontally' in the drop-down and click 'Execute'
button. This will display a horizontally flipped image on GUI. <br />

### Blur:

Click on the option 'Blur' in the drop-down and click 'Execute'. This will blur the image
and display the same on the GUI. The user can also preview the operation by dragging a slider. 
This will split the image to partially blurred and partially original image, to compare 
the effect of the operation.<br />

### Sharpen:

Click on the option 'Sharpen' in the drop-down and click 'Execute'. This will sharpen
the image and display the same on the GUI. The user can also preview the operation by dragging a slider.
This will split the image to partially sharpened and partially original image, to compare
the effect of the operation.<br />

### Convert to GreyScale:

Click on the option 'Convert to GreyScale' in the drop-down and click
'Execute'. This will greyscale the image and display the same on the GUI. 
The user can also preview the operation by dragging a slider.
This will split the image to partially greyscale and partially original image, to compare
the effect of the operation.<br />

### Convert to Sepia:

Click on the option 'Convert to Sepia' in the drop-down and click
'Execute'. This will sepia the image and display the same on the GUI.  
The user can also preview the operation by dragging a slider.
This will split the image to partially sepia and partially original image, to compare
the effect of the operation.<br />

### Compress:

Click on the option 'Compress' in the drop-down and under 'Enter Compression ratio',
enter the compression ratio value and click 'Execute'. This will compress the image and display the
same on the GUI. <br />
Note: If the compression ratio is not in the range of 0-100, the GUI will throw an
error message. <br />

### Color Correct:

Click on the option 'Color Correct' in the drop-down and click
'Execute'. This will colour correct the image and display the same on the GUI.  
The user can also preview the operation by dragging a slider.
This will split the image to partially color-corrected and partially original image, to compare
the effect of the operation.<br />

### Adjust Levels:

Click on the option 'Adjust Levels' in the drop-down, enter Black, Mid and White
values and click 'Execute'. This will level adjust the image and display the same on the GUI.  
The user can also preview the operation by dragging a slider.
This will split the image to partially level-adjusted and partially original image, to compare
the effect of the operation.<br />

Note: If the Black, Mid and White values are not entered in ascending order, the GUI will throw an
error message. <br />
Note: If the Black, Mid and White values are not in the range of 0-255, the GUI will throw an
error message. <br />

