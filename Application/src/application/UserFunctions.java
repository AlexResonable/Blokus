/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import database.Database;
import models.User;
import java.sql.*;
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
    
    public User getUserInfo(String username){
        User userInfo = new User();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT username, password, role FROM users WHERE username = '" + username + "';");
            while (rs.next()) {
                userInfo.setUsername(rs.getString("username"));
                userInfo.setPassword(rs.getString("password"));
                userInfo.setRole(rs.getString("role"));
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
    
    public User updateUser(User user, String username){
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String name = user.getUsername();
        String pw = user.getPassword();
        String rl = user.getRole();
        try {
            conn = Database.ConnectDB();
            stat = conn.createStatement();
            rs = stat.executeQuery("SELECT * FROM users WHERE username = '" + name + "';");

            if(!rs.next()){
                stat.executeUpdate("INSERT INTO users (username,password,role) VALUES ('" + username + "','" + pw + "','"+ rl +"')");
                conn.setAutoCommit(true);
            }
            else{
                stat.executeUpdate("UPDATE users SET username = '" + username + "', password = '" + pw + "', role = '" + rl + "' WHERE username = '" + name + "'");
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
            user.setUsername("");
            return null;
        }  
        return user;
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