
public class TestBetterIntArray {
	public static void main(String[] args) {
		int[] nums = { 121, 142, 121, 154, 205 };
		BetterIntArray numberArray = new BetterIntArray(nums);

		System.out.println("Original                         ::   " + numberArray);
		System.out.println("Size                             ::   " + numberArray.size());
		System.out.println("ValueAt(2)                       ::   " + numberArray.valueAt(2));
		System.out.println("IndexOf(142)                     ::   " + numberArray.indexOf(142)[0]);
		System.out.println("Contains(121)                    ::   " + numberArray.contains(121));

		int retval= numberArray.pop();
		System.out.println("Pop                              ::   " + numberArray +" :: "+retval);
		retval= numberArray.pop(1);
		System.out.println("Pop out 1                        ::   " + numberArray +" :: "+retval);

		numberArray.add(200);
		System.out.println("Added [200]                      ::   " + numberArray);
		numberArray.add(111,2);
		System.out.println("Added [200] at 2                 ::   " + numberArray);

		int[] tempArr1 = { 132, 234, 512, 234 };
		numberArray.add(tempArr1);
		System.out.println("Added [131, 234, 512, 234]       ::   " + numberArray);
		
		int[] tempArr2 = { 545, 444 };
		numberArray.add(tempArr2, 2);
		System.out.println("Added [131, 234, 512, 234]  at 2 ::   " + numberArray);

		numberArray.remove(121);
		System.out.println("Removed [121]                    ::   " + numberArray);

		int[] tempArr3 = { 142, 154 };
		numberArray.remove(tempArr3);
		System.out.println("Removed [142, 154]               ::   " + numberArray);
		
		numberArray.removeDuplicates();
		System.out.println("Removed Duplicates               ::   " + numberArray);
		
		numberArray.swap(0, 6);
		System.out.println("Swap 0 and 5                     ::   " + numberArray);
		
		
	}
}
