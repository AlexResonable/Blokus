package test.systemAdministrator;

import highScores.ScoreManagement;

import org.fest.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.Test;

public class ScoreManagementTest {

	private static FrameFixture scoreManagementFrame;
	private static ScoreManagement scoreManagementInstance;
	
	@BeforeClass
	public static void beforeTests()
	{
		scoreManagementInstance = new ScoreManagement();
		scoreManagementInstance.ShowHighScoresManagementGUI();
		scoreManagementFrame = new FrameFixture (scoreManagementInstance.getFrame());
	}
	
	@Test
	public void testLabels()
	{
		scoreManagementFrame.label("Top Ten High Scores");
	}
	
	@Test
	public void testButton()
	{
		scoreManagementFrame.button("Delete All").click();
		scoreManagementFrame.button("Back").click();
	}
}
