package ie.gmit.dip;

//Sourced code from: https://chortle.ccsu.edu/java5/notes/chap23/ch23_14.html

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LocalUpload {

	private String inputFileName;
	private File input;
	

	public LocalUpload() {
		
		while (input == null || input.exists() == false) {
			localUploadOptions();
			inputFileName = Runner.in.next().trim(); 
			//Option to navigate back to main menu
			if(inputFileName.equals("1")){
				ConsoleColour.infoFormatter("Returning to main menu");
				break;
			}
			
			input = new File(inputFileName);
			// the input.length() !=0 ensure that the created file is not empty
			if (input.exists() && input.length() != 0) {
				this.input = input;
				ConsoleColour.infoFormatter("[INFO] Your file is: " + inputFileName);
			} else {
				// if file is empty, we nullify the instance variables 
				this.input = null;
				this.inputFileName =null;
				ConsoleColour.invalidInput();
				ConsoleColour.errorFormatter("[PROMPT] Ensure to enter filepath and filename in the format 'file.png'.");
			}
		}
	}

	//Upload menu display
	private void localUploadOptions() {
		System.out.println(ConsoleColour.BLUE_BOLD);
		System.out.println("###################################");
		System.out.println("#        Local File Upload        #");
		System.out.println("###################################");
		System.out.println("Enter File Name: ");
		System.out.println("OR ");
		System.out.println("(1) Back to main menu");
		System.out.println(ConsoleColour.RESET);
	}

	//To retrieve value of instance variable
	public File getFile() {
		return this.input;
	}


}
