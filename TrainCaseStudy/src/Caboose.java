import java.awt.*;

public class Caboose extends Car{
	public Caboose(int x, int y, Color c, Graphics g) {
		super(x,y,c,g);
		g.setColor(c);
		g.fillRect(x+50, y-15, 100, 15);
		g.setColor(Color.black);
	}
}
