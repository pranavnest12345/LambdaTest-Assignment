package Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class Scenario_3 {
	
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
		ltOptions.put("build", "Scenario 3");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("project", "Scenario 3");
		ltOptions.put("console", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + hub), browserOptions);
	}

	@Test
	public void testDragAndDropSlider2() throws InterruptedException {
		System.out.println("Navigated to the https://www.lambdatest.com/selenium-playground");
		driver.get("https://www.lambdatest.com/selenium-playground");// Open the https://www.lambdatest.com/selenium-playground page
		WebElement inputFormSubmitLink = driver.findElement(By.linkText("Input Form Submit"));
		inputFormSubmitLink.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		// Click "Submit" without filling in any information in the form
		WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		// Could not able to validate the validation message as it not able to located
		// Fill in Name, Email, and other fields
		WebElement username = driver.findElement(By.name("name"));
		username.click();
		username.sendKeys("Pranav S");
		Thread.sleep(2000);
		WebElement email = driver.findElement(By.id("inputEmail4"));
		email.click();
		email.sendKeys("pranavofcl@gmail.com");
		Thread.sleep(2000);

		driver.findElement(By.id("inputPassword4")).sendKeys("testpassword");
		Thread.sleep(2000);
		driver.findElement(By.id("inputAddress1")).sendKeys("Kakkanad");
		Thread.sleep(2000);
		driver.findElement(By.id("inputAddress2")).sendKeys("Kochi");
		Thread.sleep(2000);
		driver.findElement(By.id("inputCity")).sendKeys("Kochi,Kerala");
		Thread.sleep(2000);
		driver.findElement(By.id("company")).sendKeys("xyz");
		Thread.sleep(2000);
		driver.findElement(By.id("websitename")).sendKeys("xyz");
		Thread.sleep(2000);
		driver.findElement(By.id("inputState")).sendKeys("Kerala");
		Thread.sleep(2000);

		driver.findElement(By.id("inputZip")).sendKeys("555588");
		Thread.sleep(2000);
		// Select "United States" from the Country drop-down using the text property
		Select countryDropdown = new Select(driver.findElement(By.name("country")));
		countryDropdown.selectByVisibleText("India");
		Thread.sleep(2000);
		// Click "Submit" after filling all fields
		submitButton.click();
		Thread.sleep(2000);

		// Wait for the success message to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//p[contains(text(),\"Thanks for contacting us, we will get back to you shortly.\")]")));
		Thread.sleep(2000);
		// Validate the success message
		if (successMessage.getText().equals("Thanks for contacting us, we will get back to you shortly.")) {
			System.out.println("Success message validated successfully.");
		} else {
			System.out.println("Success message validation failed.");
		}
		
		System.out.println("Completed Scenario 3");
	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status="+ status);
		driver.quit();
		}
	}
