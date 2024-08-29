package vaibhav.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vaibhav.AbstarctComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	WebElement productOfChoise;

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productsLocator = By.cssSelector(".mb-3");

	By product = By.cssSelector(".card-body b");

	By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	By locatorBy = By.cssSelector(".ng-animating");
	By toastMsg = By.id("toast-container");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsLocator);
		return products;

	}

	public WebElement getProduct(String productName) {
		productOfChoise = getProductsList().stream()
				.filter(s -> s.findElement(product).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return productOfChoise;
	}

	public void addToCart() {
		productOfChoise.findElement(addToCartButton).click();
	}

	public CartProduct cartCheck() {
		waitForElementToAppear(locatorBy);
		waitForElementToDisappear(toastMsg);
		goToCart();
		return new CartProduct(driver);
	}

}
