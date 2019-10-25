package TestAutomation.testCases;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
public class MD071_Missing_MainframeTest {
	public WebDriver driver;
	@Test
	public void gpFileDownload() throws InterruptedException, AWTException,
			IOException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\hkhandaker\\Desktop\\Selenium_Webdriver\\GpTest\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Users\\hkhandaker\\AppData\\Local\\Programs\\global-payments\\Global Payments.exe");
		options.addArguments("--app="
				+ Arrays.asList("C:\\Users\\hkhandaker\\AppData\\Local\\Programs\\global-payments"));

		options.setCapability("chromeOptions", options);
		driver = new ChromeDriver(options);

		// Enter PDF file path in pdf text box
		driver.findElement(By.xpath("//input[@id='filePathPDFInput']"))
				.sendKeys("C:\\Users\\hkhandaker\\Desktop\\Selenium_Webdriver\\GpTest\\p1.pdf");
		System.out.println("Entered File path");

		// Enter MF File path in MF text box
		driver.findElement(By.xpath("//input[@id='filePathMainframeInput']"))
				.sendKeys("C:\\Users\\hkhandaker\\Desktop\\Selenium_Webdriver\\GpTest\\m1.60749");
		System.out.println("Entered File path");

		// Click drop down
		driver.findElement(By.xpath("//select[@id='parserFormatSelect']"));
		WebElement Parser = driver.findElement(By
				.xpath("//select[@id='parserFormatSelect']"));
		Select Dropdown = new Select(Parser);
		Dropdown.selectByIndex(1);

		// Click Submit
		driver.findElement(By.xpath("//*[@id=\"submitBtn\"]")).click();
		Thread.sleep(12000);

		// StringSelection is a class that can be used for copy and paste
		// operations.
		StringSelection stringSelection = new StringSelection(
				"C:\\Users\\hkhandaker\\Desktop\\Selenium_Webdriver\\GpTest");
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);

		Robot robot = new Robot();

		// Copy and paste the folder file path in the window popup
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(3000);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(3000);

		// saving the folder
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		// Accept the Info pop-up window
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		// Accept the Success pop-up window
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

		// Reading file from the file location
		BufferedReader br = new BufferedReader(
				new FileReader(
						"C:\\Users\\hkhandaker\\Desktop\\Selenium_Webdriver\\GpTest\\MD071\\MD071-Missing-Mainframe.tsv"));
		String beforeString;
		String afterString = "";
		// stored the expected value in a local variable
		String expectedValue = "ICA_BIN	TRAN_DATE	MERCHANT_NUMBER	TRAN_AMOUNT	ACCOUNT_NUMBER	TRAN_CODE	TRAN_CURRENCY	LINE_NUMBER	hash";
		List<String> lines = new ArrayList<String>() ;
		for (int i = 0; i < 5; i++) {
			beforeString = br.readLine();
			afterString=beforeString+afterString;
			// System.out.println("line is     "+afterString);
			lines.add(beforeString.trim());
		}
		for (String line : lines) {
			System.out.println("line from text file is        " + line);
		}
		br.close();
		Assert.assertTrue(lines.contains(expectedValue));
		System.out.println("Our expected value is for Missing-Mainframe test= "+expectedValue);	
	}
	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}

}
