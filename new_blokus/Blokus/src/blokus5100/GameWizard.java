package blokus5100;

import application.GameFunctions;
import gameDesigner.PreviewBoard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import blokusGame.BlokusGame;
import java.util.ArrayList;
import java.util.HashMap;
import models.Game;

public class GameWizard implements ActionListener 
{
    private JFrame window = new JFrame("Settings for Blokus"); 
    private JLabel header = new JLabel("Modify Settings");
    private JTextField player1 = new JTextField(15);
    private JTextField player2 = new JTextField(15);
    private JTextField player3 = new JTextField(15);
    private JTextField player4 = new JTextField(15);
    private JLabel p1 = new JLabel("Name of Player 1: ");
    private JLabel p2 = new JLabel("Name of Player 2: ");
    private JLabel p3 = new JLabel("Name of Player 3: ");
    private JLabel p4 = new JLabel("Name of Player 4: "); 
    JRadioButton easy = new JRadioButton("Easy");
    JRadioButton medium = new JRadioButton("Medium");
    JRadioButton hard = new JRadioButton("Hard");
    private JComboBox gameNames = new JComboBox();
    DefaultComboBoxModel model = new DefaultComboBoxModel();  
    private JButton boardButton = new JButton("Preview Board");
    private JButton backButton = new JButton("Back");
    private JButton playButton = new JButton("Play");
    private JPanel innerPane = new JPanel();
    private GameFunctions gf = new GameFunctions();
    private Game eBoard = new Game();
    
    public void run(Game game)
    {
        this.createDesignerMain(game);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    
        int width = window.getSize().width;
        int height = window.getSize().height;
        int x = (d.width-width)/2;
        int y = (d.height-height)/2;
    
        window.setLocation(x, y);
        window.setVisible(true);
    }
    
    private void createDesignerMain(final Game game)
    {
        eBoard = game;
        window.setLayout(new GridBagLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        // "Modify Settings" field
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10,40,0,0);
        header.setFont(new Font("Arial", Font.PLAIN, 24));
        header.setName("Modify Settings"); 
        window.add(header, c);
        
        // Field in the black square
        c.gridwidth = 1;
        Border borderBlack = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.lightGray, Color.blue);
        innerPane.setLayout(new GridBagLayout());
        innerPane.setBorder(borderBlack);
        
        // add play name field 1-4
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(15,20,5,20);
        innerPane.add(p1,c);
        p1.setName("Name of Player 1"); 
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(15,0,5,0);
        innerPane.add(player1,c);
        player1.setName("Player1 Field");
        player1.setToolTipText("Enter Player 1 Name");
        
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(p2,c);
        p2.setName("Name of Player 2");
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(player2,c);
        player2.setName("Player2 Field");
        player2.setToolTipText("Enter Player 2 Name");
        
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(p3,c);
        p3.setName("Name of Player 3");
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(player3,c);
        player3.setName("Player3 Field"); 
        player3.setToolTipText("Enter Player 3 Name");
        
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(p4,c);
        p4.setName("Name of Player 4"); 
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(player4,c);
        player4.setName("Player4 Field");
        player4.setToolTipText("Enter Player 4 Name");
        
        // "Game Model" field
        gameNames.setModel(model);
        gameNames.setName("game names");
        gameNames.setToolTipText("Select game to play");
        JPanel gameMode = new JPanel(new GridBagLayout());
        TitledBorder title = BorderFactory.createTitledBorder("Game Mode");
        gameMode.setBorder(title);
        HashMap<String, String> games;
        if(game.getMode()==1){
            medium.setSelected(true);
            games = gf.getModeGames(1);
        }
        else if(game.getMode()==2){
            hard.setSelected(true);
            games = gf.getModeGames(2);
        }
        else{
            easy.setSelected(true);
            games = gf.getModeGames(0);
        }
        gameNames.removeAllItems();
        int i = 0, location = 0;
        for (String value : games.values()) 
        {
            gameNames.addItem(value);
            if(value.equals(game.getName())){
                location = i;
            }
            i++;
        }
        gameNames.setSelectedIndex(location);
        
        ButtonGroup mode = new ButtonGroup();
        easy.setName("easy");
        medium.setName("medium");
        hard.setName("hard");
        mode.add(easy);
        mode.add(medium);
        mode.add(hard);
        
