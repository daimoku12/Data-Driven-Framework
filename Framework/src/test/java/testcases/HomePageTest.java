package testcases;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import base.TestBase;
import pages.HomePage;
import pages.LoginPage;
import util.TestUtil;

/**
 * Test case to validate home page functionality
 * Name: Kunal Malik
 * Date: 10/22/2017
 */

public class HomePageTest extends TestBase {
	
	LoginPage loginpageObj;
	HomePage homepageObj;
	
	
	public HomePageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(Method result){
		
		TestBase.initialization();
		loginpageObj = new LoginPage();
		loginpageObj.login();
		homepageObj = new HomePage();
		test=extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " : Test started");
	}
		
	@Test (priority=1)
	public void homePageTitleTest(){
		String title= homepageObj.verifyHomePageTiltle();
		Assert.assertEquals(title, "CRMPRO","Homepage title does not match");
	}
	
	@Test (priority=2)
	public void verifyUserNameTest(){
		TestUtil.switchToFrame(driver,"mainpanel");
		Assert.assertTrue(homepageObj.verifyCorrectUserName());
		driver.switchTo().defaultContent();
    }
	
	@Test (priority=3)
	
	public void verifyContactsLinkTest(){
		homepageObj.clickOnContactsLink();
		test.log(LogStatus.INFO,"Clicked on Contacts link");
	}
		 
	 @Test (priority=4)
	 public void demoTestSkip(){
	 throw new SkipException("Test is not ready");
	 }
}