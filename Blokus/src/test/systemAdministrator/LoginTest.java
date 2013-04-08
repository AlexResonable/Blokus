package test.systemAdministrator;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import systemAdministrator.Login;
import systemAdministrator.ModifyUserAccount;
import systemAdministrator.SystemAdministratorMain;

public class LoginTest {

	private static FrameFixture loginFrame;
	private FrameFixture sysAdminMainFrame;
	private FrameFixture modifyUserFrame;
	private static Login loginInstance;
	private SystemAdministratorMain sysAdminInstance;
	private ModifyUserAccount modifyUserInstance;
	
	@BeforeClass
	public static void beforeTests()
	{
		loginInstance = new Login();
		loginFrame = new FrameFixture(loginInstance.getWindow());
		loginInstance.run();
	}
	@Before
	public void beforeTest()
	{
		loginFrame.show();
		loginFrame.textBox("UserName").deleteText();
		loginFrame.textBox("Password").deleteText();
	}
	@Test
	public void LoginComponents()
	{
		loginFrame.label("User Login");
		loginFrame.label("error message");
	}
	
	@Test
	public void sysAdminloginTest()
	{
		System.out.println("test sysAdmin");
		loginFrame.textBox("UserName").enterText("sysAdmin");
		loginFrame.textBox("Password").enterText("asdfasdf");
		loginFrame.button("Login").click();
		
	}
	@Test
	public void desAdminloginTest()
	{
		System.out.println("test game designer");
		loginFrame.textBox("UserName").enterText("desAdmin");
		loginFrame.textBox("Password").enterText("asdfasdf");
		loginFrame.button("Login").click();
	}
	
	
	//@Test
	/*public void sysAdminComponents()
	{
		sysAdminInstance = new SystemAdministratorMain();
		sysAdminInstance.ShowSystemAdministratorGUI();
		sysAdminMainFrame = new FrameFixture(sysAdminInstance.getFrame());
		sysAdminMainFrame.button("Logout").click();
		//sysAdminMainFrame.label("Title");
		//sysAdminMainFrame.button("User Management").click();
		//sysAdminMainFrame.show();
		//sysAdminMainFrame.button("Score Management").click();
		
	}*/
	
	
	/*@Test 
	public void modifyUserAccountComponents()
	{
		modifyUserInstance = new ModifyUserAccount();
		modifyUserInstance.showUserManagementGUI();
		modifyUserFrame = new FrameFixture(modifyUserInstance.getFrame());
		modifyUserFrame.label("Title");
		modifyUserFrame.label("Chooser Account:");
		modifyUserFrame.label("User Name: ");
		modifyUserFrame.label("Password: ");
		modifyUserFrame.label("Confirm Password: ");
		
	}*/
	
	
	

}
