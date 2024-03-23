package PomImplementation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import PomUtilities.ContactPage;
import PomUtilities.HomePage;
import PomUtilities.InformationPage;
import PomUtilities.LoginPage;
import PomUtilities.OrganizationsPage;
import Vtiger.GenericUtilities.ExcelFileUtility;
import Vtiger.GenericUtilities.JavaUtilities;
import Vtiger.GenericUtilities.PropertyFileUtility;
import Vtiger.GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC02_CreateContactWithOrgnameTest {
	@Test
	public void createOrganization() throws IOException, InterruptedException {
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
		String ORGNAME=eutil.readFromExcel("Contacts", 4, 3);
		String LASTNAME=eutil.readFromExcel("Contacts", 4, 2);
		

		//Launch Browser
		WebDriver driver=null;
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		} 
		else {
			System.out.println("Invalid Browser");
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
		
		//Click on Contact Page
		hp.clickContacts();
		
		ContactPage cp=new ContactPage(driver);
		cp.createContact(LASTNAME+rnum, driver, ORGNAME);
		
		//Validate Contact Created
		String valContact= ip.validateContactOPage();
		
		if(valContact.contains(LASTNAME+rnum)) {
			System.out.println("Contact "+LASTNAME+" is created. Test Pass");
		}else {
			System.out.println("Test Fail");
		}
		
		driver.quit();
	}
}
