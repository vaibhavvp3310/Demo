package vaibhav.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vaibhav.AbstarctComponents.AbstractComponent;

public class PlaceOrder extends AbstractComponent{
	WebDriver driver;
	public  PlaceOrder(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By locator = By.cssSelector(".ta-item span");
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	
	
	public OrderConfirmation orderSubmission(String countryName) {
		selectCountry.click();
		selectCountry.sendKeys(countryName);
		waitForElementToAppear(locator);
		 List<WebElement> countries = driver.findElements(locator); 
		WebElement country = countries.stream().filter(s ->s.getText().contains("Canada")).findFirst().orElse(null); 
		country.click();
		submit.click();
		return new OrderConfirmation(driver);
		
	}
	
	

}
