/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameDesigner;

import application.GameFunctions;
import systemAdministrator.Login;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import models.Game;

/**
 *
 * @author kamijean2
 */
public class ModifyGameDesign implements ActionListener {
    private Game board = new Game();
    private GameFunctions gf = new GameFunctions();
    private JFrame window = new JFrame("Modify Game Design"); 
    private JLabel header = new JLabel("Modify Game Board");
    
    private JComboBox gameList = new JComboBox();
    //private String[] modes = {"Easy", "Medium", "Hard"};
    //private JComboBox modeList = new JComboBox(modes);
    private JButton logout = new JButton("Logout");
    private JTextField name = new JTextField();
    private SpinnerModel gameSizeModel = new SpinnerNumberModel(20, 20, 40, 5);
    private JSpinner size1 = new JSpinner(gameSizeModel);
    private JSpinner size2 = new JSpinner(gameSizeModel);
    private SpinnerModel turnModel = new SpinnerNumberModel(10, 3, 99, 1);
    private JSpinner turnTime = new JSpinner(turnModel);
    private SpinnerModel gameModel = new SpinnerNumberModel(5, 5, 120, 1);
    private JSpinner gameTime = new JSpinner(gameModel);
    private SpinnerModel pieceModel = new SpinnerNumberModel(21, 21, 120, 1);
    private JSpinner pieces = new JSpinner(pieceModel);
    private JRadioButton easy = new JRadioButton("Easy");
    private JRadioButton medium = new JRadioButton("Medium");
    private JRadioButton hard = new JRadioButton("Hard");
    private ButtonGroup mode = new ButtonGroup();
    private JButton boardButton = new JButton("Edit Board");
    private JButton createNewButton = new JButton("New");
    private JButton saveButton = new JButton("Save Design");
    private JButton deleteButton = new JButton("Delete");
    private JLabel errorField = new JLabel(" ");
    private JPanel innerPane = new JPanel();
    
    public void run(Game game){
        this.createDesignerMain(game);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    
        int width = window.getSize().width;
        int height = window.getSize().height;
        int x = (d.width-width)/2;
        int y = (d.height-height)/2;
    
        window.setLocation(x, y);
        window.setVisible(true);
    }
    public ModifyGameDesign(){   
        size1 = new JSpinner(gameSizeModel);
        size2 = new JSpinner(gameSizeModel);
    }    
    
