/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameDesigner;

import javax.swing.JButton;

/**
 *
 * @author kamijean2
 */
public class setBoard {
    private int sizeX=100;
    private int sizeY=100;
    private int turnTime = 10;
    private int playerNumber = 4;
    private int gameTime = 30;
    private int pieceNumber = 21;
    private int elements[][] = new int[getSizeX()][getSizeY()];
    private JButton buttons[][] = new JButton[getSizeX()][getSizeY()];

    
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

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
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
    
    public setBoard(){
        this.setSizeX(20);
        this.setSizeY(20);
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j] = new JButton();
                elements[i][j] = 0;
            }
        }
    }
    public setBoard(int sizeX, int sizeY){
        this.setSizeX(sizeX);
        this.setSizeY(sizeY);
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j] = new JButton("");
                elements[i][j] = 0;
            }
        }
    }
}
