import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Manage {
	public static void main(String[] args) throws IOException {

		// No Magic Numbers (or Setup)
		int NUMGAMESTATES = 1;
		int NUMBEROFPOSMOVES = 2;
		int ITTERATIONS = 1000;
		int ORGVAL=0;
		int Loss = 0;
		Scanner s = new Scanner(System.in);

		// Matchbox Setup
		Matchbox[] gameBoxes = new Matchbox[NUMGAMESTATES];
		for (int i = 0; i < gameBoxes.length; i++) {
			gameBoxes[i] = new Matchbox(NUMBEROFPOSMOVES, ORGVAL);
		}

		// File handling
		File file = new File("lossData.txt");
		FileWriter fileWriter = new FileWriter(file);

		// Actual Game
		int lastNNmove = 0;
		for (int i = 0; i < ITTERATIONS; i++) {
			System.out.println("Game #: " + i);
			int move = lastNNmove;// s.nextInt();
			System.out.println("Pmove: " + move);
			int NNmove = gameBoxes[0].pickMove();
			System.out.println("NNmove: " + NNmove);

			// 0 is blame, 1 is no blame

			// if player blames, and NN blames
			if (move == 0 && NNmove == 0) {
				gameBoxes[0].punish(0, 0.002);
				Loss += 2;
			}
			// if player doesn't blame, and NN blames
			if (move == 1 && NNmove == 0) {
				gameBoxes[0].reinforce(0, 0.005);
				Loss -= 5;
			}
			// if player doesn't blame, and NN doesn't blame
			if (move == 1 && NNmove == 1) {
				//gameBoxes[0].punish(1, 0.001);
				//Loss += 1;
			}
			// if player blames, and NN doesn't blame
			if (move == 0 && NNmove == 1) {
				gameBoxes[0].punish(1, 0.003);
				Loss += 3;
			}
			System.out.println(gameBoxes[0].ToString());
			System.out.println("loss: " + Loss + "\n");
			lastNNmove = NNmove;
			fileWriter.write(gameBoxes[0].getWeight(0)*(100)+" "+Loss+" "+gameBoxes[0].getWeight(1)*(100)+"\n");
		}
		//Finalizing File Write
		fileWriter.flush();
		fileWriter.close();

	}
}
