package NaveenAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NUtilityPackage {
	private WebDriver driver; 
	
	public NUtilityPackage(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public void doSendKeys(By locator, String value) {
		driver.findElement(locator).sendKeys(value);
	}
	
	public void doClick(By locator) {
		driver.findElement(locator).click();
	}
	
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	
}
