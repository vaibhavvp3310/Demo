package vaibhav.tests;

import org.testng.Assert;
import org.testng.annotations.Test;



import vaibhav.testcomponents.BaseTest;
import vaibhav.testcomponents.Retry;

public class ErrorValidation extends BaseTest {
	
	
	
	@Test(retryAnalyzer = Retry.class)
	public void loginError() throws InterruptedException {
		
		loginpage.logintoApp("vaibhasashiaspatel@gmail.com", "Civic476");
		Thread.sleep(1000);
		Assert.assertTrue(loginpage.getLoginError().equalsIgnoreCase("Incorrect email or password."));
	}

}
