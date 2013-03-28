/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamijean2
 */
public class UserFunctions {
    
    public String login(String user, char password[]){
        String pw = new String(password);
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT role FROM users WHERE username = '" + user + "' AND password = '" + pw + "'");
            String role = rs.getString("role");
            stat.close();
            rs.close();
            conn.close();
            if("DA".equals(role))
                return "DA";
            else if("SA".equals(role))
                return "SA";
            else
                return null;
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
        }  
        return null;
        
    }
    public HashMap<String, String> getUsernames(){
        HashMap<String, String> users = new HashMap<String, String>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT id, username FROM users;");
            while (rs.next()) {
                users.put(rs.getString("id"),rs.getString("username"));
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
        return users;
    }
    
    public String[] getUserInfo(String username){
        String[] userInfo = new String[3];
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT username, password, role FROM users WHERE username = '" + username + "';");
            while (rs.next()) {
                userInfo[0] = rs.getString("username");
                userInfo[1] = rs.getString("password");
                userInfo[2] = rs.getString("role");
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
        return userInfo;
    }
    
    public Boolean updateUser(String oldUsername, String username, String password, String role){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM users WHERE username = '" + oldUsername + "';");

            if(!rs.next()){
                stat.executeUpdate("INSERT INTO users (username,password,role) VALUES ('" + username + "','" + password + "','"+ role +"')");
                conn.setAutoCommit(true);
            }
            else{
                stat.executeUpdate("UPDATE users SET username = '" + username + "', password = '" + password + "', role = '" + role + "' WHERE username = '" + oldUsername + "'");
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
    
    public Boolean deleteUser(String username){
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