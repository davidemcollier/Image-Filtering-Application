package ie.gmit.dip;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import ie.gmit.dip.Kernel.Filter;

public class Menu {

	private boolean runMainMenu = true;
	private boolean runFilterMenu = false;
	private boolean runUploadMenu = false;
	private double[][] filterArray;
	private String filterName;
	private File fileName;
	private BufferedImage output;

	// MAIN menu
	public void start() {
		while (runMainMenu) {
			mainOptions();
			int choice = numberValidator();

			switch (choice) {
			case 1 -> {
				runMainMenu = false;
				runUploadMenu = true;
				uploadOptions();
			}
			case 2 -> {
				runMainMenu = false;
				runFilterMenu = true;
				filterOptions();
			}
			case 3 -> {
				// if either filter or image not yet selected user is prompted to do so
				if (fileName == null || filterArray == null) {
					System.out.println(ConsoleColour.RED_BOLD); 
					System.out.print("[ERROR] You forgot to select");
					if (fileName == null) {
						System.out.print(" an image ");
					}
					;
					if (fileName == null && filterArray == null) {
						System.out.print(" AND ");
					}
					if (filterArray == null) {
						System.out.println(" a filter");
					}System.out.println(ConsoleColour.RESET);
					break;
				}
				// otherwise filter applied to photo
				ConsoleColour.infoFormatter("Please wait...");
				ImageManager IM = new ImageManager();

				// Ensures to go for the newly uploaded image when output is null
				// NB output is nullified when a new successful upload occurs
				// Otherwise, the ELSE statement applies successive filters to the currently
				// selected image (stored in the output variable).
				if (output == null) {
					this.output = IM.imageProcessor(fileName, filterArray);
				} else {
					this.output = IM.imageProcessor(this.output, filterArray);
				}
				ConsoleColour.infoFormatter("[INFO] Success! Filter has applied been to " + fileName);
			
			}
			case 4 -> {
				if(output==null) {
					ConsoleColour.errorFormatter("[ERROR] No filtered image to be saved");			
				break;}
				SaveFile sf = new SaveFile();
				sf.saveFile(output);
			}
			case 5 -> {
				ConsoleColour.infoFormatter("[INFO] Shutting down... GOOD BYE!");
				runMainMenu = false;
			}
			default -> {ConsoleColour.invalidInput();}
			}
		}
	}

	private void mainOptions() {
		System.out.println(ConsoleColour.BLUE_BOLD);
		System.out.println("###################################");
		System.out.println("#           Main Menu	          #");
		System.out.println("###################################");
		System.out.println("");
		//Prints current image user is working on
		if(this.fileName !=null) {			
			System.out.println("Current image: " + this.fileName);
			}	
		//Prints current filter user is working on
		if(this.filterName !=null) {			
			System.out.println("Current filter: " + this.filterName);
			}
		System.out.println("");
		System.out.print(ConsoleColour.RESET);
		System.out.print(ConsoleColour.BLUE);
		System.out.println("(1) Select/ Change image");
		System.out.println("(2) Select/ Change filter");
		System.out.println("(3) Apply chosen filter to image");
		System.out.println("(4) Save filtered image to file");
		System.out.println("(5) Quit");
		System.out.println("\nSelect Option [1-5]>");
		System.out.println(ConsoleColour.RESET);
	}

	// MAIN end

	// FILTER menu

	private void getFilter() {

		// Ensures numeric input
		int choice = numberValidator();
		
		System.out.println(ConsoleColour.PURPLE_BOLD_BRIGHT);
		switch (choice) {
		case 1 -> {
			//Assigns filter and retrieves name
			this.filterArray = Kernel.IDENTITY;
			this.filterName = Filter.IDENTITY.name();
		}

		case 2 -> {
			this.filterArray = Kernel.EDGE_DETECTION_1;
			this.filterName = Filter.EDGE_DETECTION_1.name();
		}
		case 3 -> {
			this.filterArray = Kernel.EDGE_DETECTION_2;
			this.filterName = Filter.EDGE_DETECTION_2.name();
		}
		case 4 -> {
			this.filterArray = Kernel.LAPLACIAN;
			this.filterName = Filter.LAPLACIAN.name();
		}
		case 5 -> {
			this.filterArray = Kernel.SHARPEN;
			this.filterName = Filter.SHARPEN.name();
		}
		case 6 -> {
			this.filterArray = Kernel.VERTICAL_LINES;
			this.filterName = Filter.VERTICAL_LINES.name();
		}
		case 7 -> {
			this.filterArray = Kernel.HORIZONTAL_LINES;
			this.filterName = Filter.HORIZONTAL_LINES.name();
		}
		case 8 -> {
			this.filterArray = Kernel.DIAGONAL_45_LINES;
			this.filterName = Filter.DIAGONAL_45_LINES.name();
		}
		case 9 -> {
			this.filterArray = Kernel.BOX_BLUR;
			this.filterName = Filter.BOX_BLUR.name();
		}
		case 10 -> {
			this.filterArray = Kernel.SOBEL_HORIZONTAL;
			this.filterName = Filter.SOBEL_HORIZONTAL.name();
		}
		case 11 -> {
			this.filterArray = Kernel.SOBEL_VERTICAL;
			this.filterName = Filter.SOBEL_VERTICAL.name();
		}
		case 12 -> {
			System.out.println("[Custom Filters]");
			CustomFilter cf = new CustomFilter();
			this.filterArray = cf.defineFilter();
			this.filterName = "Custom";
		}
		
		case 13 -> {
			ConsoleColour.infoFormatter("Returning to main menu");
		}
		default -> {
			ConsoleColour.invalidInput();
		}
		}System.out.println(ConsoleColour.RESET);
		// Turn off filter menu loop if a valid option is selected.
		if (choice > 0 && choice < 13) {
			//Prints chosen filter
			ConsoleColour.infoFormatter(this.filterName + " filter selected. ");		
		}
		//If any valid option chosen, return to main menu
		if (choice > 0 && choice < 14) {
			runFilterMenu = false;
			runMainMenu = true;
		}
	}

