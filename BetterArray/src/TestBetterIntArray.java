import java.util.Scanner;

public class TestBetterIntArray {
	public static void main(String[] args) {
		boolean Testing = true;
		Scanner scan = new Scanner(System.in);

		
		BetterIntArray numberArray = new BetterIntArray();
		
		//Shopping list with physical scanner
		if (!Testing) {
			System.out.println("Please scan your items now");
			String input="";
			while (!input.equals("end")) {
				input=scan.next();
				if (!input.equals("end")) {
					try {
						numberArray.add(Integer.parseInt(input));
					}catch(NumberFormatException e) {}
				}
				System.out.println("You Have Entered: "+input);
			}
			System.out.println("Checkout?: "+numberArray);
		}
		
		//Method Testing
		if (Testing) {
			int[] nums = { 121, 142, 121, 154, 205 };
			numberArray.add(nums);
			System.out.println("Original                         ::   " + numberArray);
			System.out.println("Size                             ::   " + numberArray.size());
			System.out.println("Average                          ::   " + numberArray.average());
			System.out.println("ValueAt(2)                       ::   " + numberArray.valueAt(2));
			System.out.println("IndexOf(142)                     ::   " + numberArray.indexOf(142)[0]);
			System.out.println("Contains(121)                    ::   " + numberArray.contains(121));

			int retval = numberArray.pop();
			System.out.println("Pop                              ::   " + numberArray + " :: " + retval);
			retval = numberArray.pop(3);
			System.out.println("Pop out 3                        ::   " + numberArray + " :: " + retval);

			numberArray.add(200);
			System.out.println("Added [200]                      ::   " + numberArray);
			numberArray.add(111, 2);
			System.out.println("Added [200] at 2                 ::   " + numberArray);

			int[] tempArr1 = { 132, 234, 512, 234 };
			numberArray.add(tempArr1);
			System.out.println("Added [131, 234, 512, 234]       ::   " + numberArray);

			int[] tempArr2 = { 545, 444 };
			numberArray.add(tempArr2, 2);
			System.out.println("Added [545, 444]  at 2           ::   " + numberArray);

			numberArray.remove(121);
			System.out.println("Removed [121]                    ::   " + numberArray);

			int[] tempArr3 = { 142, 154 };
			numberArray.remove(tempArr3);
			System.out.println("Removed [142, 154]               ::   " + numberArray);

			numberArray.removeDuplicates();
			System.out.println("Removed Duplicates               ::   " + numberArray);

			numberArray.swap(0, 6);
			System.out.println("Swap 0 and 5                     ::   " + numberArray);

			numberArray.randomize();
			System.out.println("Randomize                        ::   " + numberArray);

			numberArray.bubbleSort();
			System.out.println("Bubble Sort                      ::   " + numberArray);

			numberArray.randomize();
			System.out.println("Randomize                        ::   " + numberArray);
			
			numberArray.quickSort();
			System.out.println("Quick Sort                       ::   " + numberArray);
		}
	}
}
