package NaveenAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Class3 {
	@Test
public void browserUtils() {
	WebDriver driver= new ChromeDriver();
	driver.get("https://www.amazon.in/");
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.getCurrentUrl();// It is used when we have to navigate to another page and navigate back.
	
	}
}
