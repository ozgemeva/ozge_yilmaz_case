package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;
import testData.TestDataSets;
import utils.DriverManager;

public class HomePageTest {
	private WebDriver hp_driver;
	private HomePage hp;

	@BeforeClass
	public void setUp() {
		// Driver is started
		hp_driver = DriverManager.getDriver(TestDataSets.browserName);
		hp_driver.get(TestDataSets.base_url);
		hp = new HomePage(hp_driver);
	}

	@Test
	public void verifyHomePageIsOpened() {
		System.out.println("The url of the home page is : " + TestDataSets.base_url);
		
		hp.acceptCookiesElement();
		
		// check for insider that include inseder title
		Assert.assertTrue(hp.isHomePageOpened("insider"), "Home page is not opened correctly!");
	}

	@AfterClass
	public void setDown() {
		DriverManager.quitDriver();
	}

}
