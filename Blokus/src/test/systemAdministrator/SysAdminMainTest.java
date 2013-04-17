package test.systemAdministrator;

import org.fest.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.Test;

import systemAdministrator.SystemAdministratorMain;

public class SysAdminMainTest 
{
	private static FrameFixture sysAdminMainFrame;
	private static SystemAdministratorMain sysAdminInstance;
	
	@BeforeClass
	public static void beforeTests()
	{
		sysAdminInstance = new SystemAdministratorMain();
		sysAdminInstance.ShowSystemAdministratorGUI();
		sysAdminMainFrame = new FrameFixture(sysAdminInstance.getFrame());
		
	}
	
	@Test
	public void testLabel()
	{
		sysAdminMainFrame.show();
		sysAdminMainFrame.label("System Administrator Main");
	}
	
	@Test
	public void testButton1()
	{
		sysAdminMainFrame.show();
		sysAdminMainFrame.button("User Management").click();
	}
	@Test
	public void testButton2()
	{
		sysAdminMainFrame.show();
		sysAdminMainFrame.button("Score Management").click();
	}
	@Test
	public void testLogout()
	{
		sysAdminMainFrame.show();
		sysAdminMainFrame.button("Logout").click();
		sysAdminMainFrame.show();
	}
}
