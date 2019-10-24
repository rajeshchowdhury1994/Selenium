package com.stock.nasdaq;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Reusables.Generics;

public class ProjectBase {
	protected WebDriver driver;
	protected static String url,enviroment,browser;
	public enum browsers {GC,FF,IE, GOOGLECHROME, FIREFOX};
	
	private static final int TIMEOUT_SECONDS = 20;
	 Generics reusables=new Generics();
	 
  public ProjectBase()
  {
	  
  }
	
  @BeforeMethod
  public void setup()
  {
	//read data from config file
	  readPropertis();
	  // initiate webdriver
	  initiate_driver();
	  
  }
  
  
  @AfterMethod
  public void tearDown()
  {
	   
	  reusables.takescreenshot(driver, browser);
	  try {
		reusables.writeToTextfile("SavingsDataDrivenTestResults.txt");
		reusables.writeToExcel(reusables.TestResultsList,"DataDrivenResults.xlsx");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 driver.close();
	  //Write or display test results 
  }
  
  public static void readPropertis()
  {
	  File mypropertiesfile=new File("config.properties");
	  Properties prop=new Properties();
	  try {
	  prop.load(new FileInputStream(mypropertiesfile));
	  }
	  catch (Exception e)
	  {
		  System.out.println("Exception occurred"+ e);
	  }
	  
	  url=prop.getProperty("url");
	  enviroment=prop.getProperty("enviroment");
	  browser=prop.getProperty("browser");
	  	  
  }
  
  public void initiate_driver()
  {
	
	  browsers br=browsers.valueOf(browser.toUpperCase());
	  switch(br)
	  {
	  case GC:
	  case GOOGLECHROME: 
		  System.setProperty("webdriver.chrome.driver","E:\\classes\\chromedriver.exe");
			driver=new ChromeDriver();
			break;
			
	  case FF:
	  case FIREFOX: 
		  driver=new FirefoxDriver();
			break;
			
	  case IE:
		  System.setProperty("java.net.preferIPv4Stack", "true");
			System.setProperty("webdriver.ie.driver","E:\\classes\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			break;
	  }
	  
	   	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.get(url);
	    
	   // System.out.println("Url is loaded");
	   // reusables.GatherTestStepResult("Url is loaded		Pass"); 
  }
  
  public void typeText(By xpath, String elementname, String text)
  {
	  waitForElement(xpath,elementname);
	  WebElement element= driver.findElement(xpath);
	  element.clear();
	  element.sendKeys(text);
	  element.sendKeys(Keys.ENTER);
	 System.out.println("Typed in "+elementname+ "TEXT typed is "+text);
	  //reusables.GatherTestStepResult(text); 
	  
  }
  
  public void clickButton(By xpath, String elementname)
  {
	  waitForElement(xpath,elementname);
	  WebElement element= driver.findElement(xpath);
	  element.click();
	  System.out.println("Clicked on "+elementname);
	 // reusables.GatherTestStepResult(elementname); 
  }
  
  public String getElementText(By xpath, String elementname)
  {
	  waitForElement(xpath,elementname);
	  WebElement element= driver.findElement(xpath);
	  //System.out.println("Text returned is "+element.getText());
	  reusables.GatherTestStepResult(element.getText()); 
	  return element.getText();

	  
  }
  
  public String getText(By xpath, String elementname)
  {
	  waitForElement(xpath,elementname);
	  WebElement element= driver.findElement(xpath);
	  System.out.println("Text returned is "+element.getText());
	// reusables.GatherTestStepResult(element.getText()); 
	  return element.getText();

	  
  }
  
  public void waitForElement(By xpath, String elementname)
  {
	  WebDriverWait wait=new WebDriverWait(driver, TIMEOUT_SECONDS);
	  wait.until(ExpectedConditions.elementToBeClickable(xpath));
			  	  
  }
  
  public int getNumbetOfElements(String  myxpath)
  {
  	
  	List<WebElement> elementlist=driver.findElements(By.xpath(myxpath));
  	int listsize=elementlist.size();
  	return listsize;
  }


  public void mouseHover(String myxpath)
  {
  	Actions builder=new Actions(driver);
  	
  	WebElement tagWebElement=driver.findElement(By.xpath(myxpath));
  	builder.moveToElement(tagWebElement).build().perform();
  	reusables.GatherTestStepResult("Mouse has been moved to the selected element");
  	
  }

}