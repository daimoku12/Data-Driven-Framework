package testcases;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import base.TestBase;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	/**
	 * Test case to validate Contacts page functionality
	 * Name: Kunal Malik
	 * Date: 10/22/2017
	 */

	LoginPage loginpageObj;
	HomePage homepageObj;
	ContactsPage contactspageObj;
	String sheetName = "contacts";
	
	public ContactsPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(Method result){
		
		TestBase.initialization();
		loginpageObj = new LoginPage();
		loginpageObj.login();
		homepageObj = new HomePage();
		homepageObj.clickOnContactsLink();
		contactspageObj = new ContactsPage();
		test=extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " : Test started");	
	}
		
	@Test (priority=1)
	public void verifyContactsLabelTest(){
		String title= contactspageObj.verifyContactsLabel();
		Assert.assertEquals(title, "Contacts");
	}
		
	@DataProvider
	public Object[][] getCRMTestData(){
	Object data[][]= TestUtil.TestDataSingleRow(sheetName, 2);
	return data;
	}
	
	@Test (priority=2, dataProvider="getCRMTestData")
	public void validateCreateNewContactTest(String title, String fname, String lname, double num){
		
		homepageObj.clickOnNewContact();
		contactspageObj.createNewContact(title,fname,lname,num);
	}
}
