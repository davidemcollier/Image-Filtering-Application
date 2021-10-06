package ie.gmit.dip;

import javax.imageio.IIOException;
//17/8/21 
//trying to put a while loop in here and fix the xception handling
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

// EXAMPLE URL for testing: https://upload.wikimedia.org/wikipedia/commons/2/2f/Defence-of-Arrah-House.png

public class URLimageDownload {
	private String path;
	private URL url;
	private String inputURLName = null;
	private BufferedImage image = null;
	private String imageName;

	public String upload() {

		image = urlMenu();
		// If image == null this brings us back to the main menu
		if (image == null) {
			return null;
		}
		// Gets image name from last snippet of URL
		String imageName = "URL_Download_" + getImageName(inputURLName);
		System.out.println("");
		ConsoleColour.infoFormatter("Image found. Your image is: " + imageName);

		// Saves a copy of the image to a download folder
		String path = imageName + ".png";
		this.path = path;

		try {

			ImageIO.write(image, "png", new File(path));
		} catch (IOException e) {
			ConsoleColour.errorFormatter("[ERROR] Unable to download image to file.");
		}
		return path;
	}

	//Loop to call menu options and take input which either directs back to main menu or calls url input validator method.
	private BufferedImage urlMenu() {
		boolean invalidURL = true;

		while (invalidURL) {
			urlMenuOptions();
			String inputURLName = Runner.in.next().trim();
			// If "1" input, null returned which will return to main menu.
			if (inputURLName.equals("1")) {
				ConsoleColour.infoFormatter("Returning to main menu");
				invalidURL = false;
				image = null;
				continue;
			}
			image = urlImageValidator(inputURLName);
			this.inputURLName = inputURLName;
			if (image != null) {
				invalidURL = false;
			}
		}
		return image;
	}

	// Validates the URL catching any errors with the url itself if it is not a png
	// image file
	private BufferedImage urlImageValidator(String inputURLName) {
		try {
			url = new URL(inputURLName);
			
			ConsoleColour.infoFormatter("Searching internet for file...");
			image = ImageIO.read(url);
		} catch (IllegalArgumentException e) {
			ConsoleColour.errorFormatter("[ERROR] URL incorrect. Ensure URL is for a .png image file.");
		} catch (MalformedURLException e) {
			ConsoleColour.errorFormatter("[ERROR] URL incorrect. Ensure full URL is input.");
		} catch (IOException e) {
			ConsoleColour.errorFormatter("[ERROR] Unable to process URL.");
		}
		return image;
	}

	
	/*
	 * This method attempts to trim the URL into a more user-friendly name by
	 * trimming the URL in spite of whatever unknown and differing file naming
	 * conventions used by whomever upload the image. Reasoning is to remove the
	 * suffix ( .png ) and the first part of the URL all the way up to the
	 * last backslash, where presumably the name should be as uploaded
	 */
	private String getImageName(String urlName) {
		int lastBackslash = urlName.lastIndexOf("/") + 1;
		int suffix = urlName.lastIndexOf(".");
		String imageName = urlName.substring(lastBackslash, suffix);
		// build a string to find the last index as the initial character.
		return imageName;
	}

	
	private void urlMenuOptions() {
		System.out.println(ConsoleColour.BLUE_BRIGHT);
		System.out.println("###################################");
		System.out.println("#           URL Download          #");
		System.out.println("###################################");
		System.out.println("Enter the full URL of the image you wish to download: ");
		System.out.println("(1) Back to Main Menu ");
		System.out.println(ConsoleColour.RESET);
	}
}