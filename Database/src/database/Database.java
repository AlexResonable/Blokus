/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author kamijean
 */
public class Database {

    public static Connection ConnectDB(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/kamijean/projects/Blokus/blokus.sqlite");
            return conn;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
    }
    public void CloseDB(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Connection to database not closed");
        }
    }

   
}
