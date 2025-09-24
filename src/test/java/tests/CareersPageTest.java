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
		cp.scrollUntilSlider();
		Assert.assertTrue(cp.isCarouselVisible(), "Slider section is not visible.");
	}

	@Test(dependsOnMethods = "verifyScrollToCarousel")
	public void carouselChanges() {
		String beforeSlider = cp.getSliderBeforeTitle();
		System.out.println("Before title : " + beforeSlider);
		cp.clickNextArrowBtn();

		String afterSlider = cp.getSliderAfterTitle(beforeSlider);
		System.out.println("After title : " + afterSlider);
		Assert.assertNotEquals(beforeSlider, afterSlider, "Slider did not move!");
	}

	@Test(dependsOnMethods = "verifyOnCareerPageIsOpened")
	public void verifyJobItemsLoad() {

		int beforeClickAlltems = cp.getTeamsSizeCount();
		System.out.println("Job items before see all : " + beforeClickAlltems);
		
		Assert.assertTrue(cp.clickSeeAllTeamsBtn(), "button is not clickable");

		int afterClickAlltems = cp.getTeamsSizeCount();
		System.out.println("Job items after see all : " + afterClickAlltems);

		if (beforeClickAlltems == afterClickAlltems) {
			Assert.assertTrue(afterClickAlltems > 0, "Job item list is empty.");
		} else {
			Assert.assertTrue(afterClickAlltems > beforeClickAlltems, "Job items did not increase");
		}
	}
	
	@Test(dependsOnMethods = "verifyOnCareerPageIsOpened")
	public void verifyLifeAtInsiderSection() {
	    Assert.assertTrue(cp.isLifeAtInsiderVisible(), "Life at Insider section is missing!");
	    
	    int slides = cp.getLifeAtInsiderSlidesCount();
	    System.out.println("Life at Insider slides count: " + slides);
	    Assert.assertTrue(slides > 0, "No slides found in Life at Insider section!");
	}
	
	
	@Test(dependsOnMethods = "verifyScrollToCarousel")
	public void verifyRolePosition() {
	    Assert.assertTrue(cp.selectRoleForWork(), "Role did not select.");
	}

}