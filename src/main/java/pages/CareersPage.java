package pages;

import java.time.Duration;
import java.util.List;
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

	@FindBy(xpath = "//a[contains(@class,'loadmore')]")
	private WebElement jobBtn;

	@FindBy(xpath = "//div[contains(@class,'career-load-more')]//div[contains(@class,'job-item')]")
	private List<WebElement> allTeamsItems;

	@FindBy(xpath = "//h2[normalize-space()='Life at Insider']")
	private WebElement lifeAtInsederInsiderTitle;

	@FindBy(css = "div.elementor-main-swiper.swiper-container")
	private WebElement mainSlider;

	@FindBy(css = "div.elementor-main-swiper .swiper-slide")
	private List<WebElement> lifeAtInsiderSlides;

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
		try {
			cp_wait.until(ExpectedConditions.elementToBeClickable(nextArrow));
			nextArrow.sendKeys(Keys.ARROW_RIGHT);// to prevent sticky button so trigger for JS
			System.out.println("Clicked next arrow button");
		} catch (Exception e) {
			System.out.println("Exception in btn_seeAll() : " + e.getMessage());
		}

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

	public boolean btn_seeAllClick() {
		// href=javascript so to prevent overlay conflig.To use enter event.
		if (jobBtn.isDisplayed()) {
			jobBtn.sendKeys(Keys.ENTER);
		} else {
			System.out.println("Click failed");
		}
		System.out.println("See all button is actived");
		return true;
	}

	public int getTeamsSizeCount() {

		cp_wait.until(ExpectedConditions.visibilityOfAllElements(allTeamsItems));
		int jobItemSize = allTeamsItems.size();
		System.out.println("Job Items size: " + jobItemSize);
		return jobItemSize;
	}

	// LifeAtInsider Title control to display in the DOM.
	public boolean isLifeAtInsiderVisible() {
		try {
			cp_wait.until(ExpectedConditions.visibilityOf(lifeAtInsederInsiderTitle));
			System.out.println("Life at Insider visible in CareerPages");
			return lifeAtInsederInsiderTitle.isDisplayed() && mainSlider.isDisplayed();
		} catch (Exception e) {
			System.out.println("Life at Insider not visible: " + e.getMessage());
			return false;
		}
	}

	public int getLifeAtInsiderSlidesCount() {
		return lifeAtInsiderSlides.size();
	}

}