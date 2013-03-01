package BlokusGUIs;
/**
 * Shujie Shen/Dream Team
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class UserManagement extends JPanel implements ActionListener
{
	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	JLabel instruction;
	JButton b1, b2, b3, b4;
	JRadioButton gameDesignerButton, systemAdministratorButton;
	JTextField userNameField, passwordField, confirmPasswordField;
	String userNameText = "";
	String passwordText = "";
	String confirmPasswordText = "";
	protected static JFrame frame;

	public UserManagement()
	{
		this.setLayout(null);
		this.setBackground(Color.white);
		instruction = new JLabel("Choose account:");
		instruction.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		String [] currentUserList = {"Alex", "Stacy", "Shujie", "Kemi", "Jack"};
		JComboBox UserList = new JComboBox(currentUserList);
		UserList.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		UserList.setBackground(Color.white);
		UserList.setOpaque(true);
		
		
		b1 = new JButton("Create New Account");
		b1.setHorizontalAlignment(b1.CENTER);
		b1.setFont(new Font("Bodoni MT Black", Font.BOLD, 16));
		b1.setBackground(Color.white);
		b1.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b2 = new JButton("Save");
		b2.setFont(new Font("Bodoni MT Black", Font.BOLD, 18));
		b2.setBackground(Color.white);
		b2.setHorizontalAlignment(b2.CENTER);
		b2.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b3 = new JButton("Delete Account");
		b3.setFont(new Font("Bodoni MT Black", Font.BOLD, 16));
		b3.setBackground(Color.white);
		b3.setHorizontalAlignment(b3.CENTER);
		b3.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(null);
		buttonPane.setBackground(Color.white);
		buttonPane.add(b1);
		b1.setBounds(0, 80, 220, 45);
		buttonPane.add(b2);
		b2.setBounds(200, 0, 100, 45);
		buttonPane.add(b3);
		b3.setBounds(280, 80, 220,45);
		
		
		b4 = new JButton("Back");
		b4.setFont(new Font("Bodoni MT Black", Font.BOLD, 16));
		b4.setBackground(Color.white);
		b4.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b4.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				SystemAdministratorMain SAMain = new SystemAdministratorMain();
				SAMain.ShowSystemAdministratorGUI();
				
		}
		});
	
		
		userNameField = new JTextField(10);
		userNameField.setActionCommand(userNameText);
		userNameField.addActionListener(this);
		
		passwordField = new JTextField(10);
		passwordField.setActionCommand(passwordText);
		passwordField.addActionListener(this);
		
		confirmPasswordField = new JTextField(10);
		confirmPasswordField.setActionCommand(confirmPasswordText);
		confirmPasswordField.addActionListener(this);
		
		JLabel userNameLabel = new JLabel("User Name: ");
		userNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		userNameLabel.setLabelFor(userNameField);
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		passwordLabel.setLabelFor(passwordField);
		JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
		confirmPasswordLabel.setLabelFor(confirmPasswordField);
		confirmPasswordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		JPanel textPane = new JPanel();
		textPane.setBackground(Color.white);
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		textPane.setLayout(gridbag);
		JLabel[] labels = {userNameLabel, passwordLabel, confirmPasswordLabel};
		JTextField [] textFields = {userNameField,passwordField, confirmPasswordField };
		
		addLabelTextRows(labels, textFields, gridbag, textPane);
		
		gameDesignerButton = new JRadioButton("Game Desinger");
		gameDesignerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		gameDesignerButton.setBackground(Color.white);
		gameDesignerButton.setSelected(true);
		gameDesignerButton.addActionListener(this);
		systemAdministratorButton = new JRadioButton("System Administrator");
		systemAdministratorButton.setBackground(Color.white);
		systemAdministratorButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		systemAdministratorButton.addActionListener(this);
		
		ButtonGroup typeButtonGroup = new ButtonGroup();
		typeButtonGroup.add(gameDesignerButton);
		typeButtonGroup.add(systemAdministratorButton);
		
		JPanel radioButtonPane = new JPanel();
		radioButtonPane.setBackground(Color.white);
		radioButtonPane.setLayout(null);
		radioButtonPane.add(gameDesignerButton);
		gameDesignerButton.setBounds(0, 0,160,30);
		radioButtonPane.add(systemAdministratorButton);
		systemAdministratorButton.setBounds(180, 0, 230, 30);
		
		add(instruction);
		instruction.setBounds(70,80,140,30);
		add(UserList);
		UserList.setBounds(220,80,100,30);
		add(textPane);
		textPane.setBounds(50, 100, 400, 120);
		add(radioButtonPane);
		radioButtonPane.setBounds(120, 230, 400, 50);
		add(buttonPane);
		buttonPane.setBounds(50,300,500,200);
		add(b4);
		b4.setBounds(500, 30, 80, 30);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void showUserManagementGUI()
	{
		frame = new JFrame("MANAGE USER ACCOUNT");
		frame.setSize(600, 500); //window size
		frame.setLocation((width -600)/2,(height-500)/2); //window location
		frame.setResizable(false); // fix window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close window, program close
		
		frame.setLayout(null);
		JComponent UMmain = new UserManagement();
		frame.add(UMmain);
		
		frame.setVisible(true);//show window
		frame.setContentPane(UMmain);
	}
	public static void main(String[] args) 
	{
		
		showUserManagementGUI();

	}
	
	private void addLabelTextRows(JLabel[] labels, JTextField[]textFields, GridBagLayout gridbag, Container container)
	{
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		int numLabels = labels.length;
		for(int i=0; i<numLabels; i++)
		{
			c.gridwidth = GridBagConstraints.RELATIVE;
			c.fill = GridBagConstraints.NONE;
			c.weightx = 0.0;
			container.add(labels[i], c);
			
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1.0;
			container.add(textFields[i],c);
		}
	}

}
























