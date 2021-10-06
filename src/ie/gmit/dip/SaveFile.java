package ie.gmit.dip;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SaveFile {
	private String outputFileName;
	private File outputFile;
	private BufferedImage output;

	// Takes filename input from user and attempts to save
	public void saveFile(BufferedImage output) {
		System.out.println(ConsoleColour.BLACK_BOLD);
		System.out.println("###################################");
		System.out.println("#            Save File            #");
		System.out.println("###################################");
		System.out.println("Enter the name for your new file:");
		System.out.println("(1) Back to Main Menu ");
		System.out.println(ConsoleColour.RESET);
	
		this.output = output;
		
		outputFileName = Runner.in.next().trim();
		// If "1" input, null returned which will return to main menu.
		if (outputFileName.equals("1")) {
			ConsoleColour.infoFormatter("Returning to main menu...");	
		}else {
		
		this.outputFileName = fileFormatter(outputFileName);
		this.outputFile = new File(this.outputFileName);

		// Checks if file exists first.
		// If it doesn't then attempts to save.
		// If file is null, it returns to main menu
		if (fileNameAlreadyExists() == false && output != null) {
			fileWriter();
		}
		}
	}

	// Formats file so no duplication of filetype suffix (e.g. "out.png.png") if
	// entered by user.
	private String fileFormatter(String str) {
		if (str.endsWith(".png")) {
			str.substring(str.length() - 5, str.length() - 1);
		}
		str = str + ".png";
		return str;
	}

	// Final step in writing a file
	public void fileWriter() {

		BufferedImage output = this.output;
		String outputFileName = this.outputFileName;
		try {
			ImageIO.write(output, "png", new File(outputFileName));
			ConsoleColour.infoFormatter("[INFO] Your file is now saved as " + outputFileName);
		} catch (IllegalArgumentException e) {
			if (output == null) {
				ConsoleColour.errorFormatter("[ERROR] No image to be saved.");
			} 
		}catch (IOException e) {
			ConsoleColour.errorFormatter("[ERROR] Unable to process request.");
		}
		ConsoleColour.infoFormatter("Returning to main menu...");
	}

	// Checks if a file exists already.
	public boolean fileNameAlreadyExists() {
		if (((File) outputFile).exists()) {
			replaceMenu();

			// Add a quite save / back - option here
			int choice = numberValidator();
			switch (choice) {
			case 1 -> {
				ConsoleColour.infoFormatter("Saving file...");
				fileWriter();
			}
			case 2 -> {
				saveFile(output);
			}
			case 3 -> {
			}
			default -> ConsoleColour.invalidInput();
			}
		}
		return false;
	}
	

	// Called when filename entered already exists in folder
	public void replaceMenu() {
		System.out.println(ConsoleColour.BLACK_BOLD);
		System.out.println("###################################");
		System.out.println("#            Save File            #");
		System.out.println("###################################");
		System.out.println(ConsoleColour.RESET);
		
		ConsoleColour.errorFormatter(					
				"The file " + this.outputFileName.toUpperCase() + " already exists. Would you like to replace it?");
		System.out.println(ConsoleColour.BLACK_BOLD);
		System.out.println("(1) Replace existing file.");
		System.out.println("(2) Save as new filename...");
		System.out.println("(3) Back to main menu");
		System.out.println(ConsoleColour.RESET);
	}
	
	private int numberValidator() {
		int value = 0;
		boolean notNumber = true;
		while (notNumber) {
			try {
				value = Integer.parseInt(Runner.in.next().trim()); 
				notNumber = false;
			} catch (NumberFormatException e) {
				ConsoleColour.invalidInput();
			}
		}
		return value;
	}
}
