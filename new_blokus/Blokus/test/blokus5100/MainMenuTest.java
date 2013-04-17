/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package blokus5100;

import org.fest.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainMenuTest {
    
  private static FrameFixture mainFrame;
  private static MainMenu mainInstance;
  
  @BeforeClass
  public static void beforeTests()
  {
      mainInstance = new MainMenu();
      mainInstance.createAndShowGUI();
      mainFrame = new FrameFixture(mainInstance.getFrame());
  }
  
  @Before
  public void beforeTest()
  {
    mainFrame.show();
  }
  
  @Test
  public void testLabels()
  {
    mainFrame.label("BLOCKUS GAME");
mainFrame.label("by The Dream Team");
  }
  
  @Test
  public void testPlayButton()
  {
mainFrame.button("PLAY").click();
  }
  
  @Test
  public void testInstructionButton()
  {
mainFrame.button("Instruction").click();
  }
  
  @Test
  public void testLogInButton()
  {
mainFrame.button("LOG IN").click();
  }
  
  @Test
  public void testHighScoreButton()
  {
mainFrame.button("HIGH SCORES").click();
  }
  
  @Test
  public void testExitButton()
  {
mainFrame.button("EXIT").click();
  }
}