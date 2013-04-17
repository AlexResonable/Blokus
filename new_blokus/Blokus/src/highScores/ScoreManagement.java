package highScores;

import application.HighScoresAccess;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import models.PlayerScore;
import systemAdministrator.SystemAdministratorMain;


    public class ScoreManagement extends JPanel{	
        static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        private String currentScore;
        int SIZE = 10;
        private JLabel[] highScores= new JLabel[SIZE];
        private JButton[] deleteOneScore = new JButton[SIZE];
        private JLabel title;
        private JButton backButton;
        private JButton deleteAllButton;
        protected static JFrame frame;
        private HighScoresAccess hsa = new HighScoresAccess();
        PlayerScore[] highScoresArray = new PlayerScore[SIZE];
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        int i;



        public ScoreManagement() {
            panel.setLayout(null);
            this.setLayout(null);
            highScoresArray = hsa.getHighScores();
            ButtonHandler buttonHandler = new ButtonHandler();


            title = new JLabel("Top Ten High Scores", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.PLAIN, 24));

            add(title);
            title.setBounds(40, 20, 300, 30);
            for(i=0; i <SIZE; i++){
                if(highScoresArray[i].getId() != -1)
                {

                    currentScore = i+1 + ". " + highScoresArray[i].toString();
                    highScores[i]= new JLabel(currentScore, SwingConstants.LEFT);

                    highScores[i].setFont(new Font("Arial", Font.PLAIN, 16));
                    panel.add(highScores[i]);
                    highScores[i].setBounds(90, 50+i*30+30, 200, 30);
                    deleteOneScore[i] = new JButton("Delete");
                    panel.add(deleteOneScore[i]);
                    deleteOneScore[i].setBounds(300, 50+i*30+30, 50, 30);
                    deleteOneScore[i].addActionListener(buttonHandler);
                }
            }
            repaint();

            backButton = new JButton("Back");

            backButton.addActionListener( new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent e){
                            frame.dispose();
                            SystemAdministratorMain SAmain = new SystemAdministratorMain();
                            SystemAdministratorMain.ShowSystemAdministratorGUI();


            }
            });

            deleteAllButton = new JButton("Delete All");

            deleteAllButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    hsa.deleteAllScores();
                    panel.setVisible(false);
                    panel.repaint();
                    //add(panel);
                }
            });

            panel.setVisible(true);
            //buttonPanel.setVisible(true);
            add(panel);
            panel.setBounds(90, 40, 400, 400);
            add(backButton);
            backButton.setBounds(100, 480, 100, 30);
            add(deleteAllButton);
            deleteAllButton.setBounds(250, 480, 100, 30);
            
        }

        public void ShowHighScoresManagementGUI()
        {
            frame = new JFrame("High Scores Management");
            frame.setSize(600, 650);
            frame.setLocation((width - 600)/2, (height - 650)/2);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);
            JComponent SMMainPanel = new ScoreManagement();

            frame.add(SMMainPanel);
            
            

            frame.setVisible(true);
            frame.setContentPane(SMMainPanel);
            mouseStuff();
            


        }

        public void mouseStuff()
        {
                        frame.getContentPane().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("x is : " + e.getX() + "\n y is :" + e.getY());
                }
            });
        }
            public JFrame getFrame()
            {
                    return frame;
            }

            
            
            
            
    private class ButtonHandler implements ActionListener
    {

        public void actionPerformed(ActionEvent event)
        {
            for(int i = 0; i < SIZE; i++)
            {
                if(event.getSource() == deleteOneScore[i])
                {
                    System.out.println(highScoresArray[i].getId());
                    System.out.println(i);
                    hsa.deleteScore(highScoresArray[i]);
                    panel.remove(highScores[i]);
                    panel.remove(deleteOneScore[i]);
                    repaint();
                }
            }
        }
    }
}