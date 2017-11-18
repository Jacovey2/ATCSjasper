import java.util.Random;

public class Matchbox {
	private Random r = new Random();
	public int nodeID;
	private double[] moveWeights;
	private double[] moveBiases;
	public int lastMove;

	// Constructors
	public Matchbox(int numPossibleMoves) {
		moveWeights = new double[numPossibleMoves];
		moveBiases = new double[numPossibleMoves];
		for (int i = 0; i < moveWeights.length; i++) {
			moveWeights[i] = 0;
			moveBiases[i] = 0;
		}
	}

	public Matchbox(int numPossibleMoves, int initialGain) {
		moveWeights = new double[numPossibleMoves];
		moveBiases = new double[numPossibleMoves];
		for (int i = 0; i < moveWeights.length; i++) {
			moveWeights[i] = initialGain;
			moveBiases[i] = 0;
		}
	}

	// SetBiases
	public void setBiases(double[] Biases) {
		for (int i = 0; i < moveWeights.length; i++) {
			moveBiases[i] = Biases[i];
		}
	}

	// Reinforce Family
	public void reinforce(int weightIndex, double amount) {
		moveWeights[weightIndex] += amount;
	}

	// Punish Family
	public void punish(int weightIndex, double amount) {
		moveWeights[weightIndex] -= amount;
	}

	// ToString method for a Matchbox
	public String ToString() {
		String retString = "Matchbox includes: {";
		for (int i = 0; i < moveWeights.length - 1; i++)
			retString += moveWeights[i] + ", ";
		return retString + moveWeights[moveWeights.length - 1] + "}";
	}
	
	// Get an individual weight
	public double getWeight(int weight) {
		return moveWeights[weight];
	}

	// Pick a move from the box
	public int pickMove() {
		try {
			// Turn the weights into a list of activations
			double[] activations = new double[moveWeights.length];
			for (int i = 0; i < moveWeights.length; i++) {
				//Determines the activations of the moves using a transformed sigmoid function
				activations[i] = (1 + (-1 / (1 + Math.pow(Math.E, moveWeights[i])))) - moveBiases[i];
			}

			// Pick one out of the percentage list
			double picked = r.nextDouble() * activations[activations.length - 1];
			for (int i = 0; i < activations.length; i++) {
				// if the randomly picked value is greater than activation[i], the option is picked
				if (i == 0 && picked < activations[0]) {
					lastMove = 0;
					return 0;
				} else if (i == activations.length - 1 && picked > activations[activations.length - 2]) {
					lastMove = i;
					return i;
				} else if ((i > 0 && i < activations.length) && picked < activations[i + 1] && picked > activations[i - 1]) {
					lastMove = i;
					return i;
				}
			}
			// If it fails (somehow), it will return a nonsense negative value
			lastMove = -1;
			return -1;
		} catch (ArrayIndexOutOfBoundsException exception) {
			System.err.println("Array has invalid length: " + exception);
			lastMove = -1;
			return -1;
		}
	}
}