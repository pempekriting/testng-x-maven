package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import listener.Listener;
import pagesfactory.DetailMerchantPageFactory;
import pagesfactory.DetailProductPageFactory;
import pagesfactory.HomePageFactory;
import pagesfactory.SearchPageFactory;
import utils.Helper;

public class ParentTest extends Listener {

	protected static WebDriver driver;
	protected WebDriverWait wait;
	protected Actions action;

	SoftAssert softAssert = new SoftAssert();

	protected HomePageFactory homePageFactory;
	protected SearchPageFactory searchPageFactory;
	protected DetailProductPageFactory detailProductPageFactory;
	protected DetailMerchantPageFactory detailMerchantPageFactory;
	protected Helper helper;

	public static final int SHORT_WAIT = 5;
	public static final int MEDIUM_WAIT = 15;

	@BeforeClass(alwaysRun = true)
	@Parameters({ "platform", "browser", "version", "url", "node" })
	public void setUp(String platform, String browser, String version, String url, String node)
			throws MalformedURLException {
		driver = getDriverInstance(platform, browser, version, url, node);
		homePageFactory = new HomePageFactory(driver, test);
		searchPageFactory = new SearchPageFactory(driver, test, wait, action);
		detailProductPageFactory = new DetailProductPageFactory(driver, test);
		detailMerchantPageFactory = new DetailMerchantPageFactory(driver, test);
		helper = new Helper(driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	private WebDriver getDriverInstance(String platform, String browser, String version, String url, String node)
			throws MalformedURLException {
		WebDriver driver = null;
		DesiredCapabilities caps = new DesiredCapabilities();

		// Platforms
		if (platform.equalsIgnoreCase("Windows")) {
			caps.setPlatform(Platform.WINDOWS);
		}
		if (platform.equalsIgnoreCase("MAC")) {
			caps.setPlatform(Platform.MAC);
		}
		if (platform.equalsIgnoreCase("Linux")) {
			caps.setPlatform(Platform.LINUX);
		}
		// Browsers
		if (browser.equalsIgnoreCase("chrome")) {
			caps = DesiredCapabilities.chrome();
			String[] capabilities = { "start-maximized", "incognito", "window-size=1920,1080" };
			ChromeOptions options = new ChromeOptions();
			options.addArguments(capabilities);
			caps.setCapability(ChromeOptions.CAPABILITY, options);
		}
		if (browser.equalsIgnoreCase("firefox")) {
			caps = DesiredCapabilities.firefox();
		}
		// Version
		caps.setVersion(version);
		// Start
		driver = new RemoteWebDriver(new URL(node), caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, MEDIUM_WAIT);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(SHORT_WAIT, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}
}
