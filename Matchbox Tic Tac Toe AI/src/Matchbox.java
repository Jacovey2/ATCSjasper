import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Matchbox {
	private Random r = new Random();
	public int nodeID;
	private double[] moveWeights;
	public int lastMove;
	private int Bound = 20;

	// Constructors
	public Matchbox(int numPossibleMoves) {
		moveWeights = new double[numPossibleMoves];
		for (int i = 0; i < moveWeights.length; i++) {
			moveWeights[i] = ((r.nextDouble()/2)-.5);
		}
	}

	public Matchbox(int numPossibleMoves, int initialGain) {
		moveWeights = new double[numPossibleMoves];
		for (int i = 0; i < moveWeights.length; i++) {
			moveWeights[i] = initialGain;//+((r.nextDouble()/2)-.5);
		}
	}

	// Reinforce Family
	public void reinforce(int weightIndex, double amount) {
		moveWeights[weightIndex] += amount;
		if (moveWeights[weightIndex] > Bound)
			moveWeights[weightIndex] = Bound;
	}

	// Punish Family
	public void punish(int weightIndex, double amount) {
		moveWeights[weightIndex] -= amount;
		if (moveWeights[weightIndex] < -Bound)
			moveWeights[weightIndex] = -Bound;
	}

	// ToString method for a Matchbox
	public String toString() {
		String retString = "Matchbox includes: {";
		for (int i = 0; i < moveWeights.length - 1; i++)
			retString += Math.round(moveWeights[i] * 1000) / 1000.0 + ", ";
		return retString + Math.round(moveWeights[moveWeights.length - 1] * 1000) / 1000.0 + "}";
	}

	// Get an individual weight
	public double getWeight(int weight) {
		return moveWeights[weight];
	}

	// Save Weights to a file
	public void save(String name, String Directory) throws IOException {
		File file = new File(Directory + name + ".txt");
		FileWriter fileWriter = new FileWriter(file);

		for (int i = 0; i < moveWeights.length; i++) {
			System.out.println(moveWeights[i]);
			fileWriter.write(moveWeights[i] + "\n");
		}
		
		fileWriter.write(lastMove +"");

		fileWriter.flush();
		fileWriter.close();
	}

	// Read weights from a file
	public boolean load(String name, String Directory) throws IOException {
		try {
			File file = new File(Directory + name + ".txt");
			Scanner input = new Scanner(file);

			for (int i = 0; i < moveWeights.length; i++) {
				moveWeights[i] = input.nextDouble();
			}
			lastMove = input.nextInt();
			//System.out.println(lastMove); // Hello
			input.close();
			return true;
		} catch (java.io.FileNotFoundException e) {
			return false;
		}
	}

	// Pick a move from the box
	public int pickMove() {
		try {
			// Turn the weights into a list of activations
			double[] activations = new double[moveWeights.length];
			for (int i = 0; i < moveWeights.length; i++) {
				// Determines the activations of the moves using a transformed Sigmoid function
				if (i==0) {
					activations[i] = (1 + (-1 / (1 + Math.pow(Math.E, moveWeights[i]))));
				}
				else {
					activations[i] = (1 + (-1 / (1 + Math.pow(Math.E, moveWeights[i]))))+activations[i-1];
				}
			}

			// Pick one out of the percentage list
			double picked = r.nextDouble() * activations[activations.length-1];
			
			int index=0;
			for (int i = 0; i < activations.length; i++) {
				// if the randomly picked value is greater than activation[i], the option is picked (recursively)
				if (picked > activations[i]) {
					index = i;
				} 
			}
			return index;
			
		} catch (ArrayIndexOutOfBoundsException exception) {
			System.err.println("!ERR! Array has invalid length: " + exception);
			lastMove = -1;
			return -1;
		}
	}
}