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
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ModifyUserAccount extends JPanel implements ActionListener
{
	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	private JLabel header;
	private JLabel comboBoxLabel;
	private JButton b1, saveButton, b3, backButton;
	private JRadioButton gameDesignerButton, systemAdministratorButton;
	private JTextField userNameField;
	private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JLabel errorField = new JLabel(" ");
    private JComboBox userList;
	private String userNameText = "";
	
    ButtonGroup typeButtonGroup = new ButtonGroup();
    private JPanel innerPane;
	protected static JFrame frame;
    UserFunctions lf = new UserFunctions();

	public ModifyUserAccount()
	{
		this.setLayout(null);
		
        header = new JLabel("Modify User Account");
        header.setFont(new Font("Arial", Font.PLAIN, 24));
        
        comboBoxLabel = new JLabel("Choose Account:");
		HashMap<String, String> users = lf.getUsernames();
        ArrayList currentUserList = new ArrayList();
        currentUserList.add("");
        int i = 0;
		for (String value : users.values()) 
		{
            currentUserList.add(value);
            ++i;
        }
		userList = new JComboBox(currentUserList.toArray());
		userList.setOpaque(true);
        userList.addActionListener(this);
		
        
		saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setEnabled(false);
       
		
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		
	
		// user name field
		userNameField = new JTextField(10);
		userNameField.setActionCommand(userNameText);
        userNameField.setEnabled(false);
        JLabel userNameLabel = new JLabel("User Name: ");
		userNameLabel.setLabelFor(userNameField);

		// password field
		passwordField = new JPasswordField(10);
        passwordField.setEnabled(false);
		errorField.setEnabled(false);
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setLabelFor(passwordField);
		
		// confirm password field
		confirmPasswordField = new JPasswordField(10);
		confirmPasswordField.setEnabled(false);
		JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
		confirmPasswordLabel.setLabelFor(confirmPasswordField);
        
		// add fields and their label together
		JPanel textPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		textPane.setLayout(gridbag);
		
		JLabel[] labels = {userNameLabel, passwordLabel, confirmPasswordLabel};
		JTextField [] textFields = {userNameField,passwordField, confirmPasswordField };
                		
		addLabelTextRows(labels, textFields, gridbag, textPane);
       
		// Radio button "game designer"
		gameDesignerButton = new JRadioButton("Game Designer");
		gameDesignerButton.setSelected(true);
		gameDesignerButton.addActionListener(this);
		gameDesignerButton.setEnabled(false);
		
		// Radio button "system administrator"
		systemAdministratorButton = new JRadioButton("System Administrator");
		systemAdministratorButton.addActionListener(this);
        systemAdministratorButton.setEnabled(false);
		
        // group two radio buttons and put them on radioButtonPane
		typeButtonGroup.add(gameDesignerButton);
		typeButtonGroup.add(systemAdministratorButton);
		
		JPanel radioButtonPane = new JPanel();
		radioButtonPane.setLayout(null);
		radioButtonPane.add(gameDesignerButton);
		gameDesignerButton.setBounds(0,0,120,30);
		radioButtonPane.add(systemAdministratorButton);
		systemAdministratorButton.setBounds(130, 0, 200, 30);
		
		
		// create an inner pane, put everything, except header and "back" button, on it
		innerPane = new JPanel();
		innerPane.setLayout(null);
		Border borderBlack = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.lightGray, Color.blue);
	    innerPane.setBorder(borderBlack);
	    innerPane.add(comboBoxLabel);
	    comboBoxLabel.setBounds(63,10,100,30);
		
	    innerPane.add(userList);
	    userList.setBounds(170,10,100,30);
	    
	    innerPane.add(textPane);
	    textPane.setBounds(50, 45, 300, 80);
	    
	    innerPane.add(radioButtonPane);
	    radioButtonPane.setBounds(65, 135, 330, 50);

	    // add header, innerpane, "back" button
	    add(header);
		header.setBounds(40,10, 400, 40 );
		add(innerPane);
		innerPane.setBounds(40, 50,400,200);
	
		add(backButton);
		backButton.setBounds(40, 270, 80, 30);
		
		add(saveButton);
		saveButton.setBounds(360, 270,80, 30 );
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
                    saveButton.setEnabled(false);
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
                    saveButton.setEnabled(true);
                    b3.setEnabled(true);
                    if(userInfo[2].equals("SA")){
                        systemAdministratorButton.setSelected(true);
                    }
                    else{
                        gameDesignerButton.setSelected(true);
                    }
                }
            }
            if(ae.getSource() == saveButton){
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
                saveButton.setEnabled(true);
            }
            if(ae.getSource() == b3)
            {
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
             if(ae.getSource() == backButton)
             {
            	frame.dispose();
 				ManageUserAccount MUA = new ManageUserAccount();
 				MUA.createManageUserAccountGUI();
             }
           
        }
        
	public static void showModifyUserAccountGUI()
	{
		frame = new JFrame("Manage user account");
		frame.setSize(500, 360); //window size
		frame.setLocation((width -500)/2,(height-360)/2); //window location
		frame.setResizable(false); // fix window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close window, program close
		
		frame.setLayout(null);
		JComponent UMmain = new ModifyUserAccount();
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
























