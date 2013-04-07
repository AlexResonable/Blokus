/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.PlayerScore;
/**
 *
 * @author Stacy
 */

public class HighScoresAccess {
    
    int SIZE = 100;
    int DISPLAY_NUM = 10;
    PlayerScore[] topTenHighScores = new PlayerScore[DISPLAY_NUM];
    PlayerScore[] highScores = new PlayerScore[SIZE];
    PlayerScore playerScore;
    
    
    public HighScoresAccess()
    {
        for(int i = 0; i < SIZE; i++)
        {
            highScores[i] = new PlayerScore("", 0);
        }
    }
    
    private void sort() 
    {
        boolean swapped = true;
        int j = 0;
        PlayerScore tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < highScores.length - j; i++) {

                  if (highScores[i].getScore() < highScores[i + 1].getScore()) {                          
                        tmp = highScores[i];
                        highScores[i] = highScores[i + 1];
                        highScores[i + 1] = tmp;
                        swapped = true;
                  }
                
            }                
        }
    }
    private void print()
    {
        
        for(PlayerScore i: topTenHighScores)
        {
            System.out.println(i);
        }
    }
    
    //returns a sorted array of scores from the database.
    public PlayerScore[] getHighScores(){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM highscore");
            int i = 0;  //counter to store PlayerScore obejcts in array.
            while(rs.next() && i < SIZE)
            {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                
                //System.out.printf("%s  %d i is %d\n", name, score, i);
                highScores[i].setName(name);
                highScores[i].setScore(score);
                i++;    
            }
            stat.close();
            rs.close();
            conn.close();
            
            
            //sort array
            this.sort();

            topTenHighScores = highScores;
            //this.print();

        }
        catch (SQLException ex) {
            System.out.println("Connection not established or highScore not found");
            try {
                stat.close();
                rs.close();
                conn.close();
            } catch (SQLException ex1) {
                Logger.getLogger(HighScoresAccess.class.getName()).log(Level.SEVERE, null, ex1);
            } 
        }
        
        
        return topTenHighScores;
        
    }
    
    public void setHighScore(String name, int score)
    {
        Connection conn = null;
        Statement stat = null;

        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            stat.executeUpdate("INSERT INTO highscore (name, score) VALUES ('" + name + "'," + score + ")");
            conn.setAutoCommit(true);

            stat.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println("Connection not established or highScore not found");
            try {
                stat.close();
                conn.close();
            } catch (SQLException ex1) {
                Logger.getLogger(HighScoresAccess.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }  
    }
}
