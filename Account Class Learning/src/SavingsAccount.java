
public class SavingsAccount extends Account {
	private double intRate;
	public SavingsAccount(double initBalance, double interestRate) {
		super(initBalance*interestRate);
		intRate=interestRate;
	}
}
