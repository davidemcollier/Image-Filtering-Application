PROJECT TITLE: 
Filtering an Image with a Convolution Kernel


INTRODUCTION:
This is a menu-driven Java application which takes in an image and applies a filter. The user can apply in-built filters and create their own as well as applying multiple atop one another. They can also upload photos locally from their computer or download from the internet. Once a filter is applied, the filtered image can then be saved locally.

The user can navigate through menus of different options and back using numeric input.


HOW TO RUN:
The application, once compiled, can be run from the Runner class (where the main method lies) using a command prompt using the following syntax: java ie.gmit.dip.Runner


DESCRIPTION OF KEY FUNCTIONALITIES:

	(1) Select/ Change image:
		The user can choose a locally hosted image (entering the relative file path) or download an image from a URL. Once an image is selected the filename is displayed in the main menu.

	(2) Select/ Change filter:
		The user may select any of a list of filters to apply to an image. These are hardcoded into the enum in the Kernel class. 
Once a filter is selected its name is displayed in the main menu.

		Custom Filter:
		Alternatively, a user may define their own convolution kernel both in dimensions and inputs. Once defined, a mock up of the filter 2D array is displayed showing what the next input field for the user to fill is as well as all other previous input values. 


	(3) Apply chosen filter to image:
		If a filter and file have been selected the user can apply a filter to the image. If not, a message displays indicating which is missing. 
The user should be aware that the current selected filter and image are displayed in the main menu, for ease.

This action calls the ImageManager class and processes the image by applying a filter. There are two principal 'imageProcessor' methods with differing parameters which are called, one for a newly uploaded image, and another for applying as many filters as the user wishes thereafter.


	(4) Save filtered image to file:
		If an image has been filtered, then this can be saved to a local folder. The user is prompted to enter a file name (if containing or missing a suffix (.png) this is checked for and appended if required). The user needs to specify relative/ absolute path.

If the file already exists a replace menu is generated prompting the user to replace the existing file with the shared filename or to reenter another filename. 

	Display:
		Printed messages and menus have been colourised to draw the users attention and highlight any information or issues. 


CREDITS:

1) Code stubs - assignment brief - John Healy
2)https://lodev.org/cgtutor/filtering.html
3)http://tech.abdulfatir.com/2014/05/kernel-image-processing.html?m=1