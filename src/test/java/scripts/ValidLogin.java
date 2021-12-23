package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import pages.HomePage;
import pages.LoginPage;

public class ValidLogin extends BaseTest {

	@Test(priority = 1)
	public void testValidLogin() {
//	    1. Enter Valid User Name
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName("admin");
		
//	    2. Enter Valid Password
		loginPage.setPassword("manager");
		
//	    3. click on login button
		loginPage.clickLoginButton();
		
//	    4. Verify that Home Page is Displayed
		HomePage homePage=new HomePage(driver);
		boolean result = homePage.verifyHomePageDisplayed();
		Assert.assertTrue(result,"Home Page is not Displayed");
	}
}
