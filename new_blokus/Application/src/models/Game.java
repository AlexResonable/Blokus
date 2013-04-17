/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.swing.JButton;

/**
 *
 * @author kamijean
 */
public class Game {
    private String name = "";
    private int mode;
    private int sizeX=40;
    private int sizeY=40;
    private int turnTime = 10;
    private int gameTime = 30;
    private int pieceNumber = 21;
    private int elements[][] = new int[sizeX][sizeY];
    private String e;
    private JButton buttons[][] = new JButton[sizeX][sizeY];

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public int getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
    
    public int[][] getElements() {
        return elements;
    }

    public void setElements(int[][] elements) {
        this.elements = elements;
    }
    
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
    
    public Game(){
        this.setSizeX(40);
        this.setSizeY(40);
        for(int i=0; i < this.sizeX; i++){
            for(int j=0; j < this.sizeY; j++){
                buttons[i][j] = new JButton();
                elements[i][j] = 0;
                if(e==null){e = "0";}
                else{e += 0;}
            }
        }
    }

    public Game(int sizeX, int sizeY){
        this.setSizeX(sizeX);
        this.setSizeY(sizeY);
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j] = new JButton("");
                elements[i][j] = 0;
                if(e==null){e = "0";}
                else{e += 0;}
            }
        }
    }
}