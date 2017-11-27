import java.awt.*;

public class Engine extends Car{
	public Engine(int x, int y, Color c, Graphics g) {
		super(x,y,c,g);
		g.setColor(c);
		g.fillRect(x+150, y-50, 25, 50);
		g.clearRect(x, y, 70, 40);
		int[] xposs= {x,x-40,x};
		int[] yposs= {y+100,y+100,y+50};
		g.fillPolygon(new Polygon(xposs,yposs,3));
		g.setColor(Color.black);
	}
}
