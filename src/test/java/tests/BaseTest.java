package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import testData.TestDataSets;
import utils.DriverManager;

public abstract class BaseTest {
	protected static WebDriver driver;

	@BeforeSuite
	public void setUpSuite() {
		driver = DriverManager.getDriver(TestDataSets.browserName);
		driver.get(TestDataSets.base_url);
	}

	@AfterSuite
	public void tearDownSuite() {
		DriverManager.quitDriver();
	}
}
