/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlokusGUIs;

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


/**
 *
 * @author kamijean2
 */
public class GameWizard implements ActionListener {
    private JFrame window = new JFrame("Settings for Blokus"); 
    private JLabel header = new JLabel("Modify Settings");
    private JTextField player1 = new JTextField(15);
    private JTextField player2 = new JTextField(15);
    private JTextField player3 = new JTextField(15);
    private JTextField player4 = new JTextField(15);
    JRadioButton easy = new JRadioButton("Easy");
    JRadioButton medium = new JRadioButton("Medium");
    JRadioButton hard = new JRadioButton("Hard");
    String[] games = {"Normal Game"};
    private JComboBox gameNames = new JComboBox(games);
    DefaultComboBoxModel model = new DefaultComboBoxModel();  
    private JButton boardButton = new JButton("Preview Board");
    private JButton backButton = new JButton("Back");
    private JButton playButton = new JButton("Play!!");
    private JPanel innerPane = new JPanel();
    
    public void run(){
        this.createDesignerMain();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    
        int width = window.getSize().width;
        int height = window.getSize().height;
        int x = (d.width-width)/2;
        int y = (d.height-height)/2;
    
        window.setLocation(x, y);
        window.setVisible(true);
    }
    
    private void createDesignerMain(){
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10,40,0,0);
        header.setFont(new Font("Arial", Font.PLAIN, 24));
        window.add(header, c);
        c.gridwidth = 1;
        Border borderBlack = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.lightGray, Color.darkGray);
        innerPane.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(15,20,5,20);
        innerPane.add(new JLabel("Name of Player 1: "),c);
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(15,0,5,0);
        innerPane.add(player1,c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(new JLabel("Name of Player 2: "),c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(player2,c);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(new JLabel("Name of Player 3: "),c);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(player3,c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(new JLabel("Name of Player 4: "),c);
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(player4,c);
        gameNames.setModel(model);
        gameNames.addItem("Normal Game");
        JPanel gameMode = new JPanel(new GridBagLayout());
        TitledBorder title = BorderFactory.createTitledBorder("Game Mode");
        gameMode.setBorder(title);
        easy.setSelected(true);
        ButtonGroup mode = new ButtonGroup();
        mode.add(easy);
        mode.add(medium);
        mode.add(hard);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,0,5,5);
        gameMode.add(easy,c);
        easy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //get easy games from database and show them
                gameNames.removeAllItems();
                gameNames.addItem("Normal Game");
                
            }
        });
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(5,0,5,5);
        gameMode.add(medium,c);
        medium.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //get easy games from database and show them
                gameNames.removeAllItems();
                gameNames.addItem(" ");
                
            }
        });
        easy.addActionListener(this);
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(5,0,5,5);
        gameMode.add(hard,c);
        hard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //get easy games from database and show them
                gameNames.removeAllItems();
                gameNames.addItem(" ");
                
            }
        });
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
        
        
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(gameMode,c);
        
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 3;
        c.insets = new Insets(20,10,15,20);
        innerPane.add(boardButton,c);
        boardButton.addActionListener(this);
        innerPane.setBorder(borderBlack);
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
        backButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 3;
        c.insets = new Insets(0,30,20,30);
        playButton.setBackground(Color.blue);
        playButton.setForeground(Color.white);
        window.add(playButton, c);
        playButton.addActionListener(this);
        window.pack();


    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == backButton){
            //take back to main menu screen
            window.setVisible(false);
            MainMenu.createAndShowGUI();
        }
        else if(ae.getSource() == boardButton){
            //take to board where you can edit which squares are blocked
            window.setVisible(false);
            PreviewBoard board = new PreviewBoard();
            board.run();
        }
        else if(ae.getSource() == playButton){
            //save board to database and then take back to menu with it added to the GUI
            //window.setVisible(false);
            
        }
    }
    
}
