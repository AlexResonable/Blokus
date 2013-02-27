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
public class ModifyGameDesign implements ActionListener {
    private JFrame window = new JFrame("Modify Game Design"); 
    private JLabel header = new JLabel("Modify Game Board");
    private JButton logout = new JButton("Logout");
    private SpinnerModel gameSizeModel = new SpinnerNumberModel(20, 20, 100, 10);
    private JSpinner size1 = new JSpinner(gameSizeModel);
    private SpinnerModel gameSizeModel2 = new SpinnerNumberModel(20, 20, 100, 10);
    private JSpinner size2 = new JSpinner(gameSizeModel2);
    private SpinnerModel model = new SpinnerNumberModel(4, 1, 4, 1);
    private JSpinner players = new JSpinner(model);
    private SpinnerModel turnModel = new SpinnerNumberModel(10, 3, 99, 1);
    private JSpinner turnTime = new JSpinner(turnModel);
    private SpinnerModel gameModel = new SpinnerNumberModel(30, 5, 120, 1);
    private JSpinner gameTime = new JSpinner(gameModel);
    private SpinnerModel pieceModel = new SpinnerNumberModel(21, 21, 120, 1);
    private JSpinner pieces = new JSpinner(pieceModel);
    private JButton boardButton = new JButton("Edit Board");
    private JButton piecesButton = new JButton("Edit Pieces");
    private JButton backButton = new JButton("Back");
    private JButton saveButton = new JButton("Save Design");
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
        c.gridx = 2;
        c.gridy = 0;
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setForeground(Color.blue);
        c.insets = new Insets(10,70,5,30);
        window.add(logout,c);
        logout.addActionListener(this);
        Border borderBlack = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.lightGray, Color.blue);
        innerPane.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(15,20,5,20);
        innerPane.add(new JLabel("Game Board Size "),c);
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(15,0,5,0);
        innerPane.add(size1,c);
        c.gridx = 2;
        c.gridy = 0;
        innerPane.add(new JLabel("  x  "),c);
        c.gridx = 3;
        c.gridy = 0;
        innerPane.add(size2,c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(new JLabel("Number of Players "),c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(players,c);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(new JLabel("Turn Time (seconds) "),c);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(turnTime,c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(new JLabel("Game Time (minutes)"),c);
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(gameTime,c);
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(new JLabel("Number of Pieces"),c);
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(5,0,5,0);
        innerPane.add(pieces,c);
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
        if(ae.getSource() == logout){
          //logout user  
        }
        else if(ae.getSource() == backButton){
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
