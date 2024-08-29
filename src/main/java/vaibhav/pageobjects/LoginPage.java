package vaibhav.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vaibhav.AbstarctComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public ProductCatalog logintoApp(String useremail, String userpassword) {
		email.sendKeys(useremail);
		password.sendKeys(userpassword);
		login.click();
		ProductCatalog productcatalog = new ProductCatalog(driver);
		return productcatalog;
	}
	
	public String getLoginError() {
		waitForElementToAppear(By.cssSelector("[class*='flyInOut']"));
		return errorMsg.getText();
	}

}
