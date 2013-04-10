package gameDesigner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class EditBoard implements ActionListener {    
    private setBoard eBoard = new setBoard();
    private int sizeX = 20;
    private int sizeY = 20;
    private int elements[][] = new int[sizeX][sizeY];
    private JButton buttons[][] = new JButton[sizeX][sizeY];
    private JFrame window = new JFrame("Edit Blokus Board");
    private JButton saveGame = new JButton("Save Board");
    private JButton backGame = new JButton("Back");

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public int[][] getElements() {
        return elements;
    }

    public void setElements(int[][] elements) {
        this.elements = elements;
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }
    public int getSizeY() {
        return this.sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
    
    public EditBoard(){
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j] = new JButton();
                buttons[i][j].setName("i"+i+" j"+j);
                elements[i][j] = 0;
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
        backGame.setName("Back");
        backGame.addActionListener(this);
        c.gridx = this.getSizeX()-this.getSizeX()/2;
        c.gridy = this.getSizeY()+1;
        saveGame.setName("Save");
        window.add(saveGame, c);
        saveGame.addActionListener(this);
        window.pack();
    }
    
    public EditBoard(setBoard game){
        eBoard = game;
        System.out.println(game.getTurnTime() + " " + game.getGameTime());
        System.out.println(eBoard.getTurnTime() + " " + eBoard.getGameTime());
        this.setSizeX(game.getSizeX());
        this.setSizeY(game.getSizeY());
        this.setButtons(game.getButtons());
        int myElements[][] = game.getElements();
        for(int i=0; i < getSizeX(); i++){
            for(int j=0; j < getSizeY(); j++){
                buttons[i][j] = new JButton("");
                buttons[i][j].setEnabled(true);
                if(myElements[i][j] == 1){
                    buttons[i][j].setBackground(Color.red);
                }
            }
        }
        
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(sizeX > 95 || sizeY > 95){
            c.ipadx = -27;
            c.ipady = -4;
        }
        else if(sizeX > 85 || sizeY > 85){
            c.ipadx = -27;
            c.ipady = -4;
        }
        else if(sizeX > 75 || sizeY > 75){
            c.ipadx = -25;
            c.ipady = -2;
        }
        else if(sizeX > 65 || sizeY > 65){
            c.ipadx = -25;
            c.ipady = -2;
        }
        else if(sizeX > 55 || sizeY > 55){
            c.ipadx = -24;
            c.ipady = -1;
        }
        else if(sizeX > 45 || sizeY > 45){
            c.ipadx = -24;
            c.ipady = -1;
        }
        else if(sizeX > 35 || sizeY > 35){
            c.ipadx = -20;
            c.ipady = 3;
        }
        else if(sizeX > 25 || sizeY > 25){
            c.ipadx = -24;
            c.ipady = -1;
        }
        else if(sizeX > 15 || sizeY > 15){
            c.ipadx = -10;
            c.ipady = 10;
        }
        
        c.fill = GridBagConstraints.HORIZONTAL;
        for(int i=0; i < getSizeX(); i++){
            for(int j=0; j < getSizeY(); j++){
                buttons[i][j].setOpaque(true);
                c.gridx = i;
                c.gridy = j;
                window.add(buttons[i][j],c);
                buttons[i][j].addActionListener(this);
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
            this.window.dispose();//setVisible(false);
            ModifyGameDesign game = new ModifyGameDesign(eBoard);
            game.run();
        }
        else if(ae.getSource() == saveGame){
            //save game
            //this.window.setVisible(false);
            JOptionPane.showMessageDialog(null, "Game Board has been Saved!");
            //save board to database
            //game.setElement = this.elements;
           // ModifyGameDesign game = new ModifyGameDesign(eBoard);
           // game.run();
        }
        else{
            for(int i = 0; i < getSizeX(); i++){
                for(int j = 0; j < getSizeY(); j++){
                    if (ae.getSource() == buttons[i][j]) {
                        if(elements[i][j] == 1){
                            buttons[i][j].setBackground(null);
                            elements[i][j] = 0; 
                        }
                        else{
                            buttons[i][j].setBackground(Color.RED);
                            buttons[i][j].setOpaque(true);
                            elements[i][j] = 1; 
                        }
                    }
                }
            }
        }
    }
    
    public JFrame getFrame()
    {
    	return window;
    }
}