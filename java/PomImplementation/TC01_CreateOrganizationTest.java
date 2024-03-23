package PomImplementation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import PomUtilities.HomePage;
import PomUtilities.InformationPage;
import PomUtilities.LoginPage;
import PomUtilities.OrganizationsPage;
import Vtiger.GenericUtilities.ExcelFileUtility;
import Vtiger.GenericUtilities.JavaUtilities;
import Vtiger.GenericUtilities.PropertyFileUtility;
import Vtiger.GenericUtilities.WebDriverUtility;
import VtigerPomPages.LogoutPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC01_CreateOrganizationTest {
@Test
public void tC01_CreateOrganization() throws IOException, InterruptedException {
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
	String ORGNAME=eutil.readFromExcel("Organizations", 1, 2);
	

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
	
	//login to App
	LoginPage lp=new LoginPage(driver);
	lp.LoginToApp(USERNAME, PASSWORD);
	
	//Click on Organization Link
	HomePage hp=new HomePage(driver);
	hp.clickOrganizations();
	
	//Create Organization
	OrganizationsPage op=new OrganizationsPage(driver);
	op.createOrganizationPage(ORGNAME+rnum);
	Thread.sleep(2000);
	
	//Validate organization created
	InformationPage ip=new InformationPage(driver);
	String valOrgName= ip.validateOrgpPage();
	if(valOrgName.contains(ORGNAME+rnum)) {
		System.out.println("Organizatioon "+ORGNAME+" is created. Test Pass");
	}else {
		System.out.println("Test Fail");
	}
	//Logout of App
	lp.LogoutOfApp(driver);
	
	//Quit Driver
	driver.quit();
}
}
