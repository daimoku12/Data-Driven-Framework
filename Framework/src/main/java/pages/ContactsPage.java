package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;
import util.TestUtil;

public class ContactsPage extends TestBase {

	@FindBy(xpath="//td[contains(text(),'Contac')]")
	WebElement contactsLabel;
	
	@FindBy(xpath="//div[@class='pagination']/child::a[1]")
	WebElement page2;
	
	@FindBy(xpath="//input[@id='first_name']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='surname']")
	WebElement lastName;
	
	@FindBy(xpath=".//*[@id='phone']")
	WebElement phone;
	
	@FindBy(className="button")
	WebElement saveBtn;
	
	public ContactsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String verifyContactsLabel(){
		return contactsLabel.getText();
	}
		
	public void clickOnPage2(){
		page2.click();
	}
	
	public void createNewContact(String title, String fname, String lname, double num){
	
		Select select = new Select (driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		firstName.sendKeys(fname);
		TestUtil.highlightElement(driver, firstName);
		
		lastName.sendKeys(lname);
		TestUtil.highlightElement(driver,lastName);
		
		phone.sendKeys(String.valueOf(num));          //Important
		TestUtil.highlightElement(driver, phone);
		
		saveBtn.click();
		
	}
}
