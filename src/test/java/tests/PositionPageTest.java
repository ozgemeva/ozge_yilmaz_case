package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CareersPage;
import pages.HomePage;
import pages.PositionPage;
import testData.TestDataSets;

public class PositionPageTest extends BaseTest {

	CareersPage cp;
	HomePage hp;
	PositionPage pp;

	@BeforeClass
	public void initPages() {
		hp = new HomePage(driver);
		cp = new CareersPage(driver);
		pp = new PositionPage(driver);
	}

	private void navigateToCareers() {
		hp.acceptCookiesIfPresent();//accept cookies
		hp.clickCompanyLink();// go to company link
		hp.clickCareerLink(); // go to career section
		cp.clickSeeAllTeamsBtn();// load see all position
		cp.selectRoleForWork(TestDataSets.ROLE_NAME);//select role 
	}

	@Test
	public void verifyOnRolePage() {
		navigateToCareers();
		String roleNameLink = TestDataSets.ROLE_NAME;
		String formattedRoleNameLink = String.format(roleNameLink.replaceAll(" ", "-"));
		System.out.println("RoleName is formated for rolepage "+formattedRoleNameLink);
		Assert.assertTrue(pp.isOnTheRolePage(formattedRoleNameLink), "Position page is not opened correctly!");
	}

	@Test(priority = 1, dependsOnMethods = "verifyOnRolePage")
	public void verifyQASeeAllRole() {
		Assert.assertTrue(pp.clickButtonSeeAllQAJobs(), "See all QA Jobs button is not clickable");
	}

}
