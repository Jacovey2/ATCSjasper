import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public class Cone {
	int x;
	int y;
	int length;
	String type;
	List<Scoop> scoops;
	
	public Cone(int xPos, int yPos, int Length, Graphics g) {
		x=xPos;
		y=yPos;
		length=Length;
		Type="Cone";
		Polygon p= new Polygon();
		p.addPoint(xPos, yPos);
		p.addPoint(xPos+100, yPos);
		p.addPoint(xPos+50, yPos+150);
		g.drawPolygon(p);
	}
	
	public Cone(int xPos, int yPos, int Length, String Type, Graphics g) {
		x=xPos;
		y=yPos;
		length=Length;
		type=Type;
		Polygon p= new Polygon();
		if (Type.equals("cone")) {
			p.addPoint(xPos, yPos);
			p.addPoint(xPos+100, yPos);
			p.addPoint(xPos+50, yPos+150);
			g.drawPolygon(p);
		}
		if (Type.equals("cup")) {
			p.addPoint(xPos, yPos);
			p.addPoint(xPos+100, yPos);
			p.addPoint(xPos+15, yPos+50);
			p.addPoint(xPos+85, yPos+50);
			g.drawPolygon(p);
		}
	}
}