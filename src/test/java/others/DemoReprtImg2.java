package others;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoReprtImg2 {

	static 
	{
		WebDriverManager.chromedriver().setup(); //set the path of driver exe
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		Date d=new Date();
		String strDate = d.toString();
		System.out.println(strDate);
		String time_stamp=strDate.replaceAll(":", "_");
		System.out.println(time_stamp);
		
		//Create object of extent report
		ExtentReports extent=new ExtentReports();
		
		//Create required report format
		ExtentSparkReporter format=new ExtentSparkReporter("./myreport/extentreport"+time_stamp+".html");
		
		//attach the format to extent report
		extent.attachReporter(format);
		
		//create a test
		ExtentTest test = extent.createTest("Test1");
		
		test.log(Status.INFO, "open the browser");	
		WebDriver driver=new ChromeDriver(); //open the browser
		
		test.log(Status.INFO, "enter the URL");	
		driver.get("http://www.google.com");//enter the URL
		
		test.log(Status.INFO, "Type cast driver to TakesScreenshot");	
		TakesScreenshot t=(TakesScreenshot) driver; //type cast driver to TakesScreenshot
		
		test.log(Status.INFO, "take the screenshot");	
		File srcIMG = t.getScreenshotAs(OutputType.FILE);//take the screenshot
		
		test.log(Status.INFO, "print the location");	
		System.out.println(srcIMG.getAbsolutePath());//print the location 
		
		test.log(Status.INFO,"create new image");
		File dstIMG=new File("./myreport/google"+time_stamp+".png");//create new image
		
		test.log(Status.INFO,"copy paste the screenshot");
		FileUtils.copyFile(srcIMG, dstIMG);//copy paste the screenshot
		
		test.log(Status.INFO,"close the browser");
		driver.close();//close the browser
		
		
		//add image to a step
		String img="./google"+time_stamp+".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(img).build());
		
		//publish the report
		extent.flush();
		
		
	}
}
