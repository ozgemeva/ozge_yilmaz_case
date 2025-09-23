package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CareersPage;
import pages.HomePage;
import testData.TestDataSets;

public class CareersPageTest extends BaseTest {
	CareersPage cp;
	HomePage hp;

	@BeforeClass
	public void initPages() {
		hp = new HomePage(driver);
		cp = new CareersPage(driver);
	}

	private void navigateToCareers() {
		hp.acceptCookiesIfPresent();
		hp.clickCompanyLink();
		hp.clickCareerLink();
	}

	@Test
	public void verifyOnCareerPageIsOpened() {
		navigateToCareers();
		Assert.assertTrue(cp.isOnCareerPage(TestDataSets.careers_url), "Careers page is not opened correctly!");
	}

	@Test(priority = 1, dependsOnMethods = "verifyOnCareerPageIsOpened")
	public void verifyScrollToCarousel() {
		cp.scrollToCarouselSection();
		Assert.assertTrue(cp.isCarouselVisible(), "Slider section is not visible.");
	}

	@Test(priority = 2, dependsOnMethods = "verifyScrollToCarousel")
	public void carouselChanges() {
		String beforeSlider = cp.getSliderBeforeTitle();
		System.out.println("Before title : "+ beforeSlider);
		cp.clickNextArrow();
		
		String afterSlider = cp.getSliderAfterTitle(beforeSlider);
		System.out.println("After title : "+ afterSlider);
		Assert.assertNotEquals(beforeSlider, afterSlider, "Slider did not move!");
	}

	@Test
	public void verify_seeAllBtn() {
		Assert.assertTrue(cp.btn_seeAllClick(),"button is not clickable");
	}

}