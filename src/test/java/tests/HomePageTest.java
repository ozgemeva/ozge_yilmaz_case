package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import testData.TestDataSets;

public class HomePageTest extends BaseTest {
	private HomePage hp;
	

	@BeforeClass
	public void driverObjcetsClass() {
		hp = new HomePage(driver);
	}

	@Test(priority = 1)
	public void verifyHomePageIsOpened() {
		 System.out.println("Checking home page at URL: " + TestDataSets.BASE_URL);

		    // Accept cookies if popup exists
		    hp.acceptCookiesIfPresent();

		    // Check title contains "Insider"
		    boolean isOpened = hp.isHomePageOpened("Insider");
		    Assert.assertTrue(isOpened, "Home page is not opened correctly!");
	}

	@Test(dependsOnMethods = "verifyHomePageIsOpened")
	public void clickCompanyLinkTest() {
		hp.clickCompanyLink();
		Assert.assertTrue(hp.isdisablefordropdownMenu(), "Company menu is not visible!");
	}

	@Test(dependsOnMethods = "clickCompanyLinkTest")
	public void clickCareerLinkTest() {
		hp.clickCareerLink();
		WebElement subdropdownMenu = driver.findElement(By.xpath("//a[normalize-space()='Careers']"));
		Assert.assertTrue(subdropdownMenu.isEnabled(), "careers menu is not visible!");

	}

}
