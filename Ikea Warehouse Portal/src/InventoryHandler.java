import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class InventoryHandler {
	private static ArrayList<Item> Items;
	private static ArrayList<Integer> IDs;
	private static ArrayList<String> Names;
	private int itterator;
	public InventoryHandler(){
			Items = new ArrayList<Item>(100);
			IDs = new ArrayList<Integer>(100);
			Names = new ArrayList<String>(100);
			itterator=1000;
	}
	public boolean addItem(Item item) {
		if(Names.contains(item.name)) {
			item.iD=IDs.get(Names.indexOf(item.name));
			Items.add(item);
			return true;
		}
		else {
			Names.add(item.name);
			IDs.add(itterator);
			item.iD=itterator;
			itterator++;
			Items.add(item);
			return false;
		}
	}
	public boolean removeItem(int ID) {
		for (Item it: Items) {
			if (it.iD==ID) {
				IDs.remove(it.iD);
				Names.remove(it.name);
				Items.remove(it);
				return true;
			}
		}
		return false;
	}
	public boolean forklift(int position1, int position2) {
		if (position1>Items.size() || position2>Items.size() || position1<0 || position2<0)
			return false;
		Item item1=Items.get(position1);
		Item item2=Items.get(position2);
		Items.set(position1, item1);
		Items.set(position2, item2);
		return true;
	}
	
	public String toString() {
		String warehouse="|";
		for(Item i:Items) {
			warehouse+=i.toString()+"|";
		}
		return warehouse;
	}
	public int count() {
		return Items.size();
	}
	public Item getItemAt(int index) {
		return Items.get(index);
	}
	
	public void saveInventory(String name, String Directory){
		try {
			File file = new File(Directory + name + ".txt");
			FileWriter fileWriter = new FileWriter(file);
			for (Item it : Items) {
				fileWriter.write("");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
