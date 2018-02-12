public class Item {
	public int iD;
	public String name;
	public int location;
	public double PPU;
	
	public Item(String Name, int Location, double PricePerUnit) {
		name=Name;
		location=Location;
		PPU=PricePerUnit;
	}
	public Item(int ID, String Name, int Location, double PricePerUnit) {
		iD=ID;
		name=Name;
		location=Location;
		PPU=PricePerUnit;
	}
	
	public String toString() {
		return iD+" "+name+" "+location+" "+PPU+"\n";
	}
	
}
