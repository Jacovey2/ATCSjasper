import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Manage {
	public static void main(String[] args) throws IOException {

		// No Magic Numbers (or Setup)
		int NUMGAMESTATES = 8; // number of decisions the computer has to make (at maximum)
		int NUMBEROFPOSMOVES = 2; // number of possible choices for the computer to make (at maximum)
		int TRAINITTERATIONS = 100000; // number of training examples
		int PLAYITTERATIONS = 5; // number of player games after the training
		boolean CLEAR_BOXES_AT_START=true; //toggle if re-learning and starting from scratch
		int ORGVAL = 0; // Original value of all weights
		double losses = 0; // # games lost
		double wins = 0; // # games won
		Scanner s = new Scanner(System.in);

		// Matchbox Setup
		Matchbox[] gameBoxes = new Matchbox[NUMGAMESTATES];
		for (int i = 0; i < gameBoxes.length; i++) {
			gameBoxes[i] = new Matchbox(NUMBEROFPOSMOVES, ORGVAL); // Initialize Matchboxes
			if (!CLEAR_BOXES_AT_START)
				gameBoxes[i].load("Matchbox_" + i);
			System.out.println(gameBoxes[i]);
		}

		// File Setup
		File file = new File("lossData.txt");
		FileWriter fileWriter = new FileWriter(file);

		// Actual Game
		Random r = new Random();
		int stackSize = NUMGAMESTATES;
		for (int i = 0; i < TRAINITTERATIONS; i++) {

			int stack = stackSize; // stack = # of pieces left in Nim game
			boolean won = false; // tell whether the correct game has been won
			int wonBy = -1; // 0 is player, human is

			// Path of Boxes in this game
			ArrayList<Matchbox> boxPath = new ArrayList<Matchbox>();

			// One Game of Nim
			System.out.println("Game #: " + (i + 1));
			while (!won) {
				// Random player move (for now) **if you have a simple way of implementing a
				// better-than-random-strategy, please do**
				int move = r.nextInt(2) + 1;
				if (stack == 2)
					move = 2;
				if (stack == 1)
					move = 1;

				// removing from stack dependent on player input
				stack -= move;

				// Win Determining
				if (stack <= 0) {
					won = true;
					wonBy = 0;
				}

				// ML move (checks for won again because if it didn't the computer could always
				// win)
				if (!won) {
					int MLmove = gameBoxes[stack - 1].pickMove();
					// Add this box to the stack of boxes used in this game (for training later)
					boxPath.add(gameBoxes[stack - 1]);
					// removing pieces dependent on the computers output
					stack -= (MLmove + 1);
					// win determining
					if (stack <= 0) {
						won = true;
						wonBy = 1;
					}
				}
			}

			// Training after game
			Matchbox[] path = boxPath.toArray(new Matchbox[boxPath.size()]);
			// every box that was used in the game is responsible for the outcome, so by
			// rewarding/punishing all of them, we get a more optimized AI
			for (int x = 0; x < path.length; x++) {
				if (wonBy == 0)
					path[x].punish(path[x].lastMove, .01);
				if (wonBy == 1) {
					path[x].reinforce(path[x].lastMove, .01);
				}
			}

			// Loss Determining
			if (wonBy == 0) {
				losses += 1;
				System.out.println("Player Won");

			}
			if (wonBy == 1) {
				System.out.println("Computer Won");
				wins += 1;
			}
			// Writing to console
			System.out.println("  losses: " + losses + "\n" + "  wins: " + wins);
			System.out.println("  percentage won: " + Math.round((wins / i)*10000)/100.0 + "\n");
			// Writing to a log (for graphing on Octave)
			// ---------------# lost out of all-----------% won out of games played
			fileWriter.write(losses / TRAINITTERATIONS + " " + wins / i + "\n");
		}
		// Finalizing File Write
		fileWriter.flush();
		fileWriter.close();

		// Displaying and saving weights
		System.out.println("                       1     2");
		for (int i = 0; i < gameBoxes.length; i++) {
			System.out.println("(" + i + ")" + gameBoxes[i]);
			gameBoxes[i].save("Matchbox_" + i);
		}

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Player_Moves~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		// This is a repeat of the above code, but the matchboxes are playing not
		// learning
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

		for (int i = 0; i < PLAYITTERATIONS; i++) {
			int stack = stackSize; // stack = # of pieces left in Nim game
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
				System.out.println(" Stack size:" + stack);

				// ML move (checks for won again because otherwise the computer would always
				// win)
				if (!won) {
					int MLmove = gameBoxes[stack - 1].pickMove();
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
