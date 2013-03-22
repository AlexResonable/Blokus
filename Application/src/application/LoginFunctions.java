/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import database.Database;
import java.sql.*;

/**
 *
 * @author kamijean2
 */
public class LoginFunctions {
    public String login(String user, char password[]){
        String pw = new String(password);
        if(user.equals("") || password.length == 0){
            return null;
        }
        else{
            try {
                Connection conn = Database.ConnectDB();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery("SELECT role FROM users WHERE username = '" + user + "' AND password = '" + pw + "'");
                String role = rs.getString("role");
                    
                if("DA".equals(role))
                    return "DA";
                else if("SA".equals(role))
                    return "SA";
                else
                    return null;
            }
            catch (SQLException ex) {
                System.out.println("Connection not established or user not found");
            }  
            return null;
        }
    }
}
