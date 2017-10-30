
public class BetterIntArray {
	private int[] arr;

	public BetterIntArray(int[] setupArray) {
		arr = setupArray;
	}

	public int contains(int val) {
		int numfound = 0;
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == val)
				numfound++;
		return numfound;
	}

	public void add(int val) {
		int[] tempArr = new int[arr.length + 1];
		for (int i = 0; i < arr.length; i++)
			tempArr[i] = arr[i];
		tempArr[arr.length] = val;
		arr = tempArr;
	}

	public void add(int[] vals) {
		for (int i = 0; i < vals.length; i++)
			add(vals[i]);
	}

	public void pop() {
		int[] tempArr = new int[arr.length - 1];
		for (int i = 0; i < arr.length - 1; i++)
			tempArr[i] = arr[i];
		arr = tempArr;
	}

	public void remove(int val) {
		int[] tempArr = new int[arr.length - contains(val)];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != val) {
				tempArr[index] = arr[i];
				index++;
			}
		}
		arr = tempArr;
	}

	public void remove(int[] vals) {
		for (int i = 0; i < vals.length; i++)
			remove(vals[i]);
	}

	public int size() {
		return arr.length;
	}

	public int valueAt(int index) {
		return arr[index];
	}

	public int[] indexOf(int val) {
		int[] tempArr = new int[contains(val)];
		int index = 0;
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == val) {
				tempArr[index] = i;
				index++;
			}
		return tempArr;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < arr.length; i++) {
			s += "" + arr[i];
			if (i + 1 < arr.length)
				s += ", ";
		}
		return s;
	}
	
	public int[] toArray() {
		return arr;
	}

	public void removeDuplicates() {
		BetterIntArray tempBetIntArr= new BetterIntArray(new int[0]);
		for (int i = 0; i < arr.length; i++) 
			if (tempBetIntArr.contains(arr[i])==0)
				tempBetIntArr.add(arr[i]);
		arr=tempBetIntArr.toArray();
	}
}
