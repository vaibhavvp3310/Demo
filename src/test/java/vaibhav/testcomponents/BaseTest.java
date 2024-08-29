package vaibhav.testcomponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import vaibhav.pageobjects.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginpage;
	
	
	public void initalizeDriver() throws IOException {
	Properties properties = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\vaibhav\\resources\\GlobalData.properties");
	properties.load(fis);
	String browser = System.getProperty("browser")!= null?System.getProperty("browser"): properties.getProperty("browser") ;
	
	
	if(browser.contains("chrome")) {
    ChromeOptions options = new ChromeOptions();
    if(browser.contains("headless")) {
    options.addArguments("headless");
    }
	driver = new ChromeDriver(options);
	//optional 
	driver.manage().window().setSize(new Dimension(1400,900));
	}else if(browser.equalsIgnoreCase("firefox")) {
		//firefox code
	}else if(browser.equalsIgnoreCase("edge")) {
		System.setProperty("webdriver.edge.driver","C:\\Users\\vaibh\\Downloads\\msedgedriver.exe");
		driver = new EdgeDriver();
	}
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	

	
}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
     TakesScreenshot screenshot = (TakesScreenshot)driver;
   
    File src= screenshot.getScreenshotAs(OutputType.FILE);
    File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
    FileUtils.copyFile(src, file);
    return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    
     
     
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage landingPage() throws IOException {
		initalizeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		loginpage= new LoginPage(driver);
		return loginpage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void executionFinished() {
		driver.close();
	}
	
	public List<HashMap<Object, Object>> getJsonData(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
	    ObjectMapper mapper = new ObjectMapper();
	    List<HashMap<Object,Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<Object,Object>>>() {
		});
	    
	    return data;
	}
	
}
