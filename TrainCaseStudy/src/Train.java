import java.awt.*;
import java.util.Random;

public class Train {
	public Train(int length, Graphics g) {
		Random r= new Random();
		Engine e = new Engine(100,100,RandomColor(), g);
		
		//Make a list of the intermediate cars
		Car[] cs = new Car[length];
		for(int i=1; i<length-1; i++) {
			cs[i]= new Car(100+(200*(i)),100,RandomColor(), g);
		}
		//Make the caboose
		Caboose c = new Caboose(100+(200*(length-1)),100,RandomColor(), g);
	}
	//Create a random color
	private Color RandomColor() {
		Random r = new Random();
		return new Color(r.nextInt(254),r.nextInt(254),r.nextInt(254));
	}
}
