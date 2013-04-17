package gameDesigner;

import application.GameFunctions;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.Game;

public final class EditBoard implements ActionListener {    
    private Game eBoard = new Game();
    private GameFunctions gf = new GameFunctions();
    private int sizeX = 40;
    private int sizeY = 40;
    private int elements[][] = new int[sizeX][sizeY];
    private JButton buttons[][] = new JButton[sizeX][sizeY];
    private JFrame window = new JFrame("Edit Blokus Board");
    private JButton saveGame = new JButton("Save Board");
    private JButton backGame = new JButton("Back");
    
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
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j].setOpaque(true);
                c.gridx = i;
                c.gridy = j;
                buttons[i][j].setToolTipText("Click to block square in game");
                window.add(buttons[i][j],c);
                buttons[i][j].addActionListener(this);
            }
        }
        c.ipadx = 5;
        c.gridx = 0;
        c.gridy = this.sizeX+1;
        c.gridwidth = this.sizeX/2;
        window.add(backGame, c);
        backGame.setName("Back");
        backGame.setToolTipText("Back to Main Edit Game");
        backGame.addActionListener(this);
        c.gridx = this.sizeX-this.sizeX/2;
        c.gridy = this.sizeY+1;
        saveGame.setName("Save"); 
        window.add(saveGame, c);
        saveGame.addActionListener(this);
        window.pack();
    }
    
    public EditBoard(Game game){
        eBoard = game;
        eBoard.setSizeX(game.getSizeX());
        eBoard.setSizeY(game.getSizeY());
        eBoard.setButtons(game.getButtons());
        eBoard.setButtons(new JButton[eBoard.getSizeX()][eBoard.getSizeY()]);
        eBoard.setElements(new int[eBoard.getSizeX()][eBoard.getSizeY()]);
        String myElements = gf.getElementGame(eBoard.getName());
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
                buttons[x][y] = new JButton("");
                buttons[x][y].setEnabled(true);
                elements[x][y] = Character.getNumericValue(charArray[i]);
                if(charArray[i] == '1'){
                    buttons[x][y].setBackground(Color.red);
                }
                y++;
            }
            else{
                i = charArray.length;
            }
        }
        
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        if(eBoard.getSizeX()==40){
            c.ipady = 5;
        }
        else if(eBoard.getSizeX()==35){
            c.ipady = 6;
        }
        else if(eBoard.getSizeX()==30){
            c.ipady = 7;
        }
        else if(eBoard.getSizeX()==25){
            c.ipady = 8;
        }
        else if(eBoard.getSizeX()==20){
            c.ipady = 10;
        }
        c.ipadx = -10;
        c.fill = GridBagConstraints.HORIZONTAL;
        for(int i=0; i < game.getSizeX(); i++){
            for(int j=0; j < game.getSizeY(); j++){
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
        c.gridy = game.getSizeY()+1;
        c.gridwidth = 10;
        window.add(backGame, c);
        backGame.addActionListener(this);
        c.gridx = game.getSizeX()-5;
        c.gridy = game.getSizeY()+1;
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
            ModifyGameDesign game = new ModifyGameDesign();
            game.run(eBoard);
        }
        else if(ae.getSource() == saveGame){
            String e = null;
            for(int i=0; i < eBoard.getSizeX(); i++){
                for(int j=0; j < eBoard.getSizeY(); j++){
                    if(e == null){
                         e = Integer.toString(elements[i][j]);
                    }
                    else{
                        e += elements[i][j];
                    }
                }
            }
            eBoard.setE(e);
            Boolean saved = gf.updateBoard(eBoard);
            
            if(saved){
                JOptionPane.showMessageDialog(null, "Game Board has been Saved!");
            }
            else{
                JOptionPane.showMessageDialog(null, "There is an error in saving your game!");
            }
            //save board to database
            //game.setElement = this.elements;
           // ModifyGameDesign game = new ModifyGameDesign(eBoard);
           // game.run();
        }
        else{
            for(int i = 0; i < eBoard.getSizeX(); i++){
                for(int j = 0; j < eBoard.getSizeY(); j++){
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