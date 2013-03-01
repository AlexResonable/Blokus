/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BlokusGUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author DreamTeam
 */
public class GameDesignerMain implements ActionListener{
    private JButton logout = new JButton("Logout");
    private JLabel header = new JLabel("Game Designer Main");
    private JButton newGame = new JButton("New");
    private JFrame window = new JFrame("System Login");
    private JPanel innerPane = new JPanel();
    private JLabel game1 = new JLabel("Normal Game");
    private JButton game1Edit = new JButton("Edit");
    private JButton game1Delete = new JButton("Delete");
    
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
    
    private void createDesignerMain(){
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10,40,0,0);
        header.setFont(new Font("Arial", Font.PLAIN, 24));
        window.add(header, c);
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 0;
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setForeground(Color.blue);
        c.insets = new Insets(10,80,5,30);
        window.add(logout,c);
        logout.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10,30,0,0);
        window.add(new Label("Game Name"),c);
        c.gridx = 2; 
        c.gridy = 1;
        c.ipadx = 5;
        c.ipady = 5;
        c.insets = new Insets(10,80,5,30);
        window.add(newGame, c);
        newGame.addActionListener(this);
        Border borderBlack = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.lightGray, Color.blue);
        innerPane.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,20,5,20);
        innerPane.add(game1,c);
        c.gridx = 1;
        innerPane.add(game1Edit,c);
        game1Edit.addActionListener(this);
        c.gridx = 2;
        game1Delete.setForeground(Color.red);
        innerPane.add(game1Delete,c);
        game1Delete.setEnabled(false);
        game1Delete.addActionListener(this);
        innerPane.setBorder(borderBlack);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.insets = new Insets(0,30,20,30);
        window.add(innerPane,c);
        //contentPane.setBackground(Color.GRAY);
        window.pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == logout){
            //logout user and take to main menu
            window.setVisible(false);
            MainMenu.createAndShowGUI();
        }
        else if(ae.getSource() == newGame){
            //open new game window
            window.setVisible(false);
            ModifyGameDesign game2 = new ModifyGameDesign(new setBoard());
            game2.run();
        }
        else if(ae.getSource() == game1Edit){
            //open edit new game window
            //pull info from database
            window.setVisible(false);
            //get board from database
            ModifyGameDesign game2 = new ModifyGameDesign(new setBoard());
            game2.run();
        }
        else if(ae.getSource() == game1Delete){
            //delete game from database
        }
    }
    
}
