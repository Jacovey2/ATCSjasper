import java.awt.*;

public class Car {
	int x;
	int y;
	int number;
	Color c;
	
	//For instantiating a unique car (has no number)
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
	//For instantiating a normal car (has a number)
	public Car (int num, int xpos, int ypos, Color color, Graphics g) {
		x=xpos;
		y=ypos;
		c=color;
		number=num;
		g.setColor(color);
		g.fillRect(xpos, ypos, 200, 100);
		g.setColor(Color.black);
		g.setFont(new Font("Veranda",Font.BOLD, 50));
		g.drawString(num+"", xpos+75, ypos+50);
		g.fillOval(xpos+10, ypos+75, 50, 50);
		g.fillOval(xpos+140, ypos+75, 50, 50);
	}
	
}
