package blokus5100;
/**
 * Shujie Shen/ Dream Team
 */
import blokusGame.BlokusGame;
import highScores.HighScores;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import systemAdministrator.Login;




public class MainMenu extends JPanel
{

	static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	protected JLabel gameTitle;
	protected JLabel gameIcon;
	protected JLabel copyRight;
	protected JButton b1, b2, b3, b4, b5;
	protected static JFrame frame;
        
	public MainMenu()
	{
		//super(new BorderLayout());
		this.setLayout(null);
		//this.setBackground(Color.white);
		gameTitle = new JLabel();
                
		gameTitle.setFont(new Font("Bodoni MT Black", Font.BOLD, 42));
		gameTitle.setHorizontalAlignment(JLabel.CENTER);
		gameTitle.setPreferredSize(new Dimension(212, 227));
		gameTitle.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		gameTitle.setText("BLOCKUS GAME");
		
		copyRight = new JLabel();
		copyRight.setFont(new Font("AR DECODE",Font.BOLD, 24));
		copyRight.setText("by The Dream Team");
		/*
		gameIcon = new JLabel();
		gameIcon.setHorizontalAlignment(JLabel.CENTER);
		gameIcon.setPreferredSize(new Dimension(200, 177));
		gameIcon.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		ImageIcon icon = createImageIcon("Blockus_Img/MainIcon.png");
		gameIcon.setIcon(icon);*/
		
		
		b1 = new JButton("PLAY");
		//b1.setFont(new Font("Bodoni MT Black", Font.BOLD, 24));
		//b1.setBackground(Color.white);
		//b1.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b1.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
                                GameWizard gm = new GameWizard();
                                gm.run();
                                
				//BlokusGame bg = new BlokusGame();
				
				
		}
		});
		
		
		
		b2 = new JButton("HOW TO PLAY");
                
		//b2.setFont(new Font(Font.BOLD, 18));
		//b2.setBackground(Color.white);
		//b2.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				try {
					Instructions i = new Instructions();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		b3 = new JButton("LOG IN");
		//b3.setFont(new Font("Bodoni MT Black", Font.BOLD, 24));
		//b3.setBackground(Color.white);
		//b3.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b3.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				Login lg = new Login();
				lg.run();
				
		}
		});
		

		b4 = new JButton("EXIT");
		//b4.setFont(new Font("Bodoni MT Black", Font.BOLD, 24));
		//b4.setBackground(Color.white);
		//b4.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b4.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				
		}
		});
		b5 = new JButton("HIGH SCORES");
		//b5.setFont(new Font("Bodoni MT Black", Font.BOLD, 18));
		//b5.setBackground(Color.white);
		//b5.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		b5.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				try {
					HighScores hs = new HighScores();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
		});
		
		
		JPanel buttonPanel = new JPanel();
                
		buttonPanel.setLayout(null);
		//buttonPanel.setBackground(Color.white);
		buttonPanel.add(b1);
                //buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
		b1.setBounds(0, 0, 200, 45);
		buttonPanel.add(b2);
                //buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
		b2.setBounds(0, 65, 200, 45);
		buttonPanel.add(b3);
                //buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
		b3.setBounds(0, 130, 200,45);
		buttonPanel.add(b4);
                //buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
		b4.setBounds(0, 270, 200, 45);
		buttonPanel.add(b5);
                //buttonPanel.add(Box.createRigidArea(new Dimension(0,10)));
		b5.setBounds(0, 195, 200, 45);
                
		add(gameTitle);
		gameTitle.setBounds(100,50,600, 100);
		add(copyRight);
		copyRight.setBounds(300, 120, 200, 100);
		add(buttonPanel);
		buttonPanel.setBounds(300,230,300,318);
	//	add(gameIcon);
		//gameIcon.setBounds(210, 580, 356,141);
		this.add(buttonPanel);
		
	}
	
	public static void createAndShowGUI()
	{
		frame = new JFrame("BLOKUS MAIN MENU");
		frame.setSize(800, 800); //window size
		frame.setLocation((width -800)/2,(height-800)/2); //window location
		frame.setResizable(false); // fix window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close window, program close
		
		frame.setLayout(null);
		JComponent mainPanel = new MainMenu();
		frame.add(mainPanel);
		
		frame.setVisible(true);//show window
		frame.setContentPane(mainPanel);
	}

	protected static ImageIcon createImageIcon(String path)
	{
		java.net.URL imgURL = MainMenu.class.getResource(path);
		if(imgURL != null)
		{
			return new ImageIcon(imgURL);
		}
		else
		{
			System.err.println("Couldn't find file: "+path);
			return null;
		}
	}
	
	public static void main(String[] args) 
	{
		
		createAndShowGUI();

	}

	
}
