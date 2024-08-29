package vaibhav.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vaibhav.testcomponents.BaseTest;
import vaibhav.testcomponents.Retry;
import vaibhav.pageobjects.CartProduct;
import vaibhav.pageobjects.LoginPage;
import vaibhav.pageobjects.OrderConfirmation;
import vaibhav.pageobjects.OrderHistory;
import vaibhav.pageobjects.PlaceOrder;
import vaibhav.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest extends BaseTest {
	
	//String productName = "IPHONE 13 PRO";
			String expectedMsg = "THANKYOU FOR THE ORDER.";

	@Test(groups = "purchase", dataProvider = "getData")
	public void submitOrder(HashMap<String,String> input) {
		// TODO Auto-generated method stub

		

		// Login page

		ProductCatalog productcatalog = loginpage.logintoApp(input.get("email"), input.get("password"));

		// Product Catalog

		List<WebElement> products = productcatalog.getProductsList();
		WebElement product = productcatalog.getProduct(input.get("productName"));
		productcatalog.addToCart();
		CartProduct cartProduct = productcatalog.cartCheck();

		// Cart validation

		Assert.assertTrue(cartProduct.isProductAdded(input.get("productName")));
		PlaceOrder placeorder = cartProduct.goToCheckout();

		// place order

		OrderConfirmation orderconfirmation = placeorder.orderSubmission("ca");

		// order confirmation

		Assert.assertTrue(orderconfirmation.isMsgCorrect(expectedMsg));

	}

	@Test(dependsOnMethods = { "submitOrder" }, groups = "purchase", dataProvider = "getData")
	public void orderValidation(HashMap<String,String> input) {
		ProductCatalog productcatalog = loginpage.logintoApp(input.get("email"), input.get("password"));
		OrderHistory orderhistory = new OrderHistory(driver);
		Assert.assertTrue(orderhistory.isProductOrdered(input.get("productName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<Object,Object>> data = getJsonData(System.getProperty("user.dir")+"\\src\\\\test\\\\java\\\\vaibhav\\\\data\\\\purchase.json");
		
		
		
		return new Object[][] { { data.get(0) },
				{data.get(1) } };

	}

}
