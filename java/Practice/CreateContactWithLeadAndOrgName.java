package Practice;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.beust.jcommander.Strings;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.Enter;

public class CreateContactWithLeadAndOrgName {
	@Test
	public void createContactWithLeadAndOrgName() throws IOException, InterruptedException {
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
		Row rw = sh.getRow(7);
		Cell ce = rw.getCell(2);
		String LASTNAME = ce.getStringCellValue();
		
		Sheet sh1 = wb.getSheet("Contacts");
		Row rw1 = sh1.getRow(7);
		Cell ce1 = rw1.getCell(4);
		String ORGNAME = ce1.getStringCellValue();
		
		Sheet sh2 = wb.getSheet("Contacts");
		Row rw2 = sh2.getRow(7);
		Cell ce2 = rw2.getCell(3);
		String LEADSOURCE = ce2.getStringCellValue();
		
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
		
		
		//Click on Create Organization
		driver.findElement(By.linkText("Organizations")).click();
		//Click on Create Org lookup image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		//Enter Organization Name
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+genRNUM);
		//click on save Orgname
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//Validate ORGNAME
		String valOrgname=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(valOrgname.contains((ORGNAME+genRNUM))) {
			System.out.println("Organization "+(ORGNAME+genRNUM)+" Created successfully");
		}
		//click on Contact link
		driver.findElement(By.linkText("Contacts")).click();
		//click on contact lookup image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		//Enter Last Name
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		//Click on Organization Lookup Image
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img[@alt='Select']")).click();
		 Set<String> child = driver.getWindowHandles();
		for (String b : child) {
			driver.switchTo().window(b);
		}
		//Enter Organization name Created
		driver.findElement(By.id("search_txt")).sendKeys(ORGNAME+genRNUM);
		//Click on Search
		driver.findElement(By.name("search")).click();
		Thread.sleep(2000);
		//click on Organization name
		driver.findElement(By.id("1")).click();
		//switch to default window
		driver.switchTo().window(parent);
		//Enter Lead source
		WebElement ele=driver.findElement(By.name("leadsource"));
		ele.click();
		Select s=new Select(ele);
		s.selectByValue(LEADSOURCE);
		//click on Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Validate Contact
		String lastname=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String leadname=driver.findElement(By.id("mouseArea_Lead Source")).getText();
		
		if(lastname.contains(LASTNAME) && leadname.contains(LEADSOURCE)){
			System.out.println("Contact "+LASTNAME+" is created with Lead source as "+leadname);
		}
		else {
			System.out.println("Test Fail");

		}
		
		
		
	}

}
