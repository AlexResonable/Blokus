package systemAdministrator;
/**
 * Shujie Shen/Dream Team
 */
import application.UserFunctions;
import javax.swing.ButtonModel;
import systemAdministrator.SystemAdministratorMain;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class UserManagement extends JPanel implements ActionListener
{
	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	private JLabel instruction;
	private JButton b1, b2, b3, b4;
	private JRadioButton gameDesignerButton, systemAdministratorButton;
	private JTextField userNameField;
        private JLabel errorField = new JLabel(" ");
        private JComboBox userList;
	private String userNameText = "";
	private JPasswordField passwordField = new JPasswordField(15);
        private JPasswordField confirmPasswordField = new JPasswordField(15);
        ButtonGroup typeButtonGroup = new ButtonGroup();
	protected static JFrame frame;
        UserFunctions lf = new UserFunctions();

	public UserManagement()
	{
                HashMap<String, String> users = lf.getUsernames();
                int i = 0;
		this.setLayout(null);
		this.setBackground(Color.white);
		instruction = new JLabel("Choose account:");
		instruction.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
                ArrayList currentUserList = new ArrayList();
                currentUserList.add("");
		for (String value : users.values()) {
                    currentUserList.add(value);
                    ++i;
                }
		userList = new JComboBox(currentUserList.toArray());
		userList.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		userList.setBackground(Color.white);
		userList.setOpaque(true);
                userList.addActionListener(this);
		
		
		b1 = new JButton("Create New Account");
		b1.setHorizontalAlignment(b1.CENTER);
		b1.setFont(new Font("Bodoni MT Black", Font.BOLD, 16));
		b1.setBackground(Color.white);
		b1.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
                b1.addActionListener(this);
		b2 = new JButton("Save");
		b2.setFont(new Font("Bodoni MT Black", Font.BOLD, 18));
		b2.setBackground(Color.white);
		b2.setHorizontalAlignment(b2.CENTER);
		b2.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
                b2.addActionListener(this);
                b2.setEnabled(false);
		b3 = new JButton("Delete Account");
		b3.setFont(new Font("Bodoni MT Black", Font.BOLD, 16));
		b3.setBackground(Color.white);
		b3.setHorizontalAlignment(b3.CENTER);
		b3.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
                b3.addActionListener(this);
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
                userNameField.setEnabled(false);

                passwordField.setEnabled(false);
                confirmPasswordField.setEnabled(false);
		errorField.setEnabled(false);
                
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
                c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.0;
                c.insets = new Insets(5,0,0,0);
		textPane.add(errorField, c);
                c.insets = new Insets(0,0,0,0);
                c.gridwidth = GridBagConstraints.RELATIVE;
		
		gameDesignerButton = new JRadioButton("Game Designer");
		gameDesignerButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		gameDesignerButton.setBackground(Color.white);
		gameDesignerButton.setSelected(true);
		gameDesignerButton.addActionListener(this);
		systemAdministratorButton = new JRadioButton("System Administrator");
		systemAdministratorButton.setBackground(Color.white);
		systemAdministratorButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		systemAdministratorButton.addActionListener(this);
                gameDesignerButton.setEnabled(false);
                systemAdministratorButton.setEnabled(false);
		
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
		add(userList);
		userList.setBounds(220,80,100,30);
                
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
	public void actionPerformed(ActionEvent ae) {
            // TODO Auto-generated method stub
            if(ae.getSource() == userList){
                String user = (String)userList.getSelectedItem();
                System.out.println(user);
                if(user.equals("")){
                    userNameField.setText("");
                    userNameField.setEnabled(false);
                    passwordField.setText("");
                    passwordField.setEnabled(false);
                    confirmPasswordField.setText("");
                    confirmPasswordField.setEnabled(false);
                    gameDesignerButton.setEnabled(false);
                    systemAdministratorButton.setEnabled(false);
                    b2.setEnabled(false);
                    b3.setEnabled(false);
                }
                else{
                    String[] userInfo = new String[3];
                    userInfo = lf.getUserInfo(user);
                    userNameField.setText(userInfo[0]);
                    userNameField.setEnabled(true);
                    passwordField.setText(userInfo[1]);
                    passwordField.setEnabled(true);
                    confirmPasswordField.setText(userInfo[1]);
                    confirmPasswordField.setEnabled(true);
                    gameDesignerButton.setEnabled(true);
                    systemAdministratorButton.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    if(userInfo[2].equals("SA")){
                        systemAdministratorButton.setSelected(true);
                    }
                    else{
                        gameDesignerButton.setSelected(true);
                    }
                }
            }
            if(ae.getSource() == b2){
                String oldUsername = userList.getSelectedItem().toString();
                String userName = userNameField.getText();
                char password[] = passwordField.getPassword();
                char confirmPassword[] = confirmPasswordField.getPassword();
                String pw = new String(password);
                String cpw = new String(confirmPassword);
                String role;
                if(gameDesignerButton.isSelected())
                    role = "DA";
                else
                    role = "SA";
                
                if(!pw.equals(cpw)){
                    errorField.setText("<HTML><FONT COLOR = Red>Passwords must match</FONT></HTML>");
                }
                else if(pw.equals("") || cpw.equals("") || userName.equals("")){
                    errorField.setText("<HTML><FONT COLOR = Red>Username/password cannot be blank</FONT></HTML>");
                }
                else{
                    Boolean saved = lf.updateUser(oldUsername, userName, pw, role);
                    if(saved){
                        if(!oldUsername.equals(userName))
                        {
                            if(!oldUsername.equals("")){
                                userList.removeItemAt(userList.getSelectedIndex());
                            }
                            userList.addItem(userName);
                            userList.setSelectedItem(userName);
                        }
                        errorField.setText("<HTML><FONT COLOR = Green>User has been saved!</FONT></HTML>");
                    }
                    else{
                        errorField.setText("<HTML><FONT COLOR = Red>User has not been saved!</FONT></HTML>");
                    }
                }
            }
            if(ae.getSource() == b1){
                userList.setSelectedIndex(0);
                gameDesignerButton.setSelected(true);
                userNameField.setEnabled(true);
                passwordField.setEnabled(true);
                confirmPasswordField.setEnabled(true);
                systemAdministratorButton.setEnabled(true);
                gameDesignerButton.setEnabled(true);
                b2.setEnabled(true);
            }
            if(ae.getSource() == b3){
                String userName = userList.getSelectedItem().toString();
                Boolean deleted = lf.deleteUser(userName);
                if(deleted){
                    userList.removeItemAt(userList.getSelectedIndex());
                    userList.setSelectedIndex(0);
                    b3.setEnabled(false);
                    errorField.setText("<HTML><FONT COLOR = Green>User has been deleted!</FONT></HTML>");
                }
                else{
                    errorField.setText("<HTML><FONT COLOR = Red>User has not been deleted!</FONT></HTML>");   
                }
            }
           
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
























