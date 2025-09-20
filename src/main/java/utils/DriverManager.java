package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	private static WebDriver driver;

	// Driver is started
	public static WebDriver getDriver(String browser) {
		System.out.println("---> "+browser+" is starting...");
		if (driver == null) {
			switch (browser.toLowerCase()) {
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			case "safari":
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				break;
			case "chrome":
			default:// The test will start on chrome browser automaticly.
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			}
			driver.manage().window().maximize();
		}
		return driver;
	}

	// Driver is closed
	public static void quitDriver() {
		System.out.println("---> The browser is closed ...");
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
