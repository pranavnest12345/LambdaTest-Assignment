package Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.URL;
import java.util.HashMap;

public class Scenario_1 {
	
	private RemoteWebDriver driver;
	private String status = "failed";

	@BeforeMethod
	public void setUp() throws Exception {
		String LT_USERNAME = "pranavsofcl";
		String LT_ACCESS_KEY = "0IC76HCkWmPzcOATcUpvqNwdJwbYbNhKiLsbfgbVgB8D6d36YA";
		String hub = "@hub.lambdatest.com/wd/hub";

		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("121.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("build", "Scenario 1");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("project", "Scenario 1");
		ltOptions.put("console", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + hub), browserOptions);
	}

	@Test
	public void testLambdaTestSeleniumPlayground() throws Exception {
		System.out.println("Navigated to the https://www.lambdatest.com/selenium-playground");
		driver.get("https://www.lambdatest.com/selenium-playground");// Open LambdaTest's Selenium Playground Form

		WebElement simpleFormDemoLink = driver.findElement(By.linkText("Simple Form Demo"));
		simpleFormDemoLink.click();// Click "Simple Form Demo" under Input Forms
		Thread.sleep(2000);

		AssertJUnit.assertTrue(driver.getCurrentUrl().contains("simple-form-demo"));// Validate that the URL contains
																					// simple-form-demo
		Thread.sleep(2000);
		String message = "Welcome to LambdaTest"; // Create a variable for a String Value

		WebElement enterMessageInput = driver.findElement(By.id("user-message"));
		enterMessageInput.sendKeys(message);// Use the variable to enter values in the "Enter Message" textbox
		Thread.sleep(2000);

		WebElement showMessageButton = driver.findElement(By.id("showInput"));
		showMessageButton.click();// click "Get Checked Value"
		Thread.sleep(2000);

		WebElement yourMessageElement = driver.findElement(By.id("message"));
		String displayedMessage = yourMessageElement.getText();
		AssertJUnit.assertTrue(displayedMessage.contains(message)); // Validate the message
		Thread.sleep(2000);
		System.out.println("Completed Scenario 1");
	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status="+ status);
		driver.quit();
		}
	}
