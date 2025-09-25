package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

	@FindBy(xpath = "(//span[contains(@class,'select2-selection--single')])[1]")
	private WebElement accordionDropdown;

	// filter box for location
	public boolean selectLocation(String locationName) {
		try {
			action.sendKeys(Keys.END).perform();
			dp_wait.until(ExpectedConditions.elementToBeClickable(accordionDropdown));
			accordionDropdown.click();
			System.out.println("Dropdown clicked");
			 

//			WebElement allOption = dp_driver
//					.findElement(By.xpath("//span[@id='select2-filter-by-location-container' and text()='All']"));
//			allOption.click();
//			dp_wait.until(ExpectedConditions.elementToBeClickable(accordionDropdown));
//			List<WebElement> accordionOptionsList = dp_driver.findElements(By.xpath("//span[contains(@class,'select2-container--open')]//ul[@class='select2-results__options']/li"));
//			for (WebElement opt : accordionOptionsList) {
//				System.out.println("opt " + opt);
//				if (opt.getText().trim().contains(locationName)) {
//					opt.click();
//					System.out.println("Seçilen location: " + opt.getText());
//					return true;
//				}
//			}

			return true;
		} catch (Exception e) {
			System.out.println("Filter selection failed: " + e.getMessage());
			return false;
		}
	}
}
