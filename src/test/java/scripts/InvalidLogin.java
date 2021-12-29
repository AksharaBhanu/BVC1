package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import generic.BaseTest;
import generic.Excel;
import pages.LoginPage;

public class InvalidLogin extends BaseTest{

	@Test(priority = 2)
	public void testInvalidLogin() {
		
		test.log(Status.INFO, "read test data from excel");
		String un=Excel.getData(XL_PATH,"InvalidLogin", 1, 0);
		String pw=Excel.getData(XL_PATH,"InvalidLogin", 1, 1);
		String failMsg=Excel.getData(XL_PATH,"InvalidLogin", 1, 2);
		
		
		test.log(Status.INFO, "Enter Invalid User Name -abcd");
//	    1. Enter Invalid User Name -abcd
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(un);
		
		test.log(Status.INFO, "Enter Invalid Password- xyz");
//	    2. Enter Invalid Password- xyz
		loginPage.setPassword(pw);
		
		test.log(Status.INFO, "click on login button");
//	    3. click on login button
		loginPage.clickLoginButton();
		
		
		test.log(Status.INFO, "Verify that Error Msg is Displayed");
//	    4. Verify that Error Msg is Displayed
		boolean result = loginPage.verifyErrMsgDisplayed();
		Assert.assertTrue(result,failMsg);
	}
}
