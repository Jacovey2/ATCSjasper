public class Account {
	//Variables
	private double balance=0.0;
	
	//Constructors
	public Account(double initBalance) {
		balance=initBalance;
	}
	//Methods
	public double getBalance() {
		return balance;
	}
	public void deposit(double amt) {
		if (amt>=0) balance+=amt;
		else System.out.println("Invalid deposit");
	}
	public void withdraw(double amt) {
		if (amt>=0) balance-=amt;
		else if (amt<=balance || amt<0)System.out.println("Invalid widthdrawel");
	}
}