package NaveenAutomationInterviewScripts;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class Class16_SwitchWindowAllWindows2 {
	@Test
	public void class16_SwitchWindowAllWindows2() throws InterruptedException {
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		String parentWindow = driver.getWindowHandle();
		Thread.sleep(4000);
		WebElement tLink=driver.findElement(By.xpath("//a[contains(@href,'twitter')]"));		
		WebElement ftLink=driver.findElement(By.xpath("//a[contains(@href,'facebook')]"));
		WebElement ytLink=driver.findElement(By.xpath("//a[contains(@href,'youtube')]"));
		WebElement linLink=driver.findElement(By.xpath("//a[contains(@href,'linkedin')]"));
	
		tLink.click();
		ftLink.click();
		ytLink.click();
		linLink.click();
		
		
		Set<String> windowHandles=driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();
		
		while(it.hasNext()) {
				String windowId=it.next();
				driver.switchTo().window(windowId);
				System.out.println("url:" +driver.getCurrentUrl());
				Thread.sleep(1500);
							
				if(!windowId.equals(parentWindow)) {
					driver.close();
				}
				
		}
		
		WebDriver parLink=driver.switchTo().window(parentWindow);
		System.out.println("Parent Window URL:"+parLink.getCurrentUrl());
	}
}