import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumAdd extends JFrame implements ActionListener{
	public int number1;
	public int number2;
	public int result;
	private JTextField num1;
	private JTextField num2;
	private JTextField num3;
	public NumAdd() {
		this.setLayout(new GridLayout(1,2));
		
		JPanel pan1 = new JPanel();
		pan1.setName("Calc");
		pan1.setLayout(new GridLayout(3,2));
		JPanel pan2 = new JPanel();
		pan2.setName("Buttons");
		pan2.setLayout(new GridLayout(4,3));
		JLabel lab1 = new JLabel ("Number 1:");
		lab1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lab2 = new JLabel ("Number 2:");
		lab2.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lab3 = new JLabel ("Result:");
		lab3.setHorizontalAlignment(SwingConstants.CENTER);
		num1= new JTextField("");
		num1.setSize(10,10);
		num1.setEditable(true);
		num2= new JTextField("");
		num2.setSize(10,10);
		num2.setEditable(true);
		num3= new JTextField(result+"");
		num3.setSize(10,10);
		num3.setEditable(false);
		
		
		JButton add = new JButton("Add");
		add.setSize(20,20);
		JButton sub = new JButton("Subtract");
		sub.setSize(20,20);
		JButton mult = new JButton("Multiply");
		mult.setSize(20,20);
		JButton div = new JButton("Divide");
		div.setSize(20,20);
		JButton clear = new JButton("Clear");
		clear.setSize(20,20);
		JButton exit = new JButton("Exit");
		exit.setSize(20,20);
		
		add.addActionListener(this);
		sub.addActionListener(this);
		mult.addActionListener(this);
		div.addActionListener(this);
		clear.addActionListener(this);
		exit.addActionListener(this);
		
		
		this.add(pan1);
		this.add(pan2);
		pan1.add(lab1);
		pan1.add(num1);
		pan1.add(lab2);
		pan1.add(num2);
		pan1.add(lab3);
		pan1.add(num3);
		pan2.add(add);
		pan2.add(sub);
		pan2.add(mult);
		pan2.add(div);
		pan2.add(clear);
		pan2.add(exit);
		
		this.setTitle("thing");
		this.setSize(300, 300);
		this.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e) {
		number1=Integer.parseInt(num1.getText());
		number2=Integer.parseInt(num2.getText());
		String str = e.getActionCommand();	    
 
    if(str.equals("Add")) {
      result=number1+number2;
    } 
    else if(str.equals("Multiply")) {
      result=number1*number2;
    }  
    else if(str.equals("Divide")) {
      result=number1/number2;
    }  
    else if(str.equals("Clear")) {
    		result=0;
    		number1=0;
    		number2=0;
    		num1.setText(number1+"");
    		num2.setText(number2+"");
    		num3.setText(result+"");
    }
    else if(str.equals("Exit")) {
      System.exit(0);
    }
    num3.setText(result+"");
	}
	
	public static void main(String args[]) {
    new NumAdd();                     
  }		
}