    private void createDesignerMain(Game game){
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
        JPanel nameMode = new JPanel(new GridBagLayout());
        nameMode.setBorder(borderBlack);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(15,30,5,0);
        JLabel j1 = new JLabel("Choose Game ");
        j1.setToolTipText("Choose game to modify");
        j1.setName("Choose Designed Game ");
        innerPane.add(j1,c);
       
        HashMap<String, String> games = gf.getGames();
        ArrayList currentUserList = new ArrayList();
        currentUserList.add("");
        int i = 0, location = 0;
        for (String value : games.values()) 
        {
            currentUserList.add(value);
            if(value.equals(game.getName())){
                location = i+1;
            }
            ++i;
        }
        gameList = new JComboBox(currentUserList.toArray());
        gameList.setSelectedIndex(location);
        gameList.setOpaque(true);
        gameList.addActionListener(this);
        
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(15,0,5,50);
        nameMode.add(gameList);
        
        //c.gridx = 2;
        //c.gridy = 0;
        //c.insets = new Insets(10,20,10,0);
        //nameMode.add(new JLabel("Mode "),c);
        //modeList.setOpaque(true);
        //modeList.addActionListener(this);
        
        //c.gridx = 3;
        //c.gridy = 0;
        //c.insets = new Insets(15,15,5,10);
       // nameMode.add(modeList);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 5;
        c.insets = new Insets(15,5,5,5);
        innerPane.add(nameMode,c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5,5,5,5);
        innerPane.add(errorField,c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(10,30,0,20);
        innerPane.add(new JLabel("Game Name "),c);
        
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 3;
        c.insets = new Insets(10,0,0,0);
        name.setEnabled(false);
        name.setToolTipText("Type game name, can't be blank");
        innerPane.add(name,c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(15,30,5,20);
        JLabel j2 = new JLabel("Game Board Size ");
        j2.setName("Game Board Size ");
        innerPane.add(j2,c); 
        
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(15,0,5,0);
        size1.setEnabled(false);
        size1.setToolTipText("Choose a number from 20 to 40");
        JFormattedTextField txt = ((JSpinner.NumberEditor) size1.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
        innerPane.add(size1,c);
        
        c.gridx = 2;
        c.gridy = 3;
        JLabel j3 = new JLabel("  x  ");
        j3.setName("  x  ");
        innerPane.add(j3,c); 
        
        c.gridx = 3;
        c.gridy = 3;
        c.insets = new Insets(15,0,5,10);
        size2.setEnabled(false);
        size1.setToolTipText("Choose a number from 20 to 40");
        JFormattedTextField txt2 = ((JSpinner.NumberEditor) size2.getEditor()).getTextField();
        ((NumberFormatter) txt2.getFormatter()).setAllowsInvalid(false);
        innerPane.add(size2,c);
        
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(5,30,5,20);
        JLabel j5 = new JLabel("Turn Time (seconds) ");
        j5.setName("Turn Time (seconds) ");
        innerPane.add(j5,c); 
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(5,0,5,0);
        turnTime.setEnabled(false);
        turnTime.setToolTipText("Pick seconds from 3 to 99");
        JFormattedTextField txt3 = ((JSpinner.NumberEditor) turnTime.getEditor()).getTextField();
        ((NumberFormatter) txt3.getFormatter()).setAllowsInvalid(false);
        innerPane.add(turnTime,c);
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(5,30,5,20);
        innerPane.add(new JLabel("Game Time (minutes)"),c);JLabel j6 = new JLabel("Game Time (minutes)");
        j6.setName("Game Time (minutes)");
        innerPane.add(j6,c);c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(5,0,5,0);
        gameTime.setToolTipText("Pick minutes from 5 to 120");
        JFormattedTextField txt4 = ((JSpinner.NumberEditor) gameTime.getEditor()).getTextField();
        ((NumberFormatter) txt4.getFormatter()).setAllowsInvalid(false);
        gameTime.setEnabled(false);
        innerPane.add(gameTime,c);
        
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(5,30,5,20);
        JLabel j7 = new JLabel("Number of Pieces");
        j7.setName("Number of Pieces");
        innerPane.add(j7,c);
        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(5,0,5,0);
        pieces.setToolTipText("Pick a number 21 to 42");
        JFormattedTextField txt5 = ((JSpinner.NumberEditor) gameTime.getEditor()).getTextField();
        ((NumberFormatter) txt5.getFormatter()).setAllowsInvalid(false);
        pieces.setEnabled(false);
        innerPane.add(pieces,c);
        
        JPanel gameMode = new JPanel(new GridBagLayout());
        TitledBorder title = BorderFactory.createTitledBorder("Game Mode");
        gameMode.setBorder(title);
        if(game.getMode() == 1){
             medium.setSelected(true);
             medium.setToolTipText("Choose if game moderately easy");
        }
        else if(game.getMode() == 2){
             hard.setSelected(true);
             hard.setToolTipText("Choose if game is challenging");
        }
        else{
             easy.setSelected(true);
             easy.setToolTipText("Choose if game is easy");
        }
        mode.add(easy);
        mode.add(medium);
        mode.add(hard);
        easy.setEnabled(false);
        medium.setEnabled(false);
        hard.setEnabled(false);
        
        // "Easy" button
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,0,5,5);
        gameMode.add(easy,c);
        
        // "Medium" button
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(5,0,5,5);
        gameMode.add(medium,c);
        
        //"Hard" button
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(5,0,5,5);
        gameMode.add(hard,c);
        
         // add "Game Mode" to inner panel
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 4;
        c.insets = new Insets(10,55,5,20);
        innerPane.add(gameMode,c);
        
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 3;
        c.insets = new Insets(20,0,15,130);
        innerPane.add(boardButton,c);
        boardButton.setEnabled(false);
        boardButton.setToolTipText("Click here to block squares on board");
        boardButton.setName("Edit Board");
        boardButton.addActionListener(this);
        
        c.gridx=2;
        c.gridy=8;
        c.insets = new Insets(20,50,15,0);
        deleteButton.setName("Delete"); 
        deleteButton.setForeground(Color.red);
        deleteButton.addActionListener(this);
        deleteButton.setEnabled(false);
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
        saveButton.setEnabled(false);
        saveButton.setName("Save");
        window.add(saveButton, c);
        saveButton.addActionListener(this);
        size1.setName("game size1"); 
        size2.setName("game size2"); 
        turnTime.setName("turnTime"); 
        gameTime.setName("GameTime"); 
        pieces.setName("pieces numbers");
        if(!"".equals(game.getName())){
            name.setText(game.getName());
            name.setEnabled(true);
            size1.setValue(game.getSizeX());
            size1.setEnabled(true);
            size2.setValue(game.getSizeY());
            size2.setEnabled(true);
            turnTime.setValue(game.getTurnTime());
            turnTime.setEnabled(true);
            gameTime.setValue(game.getGameTime());
            gameTime.setEnabled(true);
            pieces.setValue(game.getPieceNumber());
            pieces.setEnabled(true);
            if(game.getMode() == 0){
                easy.setSelected(true);
            }
            else if(game.getMode() == 1){
                medium.setEnabled(true);
            }
            else{
                hard.setEnabled(true);
            }
            easy.setEnabled(true);
            medium.setEnabled(true);
            hard.setEnabled(true);
            saveButton.setEnabled(true);
            deleteButton.setEnabled(true);
            boardButton.setEnabled(true);
        }
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
        else if(ae.getSource() == createNewButton){
            gameList.setSelectedIndex(0);
            name.setText("");
            name.setEnabled(true);
            size1.setEnabled(true);
            size2.setEnabled(true);
            turnTime.setEnabled(true);
            gameTime.setEnabled(true);
            pieces.setEnabled(true);
            easy.setEnabled(true);
            medium.setEnabled(true);
            hard.setEnabled(true);
            saveButton.setEnabled(true);
            deleteButton.setEnabled(false);
            boardButton.setEnabled(true); 
        }
        else if(ae.getSource() == boardButton){
            //take to board where you can edit which squares are blocked
            if("".equals(name.getText())){
                errorField.setText("<HTML><FONT COLOR = Red>Please enter a name before you edit the board.</FONT></HTML>");
            }
            else{
                window.dispose();//setVisible(false);
                board = new Game((Integer)size1.getValue(), (Integer)size2.getValue());
                if(!"".equals(gameList.getSelectedItem().toString())){
                    board.setName(gameList.getSelectedItem().toString());
                }
                else{
                    board.setName(name.getText());
                }
                board.setGameTime((Integer)gameTime.getValue());
                board.setPieceNumber((Integer)pieces.getValue());
                board.setTurnTime((Integer)turnTime.getValue());
                EditBoard editBoard = new EditBoard(board);
                editBoard.run();
            }
        }
        else if(ae.getSource() == saveButton){
            String oldGameName = gameList.getSelectedItem().toString();
            Game newSettings = new Game();
            newSettings.setName(name.getText());
            newSettings.setSizeX(Integer.parseInt(size1.getValue().toString()));
            newSettings.setSizeY(Integer.parseInt(size2.getValue().toString()));
            newSettings.setTurnTime(Integer.parseInt(turnTime.getValue().toString()));
            newSettings.setGameTime(Integer.parseInt(gameTime.getValue().toString()));
            
            int m = 0;
            if(easy.isSelected()){
                m = 0;
            }
            else if(medium.isSelected()){
                m = 1;
            }
            else if(hard.isSelected()){
                m = 2;
            }
            newSettings.setMode(m);
            Boolean saved = gf.updateGame(newSettings, oldGameName);
            if(saved){
                if(!oldGameName.equals(newSettings.getName())){
                    if(!oldGameName.equals("")){
                        gameList.removeItemAt(gameList.getSelectedIndex());
                    }
                    gameList.addItem(newSettings.getName());
                    gameList.setSelectedItem(newSettings.getName());
                }
                errorField.setText("<HTML><FONT COLOR = Green>" + newSettings.getName() + " has been saved!</FONT></HTML>");
            }
            else{
                errorField.setText("<HTML><FONT COLOR = Red>Game has not been saved!</FONT></HTML>");
            }
            
        }
        else if(ae.getSource() == gameList){
            String game = gameList.getSelectedItem().toString();
            if(game.equals("")){
                name.setText("");
                name.setEnabled(false);
                size1.setEnabled(false);
                size2.setEnabled(false);
                turnTime.setEnabled(false);
                gameTime.setEnabled(false);
                pieces.setEnabled(false);
                easy.setEnabled(false);
                medium.setEnabled(false);
                hard.setEnabled(false);
                saveButton.setEnabled(false);
                deleteButton.setEnabled(false);
                boardButton.setEnabled(false);             
            }
            else{
                Game gameInfo = new Game();
                gameInfo = gf.getGameInfo(game);
                name.setText(gameInfo.getName());
                name.setEnabled(true);
                size1.setValue(gameInfo.getSizeX());
                size1.setEnabled(true);
                size2.setValue(gameInfo.getSizeY());
                size2.setEnabled(true);
                turnTime.setValue(gameInfo.getTurnTime());
                turnTime.setEnabled(true);
                gameTime.setValue(gameInfo.getGameTime());
                gameTime.setEnabled(true);
                pieces.setValue(gameInfo.getPieceNumber());
                pieces.setEnabled(true);
                if(gameInfo.getMode() == 2){
                    hard.setSelected(true);
                }
                else if(gameInfo.getMode() == 1){
                    medium.setSelected(true);
                }
                else{
                    easy.setSelected(true);
                }
                easy.setEnabled(true);
                medium.setEnabled(true);
                hard.setEnabled(true);
                saveButton.setEnabled(true);
                deleteButton.setEnabled(true);
                boardButton.setEnabled(true);  
            }
        }
        else if(ae.getSource() == deleteButton){
            String thisName = gameList.getSelectedItem().toString();
            Boolean deleted = gf.deleteGame(thisName);
            if("".equals(thisName)){
                deleted = false;
            }
            if(deleted){
                gameList.removeItemAt(gameList.getSelectedIndex());
                gameList.setSelectedIndex(0);
                deleteButton.setEnabled(false);
                errorField.setText("<HTML><FONT COLOR = Green>Game has been deleted!</FONT></HTML>");
            }
            else{
                errorField.setText("<HTML><FONT COLOR = Red>Game has not been deleted!</FONT></HTML>");   
            }
            
        }
    }
    public JFrame getWindow()
    {
      return window;
    } 
    
}
