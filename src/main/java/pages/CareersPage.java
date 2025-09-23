package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CareersPage {
	private WebDriver cp_driver;
	private WebDriverWait cp_wait;
	Actions action;

	// Locators for carousel elements
	@FindBy(xpath = "//*[@id='career-our-location']//a[i[contains(@class,'location-slider-next')]]")
	private WebElement nextArrow;

	@FindBy(css = "#career-our-location li.glide__slide.glide__slide--active")
	private WebElement activeSlideTitle;

	@FindBy(css = "#career-our-location")
	public WebElement sliderSection;

	@FindBy(xpath = "//*[@id='career-our-location']//a[i[contains(@class,'location-slider-prev')]]")
	private WebElement prevArrow;

	// Constructor
	public CareersPage(WebDriver driver) {
		this.cp_driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(cp_driver);
		cp_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// --- Page validation ---
	public boolean isOnCareerPage(String careersPageTitle) {
		cp_wait.until(ExpectedConditions.urlContains(careersPageTitle));
		System.out.println(" --> Title: " + careersPageTitle);
		boolean currentUrl = cp_driver.getCurrentUrl().toLowerCase().contains(careersPageTitle);
		return currentUrl;
	}

	// --- Carousel controls ---
	public void scrollToCarouselSection() {
		action.moveToElement(sliderSection).perform();

	}

	public boolean isCarouselVisible() {
		return sliderSection.isDisplayed();
	}

	// To click NextArrow
	public void clickNextArrow() {
		cp_wait.until(ExpectedConditions.elementToBeClickable(nextArrow));
		nextArrow.sendKeys(Keys.ARROW_RIGHT);//tp prevent sticky button so trigger for JS
		;
		System.out.println("Clicked next arrow()");
	}

	public String getSliderBeforeTitle() {
		cp_wait.until(ExpectedConditions.visibilityOf(activeSlideTitle));
		return activeSlideTitle.getText().trim();
	}

	// wait until the active slide text is different from the previous one
	public String getSliderAfterTitle(String beforeSlideTitle) {
		By activeSlideLocator = By.cssSelector("#career-our-location li.glide__slide.glide__slide--active");
		cp_wait.until(ExpectedConditions
				.not(ExpectedConditions.textToBePresentInElementLocated(activeSlideLocator, beforeSlideTitle)));

		if (cp_driver.findElement(activeSlideLocator).getText().trim().isEmpty()) {
			clickNextArrow();
		}
		return cp_driver.findElement(activeSlideLocator).getText().trim();
	}

	public void btn_seeAll() {

	}

}