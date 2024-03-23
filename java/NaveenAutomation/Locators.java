package NaveenAutomation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Locators {
	@Test
	public void locatorsTest() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
//		By username=By.id("input-email");
//		By password=By.id("input-password");
		NUtilityPackage nutil=new NUtilityPackage(driver);
		//Create new account
		//click on continue Button
		By continueBtn=By.linkText("Continue");
		nutil.doClick(continueBtn);
		
		//Enter Valid Details in registration Page
		By firstnameTxt =By.id("input-firstname");
		By lastnameTxt= By.id("input-lastname");
		By emailTxt= By.id("input-email");
		By telephoneTxt= By.id("input-telephone");
		By passwordTxt= By.id("input-password");
		By confPasswordTxt= By.name("confirm");
		By agreeRadioBtn= By.name("agree");
		By continueBtn2= By.xpath("//input[@type='submit']");
		
		
		nutil.doSendKeys(firstnameTxt, "madhu");
		nutil.doSendKeys(lastnameTxt, "raj");
		nutil.doSendKeys(emailTxt, "mr3@gmail.com");
		nutil.doSendKeys(telephoneTxt, "6547869852");
		nutil.doSendKeys(passwordTxt, "mrmadhu");
		nutil.doSendKeys(confPasswordTxt, "mrmadhu");
		nutil.doClick(agreeRadioBtn);
		nutil.doClick(continueBtn2);
		
		//Validate account created
		String expectedString="Your Account Has Been Created!";
		By validateAccount= By.tagName("h1");
		String nameValidate= nutil.getText(validateAccount);
		assertEquals(nameValidate, expectedString);
		System.out.println("Account Created successfully. Test Pass");
		
		
	}

}
