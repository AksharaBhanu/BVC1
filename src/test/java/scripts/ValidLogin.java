package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import generic.BaseTest;
import generic.Excel;
import pages.HomePage;
import pages.LoginPage;

public class ValidLogin extends BaseTest{

	@Test(priority = 1)
	public void testValidLogin() {
		
		test.log(Status.INFO, "read test data from excel");
		//read test data from excel
		String un=Excel.getData(XL_PATH, "ValidLogin", 1, 0);
		String pw=Excel.getData(XL_PATH, "ValidLogin", 1, 1);
		String fMsg=Excel.getData(XL_PATH, "ValidLogin", 1, 2);
		
		test.log(Status.INFO, "Enter Valid User Name");
//	    1. Enter Valid User Name
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(un);
		
		test.log(Status.INFO, "Enter Valid Password");
//	    2. Enter Valid Password
		loginPage.setPassword(pw);
		
		
		test.log(Status.INFO, "click on login button");
//	    3. click on login button
		loginPage.clickLoginButton();
		
		test.log(Status.INFO, "Verify that Home Page is Displayed");
//	    4. Verify that Home Page is Displayed
		HomePage homePage=new HomePage(driver);
		boolean result = homePage.verifyHomePageDisplayed();
		Assert.assertTrue(result,fMsg);

	}
}
