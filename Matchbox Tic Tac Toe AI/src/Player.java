import java.util.Random;

public class Player {
	public static int pickMove(int[] gameBoard) {
		Random rand = new Random();
		boolean RandomOrAI = false;//rand.nextBoolean();
		if (RandomOrAI) {
			return rand.nextInt(gameBoard.length);
		} else {
			double[] HeuristicBoard = new double[gameBoard.length];
			for (int i = 0; i < gameBoard.length; i++) {
				int[] numX = new int[4]; // 0 is rows, 1 is columns, 2 is DDs, 3 is DUs
				int[] numO = new int[4]; // 0 is rows, 1 is columns, 2 is DDs, 3 is DUs
				int[] numW = new int[4]; // 0 is rows, 1 is columns, 2 is DDs, 3 is DUs
				// Check surrounding rows and columns
				for (int k = -1; k <= 1; k++) {
					if (gameBoard[i] == 0) {
						// check rows
						int r = i + k;
						if (!conflict(r) && (i == 1 || i == 4 || i == 7)) {
							int rval = gameBoard[r];
							if (rval == 0)
								numW[0]++;
							else if (rval == 1)
								numX[0]++;
							else if (rval == 2)
								numO[0]++;
						}
						// check columns
						int c = i + (3 * k);
						if (!conflict(c) && (i == 3 || i == 4 || i == 5)) {
							int cval = gameBoard[c];
							if (cval == 0)
								numW[1]++;
							else if (cval == 1)
								numX[1]++;
							else if (cval == 2)
								numO[1]++;
						}
					}
				}
				// Checks surrounding diagonals
				for (int k = -2; k <= 2; k++) {
					if (gameBoard[i] == 0) {
						// check diagonal down
						int dd = 4*k + i;
						if (!conflict(dd) && (k == 0 || k == 4 || k == 8)) {
							int ddval = gameBoard[dd];
							if (ddval == 0)
								numW[2]++;
							else if (ddval == 1)
								numX[2]++;
							else if (ddval == 2)
								numO[2]++;
						}
						// check diagonal up
						int du = 2*k + i;
						if (!conflict(du) && (k == 2 || k == 4 || k == 6)) {
							int duval = gameBoard[du];
							if (duval == 0)
								numW[3]++;
							else if (duval == 1)
								numX[3]++;
							else if (duval == 2)
								numO[3]++;
						}
					}
				}
				if (numX[0] > 1 || numX[1] > 1 || numX[2] > 1 || numX[3] > 1)
					HeuristicBoard[i] += 2;
				if (numO[0] > 1 || numO[1] > 1 || numO[2] > 1 || numO[3] > 1)
					HeuristicBoard[i] += 3;
				HeuristicBoard[i] += 0.5 * (numW[0] + numW[1] + numW[2] + numW[3]);
			}
			double highest = 0;
			int number = 0;
			// finding highest valued space
			for (int i = 0; i < 9; i++)
				if (HeuristicBoard[i] > highest)
					highest = HeuristicBoard[i];
			// finding how many of those places exist (multiples are possible)
			for (int i = 0; i < 9; i++)
				if (HeuristicBoard[i] == highest)
					number++;
			// puts the index(s) of the highest value(s) into a list
			int[] validPeices = new int[number];
			for (int i = 0; i < 9; i++) {
				if (HeuristicBoard[i] == highest && gameBoard[i]==0) {
					validPeices[number - 1] = i;
					number--;
				}
			}
			// getting a random index from the list of valid ones
			int bound = validPeices.length;
			int index = rand.nextInt(bound);
			
			return validPeices[index];
		}
	}

	public static boolean conflict(int x) {
		if (x < 0 || x >= 9)
			return true;
		else
			return false;
	}
}