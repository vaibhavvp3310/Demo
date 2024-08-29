package vaibhav.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vaibhav.AbstarctComponents.AbstractComponent;

public class OrderHistory extends AbstractComponent {
	
	WebDriver driver;
	public OrderHistory(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderItems;
	
	public boolean isProductOrdered(String productname) {
		goToOrders();
		WebElement orderedItem = orderItems.stream().filter(s->s.getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
		if(orderedItem.getText().equalsIgnoreCase(productname)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	


}
