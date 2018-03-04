import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
	public int wonBy;
	public ArrayList<String> boxPath;

	public Game() throws IOException {
		int i = 0;
		int[] board = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		// Path of boxes used in the game
		boxPath = new ArrayList<String>();
		boolean won = false;
		while (!won) {
			// Computer = 1
			int cmove = Computer.pickMove(board);
			while (cmove >= 9 || cmove < 0 || board[cmove] != 0) // re-picking until its a valid move
				cmove = Computer.pickMove(board);
			boxPath.add(Computer.currentBoxID);
			board[cmove] = 1;// commit that move to the board
			displayGrid(board);// Display after computer's move

			//check for win
			wonBy = checkWin(board);
			if (wonBy == 1 || wonBy == 2) 
				won = true;

			// Player = 2
			if (!won) {
				int pmove = Player.pickMove(board);
				if (pmove < 9 && pmove >= 0 && board[pmove] == 0)
					board[pmove] = 2;// commit that move to the board
				// Display after player's move
				displayGrid(board);
				
				//checking for win again
				wonBy = checkWin(board);
				if (wonBy == 1 || wonBy == 2) {
					won = true;
				}
			}
		}
		displayGrid(board);
	}

	public void displayGrid(int[] Grid) {
		System.out.println("  _______");
		System.out.print("  |");
		for (int i = 0; i < Grid.length; i++) {
			if (i == 3 || i == 6)
				System.out.print("\n  |");
			if (Grid[i] == 0) {
				System.out.print(" |");
			}
			if (Grid[i] == 1) {// CompMove
				System.out.print("X|");
			}
			if (Grid[i] == 2) {// PlayerMove
				System.out.print("O|");
			}
		}
		System.out.println("");
	}

	public int checkWin(int[] board) {
		int wonby = -1;
		// Rows
		if (board[0] == board[1] && board[1] == board[2] && board[2] != 0)
			wonby = board[0];
		if (board[3] == board[4] && board[4] == board[5] && board[5] != 0)
			wonby = board[3];
		if (board[6] == board[7] && board[7] == board[8] && board[8] != 0)
			wonby = board[6];

		// Columns
		if (board[0] == board[3] && board[3] == board[6] && board[6] != 0)
			wonby = board[0];
		if (board[1] == board[4] && board[4] == board[7] && board[7] != 0)
			wonby = board[1];
		if (board[2] == board[5] && board[5] == board[8] && board[8] != 0)
			wonby = board[2];

		// Diagonals
		if (board[2] == board[4] && board[4] == board[6] && board[6] != 0)
			wonby = board[2];
		if (board[0] == board[4] && board[4] == board[8] && board[8] != 0)
			wonby = board[0];
		return wonby;
	}
}
