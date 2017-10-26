public class TestBank {
	public static void main(String[] args) {
		Account myAccount= new Account(100.0);
		myAccount.deposit(50);
		myAccount.withdraw(45);
		System.out.println(myAccount.getBalance());
	}
}
