package VTIGER_Modules_Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

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

public class TC_02_CreateContactWithOrganization {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		RandomNumber rnum=new RandomNumber();
		int genRNUM = rnum.rNum();
		
				//Read Data From PropertyFile and Excel
				FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
				Properties pobj=new Properties();
				pobj.load(fis);
				String BROWSER = pobj.getProperty("browser");
				String URL = pobj.getProperty("url");
				String USERNAME = pobj.getProperty("username");
				String PASSWORD = pobj.getProperty("password");
				
				FileInputStream fis2=new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
				Workbook wb=WorkbookFactory.create(fis2) ;
				Sheet sh = wb.getSheet("Contacts");
				Row rw = sh.getRow(4);
				Cell ce = rw.getCell(2);
				String LASTNAME = ce.getStringCellValue();
				
				Sheet sh1 = wb.getSheet("Contacts");
				Row rw1 = sh1.getRow(4);
				Cell ce1 = rw1.getCell(3);
				String ORGNAME = ce1.getStringCellValue();
				
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
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(URL);
				
				//Login to App
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				String parent = driver.getWindowHandle();
				System.out.println(parent);
				
				//Click on Organizations Link
				driver.findElement(By.linkText("Organizations")).click();
			
				//Click on '+'(Create Organization
				driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
			
				//Enter Mandatory field
				driver.findElement(By.name("accountname")).sendKeys(ORGNAME+genRNUM);
				
				//Click on Save
				driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
				Thread.sleep(2000);
										
				//Click on Contacts Module
				driver.findElement(By.linkText("Contacts")).click();
				
				//click on Create contact symbol(+)
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				
				//Enter Mandatory Text
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME+genRNUM);
				
				//click on Organization symbol
				
				driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
				Set<String> child = driver.getWindowHandles();
				for (String b:child) {
					driver.switchTo().window(b);
					
				}
				
				//enter Organization name
				driver.findElement(By.id("search_txt")).sendKeys(ORGNAME+genRNUM);
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
				if (genLASTNAME.contains(LASTNAME+genRNUM)) {
					if (genORGNAME.contains(ORGNAME+genRNUM)) {
						System.out.println("Contact Name "+LASTNAME+genRNUM+" created with Organization "+ORGNAME+genRNUM+".Test Pass");
						
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
