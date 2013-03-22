/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author kamijean2
 */
public class Connect {
    public static Connection ConnectDB(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/kamijean2/Dropbox/Kami_Wilson/Blokus5100/blokus.sqlite");
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