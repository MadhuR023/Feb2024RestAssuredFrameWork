package SkillraryImplementation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import Vtiger.GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RegrestionTest1 {
	
	@Test
	public void regressionTest() throws InterruptedException {
		
		WebDriverUtility wutil=new WebDriverUtility();
		//Launch Browser
		WebDriver driver=null;
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		
		//Enter URL
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://skillrary.com/");
		
		//Click on Gears
		driver.findElement(By.xpath("//a[text()=' GEARS ']")).click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.xpath("//li/ul//a[text()=' SkillRary Demo APP']/span"));

		Thread.sleep(3000);
		wutil.handleDropDown(ele," SkillRary Demo APP");
		ele.click();
		
		
		
	}

}
