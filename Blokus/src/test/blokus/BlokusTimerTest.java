/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blokus;

import java.util.Timer;
import java.util.TimerTask;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import blokus.BlokusTimer;
import static org.junit.Assert.*;

/**
 *
 * @author kamijean
 */
public class BlokusTimerTest {
    
    public BlokusTimerTest() {
    }

    /**
     * Test of stopTimer method, of class BlokusTimer.
     */
    @Test
    public void testStopTimer() {
        System.out.println("startTimer");
        BlokusTimer instance;
        
        instance = new BlokusTimer(new TimerTask() {

            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        instance.stopTimer();
        // TODO review the generated test code and remove the default call to fail.
        
    }
}

