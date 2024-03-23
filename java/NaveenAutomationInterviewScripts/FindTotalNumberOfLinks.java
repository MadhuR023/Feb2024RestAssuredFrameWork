package NaveenAutomationInterviewScripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import NaveenAutomation.NUtilityPackage;

public class FindTotalNumberOfLinks {
	@Test
	public void findTotalNumberOfLinks() {
	
	WebDriver driver=new ChromeDriver();
	driver.get("https://www.amazon.in/");
	//find total links in the page
	List<WebElement> linkslist=driver.findElements(By.tagName("a"));
	int totalLinks=linkslist.size();
	
	//print all the links
	System.out.println("Total Links:" +totalLinks);
	
	//print text of each link
	int count=0;
	for(WebElement link:linkslist) {
		
		String text=link.getText();
		if(text.length()>0) {
		
		System.out.println(count+ "  " +text);
		System.out.println("");
	}
		count++;
	}
	}
}
