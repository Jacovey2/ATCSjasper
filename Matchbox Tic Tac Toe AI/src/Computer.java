
import java.util.Scanner;
import java.io.IOException;

public class Computer {
	public static String currentBoxID;
	
	public static int pickMove(int[] gameBoard) throws IOException {
		//Creating the ID for this box
		String ID = "MatchBox (";
		for (int i = 0; i < gameBoard.length; i++) {
			if (i != gameBoard.length - 1)
				ID += gameBoard[i] + ",";
			else
				ID += gameBoard[i] + "";
		} 
		ID+=")";
		
		currentBoxID=ID;
		
		// File Setup
		Matchbox m = new Matchbox(Manage.NUMBEROFPOSMOVES, 0);
		
		//Look for a box
		boolean loadSucsess = m.load(ID, Manage.MATCHBOXDIRECTORY);
		
		//If the box isn't found, make one
		if (!loadSucsess) {
			m.save(ID, Manage.MATCHBOXDIRECTORY);
		}
		int pickedMove= m.pickMove();
		m.save(ID, Manage.MATCHBOXDIRECTORY);
		
		//finalizing move
		return pickedMove;
	}
}
