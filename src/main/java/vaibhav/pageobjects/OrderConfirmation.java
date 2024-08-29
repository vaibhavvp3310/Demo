package vaibhav.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmation {
	WebDriver driver;
	public OrderConfirmation(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName ="h1")
	WebElement msg;
	
	public boolean isMsgCorrect(String expectedMsg) {
		if(msg.getText().equalsIgnoreCase(expectedMsg)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
}
