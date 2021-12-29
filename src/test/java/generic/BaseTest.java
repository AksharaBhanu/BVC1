package generic;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public String XL_PATH="./src/test/resources/data/input.xlsx";
	public static final String  p1="No";
	public static final String  p2="http://localhost:4444/wd/hub";
	public static final String  p3="chrome";
	public static ExtentReports extent;
	public String browser;
	public ExtentTest test;
	static {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeSuite
	public void initReport() {
		//Create object of extent report
		extent=new ExtentReports();
		
		//Create required report format
		ExtentSparkReporter format=new ExtentSparkReporter("./myreport/extentreport.html");
		
		//attach the format to extent report
		extent.attachReporter(format);
	}

	@AfterSuite
	public void publishReport() {
		extent.flush();
	}
	
	@Parameters({"useGrid","hubURL","browser"})
	@BeforeMethod
	public void openApp(Method method,@Optional(p1)String useGrid,@Optional(p2) String hubURL,@Optional(p3)String browser) throws MalformedURLException {
		this.browser=browser;
		String testName=method.getName();
		test = extent.createTest(testName+"_"+browser);
		
		
		if(useGrid.equalsIgnoreCase("yes"))
		{
			test.log(Status.INFO,"Using Grid");
			test.log(Status.INFO,"hubURL:"+hubURL);
			test.log(Status.INFO,"browser:"+browser);
			URL url=new URL(hubURL);
			DesiredCapabilities capability=new DesiredCapabilities();
			capability.setBrowserName(browser);
			driver = new RemoteWebDriver(url, capability);
		}
		else
		{
			test.log(Status.INFO,"Not Using Grid");
			test.log(Status.INFO,"browser:chrome");
			driver = new ChromeDriver();
		}
		
		test.log(Status.INFO,"Set ITO");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		test.log(Status.INFO,"Enter URL");
		driver.get("https://demo.actitime.com/login.do");
		
		test.log(Status.INFO,"maximize browser");
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void closeApp(ITestResult result) throws Exception {
		String testName=result.getName();
		String browser=this.browser;
		
		int status=result.getStatus();
		test.log(Status.INFO,"Test Status Code:"+status);	
		
		switch (status) 
		{
		case 1:
				test.log(Status.PASS,"Test is PASSED");	
				break;
			
		case 2:
				test.log(Status.FAIL,"Test is FAILED");	
				break;
		
		case 3:
				test.log(Status.SKIP,"Test is SKIPPED");	
				break;
			
		default:
				test.log(Status.FAIL,"Test is FAILED-Status code unknow");	
				break;
		}
		
		Thread.sleep(1000);
		TakesScreenshot t=(TakesScreenshot) driver;
		File srcIMG = t.getScreenshotAs(OutputType.FILE);
		File dstIMG=new File("./myreport/"+testName+"_"+browser+".png");
		FileUtils.copyFile(srcIMG, dstIMG);
		
		//below code will add screenshot at the last step
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(testName+"_"+browser+".png").build());
		
		//below code will add screenshot at the top of the result
//		test.addScreenCaptureFromPath(testName+"_"+browser+".png");
		
		test.log(Status.INFO,"Close the browser");
		
		driver.quit();
	}
}
