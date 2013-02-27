/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus;


import java.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author kamijean2
 */
public final class EditBoard implements ActionListener {    

    private int sizeX = 20;
    private int sizeY = 20;
    private Boolean elements[][] = new Boolean[sizeX][sizeY];
    private JButton buttons[][] = new JButton[sizeX][sizeY];
    private JFrame window = new JFrame("Edit Blokus Board");
    private JButton saveGame = new JButton("Save Board");
    private JButton backGame = new JButton("Back");

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
    
    public EditBoard(){
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j] = new JButton();
                elements[i][j] = false;
            }
        }
        
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(sizeX > 85 || sizeY > 85){
            c.ipadx = -27;
            c.ipady = -4;
        }
        else if(sizeX > 75 || sizeY > 75){
            c.ipadx = -25;
            c.ipady = -2;
        }
        else if(sizeX > 55 || sizeY > 55){
            c.ipadx = -24;
            c.ipady = -1;
        }
        else if(sizeX > 35 || sizeY > 35){
            c.ipadx = -20;
            c.ipady = 3;
        }
        else if(sizeX > 15 || sizeY > 15){
            c.ipadx = -10;
            c.ipady = 10;
        }
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j].setOpaque(true);
                c.gridx = i;
                c.gridy = j;
                window.add(buttons[i][j],c);
                buttons[i][j].addActionListener(this);
            }
        }
        c.ipadx = 5;
        c.gridx = 0;
        c.gridy = this.getSizeY()+1;
        c.gridwidth = this.getSizeX()/2;
        window.add(backGame, c);
        backGame.addActionListener(this);
        c.gridx = this.getSizeX()-this.getSizeX()/2;
        c.gridy = this.getSizeY()+1;
        window.add(saveGame, c);
        saveGame.addActionListener(this);
        window.pack();
    }
    
    public EditBoard(Board game){
        this.setSizeX(game.getSizeX());
        this.setSizeY(game.getSizeY());
        JButton myButtons[][] = game.getButtons();
        Boolean myElements[][] = game.getElements();
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                myButtons[i][j] = new JButton("");
                myButtons[i][j].setEnabled(false);
                if(myElements[i][j] == true){
                    myButtons[i][j].setBackground(Color.red);
                }
            }
        }
        
        
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                myButtons[i][j].setOpaque(true);
                c.gridx = i;
                c.gridy = j;
                window.add(myButtons[i][j],c);
                myButtons[i][j].addActionListener(this);
            }
        }
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = this.getSizeY()+1;
        c.gridwidth = 10;
        window.add(backGame, c);
        backGame.addActionListener(this);
        c.gridx = this.getSizeX()-5;
        c.gridy = this.getSizeY()+1;
        window.add(saveGame, c);
        saveGame.addActionListener(this);
        window.pack();
    }
    
    public void run(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    
        int width = window.getSize().width;
        int height = window.getSize().height;
        int x = (d.width-width)/2;
        int y = (d.height-height)/2;
    
        window.setLocation(x, y);
        window.setVisible(true);
    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == backGame){
            //go back
        }
        else if(ae.getSource() == saveGame){
            //save game
        }
        else{
            for(int i = 0; i < sizeX; i++){
                for(int j = 0; j < sizeY; j++){
                    if (ae.getSource() == buttons[i][j]) {
                        if(elements[i][j]){
                            buttons[i][j].setBackground(null);
                            elements[i][j] = false; 
                        }
                        else{
                            buttons[i][j].setBackground(Color.RED);
                            buttons[i][j].setOpaque(true);
                            elements[i][j] = true; 
                        }
                    }
                }
            }
        }
    }
}