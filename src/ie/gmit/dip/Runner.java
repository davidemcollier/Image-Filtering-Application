package ie.gmit.dip;

import java.util.Scanner;

public class Runner {
	
	public static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		System.out.println(ConsoleColour.BLACK_BOLD);
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*           Image Filtering System V0.1           *");
		System.out.println("*     H.Dip in Science (Software Development)     *");		
		System.out.println("*                                                 *");
		System.out.println("***************************************************");				
		System.out.println(ConsoleColour.RESET);
		
		Menu m = new Menu();
		m.start();
	}
}