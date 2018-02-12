import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class InventoryHandler {
	public static ArrayList<Item> Items;// I know its not convention, but I love these capitalized variables
	private static ArrayList<Integer> IDs;
	private static ArrayList<String> Names;
	private int itterator;
	private int warehouseItterator;

	public InventoryHandler() {
		Items = new ArrayList<Item>(100);
		IDs = new ArrayList<Integer>(100);
		Names = new ArrayList<String>(100);
		itterator = 1000;
		warehouseItterator = 0;
	}

	public boolean addItem(String name, double PPU) {
		Item tempItem = new Item(name, warehouseItterator, PPU);
		if (Names.contains(name)) {
			tempItem.iD = IDs.get(Names.indexOf(tempItem.name));
			Items.add(tempItem);
			warehouseItterator++;
			return true;
		} else {
			Names.add(tempItem.name);
			IDs.add(itterator);
			tempItem.iD = itterator;
			itterator++;
			Items.add(tempItem);
			warehouseItterator++;
			return false;
		}
	}

	private boolean addItem(Item it) {
		if (Names.contains(it.name)) {
			it.iD = IDs.get(Names.indexOf(it.name));
			Items.add(it);
			return true;
		} else {
			Names.add(it.name);
			IDs.add(it.iD);
			Items.add(it);
			return false;
		}
	}

	public boolean removeItem(int ID) {
		for (Item it : Items) {
			if (it.iD == ID) {
				IDs.remove(it.iD);
				Names.remove(it.name);
				Items.remove(it);
				return true;
			}
		}
		return false;
	}
	public boolean removeItem(String name) {
		for (Item it : Items) {
			if (it.name.equals(name)) {
				Items.remove(it);
				return true;
			}
		}
		return false;
	}

	public boolean forklift(int position1, int position2) {
		if (position1 > Items.size() || position2 > Items.size() || position1 < 0 || position2 < 0)
			return false;
		Item item1 = Items.get(position1);
		Item item2 = Items.get(position2);
		Items.set(position1, item1);
		Items.set(position2, item2);
		return true;
	}

	public String toString() {
		String warehouse = "|";
		for (Item i : Items) {
			warehouse += i.toString() + "|";
		}
		return warehouse;
	}

	public int count() {
		return Items.size();
	}

	public Item getItemAt(int index) {
		return Items.get(index);
	}

	public void saveInventory(String name, String Directory) {
		try {
			File file = new File(Directory + name + ".txt");
			FileWriter fileWriter = new FileWriter(file);
			for (Item it : Items) {
				fileWriter.write(it.toString());
			}
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e + "" + e.getCause());
		}
	}

	public void loadInventory(String name, String Directory) {
		try {
			File file = new File(Directory + name + ".txt");
			Scanner fileScanner = new Scanner(file);
			Items.clear();
			IDs.clear();
			Names.clear();
			while (fileScanner.hasNext()) {
				String input= fileScanner.nextLine();
				String[] inputs = input.split(" ");
				Item tempItem = new Item(Integer.parseInt(inputs[0]),inputs[1],Integer.parseInt(inputs[2]),Double.parseDouble(inputs[3]));
				System.out.println(tempItem);
				this.addItem(tempItem);
				itterator++;
			}
			fileScanner.close();
		} catch (Exception e) {
			System.err.println(e + " " + e.getCause() + " ");
		}
	}
}
