package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testData.TestDataSets;



public class HomePage {
	private WebDriver hp_driver;
	WebDriverWait hp_wait;
	
	@FindBy(xpath = "//a[normalize-space()='Accept All']")
	WebElement acceptCookiesBtn;
	

	@FindBy(xpath ="//a[normalize-space()='Company']")
	WebElement dropdownBtnCompany;
	
	@FindBy(xpath = "//a[normalize-space()='Careers']")
	WebElement subDropdownBtnCareer;
	

	// Constructor
	public HomePage(WebDriver driver) {
		this.hp_driver = driver;
		PageFactory.initElements(driver, this);
		hp_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	}

	// home page check
	public boolean isHomePageOpened(String titleInclude) {
		hp_wait.until(ExpectedConditions.urlContains(TestDataSets.base_all));
		boolean title = hp_driver.getTitle().toLowerCase().contains(titleInclude);
		return title;
	}

	// If the cookie banner appears, it will be accepted; if not, the test will continue
	public void acceptCookiesIfPresent() {

		   try {
			   
		        WebDriverWait shortWait = new WebDriverWait(hp_driver, Duration.ofSeconds(3));
		        shortWait.until(ExpectedConditions.visibilityOf(acceptCookiesBtn));
		        acceptCookiesBtn.click();
		        System.out.println("Cookie banner accepted.");
		        
		    } catch (Exception e) {
		        System.out.println("No cookie banner displayed, continue without accepting.");
		    }
	}

	public void clickCompanyLink() {
		hp_wait.until(ExpectedConditions.elementToBeClickable(dropdownBtnCompany));
		dropdownBtnCompany.click();
	}
	
	public void clickCareerLink() {
		hp_wait.until(ExpectedConditions.elementToBeClickable(subDropdownBtnCareer));
		subDropdownBtnCareer.click();
	}
}
