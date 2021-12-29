package others;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoReprtImg {

	static 
	{
		WebDriverManager.chromedriver().setup(); //set the path of driver exe
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver=new ChromeDriver(); //open the browser
		
		driver.get("http://www.google.com");//enter the URL
		
		TakesScreenshot t=(TakesScreenshot) driver; //type cast driver to TakesScreenshot
		
		File srcIMG = t.getScreenshotAs(OutputType.FILE);//take the screenshot
		
		System.out.println(srcIMG.getAbsolutePath());//print the location 
		
		File dstIMG=new File("./myreport/google.png");//create new image
		
		FileUtils.copyFile(srcIMG, dstIMG);//copy paste the screenshot
		
		driver.close();//close the browser
		
		
	}
}
