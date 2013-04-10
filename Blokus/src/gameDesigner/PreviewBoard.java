package gameDesigner;

import blokus5100.GameWizard;
import gameDesigner.setBoard;
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

public final class PreviewBoard implements ActionListener {    

    private int sizeX = 20;
    private int sizeY = 20;
    private Boolean elements[][] = new Boolean[sizeX][sizeY];
    private JButton buttons[][] = new JButton[sizeX][sizeY];
    private JFrame window = new JFrame("Edit Blokus Board");
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
    
    public PreviewBoard(){
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j] = new JButton();
                buttons[i][j].setEnabled(false);
                buttons[i][j].setOpaque(true);
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
                c.gridx = i;
                c.gridy = j;
                window.add(buttons[i][j],c);
            }
        }
        c.ipadx = 5;
        c.gridx = 0;
        c.gridy = this.getSizeY()+1;
        c.gridwidth = this.getSizeX()/2;
        window.add(backGame, c);
        backGame.setName("Back");
        backGame.addActionListener(this);
        window.pack();
    }
    
    public PreviewBoard(setBoard game){
        JButton myButtons[][] = game.getButtons();
        int myElements[][] = game.getElements();
        int mySizeX = game.getSizeX();
        int mySizeY = game.getSizeY();
        for(int i=0; i < mySizeX; i++){
            for(int j=0; j < mySizeY; j++){
                myButtons[i][j] = new JButton("");
                myButtons[i][j].setEnabled(false);
                myButtons[i][j].setOpaque(true);
                if(myElements[i][j] == 1){
                    myButtons[i][j].setBackground(Color.red);
                }
            }
        }
        
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        for(int i=0; i < mySizeX; i++){
            for(int j=0; j < mySizeY; j++){
                c.gridx = i;
                c.gridy = j;
                window.add(myButtons[i][j],c);
            }
        }
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = mySizeY+1;
        c.gridwidth = 10;
        window.add(backGame, c);
        backGame.addActionListener(this);
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
            window.setVisible(false);
            GameWizard back = new GameWizard();
            back.run();
        }
    }
    
    public JFrame getFrame()
    {
    	return window;
    }
}