        // "Easy" button
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,0,5,5);
        gameMode.add(easy,c);
        easy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //get easy games from database and show them
                gameNames.removeAllItems();
                HashMap<String, String> games = gf.getModeGames(0);
                int i = 0, location = 0;
                for (String value : games.values()) 
                {
                    gameNames.addItem(value);
                    if(value.equals(game.getName())){
                        location = i+1;
                    }
                    ++i;
                }
           
            }
        });
        
        // "Medium" button
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(5,0,5,5);
        gameMode.add(medium,c);
        medium.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //get easy games from database and show them
                gameNames.removeAllItems();
                gameNames.removeAllItems();
                HashMap<String, String> games = gf.getModeGames(1);
                int i = 0, location = 0;
                for (String value : games.values()) 
                {
                    gameNames.addItem(value);
                    if(value.equals(game.getName())){
                        location = i+1;
                    }
                    ++i;
                }
            }
        });
        medium.addActionListener(this);
        
        //"Hard" button
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(5,0,5,5);
        gameMode.add(hard,c);
        hard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //get easy games from database and show them
                gameNames.removeAllItems();
                gameNames.removeAllItems();
                HashMap<String, String> games = gf.getModeGames(2);
                int i = 0, location = 0;
                for (String value : games.values()) 
                {
                    gameNames.addItem(value);
                    if(value.equals(game.getName())){
                        location = i+1;
                    }
                    ++i;
                }
            }
        });
        
        // Game combBox
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5,10,5,10);
        gameMode.add(new JLabel("Game"),c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(5,0,5,10);
        gameMode.add(gameNames,c);
        
        // add "Game Mode" to inner panel
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(gameMode,c);
        
        // add "Preview Board" to inner panel
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 3;
        c.insets = new Insets(20,10,15,20);
        innerPane.add(boardButton,c);
        boardButton.setName("Preview Board");
        boardButton.addActionListener(this);
      
        // add "inner panel,"back" button, "play" button to the window
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridwidth = 4;
        c.insets = new Insets(0,30,20,30);
        window.add(innerPane,c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.ipady = 5;
        c.insets = new Insets(0,30,20,350);
        window.add(backButton, c);
        backButton.setName("Back");
        backButton.addActionListener(this);
        
        c.gridx = 2;
        c.gridy = 3;
        c.insets = new Insets(0,30,20,30);
        window.add(playButton, c);
        playButton.setName("Play"); 
        playButton.addActionListener(this);
        window.pack();


    }
    
    public JFrame getWindow()
    {
      return window;
    } 
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == backButton)
        {
            //take back to main menu screen
            window.setVisible(false);
            MainMenu.createAndShowGUI();
        }
        else if(ae.getSource() == boardButton)
        {
            //take to board where you can edit which squares are blocked
            window.dispose();//setVisible(false);
            Game boardBlock = gf.getGameInfo(gameNames.getSelectedItem().toString());
            PreviewBoard board = new PreviewBoard(boardBlock);
            board.run();
        }
        else if(ae.getSource() == playButton)
        {
            eBoard = gf.getGameInfo(gameNames.getSelectedItem().toString());
            String myElements = eBoard.getE();
            int elements[][] = new int[eBoard.getSizeX()][eBoard.getSizeY()];
            char[] charArray;
            if(myElements != null){
                charArray = myElements.toCharArray();
            }
            else{
                charArray = eBoard.getE().toCharArray();
            }
            int x=0, y=0;
            for(int i=0; i < charArray.length; i++){
                if(y >= eBoard.getSizeY()){
                    y = 0;
                    x++;
                }
                if(x < eBoard.getSizeX()){
                    elements[x][y] = Character.getNumericValue(charArray[i]);
                    if(charArray[i] == '1'){
                        elements[x][y] = 5;
                    }
                    y++;
                }
                else{
                    i = charArray.length;
                }
            }
            eBoard.setElements(elements);
            eBoard.setGameTime(eBoard.getGameTime()*60);
            window.dispose();
            BlokusGame bg = new BlokusGame(player1.getText(), player2.getText(), player3.getText(), player4.getText(), eBoard);
            //save board to database and then take back to menu with it added to the GUI 
        }
    }
    
}
