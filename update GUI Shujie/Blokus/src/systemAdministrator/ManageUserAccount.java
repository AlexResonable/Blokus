package systemAdministrator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ManageUserAccount extends JPanel
{
	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	private JButton logoutButton, newButton, editButton1, editButton2, deleteButton1, deleteButton2, backButton;
	private JLabel header, user1, user2;
	private static JFrame window;
	private JPanel innerPane;
	
	public ManageUserAccount()
	{
		
		this.setLayout(null);
		header = new JLabel("Manage User Account");
		header.setFont(new Font("Arial", Font.PLAIN, 24));
		
		newButton = new JButton("Create New");
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				window.dispose();
				ModifyUserAccount ModifyUA = new ModifyUserAccount();
	    		ModifyUA.showModifyUserAccountGUI();
			}
			
		});
		
		logoutButton = new JButton("Logout");
		logoutButton.setForeground(Color.blue);
		logoutButton.setBorderPainted(false);
		logoutButton.setContentAreaFilled(false);
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				window.dispose();
				SystemAdministratorMain SAM = new SystemAdministratorMain();
				SAM.ShowSystemAdministratorGUI();
			}
			
		});
		
		 TitledBorder titleBorder = BorderFactory.createTitledBorder("User list");
	     Border borderBlack = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.lightGray, Color.blue);
	     titleBorder.setBorder(borderBlack);
	     
	     innerPane = new JPanel();
	     innerPane.setBorder(titleBorder);
	     innerPane.setLayout(new GridBagLayout());
	     GridBagConstraints c = new GridBagConstraints();
	     c.gridx = 0;
	     c.gridy = 0;
	     c.insets = new Insets(5,20,5,20);
	     user1 = new JLabel("sysAdmin");
	     innerPane.add(user1,c);
	    
	     c.gridx = 1;
	     editButton1 = new JButton("Edit");
	     innerPane.add(editButton1,c);
	     editButton1.addActionListener(new ActionListener(){
	    	 public void actionPerformed (ActionEvent e)
	    	 {
	    		 window.dispose();
	    		 ModifyUserAccount ModifyUA = new ModifyUserAccount();
	    		 ModifyUA.showModifyUserAccountGUI();
	    	 }
	     });
	     
	     c.gridx = 2;
	     deleteButton1 = new JButton("Delete");
	     deleteButton1.setForeground(Color.red);
	     innerPane.add(deleteButton1,c);
	     deleteButton1.setEnabled(false);
	     //deleteButton1.addActionListener(this);
	     
	     c.gridx = 1;
	     
	     add(header);
	     header.setBounds(30, 10, 300, 40);
	     
	     add(newButton);
	     newButton.setBounds(130, 80, 140, 35);
	     
	     add(logoutButton);
	     logoutButton.setBounds(300,10, 100, 35);
	     
	     add(backButton);
	     backButton.setBounds(30,250, 80, 35);
	     
	     add(innerPane);
	     innerPane.setBounds(30, 125,340, 100 );
	     
	     
	}
	public static void createManageUserAccountGUI()
	{
		window = new JFrame("Manage User Account");
		window.setSize(400, 350);
		window.setLocation((width-400)/2, (height-250)/2);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setLayout(null);
		JComponent MUA = new ManageUserAccount();
		window.add(MUA);
		window.setVisible(true);
		window.setContentPane(MUA);
	}
	
}
