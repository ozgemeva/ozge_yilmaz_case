package tests;

import org.testng.annotations.BeforeClass;

import pages.CareersPage;
import pages.DepertmantPage;
import pages.HomePage;
import pages.PositionPage;
import testData.TestDataSets;

public class DepertmantPageTest extends BaseTest {
	CareersPage cp;
	HomePage hp;
	PositionPage pp;
	DepertmantPage dp;
	
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
		pp.clickButtonSeeAllQAJobs(); // click sub-role for QA depertment
	}

}
