/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameDesigner;

import systemAdministrator.Login;
import gameDesigner.setBoard;
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
    setBoard board = new setBoard();
    int sizeX = 20;
    int sizeY = 20;
    int playerNum = 4;
    int turnNum = 10;
    int gameNum = 30;
    int pieceNum = 21;
    private JFrame window = new JFrame("Modify Game Design"); 
    private JLabel header = new JLabel("Modify Game Board");
    
    private JComboBox gameList = new JComboBox();
    private JComboBox difficultList = new JComboBox();
    
    private JButton logout = new JButton("Logout");
    private SpinnerModel gameSizeModel = new SpinnerNumberModel(getSizeX(), 20, 100, 10);
    private JSpinner size1 = new JSpinner(gameSizeModel);
    private SpinnerModel gameSizeModel2 = new SpinnerNumberModel(getSizeY(), 20, 100, 10);
    private JSpinner size2 = new JSpinner(gameSizeModel2);
    private SpinnerModel model = new SpinnerNumberModel(getPlayerNum(), 2, 4, 1);
    private JSpinner players = new JSpinner(model);
    private SpinnerModel turnModel = new SpinnerNumberModel(getTurnNum(), 3, 99, 1);
    private JSpinner turnTime = new JSpinner(turnModel);
    private SpinnerModel gameModel = new SpinnerNumberModel(getGameNum(), 5, 120, 1);
    private JSpinner gameTime = new JSpinner(gameModel);
    private SpinnerModel pieceModel = new SpinnerNumberModel(getPieceNum(), 21, 120, 1);
    private JSpinner pieces = new JSpinner(pieceModel);
    private JButton boardButton = new JButton("Edit Board");
    private JButton piecesButton = new JButton("Edit Pieces");
    private JButton createNewButton = new JButton("New");
    private JButton saveButton = new JButton("Save Design");
    private JButton deleteButton = new JButton("Detele");
    private JPanel innerPane = new JPanel();

    public int getGameNum() {
        return gameNum;
    }

    private void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }

    public int getPieceNum() {
        return pieceNum;
    }

    private void setPieceNum(int pieceNum) {
        this.pieceNum = pieceNum;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    private void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
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

    public int getTurnNum() {
        return turnNum;
    }

    private void setTurnNum(int turnNum) {
        this.turnNum = turnNum;
    }
    
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
    
    public ModifyGameDesign(setBoard game){
        this.setSizeX(game.getSizeX());
        this.setSizeY(game.getSizeY());
        this.setTurnNum(game.getTurnTime());
        this.setGameNum(game.getGameTime());
        this.setPlayerNum(game.getPlayerNumber());
        this.setPieceNum(game.getPieceNumber());
        this.board = game;
    }
    
    private void createDesignerMain(){
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10,40,0,60);
        header.setName("Modify Game Board");
        header.setFont(new Font("Arial", Font.PLAIN, 24));
        window.add(header, c);
        
        c.gridx=2;
        c.gridy=0;
        c.insets = new Insets(10,50,0,60);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setForeground(Color.blue);
        logout.addActionListener(this);
        logout.setName("Logout");
        window.add(logout, c);
        
        Border borderBlack = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.lightGray, Color.blue);
        innerPane.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth =2;
        c.insets = new Insets(15,20,5,0);
        JLabel j1 = new JLabel("Choose Designed Game ");
        j1.setName("Choose Designed Game ");
        innerPane.add(j1,c);
        
       
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(15,0,5,0);
        innerPane.add(gameList);
        
        c.gridx = 2;
        c.gridy =0;
        c.insets = new Insets(15, 0, 5, 0);
        innerPane.add(difficultList);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth =1;
        c.insets = new Insets(15,20,5,20);
        JLabel j2 = new JLabel("Game Board Size ");
        j2.setName("Game Board Size ");
        innerPane.add(j2,c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(15,0,5,0);
        size1.setValue(getSizeX());
        size1.setName("game size1");
        innerPane.add(size1,c);
        
        
        c.gridx = 2;
        c.gridy = 1;
        JLabel j3 = new JLabel("  x  ");
        j3.setName("  x  ");
        innerPane.add(j3,c);
        
        c.gridx = 3;
        c.gridy = 1;
        c.insets = new Insets(15,0,5,10);
        size2.setValue(getSizeY());
        size2.setName("game size2");
        innerPane.add(size2,c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5,20,5,20);
        JLabel j4 = new JLabel("Number of Players ");
        j4.setName("Number of Players ");
        innerPane.add(j4,c);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(5,0,5,0);
        players.setName("player numbers");
        players.setValue(getPlayerNum());
        innerPane.add(players,c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5,20,5,20);
        JLabel j5 = new JLabel("Turn Time (seconds) ");
        j5.setName("Turn Time (seconds) ");
        innerPane.add(j5,c);
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(5,0,5,0);
        turnTime.setValue(getTurnNum());
        turnTime.setName("turnTime");
        innerPane.add(turnTime,c);
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5,20,5,20);
        JLabel j6 = new JLabel("Game Time (minutes)");
        j6.setName("Game Time (minutes)");
        innerPane.add(j6,c);
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(5,0,5,0);
        gameTime.setName("GameTime");
        gameTime.setValue(getGameNum());
        innerPane.add(gameTime,c);
        
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(5,20,5,20);
        JLabel j7 = new JLabel("Number of Pieces");
        j7.setName("Number of Pieces");
        innerPane.add(j7,c);
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(5,0,5,0);
        pieces.setName("pieces numbers");
        pieces.setValue(getPieceNum());
        innerPane.add(pieces,c);
        
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 3;
        c.insets = new Insets(20,0,15,130);
        innerPane.add(boardButton,c);
        boardButton.setName("Edit Board");
        boardButton.addActionListener(this);
        
        c.gridx=2;
        c.gridy=6;
        c.insets = new Insets(20,50,15,0);
        deleteButton.setName("Delete");
        deleteButton.setForeground(Color.red);
        deleteButton.addActionListener(this);
        innerPane.add(deleteButton, c);
        
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
        window.add(createNewButton, c);
        createNewButton.setName("New");
        createNewButton.addActionListener(this);
        c.gridx = 2;
        c.gridy = 3;
        c.insets = new Insets(0,30,20,30);
        saveButton.setName("Save");
        window.add(saveButton, c);
        saveButton.addActionListener(this);
        window.pack();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == logout){
            //logout and send to main menu
            window.dispose();//setVisible(false);
            Login lg = new Login();
            lg.run();
            
        }
        else if(ae.getSource()== logout)
        {
        	window.dispose();
        	Login lg = new Login();
        	lg.run();
        }
        else if(ae.getSource() == createNewButton){
            //take back to main menu screen
            //setVisible(false);
           
        }
        else if(ae.getSource() == boardButton){
            //take to board where you can edit which squares are blocked
            window.dispose();//setVisible(false);
            board = new setBoard((Integer)size1.getValue(), (Integer)size2.getValue());
            board.setGameTime((Integer)gameTime.getValue());
            board.setPieceNumber((Integer)pieces.getValue());
            board.setPlayerNumber((Integer)players.getValue());
            board.setTurnTime((Integer)turnTime.getValue());
            EditBoard editBoard = new EditBoard(board);
            editBoard.run();
        }
        else if(ae.getSource() == saveButton){
            //save board to database and then take back to menu with it added to the GUI
          //  window.dispose();//setVisible(false);
            JOptionPane.showMessageDialog(null, "Game Board Options have been Saved!");
          //  GameDesignerMain game = new GameDesignerMain();
         //   game.run();
        }
    }
    
    public JFrame getWindow()
    {
    	return window;
    }
}
