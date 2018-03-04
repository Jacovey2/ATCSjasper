import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TunnelPuzzle {
	public static void main(String[] args) {
		pipeIn("in.txt");
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		int lines = in.nextInt();

		for (int i = 0; i < lines; i++) {
			int city = in.nextInt();
			int level = in.nextInt();
			int discount = in.nextInt();
			
			System.out.println("city: " + city + " level: " + level + " discount: " + discount + "%");
		}
		//print out results
		in.close();
	}

	public static void pipeIn(String s) {
		try {
			System.setIn(new FileInputStream(new File(s)));
		} catch (FileNotFoundException e) {
			System.out.println("The file is missing");
		}
	}

}