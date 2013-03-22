/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import database.Connect;
import gameDesigner.GameDesignerMain;
import java.sql.*;
import systemAdministrator.SystemAdministratorMain;

/**
 *
 * @author kamijean2
 */
public class LoginFunctions {
    public Boolean login(String user, char password[]){
        String pw = new String(password);
        if(user.equals("") || password.length == 0){
            return false;
        }
        else{
            try {
                Connection conn = Connect.ConnectDB();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery("SELECT role FROM users WHERE username = '" + user + "' AND password = '" + pw + "'");
                String role = rs.getString("role");
                    
                if("DA".equals(role)){
                    GameDesignerMain game = new GameDesignerMain();
                    game.run();
                    return true;
                }
                else if("SA".equals(role)){
                    SystemAdministratorMain.ShowSystemAdministratorGUI();
                    return true;
                }
                else{
                    return false;
                } 
            }
            catch (SQLException ex) {
                System.out.println("Connection not established or user not found");
            }  
            return false;
        }
    }
}
