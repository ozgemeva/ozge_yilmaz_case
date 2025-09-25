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

	@FindBy(css = "#career-our-location li.glide__slide.glide__slide--active")
	private WebElement activeSlideLocator;

	// Constructor
	public CareersPage(WebDriver driver) {
		this.cp_driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(cp_driver);
		cp_wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// --- Page validation ---
	public boolean isOnCareerPage(String careersPageTitle) {
		try {
			String expectedUrl = careersPageTitle.toLowerCase();
			cp_wait.until(ExpectedConditions.urlContains(expectedUrl));
			String current = cp_driver.getCurrentUrl().toLowerCase();
			boolean isCorrectUrl = current.contains(expectedUrl);
			System.out.println(" --> Current URL: " + current);
			return isCorrectUrl;
		} catch (Exception e) {
			System.out.println("Career page check failed: " + e.getMessage());
			return false;
		}
	}

	// General to scroll until element
	public void scrollToCarouselSection(WebElement element) {
		try {
			action.moveToElement(element).perform();
			cp_wait.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println("To scroll");
		} catch (Exception e) {
			System.out.println("Scroll failed for element: " + element + " - " + e.getMessage());
			throw e;
		}

	}

	// General click btn method
	public void clickBtn(WebElement element) {
		try {
			action.moveToElement(element).pause(Duration.ofMillis(150)).perform();// to wait on the element  150 seconds.
			cp_wait.until(ExpectedConditions.elementToBeClickable(element));
			action.moveToElement(element).click().perform();
		} catch (Exception e) {
			System.out.println("Click failed for element: " + element + " - " + e.getMessage());
			throw e;
		}
	}

	// --- Carousel controls ---
	public void scrollUntilSlider() {
		try {
			scrollToCarouselSection(sliderSection);
		} catch (Exception e) {
			System.out.println("Scroll failed for element: " + e.getMessage());
			throw e;
		}
	}

	public boolean isCarouselVisible() {
		return sliderSection.isDisplayed();
	}

	// To click NextArrow
	public void clickNextArrowBtn() {
		try {
			clickBtn(nextArrow);
			System.out.println("Clicked next arrow button");
		} catch (Exception e) {
			System.out.println("Exception in clickNextArrow : " + e.getMessage());
			throw e;
		}

	}

	public String getSliderBeforeTitle() {
		cp_wait.until(ExpectedConditions.visibilityOf(activeSlideTitle));
		return activeSlideTitle.getText().trim();
	}

	// wait until the active slide text is different from the previous one
	public String getSliderAfterTitle(String beforeSlideTitle) {

		cp_wait.until(ExpectedConditions
				.not(ExpectedConditions.textToBePresentInElement(activeSlideLocator, beforeSlideTitle)));

		if ((activeSlideLocator).getText().trim().isEmpty()) {
			clickNextArrowBtn();
		}
		return activeSlideLocator.getText().trim();
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

	// LifeAtInsider slides size
	public int getLifeAtInsiderSlidesCount() {
		return lifeAtInsiderSlides.size();
	}

	public boolean clickSeeAllTeamsBtn() {
		// href=javascript so to prevent overlay conflig.To use enter event.
		try {
			clickBtn(jobBtn);
			System.out.println("See all button is actived");
			return true;
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			return false;
		}
	}

	// Select role method
	public boolean selectRoleForWork(String roleName) {
		try {
		
			By roleLocator = By.xpath("//a[.//h3[normalize-space()='" + roleName + "']]");
			WebElement role = cp_wait.until(ExpectedConditions.elementToBeClickable(roleLocator));
			cp_wait.until(ExpectedConditions.elementToBeClickable(role));
			clickBtn(role);
			System.out.println("Clicked role: " + roleName);
			return true;
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			return false;
		}
	}

}