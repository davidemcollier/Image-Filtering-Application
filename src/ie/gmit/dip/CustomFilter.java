package ie.gmit.dip;


public class CustomFilter {
	private int row;
	private int col;
	private double[][] customKernel;

	public double[][] defineFilter() {
		System.out.println(ConsoleColour.BLACK_BOLD);
		System.out.println("###################################");
		System.out.println("#           Custom Filter         #");
		System.out.println("###################################");
		System.out.println(ConsoleColour.RESET);
		// User defines height and width of kernel
		this.customKernel = defineKernel();
		// Request user to input the values for each element
		customKernel = inputFields(customKernel);
		return customKernel;
	}

	private double[][] defineKernel() {

		// Ensures value for columns and rows are odd and are numbers
		this.row = kernelValidator("rows");
		this.col = kernelValidator("columns");

		// Print kernel size
		ConsoleColour.infoFormatter("Your kernel is " + row + " x " + col);
		// define new kernel size
		return new double[col][row];
	}


	private int kernelValidator(String rowOrCol) {

		boolean isEven = true;
		int value = 0;
		while (isEven) {
			try {
				System.out.println(ConsoleColour.BLACK_BOLD);
				System.out.println("How many " + rowOrCol + " in your kernel?");
				System.out.print("Enter an odd number:");
				System.out.println(ConsoleColour.RESET);
				value = Integer.parseInt(Runner.in.next().trim());
				// if we get past the input without an exception then the if statement will
				// check if the value is odd.
				// if value is then even, boolean switched to break loop and return the odd number
				if (value % 2 != 0) {
					isEven = false;
				}
			} catch (NumberFormatException e) {
				ConsoleColour.errorFormatter("Invalid Value. Please enter a number.");
			}
		}
		return value;
	}


	private double[][] inputFields(double[][] customKernel) {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				printInputKernel(customKernel, i, j);

				customKernel[i][j] = numberValidator();
			}
		}
		ConsoleColour.infoFormatter("Your kernel is complete:");
		printFinalKernel(customKernel);
		ConsoleColour.infoFormatter("Returning to main menu");
		return customKernel;
	}

	//Validates input in kernel as numbers
	private double numberValidator() {
		boolean notNumber = true;

		double value = 0;
		while (notNumber) {
			try {
				System.out.println(ConsoleColour.BLACK_BOLD);
				System.out.println("Please enter a value for ???");
				System.out.println(ConsoleColour.RESET);
	
				value = Double.parseDouble(Runner.in.next().trim());
				// if value is a number then boolean breaks the loop and number returned for
				// kernel
				notNumber = false;
			} catch (NumberFormatException e) {
				ConsoleColour.errorFormatter("Invalid Value. Please enter a number.");				
			}
		}
		return value;
	}
	


	// Formats the input values and indicates to user which field is next to be
	// filled.
	private void printInputKernel(double[][] customKernel, int i, int j) {

		System.out.println("{");
		for (int k = 0; k < col; k++) {
			System.out.print("  { ");
			for (int l = 0; l < row; l++) {
				// Add spacing/punctuation separating the individual elements in the array
				if (l < row) {
					// Space only at the end of an array (i.e. without a comma)
					if (l == row - 1) {
						// Printing the next '???' value to be input
						if (k == i && l == j) {
							System.out.print("???" + " ");
						} else {
							System.out.print(customKernel[k][l] + " ");
						}
					}
					// Comma and spacing for all other elements
					else if (k == i && l == j) {
						System.out.print("???" + ", ");
					} else {
						System.out.print(customKernel[k][l] + ", ");
					}
				}
			}
			System.out.println("}");
		}
		System.out.println("}");
	}

	// Final kernel print
	private void printFinalKernel(double[][] customKernel) {
		System.out.println("{");
		for (int k = 0; k < col; k++) {
			System.out.print("  { ");
			for (int l = 0; l < row; l++) {
				// Add spacing/punctuation separating the individual elements in the array
				if (l < row) {
					// Space only at the end of an array (i.e. without a comma)
					if (l == row - 1) {
						System.out.print(customKernel[k][l] + " ");
					}
					// Comma and spacing for all other elements
					else {
						System.out.print(customKernel[k][l] + ", ");
					}
				}
			}
			System.out.println("}");
		}
		System.out.println("}");
	}
}
