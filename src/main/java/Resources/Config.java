package Resources;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Config {
	public WebDriver driver;

	@BeforeSuite(alwaysRun = true)
	public void init() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\hkhandaker\\Desktop\\Selenium_Webdriver\\GpTest\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\hkhandaker\\AppData\\Local\\Programs\\global-payments\\Global Payments.exe");
			options.addArguments("--app="
					+ Arrays.asList("C:\\Users\\hkhandaker\\AppData\\Local\\Programs\\global-payments"));

			options.setCapability("chromeOptions", options);
			driver = new ChromeDriver(options);
		}
	}

	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
}
