package ie.gmit.dip;

/*REFERENCES:
 * Code from the below sources helped in guiding the code below:
 * 1) Code stubs - assignment brief - John Healy
 * 2)https://lodev.org/cgtutor/filtering.html
 * 3)http://tech.abdulfatir.com/2014/05/kernel-image-processing.html?m=1
 * 
*/
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import static java.lang.System.out;

import javax.imageio.ImageIO;

public class ImageManager {

	private BufferedImage output = null;

	// This method called for any successive filters applied to an image after
	// initial application.
	public BufferedImage imageProcessor(BufferedImage input, double[][] filter) {
		int WIDTH = input.getWidth();
		int HEIGHT = input.getHeight();


		output = new BufferedImage(WIDTH, HEIGHT, input.getType());

		for (int x = 0; x < WIDTH; x++) {
			for (int y = 0; y < HEIGHT; y++) { // Loop over the 2D image pixel-by-pixel

				// Gets value of pixel at coordinate and adds to 2D array at corresponding point

				int rgb = pixelConverter(input, output, filter, WIDTH, HEIGHT, x, y);
				output.setRGB(x, y, new Color(rgb).getRGB());
			}
		}
		return output;
	}
	

	// This method applies filter to a newly uploaded file via the Menu class
	public BufferedImage imageProcessor(File fileName, double[][] filter) {

		BufferedImage input, output;
		try {
			input = ImageIO.read(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			ConsoleColour.errorFormatter("[ERROR] Unable to read file. Please upload a different file");
			// returns null to return to main menu to give user opportunity to reselect a
			// file.
			return null;
		}
		output = imageProcessor(input, filter);
		
		return output;
	}

	
	
	public static int pixelConverter(BufferedImage input, BufferedImage output, double[][] filter, int WIDTH, int HEIGHT,
			int x, int y) {
		
		//Total-value variables to hold the values for each colour channel
		double redTotal = 0;
		double greenTotal = 0;
		double blueTotal = 0;

		// Two for-loops goingthrough the chosen 2D filter array
		for (int filterCol = 0; filterCol < filter.length; filterCol++) {
			for (int filterRow = 0; filterRow < filter[0].length; filterRow++) {


				// Modulo wraps the value to ensure edges are accounted for in filtering
				int Xvalues = (x - filter.length / 2 + filterCol + WIDTH) % WIDTH;
				int Yvalues = (y - filter.length / 2 + filterRow + HEIGHT) % HEIGHT;

				// extraction of pixel value at x and y coordinates
				int pixel = input.getRGB(Xvalues, Yvalues);

				// extraction of values from pixel for three separate colour channels
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = pixel & 0xff;

				// Each channel point in kernel multiplied against image values and added to a
				// colour-unique total
				redTotal += filter[filterRow][filterCol] * red;
				greenTotal += filter[filterRow][filterCol] * green;
				blueTotal += filter[filterRow][filterCol] * blue;

			}
		}

		// If output value goes outside limits of max (255) and min (0) values of RGB
		// colour range,
		// we curb these to either 255 or 0 respectively.
		int outR = (int) Math.min(Math.max((redTotal), 0), 255);
		int outG = (int) Math.min(Math.max((greenTotal), 0), 255);
		int outB = (int) Math.min(Math.max((blueTotal), 0), 255);

//creates new rgb variable to store the converted colour-channel values
		int rgb = 0;

		// reinserts colour-channel value into the new rgb pixel
		rgb = rgb | (outR << 16);
		rgb = rgb | (outG << 8);
		rgb = rgb | outB;

		// returns new filtered value of pixel
		return rgb;
	}
}

