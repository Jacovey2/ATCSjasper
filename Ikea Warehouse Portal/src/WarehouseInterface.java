import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private String State = "CustomerLogin";
	//Constants (Change for your system)
	static String DATABASENAME = "InventoryDatabase";
	static String DATABASEDIRECTORY = "/Users/jacovey/Documents/WarehouseProject/";
	//Login String
	public String loginStr;
	//text fields (must be globally accessible)
	private JTextField LoginStrTF;
	private JTextField NameField;
	private JTextField PPUField;
	private JTextField Location1Field;
	private JTextField Location2Field;
	//Inventory Handler (aka "Container")
	public static InventoryHandler IH;
	
	public WarehouseInterface (String state) {	
		//Defaults to customer login, but can be put into another mode by instantiating it with a state variable
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
			//Manger logins
			this.setLayout(new GridLayout(1,2));
			JPanel NavigationPanel = new JPanel();
			JPanel InterfacesPanel = new JPanel();
			JPanel AddingPanel = new JPanel();
			JPanel MovingPanel = new JPanel();
			JPanel ItemsPanel = new JPanel();
			
			//TopButtons
			JButton backToLogin = new JButton("Exit to menu");
			JButton quitOut = new JButton("Exit");
			backToLogin.addActionListener(this);
			quitOut.addActionListener(this);
			NavigationPanel.add(backToLogin);
			NavigationPanel.add(quitOut);
			
			//AddingFields
			JLabel NameFieldLabel =  new JLabel("Name:");
			NameField = new JTextField();
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
			InterfacesPanel.setLayout(new GridLayout(3,1));
			InterfacesPanel.add(NavigationPanel);
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
			this.setLayout(new GridLayout(3,1));
			//Top Panel
			JPanel topPanel = new JPanel();
			topPanel.setLayout(new GridLayout(2,1));
			topPanel.add(new JLabel("Ikea Warehouse", SwingConstants.CENTER));
			JPanel tableHeader= new JPanel();
			tableHeader.setLayout(new GridLayout(1,5));
			tableHeader.add(new JLabel("Name"));
			tableHeader.add(new JLabel("Price"));
			tableHeader.add(new JLabel("ID"));
			tableHeader.add(new JLabel("Location"));
			tableHeader.add(new JLabel("Buy Option"));
			topPanel.add(tableHeader);
			this.add(topPanel);
			JPanel bottomPanels = new JPanel();
			bottomPanels.setLayout(new GridLayout(1,2));
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new BoxLayout(bottomPanel,1));
			//List of items (long grid layout)
			for (int i=0; i<IH.count(); i++) {
				JPanel tempPanel = new JPanel();
				tempPanel.setLayout(new GridLayout(1,5));
				tempPanel.add(new JLabel(IH.getItemAt(i).iD+""));
				tempPanel.add(new JLabel(IH.getItemAt(i).name));
				tempPanel.add(new JLabel(IH.getItemAt(i).location+""));
				tempPanel.add(new JLabel(IH.getItemAt(i).PPU+""));
				JButton tempButton = new JButton ("Buy "+IH.getItemAt(i).name);
				tempButton.addActionListener(this);
				tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				tempPanel.add(tempButton);
				bottomPanel.add(tempPanel);
			}
			bottomPanels.add(bottomPanel);
			this.add(bottomPanels);
			
			//Exit button
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
		IH = new InventoryHandler();//stays constant
	}
	
	public void actionPerformed(ActionEvent e) {
    String str = e.getActionCommand();	    
 
    if(str.equals("Login as Customer")) {//Customer Login
  			this.dispose();
  			IH.loadInventory(DATABASENAME, DATABASEDIRECTORY);
    		new WarehouseInterface("Customer");
    } 
    else if (str.equals("Switch to Manager login")) {//Switch to Manager Login 
    		this.dispose();
    		new WarehouseInterface("ManagerLogin");
    }
    else if (str.equals("Login as Manager")) {
    		if (LoginStrTF.getText().equals("password123")) {//Very secure password
	  			this.dispose();
	  			IH.loadInventory(DATABASENAME, DATABASEDIRECTORY);
	    		new WarehouseInterface("Manager");
    		}
    }
    else if (str.equals("Switch to Customer login")) {//Switch to Customer Login 
  		this.dispose();
    		new WarehouseInterface("CustomerLogin");
    }
    else if (str.equals("Add")) { //Manager Add Button 
    		IH.addItem(NameField.getText(),Double.parseDouble(PPUField.getText()));
    		this.dispose();
    		new WarehouseInterface("Manager");
    }
    else if(str.equals("Move")) { //Manager Move Button 
    		IH.forklift(Integer.parseInt(Location1Field.getText()), Integer.parseInt(Location2Field.getText()));
    		this.dispose();
    		new WarehouseInterface("Manager");
    }
    else if(str.equals("Exit to menu")) { //Exit to Menu Button 
    		IH.saveInventory(DATABASENAME, DATABASEDIRECTORY);
    		this.dispose();
    		new WarehouseInterface("CustomerLogin");
    }  
    else if(str.startsWith("Buy")) {//Buying Button(s?)
    		IH.removeItem(str.substring(4));
    		this.dispose();
    		new WarehouseInterface("Customer");
    }
    else if(str.equals("Exit")) {//Exit App Button
    		IH.saveInventory(DATABASENAME, DATABASEDIRECTORY);
    		System.exit(0);
    }  
  }
}
