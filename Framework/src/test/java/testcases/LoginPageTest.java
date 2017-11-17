package testcases;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import base.TestBase;
import pages.LoginPage;

/**
 * Test case to validate login page functionality
 * Name: Kunal Malik
 * Date: 10/22/2017
 */

public class LoginPageTest extends TestBase{

	LoginPage loginpageObj;        //Initialize object of LoginPage class
	
	public LoginPageTest(){
		super();                   //Call TestBase class constructor
	}
	
	@BeforeMethod
	public void setUp(Method result){	
		TestBase.initialization();
		loginpageObj = new LoginPage();
		test=extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " : Test started");
	}
		
	@Test (priority=1)
	public void loginPageTitleTest(){
		String title= loginpageObj.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM for Any Business: Online Customer Relationship Software");
	}
	
	@Test (priority=2)
	public void loginTest(){
		loginpageObj.login();
		test.log(LogStatus.INFO,"Username entered");
		test.log(LogStatus.INFO,"Password entered");
		test.log(LogStatus.INFO,"Clicked on Login button");
	}
	
	@Test (priority=3)
	public void sign(){
		loginpageObj.signin();
	}
	}

