package systemAdministrator;

import application.UserFunctions;
import blokus5100.MainMenu;

import gameDesigner.ModifyGameDesign;
import gameDesigner.setBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login implements ActionListener {
    private JLabel header = new JLabel("User Login");
    private JLabel errorField = new JLabel(" ");
    
    private JTextField usernameField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(15);
    private JButton backButton = new JButton("Back");
    private JButton loginButton = new JButton("Login");
    
    private JFrame window = new JFrame("System Login");
    private JPanel contentPane = (JPanel)window.getContentPane(); 
    
    public void run(){
        this.createLogin();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    
        int width = window.getSize().width;
        int height = window.getSize().height;
        int x = (d.width-width)/2;
        int y = (d.height-height)/2;
    
        window.setLocation(x, y);
        window.setVisible(true);
    }
    
    private void createLogin()
    {
    	backButton.setName("Back");
    	loginButton.setName("Login");
    	header.setName("User Login");
    	errorField.setName("error message");
    	usernameField.setName("UserName");
    	passwordField.setName("Password");
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(30,40,0,0);
        header.setFont(new Font("Arial", Font.PLAIN, 24));
        window.add(header, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.insets = new Insets(15,40,0,0);
        window.add(errorField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10,40,10,0);
        window.add(new JLabel("Username: "), c);
        c.gridx = 1;
        c.gridy = 2;
        c.ipady = 10;
        c.ipadx = 10;
        c.gridwidth = 2;
        c.insets = new Insets(10,0,10,40);
        window.add(usernameField, c);
        usernameField.addActionListener(this);
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 0;
        c.ipadx = 0;
        c.insets = new Insets(0,40,20,0);
        c.gridwidth = 1;
        window.add(new JLabel("Password: "), c);
        c.gridx = 1;
        c.gridy = 3;
        c.ipady = 10;
        c.ipadx = 10;
        c.gridwidth = 2;
        c.insets = new Insets(0,0,20,40);
        window.add(passwordField, c);
        passwordField.addActionListener(this);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(10,40,30,40);
        window.add(backButton, c);
        backButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 4;
        c.insets = new Insets(10,90,30,40);
        c.gridwidth = 1;
        window.add(loginButton, c);
        loginButton.addActionListener(this);
        window.setResizable(false);
        contentPane.setBorder(BorderFactory.createEtchedBorder());
        contentPane.setBackground(new Color(204, 204, 204));
        window.pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        UserFunctions lf = new UserFunctions();
        if(ae.getSource() == backButton){
            //call menu
        	window.dispose();
            MainMenu.createAndShowGUI();
        }
        else if(ae.getSource() == loginButton){
            String username = usernameField.getText();
            char password[] = passwordField.getPassword();
            String loginReturn = lf.login(username, password);
            if(loginReturn == null)
                errorField.setText("<HTML><FONT COLOR = Red>Username and password must be valid</FONT></HTML>");
            else if("DA".equals(loginReturn)){
                //GameDesignerMain game = new GameDesignerMain();
                //game.run();
            	
            	ModifyGameDesign MGD = new ModifyGameDesign(new setBoard());
            	MGD.run();
                window.dispose();
            }
            else if("SA".equals(loginReturn)){
                SystemAdministratorMain.ShowSystemAdministratorGUI();
                window.dispose();
            }
            else
                errorField.setText("<HTML><FONT COLOR = Red>Username and password must be valid</FONT></HTML>");
        }
        
    }
    
    public JFrame getWindow()
    {
    	return window;
    }
    
}