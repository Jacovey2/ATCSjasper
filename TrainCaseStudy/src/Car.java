import java.awt.*;

public class Car {
	int x;
	int y;
	Color c;
	public Car (int xpos, int ypos, Color color, Graphics g) {
		x=xpos;
		y=ypos;
		c=color;
		g.setColor(color);
		g.fillRect(xpos, ypos, 200, 100);
		g.setColor(Color.black);
		g.fillOval(xpos+10, ypos+75, 50, 50);
		g.fillOval(xpos+140, ypos+75, 50, 50);
	}
}
