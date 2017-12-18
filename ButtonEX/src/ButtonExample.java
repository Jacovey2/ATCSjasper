import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//type Jframe
public class ButtonExample extends JFrame implements ActionListener {
	
	//Constructor
	public ButtonExample() {                    	    
		this.setLayout(new FlowLayout());
		JButton rb = new JButton("Red");		   
    JButton gb = new JButton("Green");
    JButton ob = new JButton("Orange");
    
    rb.addActionListener(this);		    
    gb.addActionListener(this);
    ob.addActionListener(this);
    
    this.add(rb);
    this.add(gb); 
    this.add(ob); 
 
    this.setTitle("Button in Action");
    this.setSize(300, 80);                      
    this.setVisible(true);			    
  }
			                   
  public void actionPerformed(ActionEvent e) {
    String str = e.getActionCommand();	    
    System.out.println("You clicked " + str + " button");
 
    if(str.equals("Red")) {
      this.getContentPane().setBackground(Color.GREEN);
    } 
    else if(str.equals("Green")) {
      this.getContentPane().setBackground(Color.ORANGE);
    }  
    else if(str.equals("Orange")) {
      this.getContentPane().setBackground(Color.RED);
    }
  }
  public static void main(String args[]) {
    new ButtonExample();                     
  }		
}