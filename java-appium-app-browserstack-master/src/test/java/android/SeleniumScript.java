package android;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumScript {
	WebDriver driver;

	@Test(priority=1)
	public void setUp() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automate.browserstack.com/");
		driver.findElement(By.linkText("Sign in with Google")).click();
		driver.findElement(By.id("identifierId")).sendKeys("learn.grow90@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Learn@90");
		driver.findElement(By.xpath("//span[text()='Next']")).click();

		Thread.sleep(3000);

		driver.findElement(By.id("app_live_cross_product_explore")).click();

		driver.findElement(By.id("skip-local-installation")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(text(),'Upload')]")).click();
		Thread.sleep(3000);
		WebElement elm = driver.findElement(By.xpath("//button[text()='Upload']"));
		elm.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='file-upload__input']")).sendKeys(
				"F:\\SmapleAPK\\WikipediaSample.apk");
		Thread.sleep(2000);
		driver.navigate().to("https://app-automate.browserstack.com/dashboard/v2");
		Thread.sleep(5000);

	}

	@Test
	public void browserStackAndroid() throws InterruptedException, MalformedURLException {

		String userName = "shaveta_wxcV7k";
		String accessKey = "zz5uALPRArLXarTypMru";

		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("device", "Samsung Galaxy S8 Plus");
		caps.setCapability("os_version", "7.0");
		caps.setCapability("project", "My First Project");
		caps.setCapability("build", "My First Build");
		caps.setCapability("name", "Bstack-[Java] Sample Test");
		caps.setCapability("app_url", "bs://23963a99e9b05b2a47e21fb99bd39dc42b397212");
		 

		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
				new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);

		AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
		searchElement.click();
		AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30)
				.until(ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
		insertTextElement.sendKeys("BrowserStack");
		Thread.sleep(5000);

		List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
		assert (allProductsName.size() > 0);

		// The driver.quit statement is required, otherwise the test continues to
		// execute, leading to a timeout.

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
