package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;
import util.TestUtil;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//td[contains(text(),'unal')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath="//a[@title='New Contact']")
	WebElement newContact;

	public HomePage(){
	PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTiltle(){
		return driver.getTitle();
	}
	
	public Boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	
	public void clickOnContactsLink(){
		
		TestUtil.switchToFrame(driver,"mainpanel");
		contactsLink.click();
	}
	
	public void clickOnDealsLink(){
		dealsLink.click();
	}
	
	public void clickOnTasksLink(){
		tasksLink.click();
	}
	
	public void clickOnNewContact(){
	
	//TestUtil.switchToFrame(driver,"mainpanel");	
	TestUtil.mouseAction(driver, contactsLink, newContact);
	}
}


