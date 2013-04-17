package test.systemAdministrator;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import systemAdministrator.Login;

public class LoginTest {

	private static FrameFixture loginFrame;
	private static Login loginInstance;
	
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

}
