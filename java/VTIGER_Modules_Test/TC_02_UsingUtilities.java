package VTIGER_Modules_Test;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Vtiger.GenericUtilities.ExcelFileUtility;
import Vtiger.GenericUtilities.JavaUtilities;
import Vtiger.GenericUtilities.PropertyFileUtility;
import Vtiger.GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_02_UsingUtilities {

	public static void main(String[] args) throws InterruptedException, IOException {
		//Create all Objects
		ExcelFileUtility eutil=new ExcelFileUtility();
		JavaUtilities jutil=new JavaUtilities();
		PropertyFileUtility putil=new PropertyFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		//Generate Random Number
		int rnum = jutil.getRandomNumber();
		
		//Read Data From PropertyFile 
		String BROWSER = putil.readFromPropertyFile("browser");
		String URL = putil.readFromPropertyFile("url");
		String USERNAME=putil.readFromPropertyFile("username");
		String PASSWORD = putil.readFromPropertyFile("password");
		
		//Read Data From ExcelFile 
		String LASTNAME = eutil.readFromExcel("Contacts", 4, 2);
		String ORGNAME=eutil.readFromExcel("Contacts", 4, 3);
		
	
		//Launch Browser
		WebDriver driver=null;
		
		switch (BROWSER.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;	
		default:
			System.out.println("Please enter the valid Browser name"+BROWSER);
			break;
		}
		wutil.maximizeWindow(driver);
		wutil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Login to App
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		String parent = driver.getWindowHandle();
		
		//Click on Organizations Link
		driver.findElement(By.linkText("Organizations")).click();
	
		//Click on '+'(Create Organization
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
	
		//Enter Mandatory field
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+rnum);
		
		//Click on Save
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		Thread.sleep(2000);
								
		//Click on Contacts Module
		driver.findElement(By.linkText("Contacts")).click();
		
		//click on Create contact symbol(+)
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Enter Mandatory Text
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME+rnum);
		
		//click on Organization symbol
		
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		Set<String> child = driver.getWindowHandles();
		for (String b:child) {
			driver.switchTo().window(b);
			
		}
		
		//enter Organization name
		driver.findElement(By.id("search_txt")).sendKeys(ORGNAME+rnum);
		driver.findElement(By.name("search")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("1")).click();
		
					
		//Switch to Parent Page
		driver.switchTo().window(parent);
		//Thread.sleep(2000);
		
		//Save the contact
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		String genLASTNAME = driver.findElement(By.id("mouseArea_Last Name")).getText();
		String genORGNAME = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		
		//verify Orgname and Contact Name
		if (genLASTNAME.contains(LASTNAME+rnum)) {
			if (genORGNAME.contains(ORGNAME+rnum)) {
				System.out.println("Contact Name "+LASTNAME+rnum+" created with Organization "+ORGNAME+rnum+".Test Pass");
				
			}
			}
		else {
			System.out.println("Test Fail!");
		}	
		
		//Logout of WebPage
		WebElement ele = driver.findElement(By.xpath("//td[@class='small']/table/tbody/tr/td[2]"));
		ele.click();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
		
	
	
	}
}