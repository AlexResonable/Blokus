package gameDesigner;

import application.GameFunctions;
import blokus5100.GameWizard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import models.Game;

public final class PreviewBoard implements ActionListener {    
    
    private Game eBoard = new Game();
    private int sizeX = 20;
    private int sizeY = 20;
    private int elements[][] = new int[sizeX][sizeY];
    private JButton buttons[][] = new JButton[sizeX][sizeY];
    private JFrame window = new JFrame("Edit Blokus Board");
    private JButton backGame = new JButton("Back");
    private GameFunctions gf = new GameFunctions();
    
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
                elements[i][j] = 0;
            }
        }
        
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(sizeX==40){
            c.ipady = 5;
        }
        else if(sizeX==35){
            c.ipady = 6;
        }
        else if(sizeX==30){
            c.ipady = 7;
        }
        else if(sizeX==25){
            c.ipady = 8;
        }
        else if(sizeX==20){
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
    
    public PreviewBoard(Game game){
        eBoard = game;
        eBoard.setName(game.getName());
        eBoard.setMode(game.getMode());
        JButton myButtons[][] = game.getButtons();
        elements = new int[game.getSizeX()][game.getSizeY()];
        String previewElements = gf.getElementGame(game.getName());
        char[] charArray = previewElements.toCharArray();
        int x=0, y=0;
        if(game.getSizeX()*game.getSizeY() != charArray.length){
            for(int i=0;i<game.getSizeX();i++){
                for(int j=0;j<game.getSizeY();j++){
                    myButtons[i][j].setEnabled(false);
                }
            }
        }
        else{
            for(int i=0; i < charArray.length; i++){
                if(y >= game.getSizeY()){
                    y = 0;
                    x++;
                }
                if(x < game.getSizeX()){
                    myButtons[x][y].setEnabled(false);
                    elements[x][y] = Character.getNumericValue(charArray[i]);
                    if(charArray[i] == '1'){
                        myButtons[x][y].setBackground(Color.red);
                    }
                    y++;
                }
                else{
                    i = charArray.length;
                }
            }
        }
        game.setElements(elements);
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(sizeX==40){
            c.ipady = 5;
        }
        else if(sizeX==35){
            c.ipady = 6;
        }
        else if(sizeX==30){
            c.ipady = 7;
        }
        else if(sizeX==25){
            c.ipady = 8;
        }
        else if(sizeX==20){
            c.ipady = 10;
        }
        
        c.fill = GridBagConstraints.HORIZONTAL;
        for(int i=0; i < game.getSizeX(); i++){
            for(int j=0; j < game.getSizeY(); j++){
                c.gridx = i;
                c.gridy = j;
                window.add(myButtons[i][j],c);
            }
        }
        c.ipadx = 5;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = game.getSizeY()+1;
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
            this.window.dispose();
            GameWizard back = new GameWizard();
            back.run(this.eBoard);
        }
    }
    public JFrame getFrame()
    {
      return window;
    } 
}