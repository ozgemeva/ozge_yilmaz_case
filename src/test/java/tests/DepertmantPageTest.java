package tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
		dp = new DepertmantPage(driver);
	}
	private void navigateToCareers() {
		hp.acceptCookiesIfPresent();//accept cookies
		hp.clickCompanyLink();// go to company link
		hp.clickCareerLink(); // go to career section
		cp.clickSeeAllTeamsBtn();// load see all position
		cp.selectRoleForWork(TestDataSets.ROLE_NAME);//select role
		pp.clickButtonSeeAllQAJobs(); // click sub-role for QA depertment
	}
	
	@Test
	public void verifyIsOnDepertmantPage() {
		navigateToCareers();
		//qualityassurance
		String roleNameLink = TestDataSets.ROLE_NAME;
		String formattedDepertmantName= String.format(roleNameLink.replaceAll(" ", "")).toLowerCase();
	
		System.out.println("Depertmant name is formated for rolepage "+formattedDepertmantName);
		Assert.assertTrue(dp.isOnDepartmentPage(formattedDepertmantName),"is not on this rolepage ->");
	}

}
