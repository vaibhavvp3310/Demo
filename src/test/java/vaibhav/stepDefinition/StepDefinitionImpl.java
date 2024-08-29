package vaibhav.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vaibhav.pageobjects.CartProduct;
import vaibhav.pageobjects.LoginPage;
import vaibhav.pageobjects.OrderConfirmation;
import vaibhav.pageobjects.PlaceOrder;
import vaibhav.pageobjects.ProductCatalog;
import vaibhav.testcomponents.BaseTest;

// Tidy Gherkin plugin to generate this file automatically

public class StepDefinitionImpl extends BaseTest{
	
	
	public LoginPage loginpage;
	public ProductCatalog productcatalog;
	public PlaceOrder placeorder;
	public OrderConfirmation orderconfirmation;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		loginpage =landingPage();
	}
	
	@Given("^logged in to application with username (.+) and password (.+)$")
	public void logged_in_to_application(String username, String password) {
		 productcatalog = loginpage.logintoApp(username, password);
	}
	
	@When("^select product (.+) and add to cart$")
	public void select_product_and_add_to_cart(String productName) {
		List<WebElement> products = productcatalog.getProductsList();
		WebElement product = productcatalog.getProduct(productName);
		productcatalog.addToCart();
	}
	
	@When("^checkout product (.+)$")
	public void checkout_product(String productname) {
		CartProduct cartProduct = productcatalog.cartCheck();
		Assert.assertTrue(cartProduct.isProductAdded(productname));
		placeorder = cartProduct.goToCheckout();
	}
	
	@When("submit order")
	public void submit_order() {
		orderconfirmation = placeorder.orderSubmission("ca");
	}
	
	@Then("validate order confirmation message {string}")
	public void validate_order_confirmation_message(String string) {
		Assert.assertTrue(orderconfirmation.isMsgCorrect(string));
		executionFinished();
	}
	
	
	
	@Then("error message {string} should be displayed")
	public void error_message_should_be_displayed(String msg) throws InterruptedException {
	    Thread.sleep(1000);
		Assert.assertTrue(loginpage.getLoginError().equalsIgnoreCase(msg));
		executionFinished();
	}

}
