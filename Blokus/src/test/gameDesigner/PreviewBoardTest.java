package test.gameDesigner;

import org.fest.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.Test;

import gameDesigner.PreviewBoard;

public class PreviewBoardTest {

	private static FrameFixture previewBoardFrame;
	private static PreviewBoard previewBoardInstance;
	
	@BeforeClass
	public static void beforeTests()
	{
		previewBoardInstance = new PreviewBoard();
		previewBoardInstance.run();
		previewBoardFrame = new FrameFixture(previewBoardInstance.getFrame());
	}

	@Test
	public void testBactButton()
	{
		previewBoardFrame.button("Back").click();
	}
}
