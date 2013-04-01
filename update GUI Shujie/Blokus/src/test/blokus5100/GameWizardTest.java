/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blokus5100;

import java.awt.event.ActionEvent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import blokus5100.GameWizard;
import static org.junit.Assert.*;

/**
 *
 * @author kamijean
 */
public class GameWizardTest {
    
    public GameWizardTest() {
    }

    /**
     * Test of run method, of class GameWizard.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        GameWizard instance = new GameWizard();
        instance.run();
    }

}
