/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus;

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
    private SpinnerModel turnModel = new SpinnerNumberModel(10, 3, 99, 1);
    private JSpinner turnTime = new JSpinner(turnModel);
    private SpinnerModel gameModel = new SpinnerNumberModel(30, 5, 120, 1);
    private JSpinner gameTime = new JSpinner(gameModel);
    private JButton boardButton = new JButton("Preview Board");
    private JButton backButton = new JButton("Back");
    private JButton saveButton = new JButton("Play!!");
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
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(new JLabel("Turn Time (seconds) "),c);
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(5,0,5,130);
        innerPane.add(turnTime,c);
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(new JLabel("Game Time (minutes)"),c);
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(5,0,5,130);
        innerPane.add(gameTime,c);
        c.gridx = 1;
        c.gridy = 6;
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
        c.insets = new Insets(0,30,20,350);
        window.add(backButton, c);
        backButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 3;
        c.insets = new Insets(0,30,20,30);
        window.add(saveButton, c);
        saveButton.addActionListener(this);
        window.pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == backButton){
            //take back to main menu screen
        }
        else if(ae.getSource() == boardButton){
            //take to board where you can edit which squares are blocked
        }
        else if(ae.getSource() == saveButton){
            //save board to database and then take back to menu with it added to the GUI
        }
    }
    
}
