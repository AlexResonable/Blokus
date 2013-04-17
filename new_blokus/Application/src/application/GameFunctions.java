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
import models.Game;

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
            rs = stat.executeQuery("SELECT gameId, name FROM games;");
            while (rs.next()) {
                games.put(rs.getString("gameId"),rs.getString("name"));
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
    
    public HashMap<String, String> getModeGames(int mode){
        HashMap<String, String> games = new HashMap<String, String>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT gameId, name FROM games WHERE mode = '" + mode + "';");
            while (rs.next()) {
                games.put(rs.getString("gameId"),rs.getString("name"));
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
    
    public Game getGameInfo(String name){
        Game gameInfo = new Game();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM games WHERE name = '" + name + "';");
            while (rs.next()) {
                gameInfo.setName(rs.getString("name"));
                gameInfo.setSizeX(Integer.parseInt(rs.getString("boardSizeX")));
                gameInfo.setSizeY(Integer.parseInt(rs.getString("boardSizeY")));
                gameInfo.setTurnTime(Integer.parseInt(rs.getString("turnTime")));
                gameInfo.setGameTime(Integer.parseInt(rs.getString("gameTime")));
                gameInfo.setPieceNumber(Integer.parseInt(rs.getString("pieceNo")));
                gameInfo.setE(rs.getString("elements"));
                gameInfo.setMode(Integer.parseInt(rs.getString("mode")));
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
    
    public String getElementGame(String name){
        String element = null;
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM games WHERE name = '" + name + "';");
            while (rs.next()) {
                element = rs.getString("elements");
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
        return element;
    }
    
    public Boolean updateGame(Game game, String oldName){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM games WHERE name = '" + oldName + "';");

            if(!rs.next()){
                stat.executeUpdate("INSERT INTO games (name,boardSizeX,boardSizeY,turnTime,gameTime,pieceNo,elements,mode) VALUES ('" + game.getName() + "','" + game.getSizeX() + "','"+ game.getSizeY() +"','" + game.getTurnTime() +"','" + game.getGameTime() +"','" + game.getPieceNumber() + "','" + game.getE() + "','" + game.getMode() + "')");
                conn.setAutoCommit(true);
            }
            else{
                stat.executeUpdate("UPDATE games SET name = '" + game.getName() + "', boardSizeX = '" + game.getSizeX() + "', boardSizeY = '" + game.getSizeY() + "', turnTime = '" + game.getTurnTime() + "', gameTime = '" + game.getGameTime() + "', pieceno = '" + game.getPieceNumber() + "', elements = '" + game.getE() + "', mode = '" + game.getMode() + "' WHERE name = '" + oldName + "'");
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
    
    
    public Boolean updateBoard(Game game){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
         
            stat.executeUpdate("UPDATE games SET elements = '" + game.getE() + "' WHERE name = '" + game.getName() + "'");
            conn.setAutoCommit(true);
            
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
    
    public Boolean deleteGame(String name){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            stat.executeUpdate("DELETE FROM games WHERE name = '" + name + "';");
            rs = stat.executeQuery("SELECT name FROM games WHERE name = '" + name + "';");
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