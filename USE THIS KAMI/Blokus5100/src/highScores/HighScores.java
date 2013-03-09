package highScores;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import blokus5100.MainMenu;
import javax.swing.*;


public class HighScores extends JFrame{	
	static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	private int height = 600;
	private int width = 400;
	private String currentLine;
	private String file = "highscores.dat";
	private BufferedReader br;
	private JLabel[] highScores= new JLabel[10];
	private JLabel title;
	private JButton backButton;
	private JFrame fm;
	public HighScores() throws IOException {		
		fm = this;
		this.setLocation((screenWidth -400)/2,(screenHeight-600)/2);
		setTitle("High Scores");
		setSize(width, height);
		Container pane = getContentPane();
		pane.setLayout(null);
		
		title = new JLabel("Top Ten High Scores", SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.BOLD, 22));
		pane.add(title);
		title.setBounds(90, 20, 200, 30);
		
		br = new BufferedReader(new FileReader(file));
		
		for(int ind=0; ind <10; ind++){
			currentLine = br.readLine();
			highScores[ind]=new JLabel(currentLine, SwingConstants.CENTER);
			highScores[ind].setFont(new Font("Serif", Font.BOLD, 24));
			pane.add(highScores[ind]);
			highScores[ind].setBounds(90, 50+ind*30+30, 200, 30);
		}
		
		br.close();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Bodoni MT Black", Font.BOLD, 16));
		backButton.setBackground(Color.white);
		backButton.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		backButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fm.dispose();
				MainMenu mm = new MainMenu();
				mm.createAndShowGUI();
				
		}
		});
		
		pane.add(backButton);
		backButton.setBounds(130, 480, 100, 30);
		
	}
	
	public static void main(String[] args) throws IOException {
		HighScores window = new HighScores();
	}
}

