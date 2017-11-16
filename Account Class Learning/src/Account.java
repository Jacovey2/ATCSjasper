public class Account {
	// Variables
	protected double balance = 0.0;

	// Constructors
	public Account() {
		balance = 0;
	}

	public Account(double initBalance) {
		balance = initBalance;
	}

	// Methods
	public double getBalance() {
		return balance;
	}

	public boolean deposit(double amt) {
		if (amt >= 0) {
			balance += amt;
			return true;
		} else {
			System.out.println("Invalid deposit");
			return false;
		}
	}

	public boolean withdraw(double amt) {
		if (amt >= 0) {
			balance -= amt;
			return true;
		} else if (amt <= balance || amt < 0) {
			System.out.println("Invalid widthdrawel");
			return false;
		}
		return false;
	}
}
