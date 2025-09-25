package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testData.TestDataSets;

public class DepertmantPage {
	private WebDriver dp_driver;
	private WebDriverWait dp_wait;
	Actions action;

	@FindBy(css = "span.select2-selection--single")
	private WebElement dropdownLocation;

	@FindBy(css = "ul.select2-results__options")
	private WebElement locationOption;

	public DepertmantPage(WebDriver driver) {
		this.dp_driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(dp_driver);
		dp_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

//check right depertmant page
	public boolean isOnDepartmentPage(String department) {
		try {
			String expectedUrl = TestDataSets.DEPARTMENT_URL_TEMPLATE + department.toLowerCase();
			dp_wait.until(ExpectedConditions.urlContains("department=" + department.toLowerCase()));
			String currentUrl = dp_driver.getCurrentUrl();
			return currentUrl.equals(expectedUrl);
		} catch (Exception e) {
			System.out.println("URL is wrong: " + e.getMessage());
			return false;
		}
	}

	// General click btn method
	public void clickBtnForDepartmentPage(WebElement element) {
		try {
			action.moveToElement(element).pause(Duration.ofMillis(150)).perform();// to wait on the element 150
																					// milseconds.
			dp_wait.until(ExpectedConditions.elementToBeClickable(element));
			action.moveToElement(element).click().perform();
		} catch (Exception e) {
			System.out.println("Click failed for element: " + element + " - " + e.getMessage());
			throw e;
		}
	}

	// filter box for location
	public boolean openedfilterBoxLocationAndoption() {
		try {
			dp_wait.until(ExpectedConditions.elementToBeClickable(dropdownLocation));
			dropdownLocation.click();
			System.out.println("Opened dropdown menu for location filter.");

			dp_wait.until(ExpectedConditions.elementToBeClickable(locationOption));
			System.out.println("Location dropdown opened");

			return true;
		} catch (Exception e) {
			System.out.println("Filter selection failed: " + e.getMessage());
			return false;
		}
	}

}
