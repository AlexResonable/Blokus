package test.systemAdministrator;

import static org.junit.Assert.*;

import org.fest.swing.fixture.FrameFixture;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import systemAdministrator.ModifyUserAccount;

public class ModifyUserAccountTest {

	private static FrameFixture modifyUserFrame;
	private static ModifyUserAccount modifyUserInstance;
	
	@BeforeClass
	public static void beforeTests()
	{
		modifyUserInstance = new ModifyUserAccount();
		modifyUserInstance.showUserManagementGUI();
		modifyUserFrame = new FrameFixture(modifyUserInstance.getFrame());
	}
	
	
	@Test
	public void testCompounents()
	{
		modifyUserFrame.label("Modify User Account");
		modifyUserFrame.label("Choose Account:");
		modifyUserFrame.label("User Name: ");
		modifyUserFrame.label("Password: ");
		modifyUserFrame.label("Confirm Password: ");
	}
	
	@Test
	public void testCreateNewAndDelete()
	{
	
		modifyUserFrame.comboBox("User List").requireItemCount(3);
		modifyUserFrame.button("New").click();
		modifyUserFrame.textBox("UserName").enterText("tester");
		modifyUserFrame.textBox("Password").enterText("abc");
		modifyUserFrame.textBox("Confirm").enterText("abc");
		modifyUserFrame.radioButton("gameDesigner").click();
		modifyUserFrame.button("Save").click();
		//after add another user "tester", there should be 4 users in the list
		modifyUserFrame.comboBox("User List").requireItemCount(4);
		
		modifyUserFrame.comboBox("User List").selectItem("tester");
		modifyUserFrame.button("Delete").click();
		
		// after delete "tester", the user numbers go back to 3
		modifyUserFrame.comboBox("User List").requireItemCount(3);
	}
	
	@Test
	public void testModify()
	{
		modifyUserFrame.comboBox("User List").selectItem("desAdmin");
		modifyUserFrame.textBox("Password").deleteText();
		modifyUserFrame.textBox("Confirm").deleteText();
		modifyUserFrame.textBox("Password").enterText("ttt");
		modifyUserFrame.textBox("Confirm").enterText("ttt");
		modifyUserFrame.button("Save").click();
		modifyUserFrame.comboBox("User List").selectItem("desAdmin");
		
		// the password of the desAdmin has been changed to "ttt" from "asdfasdf"
		modifyUserFrame.textBox("Password").requireText("ttt");
		modifyUserFrame.textBox("Password").deleteText();
		modifyUserFrame.textBox("Confirm").deleteText();
		modifyUserFrame.textBox("Password").enterText("asdfasdf");
		modifyUserFrame.textBox("Confirm").enterText("asdfasdf");
		modifyUserFrame.button("Save").click();
	}
	
/*	@Test
	public void testBackButton()
	{
		modifyUserFrame.button("Back").click();
		modifyUserFrame.show();
	}*/
}
