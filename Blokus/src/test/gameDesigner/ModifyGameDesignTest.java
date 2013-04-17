package test.gameDesigner;

import gameDesigner.ModifyGameDesign;
import gameDesigner.setBoard;

import org.fest.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModifyGameDesignTest 
{
	private static FrameFixture modifyGameFrame;
	private static ModifyGameDesign modifyGameInstance;
	private static setBoard boardInstance;
	
	@BeforeClass
	public static void beforeTests()
	{
		boardInstance = new setBoard();
		modifyGameInstance = new ModifyGameDesign(boardInstance);
		modifyGameInstance.run();
		modifyGameFrame = new FrameFixture(modifyGameInstance.getWindow());
	}
	
	@Test
	public void testLabels()
	{
		modifyGameFrame.label("Modify Game Board");
		modifyGameFrame.label("Choose Designed Game ");
		modifyGameFrame.label("Game Board Size ");
		modifyGameFrame.label("Number of Players ");
		modifyGameFrame.label("Turn Time (seconds) ");
		modifyGameFrame.label("Game Time (minutes)");
		modifyGameFrame.label("Number of Pieces");
	}
	
	@Test
	public void testSpinners()
	{
		modifyGameFrame.spinner("game size1").requireValue(20);
		modifyGameFrame.spinner("game size1").increment(1);
		modifyGameFrame.spinner("game size1").requireValue(30);
		
		modifyGameFrame.spinner("game size2").requireValue(20);
		modifyGameFrame.spinner("game size2").increment(1);
		modifyGameFrame.spinner("game size2").requireValue(30);
		
		modifyGameFrame.spinner("player numbers").requireValue(4);
		modifyGameFrame.spinner("player numbers").decrement(2);
		modifyGameFrame.spinner("player numbers").requireValue(2);
		
		modifyGameFrame.spinner("turnTime").requireValue(10);
		modifyGameFrame.spinner("turnTime").increment(5);
		modifyGameFrame.spinner("turnTime").requireValue(15);
		modifyGameFrame.spinner("turnTime").decrement(3);
		modifyGameFrame.spinner("turnTime").requireValue(12);
		
		modifyGameFrame.spinner("GameTime").requireValue(30);
		modifyGameFrame.spinner("GameTime").increment(5);
		modifyGameFrame.spinner("GameTime").requireValue(35);
		modifyGameFrame.spinner("GameTime").decrement(3);
		modifyGameFrame.spinner("GameTime").requireValue(32);
		
		modifyGameFrame.spinner("pieces numbers").requireValue(21);
		modifyGameFrame.spinner("pieces numbers").increment(5);
		modifyGameFrame.spinner("pieces numbers").requireValue(26);
		modifyGameFrame.spinner("pieces numbers").decrement(3);
		modifyGameFrame.spinner("pieces numbers").requireValue(23);
	}
	
	@Test
	public void testButtons()
	{
		modifyGameFrame.button("Edit Board").click();
		//modifyGameFrame.show();
		//modifyGameFrame.button("Delete");
		modifyGameFrame.show();
		modifyGameFrame.button("New").click();
		modifyGameFrame.button("Save").click();
		modifyGameFrame.button("Logout").click();
		modifyGameFrame.show();
	}
	

}
