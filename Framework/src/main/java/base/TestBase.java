package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
//import atu.testrecorder.ATUTestRecorder;
import listener.WebEventListener;
import util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties pr;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ExtentReports extent;
	public static ExtentTest test;
	protected Logger log;
	public static ChromeOptions options;
	//public static ATUTestRecorder recorder;
	
	public TestBase(){
		
		try {
		     pr = new Properties();
			 FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
					 +"\\src\\main\\java\\config\\config.properties");
			 
			 pr.load(ip);
		    } 
		catch (Exception e) {
			e.printStackTrace();
		    }	

		log=Logger.getLogger("Log");
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/java/config/Log4j.properties");         
	}
	
	static{
		
		     // use LocalDateTime.now(); (Java 8 onwards)
		     
		     //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");
		     //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		     //extent=new ExtentReports(System.getProperty("user.dir")+"/Report/"+sdf.format(timestamp)+".html",true); 
		     
		     extent=new ExtentReports(System.getProperty("user.dir")+"/Report/"+"extent.html",true); 
		     
		     //extent=new ExtentReports("./Report/"+"extent.html",true);
		     //extent=new ExtentReports("C:\\Users\\Kannu\\git\\MyFramework\\Framework\\Report\\extent.html",true); 
		     
			 extent.addSystemInfo("Project", "CRM Automation Testing");
			 extent.addSystemInfo("SUITE", "Regression");
			 extent.loadConfig(new File (System.getProperty("user.dir")+"/src/main/java/config/extent-config.xml"));
			 
			 //Video recording
			 
/*			 try {
				   recorder = new ATUTestRecorder("C:\\Users\\Kannu\\Downloads\\Selenium Video", "myrecording", false);
				   recorder.start();
			     }
			 catch (ATUTestRecorderException e) 
			     {
				   e.printStackTrace();
			     }*/
		  }
	
	public static void getresult(ITestResult result){

		if(result.getStatus()==ITestResult.SUCCESS){
		test.log(LogStatus.PASS, result.getName()+ " : Test passed");
		}
		
		else if(result.getStatus()==ITestResult.SKIP){
		test.log(LogStatus.SKIP, result.getName()+ " : Skip reason - " + result.getThrowable());
		}
		
		else if(result.getStatus()==ITestResult.FAILURE){
			
			try {
				String path = TestUtil.TakeScreenShot(driver);
				test.log(LogStatus.FAIL, result.getName()+ " : Failure reason - "+ result.getThrowable());
				test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(path));
		
			} 
			
			catch (IOException e) {
		      e.printStackTrace();
			}		
  }
}
		
	public static void initialization(){
		
		if (pr.getProperty("browser").equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();	
		}
		
		if (pr.getProperty("browser").equalsIgnoreCase("Firefox"))
		{
		System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
		driver = new FirefoxDriver();
		}
		
		if (pr.getProperty("browser").equalsIgnoreCase("headless"))
		{
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		options = new ChromeOptions();
		options.addArguments("headless");
		//options.addArguments("window-size=1400x800");
		driver = new ChromeDriver(options);	
		}
				
		e_driver = new EventFiringWebDriver(driver);
		//Create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.get(pr.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);	
	}
	
	@AfterMethod
	public void teardown(ITestResult result){
		getresult(result);
		driver.quit();
		
	}
	
	@AfterClass(alwaysRun=true)
	public void endTest(){
	  extent.endTest(test);
	  extent.flush();
	}
	
/*	@AfterTest
	public void record() throws ATUTestRecorderException{
	recorder.stop();	
	}*/
}
