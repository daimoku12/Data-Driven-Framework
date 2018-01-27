package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.TestBase;
import util.TestUtil;


public class LoginPage extends TestBase {
	
    //Page Factory - OR
	//To improve performance of scripts, use CacheLookup - same instance in DOM will be used
	//Prefix txt (for textbox), btn (for button), link, rbtn, dd (dropdown)
	//Create methods for every functionality not action on a page 
	//Any test case should not directly interact with Browser(Web App)
	//Tests-->Framework-->Selenium-->Browser(WebApp)
	
	@FindBy(name="username")
	@CacheLookup
    WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@class='btn btn-small']")
	WebElement loginBtn;
 
	@FindBy(xpath="//button[contains(text(),'ign')]")
	WebElement signUpBtn;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}

	public void login(){
		
		username.sendKeys(pr.getProperty("username"));
		TestUtil.highlightElement(driver, username);
		log.info("Username entered");
		log.warn("warning example");
		
		password.sendKeys(pr.getProperty("password"));
		TestUtil.highlightElement(driver, password);
		log.info("Password entered");
		
		loginBtn.click();
		log.info("Clicked on login button");
	}
	
	public void signin(){
		signUpBtn.click();
	}
}