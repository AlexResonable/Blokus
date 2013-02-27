/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus;

import javax.swing.JButton;

/**
 *
 * @author kamijean2
 */
public class Board {
    private int sizeX = 20;
    private int sizeY = 20;
    private Boolean elements[][] = new Boolean[sizeX][sizeY];
    private JButton buttons[][] = new JButton[sizeX][sizeY];

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public Boolean[][] getElements() {
        return elements;
    }

    public void setElements(Boolean[][] elements) {
        this.elements = elements;
    }
    
    public int getSizeX() {
        return sizeX;
    }

    private void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }

    private void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
    
    public Board(){
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j] = new JButton();
                elements[i][j] = false;
            }
        }
    }
    public Board(int sizeX, int sizeY){
        this.setSizeX(sizeX);
        this.setSizeY(sizeY);
        for(int i=0; i < sizeX; i++){
            for(int j=0; j < sizeY; j++){
                buttons[i][j] = new JButton("");
                elements[i][j] = false;
            }
        }
    }
}
