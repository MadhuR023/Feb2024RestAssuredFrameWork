package VTIGER_Modules_Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


import Practice.RandomNumber;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_01_CreateOrganization {

	public static void main(String[] args) throws IOException {
		RandomNumber rnum=new RandomNumber();
		int genRnum = rnum.rNum();
		
		WebDriver driver = null;
		// Step1: Read Data from PropertyFile
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		
		//Read data from Excel
		FileInputStream fis2=new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		Workbook wb= WorkbookFactory.create(fis2);
		Sheet sh = wb.getSheet("Organizations");
		Row rw = sh.getRow(1);
		Cell ce = rw.getCell(2);
		String ORGNAME = ce.getStringCellValue();	
		
		//Launch Browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			//WebDriverManager.chromedriver().setup();
		//	ChromeOptions options = new ChromeOptions();
			//options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		} 
		else {
			System.out.println("Invalid Browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		// Login to page
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Click on Organizations Link
		driver.findElement(By.linkText("Organizations")).click();

		// Click on '+'(Create Organization
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();

		// Enter Mandatory field
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+genRnum);

		//Click on Save
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//Verify ORGNAME
		String genOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (genOrgname.contains(ORGNAME+genRnum)) {
			System.out.println("Organisation "+ORGNAME+genRnum+" Created. Test Pass");
		}else {
			System.out.println("Test Fail!");
		}
		
		
		//Logout of webpage
		WebElement ele = driver.findElement(By.xpath("//td[@class='small']/table/tbody/tr/td[2]"));
		ele.click();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();
		
		
		
	}

}
