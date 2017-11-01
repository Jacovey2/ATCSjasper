
public class BetterIntArray {
	private int[] arr;

	public BetterIntArray() {
		arr = new int[0];
	}

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

	public void swap(int pos1, int pos2) {
		int l = arr.length;
		if (pos1 < l && pos1 >= 0 && pos2 < l && pos2 >= 0) {
			int store1 = arr[pos1];
			int store2 = arr[pos2];
			arr[pos2] = store1;
			arr[pos1] = store2;
		}
	}

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
		BetterIntArray tempBetIntArr = new BetterIntArray(new int[0]);
		for (int i = 0; i < arr.length; i++)
			if (tempBetIntArr.contains(arr[i]) == 0)
				tempBetIntArr.add(arr[i]);
		arr = tempBetIntArr.toArray();
	}
}
