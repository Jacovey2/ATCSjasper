import java.util.Scanner;

public class Passwords {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your username:");
		String Usr = scan.nextLine();
		System.out.println("Enter your new password:");
		boolean valid = false;
		String Pass = "";

		while (!valid) {
			Pass = scan.nextLine();
			boolean hasUpper = false;
			boolean hasLower = false;
			boolean hasSpecial = false;
			boolean hasNumber = false;
			for (char c : Pass.toCharArray()) {
				if (c >= 97 && c <= 122)
					hasLower = true;
				if (c >= 65 && c <= 90)
					hasUpper = true;
				if ((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 126))
					hasSpecial = true;
				if (c >= 48 && c <= 57)
					hasNumber = true;
			}
			valid = hasUpper && hasLower && hasSpecial && hasNumber && !Pass.contains(Usr) && !Pass.contains(" ")
					&& Pass.length() > 8;
			if (!valid) {
				System.out.print("     Invalid Password, please add: ");
				if (!hasUpper)
					System.out.print("one uppercase letter, ");
				if (!hasLower)
					System.out.print("one lowercase letter, ");
				if (!hasSpecial)
					System.out.print("one special charachter, ");
				if (!hasNumber)
					System.out.print("one number, ");
				if (Pass.length() <= 8)
					System.out.print((8-Pass.length()) +" more charachters, ");
				System.out.print("and remember no usernames or spaces\n");
			}
		}
		System.out.println("Remember your password is: " + Pass);
	}
}
