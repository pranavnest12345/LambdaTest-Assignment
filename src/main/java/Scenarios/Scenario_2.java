package Scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.URL;
import java.util.HashMap;

public class Scenario_2 {
	
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
		ltOptions.put("build", "Scenario 2");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("project", "Scenario 2");
		ltOptions.put("console", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + hub), browserOptions);
	}
	@Test
	public void testDragAndDropSlider() throws InterruptedException {
		Actions actions = new Actions(driver);
		System.out.println("Navigated to the https://www.lambdatest.com/selenium-playground");
		driver.get("https://www.lambdatest.com/selenium-playground");// Open the
																		// https://www.lambdatest.com/selenium-playground
																		// page

		WebElement dragAndDropSlidersLink = driver.findElement(By.linkText("Drag & Drop Sliders"));
		dragAndDropSlidersLink.click();// click “Drag & Drop Sliders”
		Thread.sleep(2000);

		WebElement slider = driver.findElement(By.id("slider3"));
		actions.dragAndDropBy(slider, 0, 0).perform();
		WebElement rangeValueElement = driver.findElement(By.xpath("//div[@id=\"slider3\"]//child::output"));
		Thread.sleep(2000);

		actions.dragAndDropBy(slider, 195, 0).perform();
		String rangeValue = rangeValueElement.getText();
		AssertJUnit.assertEquals("95", rangeValue);
		Thread.sleep(2000);
		System.out.println("Completed Scenario 2");
		// Select the slider “Default value 15” and drag the bar to make it 95 by
		// validating whether the range value shows 95.
	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status="+ status);
		driver.quit();
		}
	}
