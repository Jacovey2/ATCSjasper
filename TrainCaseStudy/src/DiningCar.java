import java.awt.Color;
import java.awt.Graphics;

public class DiningCar extends Car{
	public DiningCar(int x, int y, Color c, Graphics g) {
		super(x,y,c,g);
		g.setColor(c);
		g.fillRect(x+50, y-15, 100, 15);
		g.clearRect(x+15, y+15, 25, 50);
		g.clearRect(x+50, y+15, 25, 50);
		g.clearRect(x+85, y+15, 25, 50);
		g.clearRect(x+120, y+15, 25, 50);
		g.clearRect(x+155, y+15, 25, 50);
		g.setColor(Color.black);
	}
}
