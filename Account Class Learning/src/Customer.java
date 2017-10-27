public class Customer {
	private String firstName;
	private String lastName;
	private Account account;

	public Customer(String f, String l, double bal) {
		firstName = f;
		lastName = l;
		account = new Account(bal);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Account getAccount() {
		return account;
	}
}
