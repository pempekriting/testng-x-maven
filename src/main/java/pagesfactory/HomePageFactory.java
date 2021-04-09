package pagesfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class HomePageFactory {
	WebDriver driver;
	ThreadLocal<ExtentTest> test;

	@FindBy(xpath = "//div[@data-testid='txtHeaderSearchBar']//input")
	private WebElement txtHeaderSearchBar;

	@FindBy(xpath = "//div[@data-testid='txtHeaderSearchBar']//button")
	private WebElement btnHeaderSearchBar;

	public HomePageFactory(WebDriver driver, ThreadLocal<ExtentTest> test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	public void setSearchBar(String itemName) {
		txtHeaderSearchBar.clear();
		test.get().log(Status.PASS, "Clearing the search bar");
		txtHeaderSearchBar.sendKeys(itemName);
		test.get().log(Status.PASS, "Typing on the search bar");
	}

	public void clickButtonSearch() {
		btnHeaderSearchBar.click();
		test.get().log(Status.PASS, "Click on the search button");
	}
}
