package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
	WebDriverWait wait;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	// home page check
	public boolean isHomePageOpened(String titleInclude) {
		wait.until(ExpectedConditions.urlContains("useinsider"));
		boolean title = driver.getTitle().toLowerCase().contains(titleInclude);
		return title;
	}

}
