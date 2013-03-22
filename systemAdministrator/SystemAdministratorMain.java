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

public class SystemAdministratorMain extends JPanel
{
	
	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	protected JLabel hello;
	protected JButton b1, b2, b3;
	protected static JFrame frame;
	public SystemAdministratorMain()
	{
		this.setLayout(null);
		this.setBackground(Color.white);
		hello = new JLabel("Hello, XXX!");
		hello.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		
		b1 = new JButton("USER MANAGEMENT");
		b1.setHorizontalAlignment(b1.CENTER);
		b1.setFont(new Font("Bodoni MT Black", Font.BOLD, 18));
		b1.setBackground(Color.white);
		b1.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				UserManagement UMframe = new UserManagement();
				UMframe.showUserManagementGUI();
			}
		});
		
		
		
		b2 = new JButton("SCORE MANAGEMENT");
		b2.setHorizontalAlignment(b2.CENTER);
		b2.setFont(new Font("Bodoni MT Black", Font.BOLD, 18));
		b2.setBackground(Color.white);
		b2.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b2.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				frame.dispose();
				ScoreManagement SM = new ScoreManagement();
				SM.showScoreManagementGUI();
			}
		});
		
		
		
		b3 = new JButton("Log out");
		b3.setHorizontalAlignment(b3.CENTER);
		b3.setFont(new Font("Bodoni MT Black", Font.BOLD, 18));
		b3.setBackground(Color.white);
		b3.setBorder(BorderFactory.createLineBorder(Color.gray, 0));
		b3.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				frame.dispose();
				Login lg = new Login();
				lg.run();
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBackground(Color.white);
		buttonPanel.add(b1);
		b1.setBounds(0,0,300,45);
		
		buttonPanel.add(b2);
		b2.setBounds(0,75, 300, 45);
		
		//buttonPanel.add(b3);
		//b3.setBounds(0,150,300,45);
		
		add(hello);
		hello.setBounds(20, 20, 150, 60);
		add(buttonPanel);
		buttonPanel.setBounds(150,150,300,300);
		add(b3);
		b3.setBounds(400, 25, 115, 45);
		
		
	}
	
	public static void ShowSystemAdministratorGUI()
	{
		frame = new JFrame("SYSTEM ADMINISHTRATOR MAIN MENU");
		frame.setSize(600, 400); //window size
		frame.setLocation((width -600)/2,(height-400)/2); //window location
		frame.setResizable(false); // fix window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close window, program close
		
		frame.setLayout(null);
		JComponent SAMainPanel = new SystemAdministratorMain();
		frame.add(SAMainPanel);
		
		frame.setVisible(true);//show window
		frame.setContentPane(SAMainPanel);
	}
	

}
