package systemAdministrator;
/**
 * Shujie Shen/Dream Team
 */
import models.User;
import application.UserFunctions;
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
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
    private JButton newButton, deleteButton, saveButton, backButton;
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
            header.setName("Modify User Account");
            header.setFont(new Font("Arial", Font.PLAIN, 24));

            comboBoxLabel = new JLabel("Choose Account:");
            comboBoxLabel.setName("Choose Account:");
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
            userList.setName("User List");
            userList.setOpaque(true);
            userList.addActionListener(this);

            newButton = new JButton("New");
            newButton.setName("New");
            newButton.addActionListener(this);
            newButton.setEnabled(true);
            
            deleteButton = new JButton("Delete");
            deleteButton.setName("Delete");
            deleteButton.addActionListener(this);
            deleteButton.setEnabled(false);

            saveButton = new JButton("Save");
            saveButton.setName("Save");
            saveButton.addActionListener(this);
            saveButton.setEnabled(false);

            backButton = new JButton("Back");
            backButton.setName("Back");
            backButton.addActionListener(this);

            // user name field
            userNameField = new JTextField(10);
            userNameField.setName("UserName");
            userNameField.setActionCommand(userNameText);
            userNameField.setEnabled(false);
            JLabel userNameLabel = new JLabel("User Name: ");
            userNameLabel.setName("User Name: ");
            userNameLabel.setLabelFor(userNameField);

            // password field
            passwordField = new JPasswordField(10);
            passwordField.setName("Password");
            passwordField.setEnabled(false);
            errorField.setEnabled(false);
            JLabel passwordLabel = new JLabel("Password: ");
            passwordLabel.setName("Password: ");
            passwordLabel.setLabelFor(passwordField);

            // confirm password field
            confirmPasswordField = new JPasswordField(10);
            confirmPasswordField.setName("Confirm");
            confirmPasswordField.setEnabled(false);
            JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
            confirmPasswordLabel.setName("Confirm Password: ");
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
            gameDesignerButton.setName("gameDesigner");
            gameDesignerButton.setSelected(true);
            gameDesignerButton.addActionListener(this);
            gameDesignerButton.setEnabled(false);

            // Radio button "system administrator"
            systemAdministratorButton = new JRadioButton("System Administrator");
            systemAdministratorButton.setName("systemAdmin");
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
            add(newButton);
            newButton.setBounds(360,10,80,30 );
            innerPane.setBorder(borderBlack);
            innerPane.add(comboBoxLabel);
            comboBoxLabel.setBounds(63,10,100,30);

            innerPane.add(userList);
            userList.setBounds(170,10,100,30);

            innerPane.add(textPane);
            textPane.setBounds(50, 45, 300, 80);

            innerPane.add(errorField);
            errorField.setBounds(45, 125, 300, 15 );
            
            innerPane.add(radioButtonPane);
            radioButtonPane.setBounds(65, 145, 330, 50);

            innerPane.add(deleteButton);
            deleteButton.setForeground(Color.red);
            deleteButton.setBounds(300, 200, 90, 30);
            
            // add header, innerpane, "back" button
            add(header);
            header.setBounds(40,10, 400, 40 );
            add(innerPane);
            innerPane.setBounds(40, 50,400,240);

            add(backButton);
            backButton.setBounds(40, 300, 80, 30);

            add(saveButton);
            saveButton.setBounds(360, 300,80, 30 );
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
            // TODO Auto-generated method stub
            if(ae.getSource() == userList){
                String user = (String)userList.getSelectedItem();
                if(user.equals("")){
                    userNameField.setText("");
                    userNameField.setEnabled(false);
                    passwordField.setText("");
                    passwordField.setEnabled(false);
                    confirmPasswordField.setText("");
                    confirmPasswordField.setEnabled(false);
                    gameDesignerButton.setEnabled(false);
                    systemAdministratorButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    newButton.setEnabled(true);
                    saveButton.setEnabled(false);
                }
                else{
                    User userInfo = new User();
                    userInfo = lf.getUserInfo(user);
                    userNameField.setText(userInfo.getUsername());
                    userNameField.setEnabled(true);
                    passwordField.setText(userInfo.getPassword());
                    passwordField.setEnabled(true);
                    confirmPasswordField.setText(userInfo.getPassword());
                    confirmPasswordField.setEnabled(true);
                    gameDesignerButton.setEnabled(true);
                    systemAdministratorButton.setEnabled(true);
                    newButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                    saveButton.setEnabled(true);
                    if(userInfo.getRole().equals("SA")){
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
                    User temp = new User(oldUsername, pw, role);
                    User saved = lf.updateUser(temp, userName);
                    System.out.println(saved.getUsername());
                    if(saved.getUsername().equals(userName)){
                        if(!oldUsername.equals(userName))
                        {
                            if(!oldUsername.equals("")){
                                userList.removeItemAt(userList.getSelectedIndex());
                            }
                            userList.addItem(userName);
                            userList.setSelectedItem(userName);
                        }
                        errorField.setText("<HTML><FONT COLOR = Green>" + saved.getUsername() + " has been saved!</FONT></HTML>");
                    }
                    else{
                        errorField.setText("<HTML><FONT COLOR = Red>User has not been saved!</FONT></HTML>");
                    }
                }
            }
            if(ae.getSource() == newButton){
                userList.setSelectedIndex(0);
                gameDesignerButton.setSelected(true);
                userNameField.setEnabled(true);
                passwordField.setEnabled(true);
                confirmPasswordField.setEnabled(true);
                systemAdministratorButton.setEnabled(true);
                gameDesignerButton.setEnabled(true);
                saveButton.setEnabled(true);
            }
            if(ae.getSource() == deleteButton){
                String userName = userList.getSelectedItem().toString();
                Boolean deleted = lf.deleteUser(userName);
                if(deleted){
                    userList.removeItemAt(userList.getSelectedIndex());
                    userList.setSelectedIndex(0);
                    deleteButton.setEnabled(false);
                    errorField.setText("<HTML><FONT COLOR = Green>User has been deleted!</FONT></HTML>");
                }
                else{
                    errorField.setText("<HTML><FONT COLOR = Red>User has not been deleted!</FONT></HTML>");   
                }
                
            }
            if(ae.getSource() == backButton)
            {
            	frame.dispose();
 		SystemAdministratorMain MUA = new SystemAdministratorMain();
 		MUA.ShowSystemAdministratorGUI();
            }
           
        }
        
	public static void showUserManagementGUI()
	{
		frame = new JFrame("MANAGE USER ACCOUNT");
		frame.setSize(500, 380); //window size
		frame.setLocation((width -500)/2,(height-380)/2); //window location
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
	
	public JFrame  getFrame()
	{
		return frame;
	}

}