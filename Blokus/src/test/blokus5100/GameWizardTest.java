/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blokus5100;

import java.awt.event.ActionEvent;

import org.fest.swing.fixture.FrameFixture;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import blokus5100.GameWizard;
import static org.junit.Assert.*;

public class GameWizardTest {
    
    private static FrameFixture gameWizardFrame;
    private static GameWizard gameWizardInstance;
    
    @BeforeClass
    public static void beforeTests()
    {
    	gameWizardInstance = new GameWizard();
    	gameWizardInstance.run();
    	gameWizardFrame = new FrameFixture(gameWizardInstance.getWindow());
    }
    
    @Test
    public void testLabels()
    {
    	gameWizardFrame.label("Modify Settings");
    	gameWizardFrame.label("Name of Player 1");
    	gameWizardFrame.label("Name of Player 2");
    	gameWizardFrame.label("Name of Player 3");
    	gameWizardFrame.label("Name of Player 4");
    }
    @Test
    public void testPlayers()
    {
    	gameWizardFrame.textBox("Player1 Field").enterText("Kaimi");
    	gameWizardFrame.textBox("Player2 Field").enterText("Stacy");
    	gameWizardFrame.textBox("Player3 Field").enterText("Alex");
    	gameWizardFrame.textBox("Player4 Field").enterText("Jacob");
    }
    
    @Test
    public void testRadioButtons()
    {
    	gameWizardFrame.radioButton("easy").click();
    	gameWizardFrame.radioButton("medium").click();
    	gameWizardFrame.radioButton("hard").click();
    }
    
    @Test
    public void testComboBox()
    {
    	gameWizardFrame.radioButton("easy").click();
    	gameWizardFrame.comboBox("game names").selectItem("Normal Game");
    }
    
    @Test
    public void testButtons()
    {
    	gameWizardFrame.button("Preview Board").click();
    	gameWizardFrame.show();
    	gameWizardFrame.button("Play").click();
    	gameWizardFrame.show();
    	gameWizardFrame.button("Back").click();
    	gameWizardFrame.show();
    }
    
    
    
    
   

}
