package systemAdministrator;
/**
 * Shujie Shen/Dream Team
 */
import highScores.ScoreManagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class SystemAdministratorMain extends JPanel
{
	
	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	protected JLabel header;
	protected JButton userButton, scoreButton, logoutButton ;
	protected static JFrame frame;
	
	public SystemAdministratorMain()
	{
		this.setLayout(null);
		
		header = new JLabel("System Administrator Main");
		header.setName("System Administrator Main");
		header.setFont(new Font("Arial", Font.PLAIN, 24));
		
		userButton = new JButton("User Management");
		userButton.setName("User Management");
		userButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				ModifyUserAccount MUA = new ModifyUserAccount();
				MUA.showUserManagementGUI();
			}
		});
		
		scoreButton = new JButton("Score Management");
		scoreButton.setName("Score Management");
		scoreButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				frame.dispose();
				ScoreManagement SM = new ScoreManagement();
				SM.showScoreManagementGUI();
			}
		});
		
		logoutButton = new JButton("Log out");
		logoutButton.setName("Logout");
		logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setForeground(Color.blue);
		logoutButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				frame.dispose();
				Login lg = new Login();
				lg.run();
			}
		});
		
		Border borderBlack = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.lightGray, Color.blue);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(borderBlack);
		buttonPanel.setLayout(null);
		buttonPanel.add(userButton);
		userButton.setBounds(120,20,180,35);
		
		buttonPanel.add(scoreButton);
		scoreButton.setBounds(120,75, 180, 35);
		
		
		add(header);
		header.setBounds(30, 20, 350, 40);
		add(buttonPanel);
		buttonPanel.setBounds(30,70,420,130);
		add(logoutButton);
		logoutButton.setBounds(370, 20, 80, 35);
		
		
	}
	
	public static void ShowSystemAdministratorGUI()
	{
		frame = new JFrame("SYSTEM ADMINISHTRATOR MAIN MENU");
		frame.setSize(500, 250); //window size
		frame.setLocation((width -500)/2,(height-250)/2); //window location
		frame.setResizable(false); // fix window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close window, program close
		
		frame.setLayout(null);
		JComponent SAMainPanel = new SystemAdministratorMain();
		frame.add(SAMainPanel);
		
		frame.setVisible(true);//show window
		frame.setContentPane(SAMainPanel);
	}
	
	public JFrame getFrame()
	{
		return frame;
	}

}
