
public class TestBetterArray {
	public static void main(String[] args) {
		int[] nums = { 121, 142, 121, 154, 205 };
		BetterArray numberArray = new BetterArray(nums);
		
		System.out.println("Original: " + numberArray.toString());
		System.out.println("Size: "+numberArray.size());
		System.out.println("ValueAt(2): "+numberArray.valueAt(2));
		System.out.println("IndexOf: "+numberArray.indexOf(142)[0]);
		System.out.println("Contains(121): "+numberArray.contains(121));
		
		numberArray.pop();
		System.out.println("Pop: " + numberArray.toString());

		numberArray.add(200);
		System.out.println("Added (200): " + numberArray.toString());

		int[] tempArr1 = { 132, 234, 512 };
		numberArray.add(tempArr1);
		System.out.println("Added (131, 234, 512): " + numberArray.toString());

		numberArray.remove(121);
		System.out.println("Removed (121): " + numberArray.toString());

		int[] tempArr2 = { 142, 154 };
		numberArray.remove(tempArr2);
		System.out.println("Removed (142, 154): " + numberArray.toString());
	}
}
