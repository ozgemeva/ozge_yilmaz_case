package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PositionPage {
	private WebDriver pp_driver;
	private WebDriverWait pp_wait;
	Actions action;

	@FindBy(xpath = "//a[normalize-space()='See all QA jobs']")
	private WebElement seeAllQARole;

	public PositionPage(WebDriver driver) {
		this.pp_driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(pp_driver);
		pp_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public boolean isOnTheRolePage(String rolePageTitle) {
		String expectedUrl =rolePageTitle.toLowerCase();
		 pp_wait.until(ExpectedConditions.urlContains(expectedUrl));
		 
		 // get current url
	        String current = pp_driver.getCurrentUrl().toLowerCase();
	        boolean isCorrectUrl = current.contains(expectedUrl);
	        
		System.out.println(" --> currentUrl: " +pp_driver.getCurrentUrl().toLowerCase());
		return isCorrectUrl;
	}
	

	public void clickButtonRole(WebElement element) {
		try {
			action.moveToElement(element).perform();
			pp_wait.until(ExpectedConditions.elementToBeClickable(element));
			action.moveToElement(element).click().perform();
		} catch (Exception e) {
			System.out.println("Click failed for element: " + element + " - " + e.getMessage());
			throw e;
		}
	}

	public boolean clickButtonSeeAllQAJobs() {
		try {
			clickButtonRole(seeAllQARole);
			System.out.println("Click for all QA role");
			return true;

		} catch (Exception e) {
			System.out.println("Click failed for all QA role button: " + e.getMessage());
			throw e;
		}

	}

}
