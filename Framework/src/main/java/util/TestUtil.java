package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TestUtil{
	
	public static long PAGE_LOAD_TIMEOUT =20;
	public static long IMPLICIT_WAIT = 10;
	
	public static String destination;
	
//....................................Excel Sheet Objects.............................................	
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static Object [][] data;
	
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\TestData.xlsx";
	
//................Capture Screenshot.....................................................................

	public static void captureScreenShot(WebDriver driver){
		
		  SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");
		  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		  
		  //System.getProperty("user.dir")/      current project location
		  
		  File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		  try { 	
		      FileUtils.copyFile(src, new File("./Screenshot/"+sdf.format(timestamp)+".png")); 
			  //FileUtils.copyFile(src, new File("./Screenshot/"+System.currentTimeMillis()+".png"));   
		      }
		 
		catch (IOException e)
		      {
		        System.out.println(e.getMessage());
		      }
 }
//..................Highlight Element........................................................................
	
	public static void highlightElement(WebDriver driver, WebElement element)
	{
	    JavascriptExecutor js=(JavascriptExecutor)driver; 
	    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        //captureScreenShot(driver);	 
	    try 
	      {
	       Thread.sleep(1000);
	      } 
	    catch (InterruptedException e) {
	       System.out.println(e.getMessage());
	      } 
	 js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 
	 
	}	
//..............Switch to Frame by name.............................................................................
	
	public static void switchToFrame(WebDriver driver, String frameName){
		driver.switchTo().frame(frameName);
	}
//....................Mouse (move and click on element)..............................................................
	
	public static void mouseAction (WebDriver driver, WebElement moveToElement, WebElement clickOnElement){
		
		Actions action = new Actions(driver);
		action.moveToElement(moveToElement).build().perform();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		action.moveToElement(clickOnElement).click().build().perform();
	}
//........................DataProvider(get data from all rows)................................................
	
	@SuppressWarnings("deprecation")
	public static Object[][] getTestData(String sheetName) {
		
		FileInputStream file = null;

		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		    } 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		    }
		
		try {
			wb = new XSSFWorkbook(file);
		    } 
		catch (Exception e) {
			e.printStackTrace();
		    }
		sh = wb.getSheet(sheetName);
		row =sh.getRow(0);
		
		data = new Object[sh.getPhysicalNumberOfRows()-1][row.getPhysicalNumberOfCells()];
		
		for (int i=1; i<sh.getPhysicalNumberOfRows();i++){
			for (int j=0; j<row.getPhysicalNumberOfCells();j++){
				
				if (sh.getRow(i).getCell(j).getCellType()==0)
				{   
					data[i-1][j]= sh.getRow(i).getCell(j).getNumericCellValue();     		
				}
				
				else data[i-1][j]= sh.getRow(i).getCell(j).getStringCellValue();		
			}
		}	
		return data;
	}
//........................DataProvider(get data from one row)................................................	

@SuppressWarnings("deprecation")
public static Object[][] TestDataSingleRow(String sheetName, int rownum) {
		
		FileInputStream file = null;

		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		    } 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		    }
		
		try {
			wb = new XSSFWorkbook(file);
		    } 
		catch (Exception e) {
			e.printStackTrace();
		    }
		sh = wb.getSheet(sheetName);
		row =sh.getRow(0);
		data = new Object[1][row.getPhysicalNumberOfCells()];
		
		for (int i=rownum; i<=rownum;i++){
			for (int j=0; j<row.getPhysicalNumberOfCells();j++){
				
				if (sh.getRow(i).getCell(j).getCellType()==0)
				{   
					data[0][j]= sh.getRow(i).getCell(j).getNumericCellValue();     		
				}
				
				else data[0][j]= sh.getRow(i).getCell(j).getStringCellValue();
			}
		}	
		return data;
	}
//...................................Take Screenshot.................................

	public static String TakeScreenShot(WebDriver driver) throws IOException{
		
		  //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");
		  //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		  
		    TakesScreenshot ts = (TakesScreenshot)driver; 
			File src= ts.getScreenshotAs(OutputType.FILE);
			
			//String dest = System.getProperty("user.dir")+"\\Screenshot\\"+sdf.format(timestamp)+".png";
			
			//String dest = System.getProperty("user.dir")+"\\Report\\screenshot.png";
			
			String dest = "../Report/screenshot.png";
			
			//String dest = "C:/Users/Kannu/git/MyFramework/Framework/Report/screenshot.png";
			
			File destination = new File (dest);
			FileUtils.copyFile(src, destination); 
			return dest;
 }
}


