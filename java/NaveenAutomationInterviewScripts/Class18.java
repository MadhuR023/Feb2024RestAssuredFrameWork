package NaveenAutomationInterviewScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Class18 {
	@Test
	public void javaScriptExecuter() {
		WebDriver driver =new ChromeDriver();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		String script="return window.getComputedStyle(document.querySelector(\"label[for='input-firstname'\"),'::before').getPropertyValue('content')";
		
		String man_field=js.executeScript(script).toString();
		System.out.println(man_field);
		
		if(man_field.contains("*")) {
			System.out.println("It is a Mandatory Field");
		}
	}

	@Test
	public void getTextFromTextField() {
		WebDriver driver =new ChromeDriver();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
		driver.findElement(By.id("input-firstname")).sendKeys("Madhusudhan");
		//We cannot use .geText() to fetch the text we have Entered
		//String Text=driver.findElement(By.name("firstname")).getText();
		//Instead we will use .getAttribute("value")
		String Text=driver.findElement(By.name("firstname")).getAttribute("value");
		System.out.println(Text);
	}
	
	
	
}
