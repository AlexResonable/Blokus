package blokus5100;
import highScores.HighScores;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import models.Game;
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
		// "Game title"
		this.setLayout(null);
		gameTitle = new JLabel();
                gameTitle.setName("BLOCKUS GAME");
		gameTitle.setFont(new Font("Bodoni MT Black", Font.BOLD, 42));
		
		gameTitle.setHorizontalAlignment(JLabel.CENTER);
		gameTitle.setPreferredSize(new Dimension(212, 227));
		gameTitle.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		gameTitle.setText("BLOCKUS GAME");
		
		// "Team name"
		copyRight = new JLabel();
                copyRight.setName("by The Dream Team"); 
		copyRight.setFont(new Font("Arial", Font.ITALIC, 20));
		copyRight.setText("by The Dream Team");
		
		
		// "Play" button
		b1 = new JButton("PLAY");
                b1.setName("PLAY"); 
		b1.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
                GameWizard gm = new GameWizard();
                gm.run(new Game());
            }
		});
		
		//"Instruction button"
		b2 = new JButton("HOW TO PLAY");
                b2.setName("Instruction");
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
		
		//"Log in button"
		b3 = new JButton("LOG IN");
                b3.setName("LOG IN");
		b3.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				Login lg = new Login();
				lg.run();
				
		}
		});
		
		//"Exit" button
		b4 = new JButton("EXIT");
                b4.setName("EXIT");
		b4.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
				
		}
		});
		
		//"High score" button
		b5 = new JButton("HIGH SCORES");
                b5.setName("HIGH SCORES");
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
		
		// buttonPanel holds all buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		
        
		// add buttons 1-5 to the buttonPanel
		buttonPanel.add(b1);
		b1.setBounds(0, 0, 200, 45);
		
		buttonPanel.add(b2);      
		b2.setBounds(0, 65, 200, 45);
		
		buttonPanel.add(b3);       
		b3.setBounds(0, 130, 200,45);
		
		buttonPanel.add(b4);        
		b4.setBounds(0, 270, 200, 45);
		
		buttonPanel.add(b5);      
		b5.setBounds(0, 195, 200, 45);
        
		// add three parts into mainPanel
		add(gameTitle);
		gameTitle.setBounds(10,30,600, 40);
		add(copyRight);
		copyRight.setBounds(200, 80, 200, 100);
		add(buttonPanel);
		buttonPanel.setBounds(200,200,300,318);

		
	}
	
	public static void createAndShowGUI()
	{
		frame = new JFrame("BLOKUS MAIN MENU");
		frame.setSize(600, 650);
		frame.setLocation((width -600)/2,(height-650)/2); 
		frame.setResizable(false); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		// add mainPanel in frame
		JComponent mainPanel = new MainMenu();
		frame.add(mainPanel);
		
		frame.setVisible(true);
		frame.setContentPane(mainPanel);
	}
	
	public static void main(String[] args) 
	{
		createAndShowGUI();
	}
        
        public JFrame getFrame()
        {
            return frame;
        } 

	
}
