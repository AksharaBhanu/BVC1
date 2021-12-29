package others;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class DemoReporint {

	public static void main(String[] args) {
	
		//Create object of extent report
		ExtentReports extent=new ExtentReports();
		
		//Create required report format
		ExtentSparkReporter format=new ExtentSparkReporter("./target/extentreport.html");
		
		//attach the format to extent report
		extent.attachReporter(format);
		
		//create a test
		ExtentTest test = extent.createTest("Test1");
		
		//log the message
		test.log(Status.INFO,"This is step1 with the status as INFO");
		
		test.log(Status.INFO,"This is step2 with the status as INFO");
		
		test.log(Status.INFO,"This is step3 with the status as INFO");
		
		test.log(Status.INFO,"This is step4 with the status as INFO");
		
		
		//create another test
		test = extent.createTest("Test2");
				
		//log the message
		test.log(Status.INFO,"This is step1 with the status as INFO");
		
//		test.log(Status.SKIP,"This is step2 with the status as INFO");
				
//		test.log(Status.WARNING,"This is step3 with the status as INFO");
				
		test.log(Status.PASS,"This is step4 with the status as INFO");
		
//		test.log(Status.FAIL,"This is step4 with the status as INFO");
		
		test.log(Status.INFO,"This is step1 with the status as INFO");
		
//		test.log(Status.SKIP,"This is step2 with the status as INFO");
				
//		test.log(Status.WARNING,"This is step3 with the status as INFO");
				
		test.log(Status.PASS,"This is step4 with the status as INFO");
		
//		test.log(Status.FAIL,"This is step4 with the status as INFO");
		
		//publish the report
		extent.flush();
	}

}
