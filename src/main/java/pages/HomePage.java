package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {
	private WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//a[normalize-space()='Accept All']")
	WebElement acceptCookiesBtn;
	

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

	// If the cookie banner appears, it will be accepted; if not, the test will continue
	public void acceptCookiesElement() {

		   try {
			   
		        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
		        shortWait.until(ExpectedConditions.visibilityOf(acceptCookiesBtn));
		        acceptCookiesBtn.click();
		        System.out.println("Cookie banner accepted.");
		        
		    } catch (Exception e) {
		        System.out.println("No cookie banner displayed, continue without accepting.");
		    }
	}

	
}
