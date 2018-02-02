import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Manage {

	// No Magic Numbers (or Setup)
	// int NUMGAMESTATES = 8; // number of decisions the computer has to make (at
	// maximum)
	public static int NUMBEROFPOSMOVES = 9; // number of possible choices for the computer to make (at maximum)
	public static int TRAINITTERATIONS = 10000; // number of training examples
	public static String MATCHBOXDIRECTORY = "/Users/jacovey/Documents/Matchboxes/";
	// int PLAYITTERATIONS = 5; // number of player games after the training
	public static boolean CLEAR_BOXES_AT_START = false; // toggle if re-learning and starting from scratch
	public static int ORGVAL = 0; // Original value of all weights
	static double losses = 0; // # games lost
	static double wins = 0; // # games won
	static double draws = 0; // # games won

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		// Clearing Matchbox directory
		if (CLEAR_BOXES_AT_START) {
			File matchboxFolder = new File(MATCHBOXDIRECTORY + "");
			deleteFolder(matchboxFolder);
		}
		// File Setup
		File file = new File("lossData.txt");
		FileWriter fileWriter = new FileWriter(file);

		// Actual Game
		Random r = new Random();
		for (int i = 0; i < TRAINITTERATIONS; i++) {
			// One Game of Tic-Tac-Toe
			System.out.println("Game #: " + (i + 1));
			Game game = new Game();

			// Training after game
			String[] IDs = game.boxPath.toArray(new String[game.boxPath.size()]);
			for (int in = 0; in < game.boxPath.size(); in++) {
				Matchbox m = new Matchbox(NUMBEROFPOSMOVES);
				m.load(IDs[in], MATCHBOXDIRECTORY);
				// every box that was used in the game is responsible for the outcome, so by
				// rewarding/punishing all of them, we get a more optimized AI
				if (game.wonBy == 1) {// Computer won, so reward the AI
					m.reinforce(m.lastMove, .1);
				} else if (game.wonBy == 2) {// Computer lost, so punish the AI
					m.punish(m.lastMove, .1);
				} else { // Both drew, so reward, but with less
					m.reinforce(m.lastMove, .005);
				}
				m.save(IDs[in], MATCHBOXDIRECTORY);
			}

			// Book keeping
			if (game.wonBy == 1) // Computer won
				wins++;
			else if (game.wonBy == 2) // Player won
				losses++;
			else // Both drew
				draws++;

			// Writing to console
			System.out.println("  losses: " + losses + "\n" + "  wins: " + wins + "\n" + "  draws: " + draws);
			System.out.println("    percentage won: " + (wins / (i+1)) * 100 + "\n");

			// Writing to a log (for graphing on Octave)
			// --------------# lost out of all-----------% won out of games played
			fileWriter.write(losses / (i+1) + " " + wins / (i+1) + "\n");
		}
		// Finalizing File Write
		fileWriter.flush();
		fileWriter.close();
		s.close();
	}

	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) { // some JVMs return null for empty dirs
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		else {System.out.println("Failed to delete files");}
	}
}
