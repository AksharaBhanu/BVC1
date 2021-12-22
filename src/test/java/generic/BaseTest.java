package generic;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public String XL_PATH="./src/test/resources/data/input.xlsx";
	
	static {
		WebDriverManager.chromedriver().setup();
	}
	

	@BeforeMethod
	public void openApp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demo.actitime.com/login.do");
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void closeApp() {
		driver.quit();
	}
}
