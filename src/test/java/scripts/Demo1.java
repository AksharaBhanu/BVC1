package scripts;

import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import pages.LoginPage;

public class Demo1 extends BaseTest{

	@Test
	public void testA() throws InterruptedException {
		String un=Excel.geXlData(XL_PATH,"Sheet1", 1, 0);
		LoginPage l=new LoginPage(driver);
		l.setUserName(un);
	}
}
