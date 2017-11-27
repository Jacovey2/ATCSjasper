import java.awt.*;
import java.util.Random;

public class House {
	public static void drawHouse(Graphics g) {

		//Roofs
		drawRoof(50, g, 250, 100);
		drawRoof(50, g, 650, 100);
		
		//Structure
		//left
		drawBlock(100, g, 250, 200);
		drawBlock(100, g, 250, 300);
		drawBlock(100, g, 250, 400);
		
		//middle
		drawBlock(100, g, 450, 300);
		drawBlock(15, g, 450, 400);
		
		//right
		drawBlock(100, g, 650, 200);
		drawBlock(100, g, 650, 300);
		drawBlock(100, g, 650, 400);
		
		//Floors
		drawFloor(15, g, 150, 510, 30);
		drawFloor(15, g, 350, 510, 30);
		drawFloor(15, g, 550, 510, 30);
		
		//Railings
		drawFloor(3, g, 350, 300, 20);
		drawFloor(3, g, 350, 400, 20);
		
		drawFloor(5, g, 150, 200, 10);
		drawFloor(5, g, 550, 200, 10);
		
		//Windows
		drawWindow(g, 230, 225);
		drawWindow(g, 630, 225);
	}

	public static void drawWindow(Graphics g, int x, int y) {
		Random r = new Random();
		g.clearRect(x, y, 40, 80);
		// Top
		for (int i = 0; i < 3; i++)
			g.drawLine(x + (r.nextInt(10) - 5), y + (r.nextInt(10) - 5), x + 40 + (r.nextInt(10) - 5),
					y + (r.nextInt(10) - 5));
		// Bottom
		for (int i = 0; i < 3; i++)
			g.drawLine(x + (r.nextInt(10) - 5), y + 80 + (r.nextInt(10) - 5), x + 40 + (r.nextInt(10) - 5),
					y + 80 + (r.nextInt(10) - 5));

	}

	public static void drawRoof(int itterations, Graphics g, int x, int y) {
		Random r = new Random();
		for (int i = 0; i < itterations; i++) {
			g.drawLine(x + (r.nextInt(20) - 10), y + (r.nextInt(20) - 10), x + (r.nextInt(200) - 100),
					y + 100 + (r.nextInt(20) - 10));
		}
	}

	public static void drawFloor(int itterations, Graphics g, int x, int y, int spread) {
		Random r = new Random();
		for (int i = 0; i < itterations; i++) {
			g.drawLine(x + (r.nextInt(spread) - (spread / 2)), y + (r.nextInt(spread) - (spread / 2)),
					x + 200 + (r.nextInt(spread) - (spread / 2)), y + (r.nextInt(spread) - (spread / 2)));
		}
	}

	public static void drawBlock(int itterations, Graphics g, int x, int y) {
		Random r = new Random();
		for (int i = 0; i < itterations; i++) {
			int seed = (r.nextInt(200) - 100);
			g.drawLine(x + seed, y + (r.nextInt(20) - 10), x + seed + (r.nextInt(30) - 15), y + 100 + (r.nextInt(20) - 10));
		}
	}
}
