import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle {
	public static void main(String[] args) {
		pipeIn("in.txt");
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		int lines = in.nextInt();
		int[] bestCitiesForLevels = { 0, 0, 0 };
		int[] bestDiscountsForLevels = { 0, 0, 0 };

		for (int i = 0; i < lines; i++) {
			int city = in.nextInt();
			int level = in.nextInt();
			int discount = in.nextInt();
			for (int levelIndex = 0; levelIndex < 3; levelIndex++)
				if ((level == levelIndex + 1 && discount > bestDiscountsForLevels[levelIndex]) || (level == levelIndex + 1
						&& discount == bestDiscountsForLevels[levelIndex] && city < bestCitiesForLevels[levelIndex])) {
					bestCitiesForLevels[levelIndex] = city;
					bestDiscountsForLevels[levelIndex] = discount;
				}
			System.out.println("city: " + city + " level: " + level + " discount: " + discount + "%");
		}
		// print out results
		for (int i = 0; i < 3; i++)
			System.out.println("best city for level " + (i + 1) + " is city " + bestCitiesForLevels[i] + " with a "
					+ bestDiscountsForLevels[i] + "% discount");
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