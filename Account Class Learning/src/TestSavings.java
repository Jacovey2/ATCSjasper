
public class TestSavings {

	public static void main(String[] args) {
		SavingsAccount caleb= new SavingsAccount(5.0,1.12);
		System.out.println("Caleb's Balance is: "+caleb.getBalance());
		CheckingAccount caleb1= new CheckingAccount(200, 100);
		System.out.println("Caleb's Balance is: "+caleb1.getBalance());
		caleb1.withdraw(100);
		System.out.println("Caleb's Balance is: "+caleb1.getBalance());
		caleb1.withdraw(150);
		System.out.println("Caleb's Balance is: "+caleb1.getBalance());
		caleb1.withdraw(150);
		System.out.println("Caleb's Balance is: "+caleb1.getBalance());
	}

}
