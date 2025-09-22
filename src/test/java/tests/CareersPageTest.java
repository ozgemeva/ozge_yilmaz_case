package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;
import testData.TestDataSets;

public class CareersPageTest extends BaseTest{
	CareersPage cp;
	HomePage hp;

	public void startCareer() {
		hp = new HomePage(driver);
		hp.acceptCookiesIfPresent();
		hp.clickCompanyLink();
		hp.clickCareerLink();
	}

	@Test
	public void verifyOnCareerPageIsOpened() {
		startCareer();
		Assert.assertTrue(cp.isOnCareerPage(TestDataSets.careers_url), "Careers page is not opened correctly!");

	}

}
