package others;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoTest {

	@BeforeMethod
	public void bm(Method method)
	{
		System.out.println("@BeforeMethod is running");
		//print name of test method here?
		String name=method.getName();
		System.out.println("Test Name is:"+name);
	}
	
	@AfterMethod
	public void am(ITestResult result) {
		int status = result.getStatus();
		System.out.println(status);
		System.out.println("@AfterMethod is running");
	}
	
	@Test
	public void testA() {
		System.out.println("testA is running");
	}
	
	@Test
	public void testB() {
		System.out.println("testB is running");
		Assert.fail();
	}
}
