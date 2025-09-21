package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import testData.TestDataSets;
import utils.DriverManager;

public class HomePageTest {
	@Test
	public void verifyHomePageIsOpened() {
		// Driver is started
		WebDriver driver = DriverManager.getDriver(TestDataSets.browserName);
		// opened site
		driver.get(TestDataSets.base_url);
		HomePage hp = new HomePage(driver);
		// check for insider that include inseder title
		Assert.assertTrue(hp.isHomePageOpened("insider"), "Home page is not opened correctly!");
		// Driver is closed
		DriverManager.quitDriver();

	}

}
