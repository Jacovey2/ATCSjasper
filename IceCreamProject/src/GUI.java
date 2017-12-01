import java.awt.*;
import java.applet.*;

public class GUI extends Applet{
	public void init() {
		setSize(new Dimension(500,500));
	}
	
	public void paint(Graphics g) {
		Cone c= new Cone(200,500,0);
	}
	
}