import java.util.Random;

public class Matchbox {
	private Random r = new Random();
	public int nodeID;
	private double[] moveWeights;

	// Constructors
	public Matchbox(int numPossibleMoves) {
		moveWeights = new double[numPossibleMoves];
		for (int i = 0; i < moveWeights.length; i++)
			moveWeights[i] = 0;
	}

	public Matchbox(int numPossibleMoves, int initialGain) {
		moveWeights = new double[numPossibleMoves];
		for (int i = 0; i < moveWeights.length; i++)
			moveWeights[i] = initialGain;
	}

	// Reinforce Family
	public void reinforce(int weightIndex, double amount) {
		moveWeights[weightIndex] += amount;
	}

	// Punish Family
	public void punish(int weightIndex, double amount) {
		moveWeights[weightIndex] -= amount;
	}
	
	//Get a weight (for printing)
	public String ToString() {
		String retString ="Matchbox includes: {";
		for(int i=0; i<moveWeights.length-1; i++)
				retString+=moveWeights[i]+", ";
		return retString+moveWeights[moveWeights.length-1]+"}";
	}
	public double getWeight(int weight) {
		return moveWeights[weight];
	}

	// Pick a move from the box
	public int pickMove() {
		try {

			// Turn the weights into a list of percentages
			double[] percentages = new double[moveWeights.length];
			double last = 0;
			for (int i = 0; i < moveWeights.length; i++) {
				percentages[i] = last + 1 +(-1/(1+Math.pow(Math.E, moveWeights[i])));
				last = percentages[i];
			}
			System.out.println(last);

			// Pick one out of the percentage list
			double picked = r.nextDouble();
			for (int i = 0; i < percentages.length; i++) {
				if (i == 0 && picked < percentages[0])
					return 0;
				else if (i == percentages.length - 1 && picked > percentages[percentages.length - 2])
					return i;
				else if ((i > 0 && i < percentages.length) && picked < percentages[i + 1] && picked > percentages[i - 1])
					return i;
			}
			// If it fails (somehow), it will return a nonsense negative value
			return -1;
		} catch (ArrayIndexOutOfBoundsException exception) {
			System.err.println("Array has invalid length: " + exception);
			return -1;
		}
	}
}