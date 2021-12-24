package generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public String XL_PATH="./src/test/resources/data/input.xlsx";
	public static final String  p1="No";
	public static final String  p2="http://localhost:4444/wd/hub";
	public static final String  p3="chrome";
	
	static {
		WebDriverManager.chromedriver().setup();
	}
	

	@Parameters({"useGrid","hubURL","browser"})
	@BeforeMethod
	public void openApp(@Optional(p1)String useGrid,@Optional(p2) String hubURL,@Optional(p3)String browser) throws MalformedURLException {
		
		if(useGrid.equalsIgnoreCase("yes"))
		{
			Reporter.log("Using Grid",true);
			URL url=new URL(hubURL);
			DesiredCapabilities capability=new DesiredCapabilities();
			capability.setBrowserName(browser);
			driver = new RemoteWebDriver(url, capability);
		}
		else
		{
			Reporter.log("Not Using Grid",true);
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demo.actitime.com/login.do");
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void closeApp() {
		driver.quit();
	}
}
