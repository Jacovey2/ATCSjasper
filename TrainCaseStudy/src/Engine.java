import java.awt.*;

public class Engine extends Car{
	public Engine(int x, int y, Color c, Graphics g) {
		super(x,y,c,g);
		g.setColor(c);
		g.clearRect(x, y, 125, 30);
		g.clearRect(x+135, y+10, 20, 30);
		g.clearRect(x+195, y, 5, 10);
		g.clearRect(x+180, y+10, 20, 40);
		g.fillRect(x+20, y-10, 25, 50);
		int[] xposs= {x,x-30,x};
		int[] yposs= {y+110,y+110,y+80};
		g.fillPolygon(new Polygon(xposs,yposs,3));
		g.fillOval(x-10, y+30, 20, 50);
		g.setColor(Color.black);
		g.drawOval(x-10, y+30, 20, 50);
		g.fillOval(x+100, y+85, 40, 40);
	}
}