	private void filterOptions() {
		while (runFilterMenu) {
			System.out.println(ConsoleColour.PURPLE_BOLD); 
			System.out.println("###################################");
			System.out.println("#          Filter Menu            #");
			System.out.println("###################################");
			System.out.println(ConsoleColour.RESET);
			System.out.println(ConsoleColour.PURPLE_BOLD);
			System.out.println("(1) Identity");
			System.out.println("(2) Edge Detection 1");
			System.out.println("(3) Edge Detection 2");
			System.out.println("(4) Laplacian");
			System.out.println("(5) Sharpen");
			System.out.println("(6) Vertical Lines");
			System.out.println("(7) Horizontal Lines");
			System.out.println("(8) Diagonal 45 Lines");
			System.out.println("(9) Box Blur");
			System.out.println("(10) Sobel Horizontal");
			System.out.println("(11) Sobel Vertical");
			System.out.println("(12) Custom Filter");
			System.out.println("(13) Back to Main Menu");
			
			System.out.println("\nSelect Option [1-13]>");
			System.out.println(ConsoleColour.RESET);
			getFilter();
		}
	}

	// FILTER end

	// UPLOAD menu

	private void uploadOptions() {
		while (runUploadMenu) {
			System.out.println(ConsoleColour.BLUE_BOLD);
			System.out.println("###################################");
			System.out.println("#           Upload Menu           #");
			System.out.println("###################################");
			System.out.println(ConsoleColour.RESET);
			System.out.println(ConsoleColour.BLUE);
			System.out.println("(1) Access file from folder");
			System.out.println("(2) Access image file from URL");
			System.out.println("(3) Back to Main Menu");
			System.out.println("\nSelect Option [1-3]>");
			System.out.println(ConsoleColour.RESET);
			getUpload();
		}
	}

	public void getUpload() {

		int choice = numberValidator();
		switch (choice) {
		case 1 -> {
			//Upload local image
			LocalUpload ul = new LocalUpload();
			this.fileName = ul.getFile();
			//Everytime a new file is uplaoded, any previous filterimage (ie output) is cleared 
			// this allows for the new image to be filtered and re-filtered
			if(this.fileName != null) {
				this.output = null;
			}
		}
		case 2 -> {
			//Download image from Internet with URL
			URLimageDownload u = new URLimageDownload();
			// If null returned, back to main menu
			String returnedfileName = u.upload();
			if (returnedfileName == null) {
				break;
			} else {
				this.fileName = new File(returnedfileName);
				//When new file uploaded, output is nullified (see note for SWITCH CASE 3 in (start()))
				this.output = null;
			}
		}
		case 3 -> {
			// Return to main menu
			ConsoleColour.infoFormatter("Returning to main menu");
			runMainMenu = true;
			runUploadMenu = false;
		}
		default ->{ 
			ConsoleColour.invalidInput();}
		}
		// Turn off upload menu loop if a valid option is selected.
		if (choice > 0 && choice < 4) {
			runUploadMenu = false;
			runMainMenu = true;
		}
	}

// UPLOAD menu end 

// NUMBER VALIDATOR  - auxiliary method
	// Keeps prompting user until a number is entered
	private int numberValidator() {
		int value = 0;
		boolean notNumber = true;
		while (notNumber) {
			try {
				value = Integer.parseInt(Runner.in.next().trim()); // Blocks...
				notNumber = false;
			} catch (NumberFormatException e) {
				ConsoleColour.invalidInput();
			} 
		}
		return value;
	}
// NUMBER VALIDATOR  - end	


	
	
	
	
}
