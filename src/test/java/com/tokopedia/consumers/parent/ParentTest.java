package com.tokopedia.consumers.parent;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.tokopedia.consumers.listener.Listener;
import com.tokopedia.consumers.pagesfactory.DetailMerchantPageFactory;
import com.tokopedia.consumers.pagesfactory.DetailProductPageFactory;
import com.tokopedia.consumers.pagesfactory.HomePageFactory;
import com.tokopedia.consumers.pagesfactory.SearchPageFactory;

import lombok.Getter;

public class ParentTest extends Listener {

	@Getter
	protected static WebDriver driver;
	protected WebDriverWait wait;
	protected Actions action;

	SoftAssert softAssert = new SoftAssert();

	protected HomePageFactory homePageFactory;
	protected SearchPageFactory searchPageFactory;
	protected DetailProductPageFactory detailProductPageFactory;
	protected DetailMerchantPageFactory detailMerchantPageFactory;
	Listener listener;

	public static final int SHORT_WAIT = 5;
	public static final int MEDIUM_WAIT = 15;
	public static final String WINDOWS_DRIVER_PATH = "C:\\Windows\\";
	public static final String UNIX_DRIVER_PATH = "/usr/local/bin/";

	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser", "url" })
	public void setUp(String browser, String url) {
		driver = startBrowser(System.getProperty("os.name"), browser);
		driver.get(url);
		wait = new WebDriverWait(driver, MEDIUM_WAIT);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(SHORT_WAIT, TimeUnit.SECONDS);

		homePageFactory = new HomePageFactory(driver, test);
		searchPageFactory = new SearchPageFactory(driver, test, wait, action);
		detailProductPageFactory = new DetailProductPageFactory(driver, test);
		detailMerchantPageFactory = new DetailMerchantPageFactory(driver, test);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	private WebDriver startBrowser(String osName, String browserName) {
		String basePath = "";
		String fileExt = "";
		String execName = "";
		if (osName.startsWith("Windows")) {
			basePath = WINDOWS_DRIVER_PATH;
			fileExt = ".exe";
		} else {
			basePath = UNIX_DRIVER_PATH;
		}

		if (BrowserType.FIREFOX.equals(browserName)) {
			execName = "geckodriver";
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_XPI_PROPERTY, basePath + execName + fileExt);
			return new FirefoxDriver();
		} else if (BrowserType.SAFARI.equals(browserName)) {
			execName = "safaridriver";
			System.setProperty("webdriver.safari.driver", basePath + execName + fileExt);
			return new SafariDriver();
		} else if (BrowserType.EDGE.equals(browserName)) {
			execName = "msedgedriver";
			System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, basePath + execName + fileExt);
			return new EdgeDriver();
		} else {
			execName = "chromedriver";
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, basePath + execName + fileExt);
			String capabilities[] = { "start-maximized", "incognito" };
			ChromeOptions options = new ChromeOptions();
			options.addArguments(capabilities);
			return new ChromeDriver(options);
		}
	}
}
