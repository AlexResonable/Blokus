/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import database.Database;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamijean2
 */
public class GameFunctions {
    
    public HashMap<String, String> getGames(){
        HashMap<String, String> games = new HashMap<String, String>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT id, name FROM games;");
            while (rs.next()) {
                games.put(rs.getString("id"),rs.getString("name"));
            } 
            rs.close();
            stat.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println("Connection not established or user not found");
            try {
                stat.close();
                rs.close();
                conn.close();
            } catch (SQLException ex1) {
                Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return null;
        }  
        return games;
    }
    
    public String[] getGameInfo(String name){
        String[] gameInfo = new String[9];
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM games WHERE name = '" + name + "';");
            while (rs.next()) {
                gameInfo[0] = rs.getString("name");
                gameInfo[1] = rs.getString("boardSizeX");
                gameInfo[2] = rs.getString("boardSizeY");
                gameInfo[3] = rs.getString("playerNo");
                gameInfo[4] = rs.getString("turnTime");
                gameInfo[5] = rs.getString("gameTime");
                gameInfo[6] = rs.getString("pieceNo");
                gameInfo[7] = rs.getString("elements");
                gameInfo[8] = rs.getString("mode");
            }
            rs.close();
            stat.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println("Connection not established or user not found");
            try {
                stat.close();
                rs.close();
                conn.close();
            } catch (SQLException ex1) {
                Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return null;
        }  
        return gameInfo;
    }
    
    public Boolean updateGame(String name, int boardX, int boardY, int playerNo, int turnTime, int gameTime, int pieceNo, String elements, int mode){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM users WHERE username = '" + name + "';");

            if(!rs.next()){
                stat.executeUpdate("INSERT INTO users (name,boardSizeX,boardSizeX,playerNo,turnTime,gameTime,pieceno,elements,mode) VALUES ('" + name + "','" + boardX + "','"+ boardY +"','" + playerNo + "','"+ turnTime +"')");
                conn.setAutoCommit(true);
            }
            else{
                //stat.executeUpdate("UPDATE users SET username = '" + username + "', password = '" + password + "', role = '" + role + "' WHERE username = '" + oldUsername + "'");
                conn.setAutoCommit(true);
            }
            rs.close();
            stat.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println("Connection not established or user not found");
            try {
                stat.close();
                rs.close();
                conn.close();
            } catch (SQLException ex1) {
                Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }  
        return true;
    }
    
    public Boolean deleteGame(String username){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            stat.executeUpdate("DELETE FROM users WHERE username = '" + username + "';");
            rs = stat.executeQuery("SELECT username FROM users WHERE username = '" + username + "';");
            Boolean hasNext = true;
            if(!rs.next()){
                hasNext = false;
            }
            rs.close();
            stat.close();
            conn.close();
            
            if(hasNext) 
                return false;
            else
                return true;
        }
        catch (SQLException ex) {
            System.out.println("Connection not established or user not found");
            try {
                stat.close();
                rs.close();
                conn.close();
            } catch (SQLException ex1) {
                Logger.getLogger(UserFunctions.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }  
    }
    
}