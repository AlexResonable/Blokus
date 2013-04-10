package test.gameDesigner;

import gameDesigner.EditBoard;

import org.fest.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.Test;

public class EditBoardTest {

	private static FrameFixture editBoardFrame;
	private static EditBoard editBoardInstance;
	
	@BeforeClass
	public static void beforeTests()
	{
		editBoardInstance = new EditBoard();
		editBoardInstance.run();
		editBoardFrame = new FrameFixture(editBoardInstance.getFrame());
	}

	@Test
	public void testBoardButtons()
	{
		editBoardFrame.show();
		editBoardFrame.button("i4 j5").click();
		editBoardFrame.button("i5 j12").click();
		editBoardFrame.button("i14 j18").click();
	}
	
	@Test
	public void testOtherButtons()
	{
		editBoardFrame.button("Back").click();
		editBoardFrame.show();
		editBoardFrame.button("Save").click();
	}
}
