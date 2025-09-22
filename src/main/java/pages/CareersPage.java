package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CareersPage {
	private WebDriver cp_driver;
	WebDriverWait cp_wait;
	@FindBy(xpath = "//div[@id='location-slider']")
	WebElement LocationSlider;

// Constructor
	public CareersPage(WebDriver driver) {
		this.cp_driver = driver;
		PageFactory.initElements(driver, this);
		cp_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public boolean isOnCareerPage(String careersPageTitle) {
		cp_wait.until(ExpectedConditions.urlContains(careersPageTitle));
		System.out.println(" --> Title: " + careersPageTitle);
		boolean currentUrl = cp_driver.getTitle().toLowerCase().contains(careersPageTitle);
		return currentUrl;

	}
	
	public void locationSlider() {
		
	

	}
}
