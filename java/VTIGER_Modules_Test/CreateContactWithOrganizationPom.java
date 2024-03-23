package VTIGER_Modules_Test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import Vtiger.GenericUtilities.ExcelFileUtility;
import Vtiger.GenericUtilities.JavaUtilities;
import Vtiger.GenericUtilities.PropertyFileUtility;
import Vtiger.GenericUtilities.WebDriverUtility;
import VtigerPomPages.CreateContactPage;
import VtigerPomPages.CreateOrganizationPage;
import VtigerPomPages.LoginPage;
import VtigerPomPages.LogoutPage;
import VtigerPomPages.VerifyCreateContactWithOrgNamePage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class CreateContactWithOrganizationPom {

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
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp(USERNAME, PASSWORD);
				
		//Create Organization
		CreateOrganizationPage createOrgPage= new CreateOrganizationPage(driver);
		createOrgPage.createOrganization(ORGNAME+rnum);
			
		//Create Contact with Organization Name
		Thread.sleep(1000);
		CreateContactPage createContactPage=new CreateContactPage(driver);
		createContactPage.createContactWithOrgName(driver, LASTNAME+rnum, ORGNAME+rnum);
		
		//verify Orgname and Contact Name
		VerifyCreateContactWithOrgNamePage verifyLastAndOrgName=new VerifyCreateContactWithOrgNamePage(driver);
		String genLASTNAME = verifyLastAndOrgName.verifyLastName();
		String genORGNAME = verifyLastAndOrgName.verifyOrgName();
		
		if (genLASTNAME.contains(LASTNAME+rnum)) {
			System.out.println("Contact Name "+LASTNAME+rnum+" created with Organization "+genORGNAME+".Test Pass");
				
			
			}
		else {
			System.out.println("Test Fail!");
		}	
	
		//Logout of WebPage
		LogoutPage logout=new LogoutPage(driver);
		logout.logoutOfApp();
		
		driver.close();
		
	
	
	}

}
