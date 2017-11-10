import java.util.Random;

/*
 * There are many possible use cases for a better array
 * For example, product numbers in a shopping list.
 * My code handles multiple identical entries, which 
 * one would need for a shopping list that can have
 * multiple of an entry.
*/

public class BetterIntArray {
	private int[] arr;

	// Constructors
	public BetterIntArray() {
		arr = new int[0];
	}

	public BetterIntArray(int i) {
		arr = new int[i];
	}

	public BetterIntArray(int[] setupArray) {
		arr = setupArray;
	}

	// Get
	public int valueAt(int index) {
		return arr[index];
	}

	// The Push family of methods
	public void add(int val) {// push
		int[] tempArr = new int[arr.length + 1];
		for (int i = 0; i < arr.length; i++)
			tempArr[i] = arr[i];
		tempArr[arr.length] = val;
		arr = tempArr;
	}

	public void add(int val, int position) {// insert
		int[] tempArr = new int[arr.length + 1];
		for (int i = 0; i < arr.length + 1; i++) {
			if (i < position) {
				tempArr[i] = arr[i];
			} else if (i == position) {
				tempArr[i] = val;
			} else if (i > position) {
				tempArr[i] = arr[i - 1];
			}
		}
		arr = tempArr;
	}

	public void add(int[] vals) {// push multiple
		for (int i = 0; i < vals.length; i++)
			add(vals[i]);
	}

	public void add(int[] vals, int startpos) {// insert multiple
		for (int i = 0; i < vals.length; i++)
			add(vals[i], startpos + i);
	}

	// The Pop family of methods
	public int pop() {// pop last
		int[] tempArr = new int[arr.length - 1];
		int retval = arr[arr.length - 1];
		for (int i = 0; i < arr.length - 1; i++)
			tempArr[i] = arr[i];
		arr = tempArr;
		return retval;
	}

	public int pop(int position) {// indexed pop
		int[] tempArr = new int[arr.length - 1];
		int retval = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i < position)
				tempArr[i] = arr[i];
			else if (i == position)
				retval = arr[i];
			else if (i > position)
				tempArr[i - 1] = arr[i];
		}
		arr = tempArr;
		return retval;
	}

	// The Remove family of methods
	public boolean remove(int val) {
		if (contains(val) > 0) {
			int[] tempArr = new int[arr.length - contains(val)];
			int index = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != val) {
					tempArr[index] = arr[i];
					index++;
				}
			}
			arr = tempArr;
			return true;
		} else
			return false;
	}

	public boolean[] remove(int[] vals) {
		boolean[] flags = new boolean[vals.length];
		for (int i = 0; i < vals.length; i++) {
			flags[i] = remove(vals[i]);
		}
		return flags;
	}

	// Miscellaneous methods
	public void update(int pos, int val) {
		arr[pos] = val;
	}

	public void swap(int pos1, int pos2) {
		int l = arr.length;
		if (pos1 < l && pos1 >= 0 && pos2 < l && pos2 >= 0) {
			int store1 = arr[pos1];
			int store2 = arr[pos2];
			arr[pos2] = store1;
			arr[pos1] = store2;
		}
	}

	public void bubbleSort() { // Bubble sorts
		boolean done = false;
		int checkLength = arr.length;
		while (!done) {
			boolean changing = false;
			for (int i = 0; i < checkLength; i++) {
				if (i + 1 < checkLength && arr[i] > arr[i + 1]) {
					swap(i, i + 1);
					changing = true;
				}
			}
			if (!changing)
				done = true;
			checkLength--;
		}
	}

	public int max() { // max of arr
		int highest = arr[1];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > highest)
				highest = arr[i];
		}
		return highest;
	}

	public int min() { //min of arr
		int lowest = arr[1];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < lowest)
				lowest = arr[i];
		}
		return lowest;
	}

	public int size() {
		return arr.length;
	}

	// Optional methods
	public double average() { // average of values in arr
		int total = 0;
		for (int i = 0; i < arr.length; i++)
			total += arr[i];
		return (double) total / (double) arr.length;
	}

	public int[] toArray() { // or getArray
		return arr;
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

	// Custom Methods
	public int[] indexOf(int val) { // search for a certain value and returns all the positions
		int[] tempArr = new int[contains(val)];
		int index = 0;
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == val) {
				tempArr[index] = i;
				index++;
			}
		return tempArr;
	}

	public void removeDuplicates() { //removes all the duplicates
		BetterIntArray tempBetIntArr = new BetterIntArray(new int[0]);
		for (int i = 0; i < arr.length; i++)
			if (tempBetIntArr.contains(arr[i]) == 0)
				tempBetIntArr.add(arr[i]);
		arr = tempBetIntArr.toArray();
	}

	public void randomize() { //randomizes the array
		Random r = new Random();
		BetterIntArray indecies = new BetterIntArray(new int[0]);
		int[] newArr = new int[arr.length];
		int index;
		for (int i = 0; i < arr.length; i++) {
			index = r.nextInt(arr.length);
			while (indecies.contains(index) != 0)
				index = r.nextInt(arr.length);
			newArr[i] = arr[index];
			indecies.add(index);
		}
		arr = newArr;
	}

	public int contains(int val) { //returns number of vals found in arr
		int numfound = 0;
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == val)
				numfound++;
		return numfound;
	}
}
