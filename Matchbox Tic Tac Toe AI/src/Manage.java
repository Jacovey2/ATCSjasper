import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Manage {
	public static void main(String[] args) throws IOException {

		// No Magic Numbers (or Setup)
		int NUMGAMESTATES = 10; // number of decisions the computer has to make (at maximum)
		int NUMBEROFPOSMOVES = 2; // number of possible choices for the computer to make (at maximum)
		int TRAINITTERATIONS = 50000; // number of training examples
		int PLAYITTERATIONS = 5; // number of player games after the training
		int ORGVAL = 0; // Original value of all weights
		double losses = 0; // # games lost
		double wins = 0; // # games won
		Scanner s = new Scanner(System.in);

		// Matchbox Setup
		Matchbox[] gameBoxes = new Matchbox[NUMGAMESTATES];
		for (int i = 0; i < gameBoxes.length; i++) {
			gameBoxes[i] = new Matchbox(NUMBEROFPOSMOVES, ORGVAL); // Initialize Matchboxes
		}

		// File Setup
		File file = new File("lossData.txt");
		FileWriter fileWriter = new FileWriter(file);

		// Actual Game
		Random r = new Random();
		for (int i = 0; i < TRAINITTERATIONS; i++) {

			int stack = 10; // stack = # of pieces left in Nim game
			boolean won = false; // tell whether the correct game has been won
			int wonBy = -1; // 0 is player, human is
			int counter = 0;// just for aesthetics

			// Path of Boxes in this game
			ArrayList<Matchbox> boxPath = new ArrayList<Matchbox>();

			// One Game of Nim
			System.out.println("Game #: " + (i + 1));
			while (!won) {
				// Human move
				System.out.println("  Turn #: " + counter);
				System.out.println("    How many pieces do you want to take out? (1-2)");

				// Random player move (for now) **if you have a simple way of implementing a
				// better-than-normal-strategy, please do**
				int move = r.nextInt(2) + 1;// s.nextInt();
				System.out.println("    Player move: " + move);

				// removing from stack dependent on player input
				stack -= move;

				// Win Determining
				if (stack <= 0) {
					won = true;
					wonBy = 0;
				}

				// ML move (checks for won again because if it didn't the computer would always
				// win)
				if (!won) {
					int MLmove = gameBoxes[stack - 1].pickMove();
					// Add this box to the stack of boxes used in this game (for training later)
					boxPath.add(gameBoxes[stack - 1]);
					System.out.println("    ML move: " + MLmove);
					// removing pieces dependent on the computers output
					stack -= (MLmove + 1);
					// win determining
					if (stack <= 0) {
						won = true;
						wonBy = 1;
					}
				}
				System.out.println(" Stack size:" + stack);

				// just for aesthetics
				counter++;
			}

			// Training after game
			Matchbox[] path = boxPath.toArray(new Matchbox[boxPath.size()]);
			// every box that was used in the game is responsible for the outcome, so by
			// rewarding/punishing all of them, we get a more optimized AI
			for (int x = 0; x < path.length; x++) {
				if (wonBy == 0)
					path[x].punish(path[x].lastMove, .01);
				if (wonBy == 1)
					path[x].reinforce(path[x].lastMove, .01);
				// print out that Box's contents
				System.out.println("      " + gameBoxes[x].ToString());
			}

			// Loss Determining
			if (wonBy == 0) {
				losses += 1;
				System.out.println("Player Won\n");

			}
			if (wonBy == 1) {
				System.out.println("Computer Won\n");
				wins += 1;
			}
			// Writing to console
			System.out.println("losses: " + losses + "\n" + "wins:" + wins + "\n");
			// Writing to a log (for graphing on Octave)
			// ---------------# lost out of all-----------% won out of games played
			fileWriter.write(losses / TRAINITTERATIONS + " " + wins / i + "\n");
		}
		// Finalizing File Write
		fileWriter.flush();
		fileWriter.close();

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Player_Moves~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		// This is a repeat of the above code, but the program is just playing and not
		// learning
	  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

		for (int i = 0; i < PLAYITTERATIONS; i++) {
			int stack = 10; // stack = # of pieces left in Nim game
			boolean won = false; // tell whether the correct game has been won
			int wonBy = -1; // 0 is player, human is
			while (!won) {
				// Human move
				System.out.println("    How many pieces do you want to take out? (1-2)");

				// Player move
				int move = s.nextInt();
				System.out.println("    Player move: " + move);

				// removing from stack dependent on player input
				stack -= move;

				// Win Determining
				if (stack <= 0) {
					won = true;
					wonBy = 0;
				}

				// ML move (checks for won again because if it didn't the computer would always
				// win)
				if (!won) {
					int MLmove = gameBoxes[stack - 1].pickMove();
					// Add this box to the stack of boxes used in this game (for training later)
					System.out.println("    ML move: " + (MLmove + 1));
					// removing pieces dependent on the computers output
					stack -= (MLmove + 1);
					// win determining
					if (stack <= 0) {
						won = true;
						wonBy = 1;
					}
				}
				System.out.println(" Stack size:" + stack);

				// just for aesthetics
			}
			if (wonBy == 0)
				System.out.println("Player Won\n");
			if (wonBy == 1)
				System.out.println("Computer Won\n");
		}
	}
}
