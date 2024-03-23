package NaveenAutomationInterviewScripts;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Class16_SwitchWindowAllWindows1 {

	@Test
	public void switchWindowAllWindowsAtOnce() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(4000);
		WebElement ytLink=driver.findElement(By.xpath("//a[contains(@href,'youtube')]"));
		ytLink.click();
		Set<String> wHandles=driver.getWindowHandles();
		Iterator<String> it = wHandles.iterator();
		String parentWindow=it.next();
		System.out.println("parent Window Handle:" +parentWindow);
		String childWindow=it.next();
		System.out.println("Child Window Handle:" +childWindow);
		WebDriver childwin = driver.switchTo().window(childWindow);
		System.out.println("URL of Child Window:" +childwin.getCurrentUrl());
		driver.close();
		WebDriver parentWin=driver.switchTo().window(parentWindow);
		System.out.println("URL of Parent Window:" +childwin.getCurrentUrl());
		driver.quit();


		
		
		
	}
}
