
public class CheckingAccount extends Account {
	double overDraftAmount;
	public CheckingAccount(double initBalance) {
		super(initBalance);
	}
	public CheckingAccount(double initBalance, double overDraftAmt) {
		super(initBalance);
		overDraftAmount=overDraftAmt;
	}
	
	public boolean withdrawn(double amt) {
		if (amt > getBalance() + overDraftAmount) {
			System.out.println("Overdraft Exceded");
			return false;
		}
		else if(amt<getBalance()) {
			balance-=amt;
			return true;
		}
		else {
			balance=0;
			System.out.println("Owed:"+(amt-getBalance()+overDraftAmount));
			return true;
		}
	}
}
