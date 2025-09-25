package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testData.TestDataSets;

public class DepertmantPage {
	private WebDriver dp_driver;
	private WebDriverWait dp_wait;
	Actions action;

	public DepertmantPage(WebDriver driver) {
		this.dp_driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(dp_driver);
		dp_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public boolean isOnDepartmentPage(String department) {
		try {
			String expectedUrl = TestDataSets.DEPARTMENT_URL_TEMPLATE+ department.toLowerCase();
			dp_wait.until(ExpectedConditions.urlContains("department=" + department.toLowerCase()));
			String currentUrl = dp_driver.getCurrentUrl();
			return currentUrl.equals(expectedUrl);
		} catch (Exception e) {
			System.out.println("URL is wrong: " + e.getMessage());
			return false;
		}

	}
}
