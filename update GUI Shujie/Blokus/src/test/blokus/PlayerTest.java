/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blokus;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import blokus.Player;
import static org.junit.Assert.*;

/**
 *
 * @author kamijean
 */
public class PlayerTest {
    
    public PlayerTest() {
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String playerName = "test";
        Player instance = new Player(1);
        instance.setName(playerName);
        assertEquals(instance.name, playerName);
    }

    /**
     * Test of getTotalScore method, of class Player.
     */
    @Test
    public void testGetTotalScore() {
        System.out.println("getTotalScore");
        Player instance = new Player(1);
        int expResult = 0;
        int result = instance.getTotalScore();
        assertEquals(expResult, result);
    }
}
