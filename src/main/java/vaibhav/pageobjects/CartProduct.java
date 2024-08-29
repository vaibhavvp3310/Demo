package vaibhav.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dev.failsafe.internal.util.Assert;
import vaibhav.AbstarctComponents.AbstractComponent;

public class CartProduct extends AbstractComponent {

	WebDriver driver;

	public CartProduct(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "ul .items")
	List<WebElement> productsInCart;
	 
	 
	 @FindBy(css=".totalRow button")
	 WebElement checkout;

	public Boolean isProductAdded(String productName) {

		WebElement product = productsInCart.stream()
				.filter(s -> s.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
		if (product.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName)) {
			return true;
		} else {
			return false;
		}
	}
	
	public PlaceOrder goToCheckout() {
		checkout.click();
		return new PlaceOrder(driver);
	}

}
