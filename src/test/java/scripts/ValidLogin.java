package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import pages.HomePage;
import pages.LoginPage;

public class ValidLogin extends BaseTest{

	@Test
	public void testValidLogin() {
//	    1. Enter Valid User Name -admin
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName("admin");
//	    2. Enter Valid Password- manager
		loginPage.setPassword("manager");
//	    3. click on login button
		loginPage.clickLoginButton();
//	    4. Verify that Home Page is Displayed
		HomePage homePage=new HomePage(driver);
		boolean status = homePage.verifyHomePageDisplayed();
		Assert.assertTrue(status,"Home Page is Not Displayed");
	}
}
