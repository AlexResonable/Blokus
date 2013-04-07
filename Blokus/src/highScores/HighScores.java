package highScores;

import application.HighScoresAccess;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import blokus5100.MainMenu;
import javax.swing.*;
import models.PlayerScore;


public class HighScores extends JFrame{	
	static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	private int height = 600;
	private int width = 400;
	private String currentScore;
	//private String file = "highscores.dat";
	//private BufferedReader br;
        int SIZE = 10;
	private JLabel[] highScores= new JLabel[SIZE];
	private JLabel title;
	private JButton backButton;
	private JFrame fm;
        private HighScoresAccess hsa = new HighScoresAccess();
	public HighScores() throws IOException {
            
            PlayerScore[] highScoresArray = new PlayerScore[SIZE];
            highScoresArray = hsa.getHighScores();
                
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

            //File file = new File("highscore.txt");
            //System.out.println(file.getCanonicalPath());
            //br = new BufferedReader(new FileReader(file));


            for(int i=0; i <SIZE; i++){
                //currentLine = br.readLine();
                if(highScoresArray[i].getScore() != 0)
                {
                    currentScore = i+1 + ".     " + highScoresArray[i].toString();
                    highScores[i]=new JLabel(currentScore, SwingConstants.LEFT);
                    //highScores[i].setFont(new Font("Serif", Font.BOLD, 24));
                    pane.add(highScores[i]);
                    highScores[i].setBounds(90, 50+i*30+30, 200, 30);
                }
                
            }

            //br.close();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);

            backButton = new JButton("Back");
            //backButton.setFont(new Font("Bodoni MT Black", Font.BOLD, 16));
            //backButton.setBackground(Color.white);
            //backButton.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
            backButton.addActionListener( new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent e){
                            fm.dispose();
                            MainMenu mm = new MainMenu();
                            mm.createAndShowGUI();

            }
            });

            pane.add(backButton);
            backButton.setBounds(130, 480, 100, 30);

	}
}

