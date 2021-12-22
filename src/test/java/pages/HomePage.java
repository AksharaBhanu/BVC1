package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(xpath="//td[text()='Enter Time-Track']")
	private WebElement welcomeMsg;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public boolean verifyHomePageDisplayed() {
		
		return welcomeMsg.isDisplayed();
	}
}
