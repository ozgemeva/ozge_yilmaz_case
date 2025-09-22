package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CareersPage;
import pages.HomePage;
import testData.TestDataSets;

public class HomePageTest extends BaseTest {
	private HomePage hp;
	private CareersPage cp;

	@BeforeClass
	public void driverObjcetsClass() {
		hp = new HomePage(driver);
	}

	@Test(priority = 1)
	public void verifyHomePageIsOpened() {

		System.out.println("The url of the home page is : " + TestDataSets.base_url);

		hp.acceptCookiesIfPresent();
		// check for insider that include inseder title
		Assert.assertTrue(hp.isHomePageOpened(TestDataSets.baseUrlInclude), "Home page is not opened correctly!");
	}

	@Test(priority = 2, dependsOnMethods = "verifyHomePageIsOpened")
	public void clickCompanyLinkTest() {
		hp.clickCompanyLink();
		WebElement dropdownMenu = driver.findElement(By.xpath("//a[normalize-space()='Company']"));
		Assert.assertTrue(dropdownMenu.isDisplayed(), "Company menu is not visible!");
	}

	@Test(priority = 3, dependsOnMethods = "clickCompanyLinkTest")
	public void clickCareerLinkTest() {
		hp.clickCareerLink();
		WebElement subdropdownMenu = driver.findElement(By.xpath("//a[normalize-space()='Careers']"));
		Assert.assertTrue(subdropdownMenu.isEnabled(), "careers menu is not visible!");

	}

}
