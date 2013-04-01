/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blokus5100;

import javax.swing.ImageIcon;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import blokus5100.MainMenu;
import static org.junit.Assert.*;

/**
 *
 * @author kamijean
 */
public class MainMenuTest {
    
    public MainMenuTest() {
    }

    /**
     * Test of createAndShowGUI method, of class MainMenu.
     */
    @Test
    public void testCreateAndShowGUI() {
        System.out.println("createAndShowGUI");
        MainMenu.createAndShowGUI();
    }

    /**
     * Test of createImageIcon method, of class MainMenu.
     */
//    @Test
//    public void testCreateImageIcon() {
//        System.out.println("createImageIcon");
//        String path = "";
//        ImageIcon expResult = null;
//        ImageIcon result = MainMenu.createImageIcon(path);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of main method, of class MainMenu.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        MainMenu.main(args);
    }
}
