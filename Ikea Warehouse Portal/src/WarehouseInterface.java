import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WarehouseInterface extends JFrame implements ActionListener {
	String State = "CustomerLogin";
	public String loginStr;
	private JTextField LoginStrTF;
	private JTextField NameField;
	private JTextField LocationField;
	private JTextField PPUField;
	private JTextField Location1Field;
	private JTextField Location2Field;
	public static InventoryHandler IH;
	public WarehouseInterface (String state) {		
		if (!state.equals("")) {
		State=state;
		}
		//Login Page
		if(State.equals("CustomerLogin")) {
			//Setup
			this.setLayout(new GridLayout(3,1));

			//Login Buttons
			JButton lo = new JButton("Login as Customer");
			JButton lom = new JButton("Switch to Manager login");
	    JButton ex = new JButton("Exit");
	    
	    //Listeners
	    lo.addActionListener(this);		 
	    lom.addActionListener(this);	
	    ex.addActionListener(this);
	    
	    //Adding all components to the GUI
	    this.add(lo);
	    this.add(lom);
	    this.add(ex); 
		}
		else if (State.equalsIgnoreCase("ManagerLogin")) {
			//Setup
			this.setLayout(new GridLayout(4,1));
			
			//Login Buttons
			JButton lo = new JButton("Login as Manager");
			JButton loc = new JButton("Switch to Customer login");
	    JButton ex = new JButton("Exit");
	    
	    //Listeners
	    lo.addActionListener(this);		  
	    loc.addActionListener(this);
	    ex.addActionListener(this);
	    
			//Panels
			JPanel pan1= new JPanel();
			
			//Login String Text Field
	    LoginStrTF = new JPasswordField(20);
	    LoginStrTF.setSize(100,100);
	    LoginStrTF.setEditable(true);
	    
	    //Adding all components to the GUI
	    this.add(pan1);
	    pan1.add(LoginStrTF);
	    this.add(lo);
	    this.add(loc);
	    this.add(ex); 
		}
		else if (State.equals("Manager")) {
			this.setLayout(new GridLayout(1,2));
			JPanel InterfacesPanel = new JPanel();
			JPanel AddingPanel = new JPanel();
			JPanel MovingPanel = new JPanel();
			JPanel ItemsPanel = new JPanel();
			//AddingFields
			JLabel NameFieldLabel =  new JLabel("Name:");
			NameField = new JTextField();
			JLabel LocationFieldLabel =  new JLabel("Location:");
			LocationField = new JTextField();
			JLabel PPUFieldLabel =  new JLabel("PPU:");
			PPUField = new JTextField();
			JButton Add = new JButton("Add");
			//MovingFields
			JLabel Loc1FieldLabel =  new JLabel("Location 1:");
			Location1Field = new JTextField();
			JLabel Loc2FieldLabel =  new JLabel("Location 2:");
			Location2Field = new JTextField();
			JButton Move = new JButton("Move");
			//Adding
			AddingPanel.setLayout(new GridLayout(7,1));
			AddingPanel.add(NameFieldLabel);
			AddingPanel.add(NameField);
			AddingPanel.add(LocationFieldLabel);
			AddingPanel.add(LocationField);
			AddingPanel.add(PPUFieldLabel);
			AddingPanel.add(PPUField);
			Add.addActionListener(this);		  
			AddingPanel.add(Add);
			//Moving
			MovingPanel.setLayout(new GridLayout(5,1));
			MovingPanel.add(Loc1FieldLabel);
			MovingPanel.add(Location1Field);
			MovingPanel.add(Loc2FieldLabel);
			MovingPanel.add(Location2Field);
			Move.addActionListener(this);
			MovingPanel.add(Move);
			//Merging
			InterfacesPanel.setLayout(new GridLayout(2,1));
			InterfacesPanel.add(AddingPanel);
			InterfacesPanel.add(MovingPanel);
			//Items
			ItemsPanel.setLayout(new BoxLayout(ItemsPanel,1));
			for (int i=0; i<IH.count(); i++) {
				JLabel tempLabel = new JLabel((i+1)+" - "+IH.getItemAt(i).toString());
				tempLabel.setBorder(BorderFactory.createLineBorder(Color.black));
				ItemsPanel.add(new JLabel((i+1)+" - "+IH.getItemAt(i).toString()));
			}
			//Borders
			InterfacesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			ItemsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			AddingPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			MovingPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			//Final Pasting
			this.add(InterfacesPanel);
			this.add(ItemsPanel);
		}
		else if (State.equals("Customer")) {
			this.setLayout(new GridLayout(4,1));
			this.add(new JLabel("Ikea Warehouse", SwingConstants.CENTER));
			//Top Panel
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new GridLayout(1,5));
			topPanel.add(new JLabel("Name"));
			topPanel.add(new JLabel("Price"));
			topPanel.add(new JLabel("ID"));
			topPanel.add(new JLabel("Location"));
			topPanel.add(new JLabel("Buy Option"));
			this.add(topPanel);
			JPanel bottomPanels = new JPanel();
			bottomPanels.setLayout(new GridLayout(2,1));
			JPanel bottomPanel1 = new JPanel();
			JPanel bottomPanel2 = new JPanel();
			bottomPanel1.setLayout(new BoxLayout(bottomPanel1,1));
			bottomPanel2.setLayout(new BoxLayout(bottomPanel2,1));
			for (int i=0; i<IH.count(); i++) {
				JLabel tempLabel = new JLabel((i+1)+" - "+IH.getItemAt(i).toString());
				tempLabel.setBorder(BorderFactory.createLineBorder(Color.black));
				bottomPanel1.add(new JLabel((i+1)+" - "+IH.getItemAt(i).toString()));
				JButton tempButton = new JButton ("Buy "+IH.getItemAt(i).name);
				tempButton.addActionListener(this);
				bottomPanel2.add(tempButton);
			}
			bottomPanels.add(bottomPanel1);
			bottomPanels.add(bottomPanel2);
			this.add(bottomPanels);
			
			JButton menuExit = new JButton("Exit to menu");
			menuExit.addActionListener(this);
			this.add(menuExit);
		}
		
		//Window Setup Final
		this.setTitle("Warehouse Interface");
    this.setSize(500, 500);                      
    this.setVisible(true);	
	}
	
	public static void main (String[] args) {
		new WarehouseInterface("");
		IH = new InventoryHandler();
	}
	
	public void actionPerformed(ActionEvent e) {
    String str = e.getActionCommand();	    
    System.out.println("You clicked " + str + " button");
 
    if(str.equals("Login as Customer")) {
  		this.dispose();
    		new WarehouseInterface("Customer");
    } 
    else if (str.equals("Switch to Manager login")) {
    		this.dispose();
    		new WarehouseInterface("ManagerLogin");
    }
    else if (str.equals("Login as Manager")) {
    		if (LoginStrTF.getText().equals("password123")) {//Very secure password
	  			this.dispose();
	    		new WarehouseInterface("Manager");
    		}
    }
    else if (str.equals("Switch to Customer login")) {
  		this.dispose();
    		new WarehouseInterface("CustomerLogin");
    }
    else if (str.equals("Add")) {
    		IH.addItem(new Item(NameField.getText(),Integer.parseInt(LocationField.getText()),Double.parseDouble(PPUField.getText())));
    		this.dispose();
    		new WarehouseInterface("Manager");
    }
    else if(str.equals("Move")) {
    		IH.forklift(Integer.parseInt(Location1Field.getText()), Integer.parseInt(Location2Field.getText()));
    		this.dispose();
    		new WarehouseInterface("Manager");
    }
    else if(str.equals("Exit")) {
    		System.exit(0);
    }  
    System.out.println(State);
  }
}
