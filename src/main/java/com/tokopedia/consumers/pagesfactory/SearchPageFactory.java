package com.tokopedia.consumers.pagesfactory;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import lombok.Getter;

@Getter
public class SearchPageFactory {
	WebDriver driver;
	ThreadLocal<ExtentTest> test;
	WebDriverWait wait;
	Actions action;

	@FindBy(xpath = "//button[@data-testid='btnSRPProductTab']")
	private WebElement btnSRPProductTab;

	@FindBy(xpath = "//button[@data-testid='btnSRPShopTab']")
	private WebElement btnSRPShopTab;

	@FindBy(xpath = "//div[@data-testid='topadsCPMWrapper']")
	private WebElement topadsCPMWrapper;

	@FindBy(xpath = "//div[@data-testid='divShopContainer']")
	private WebElement divShopContainer;

	@FindBy(xpath = "//div[@data-testid='divSRPContentProducts']//div[@data-testid='imgSRPProdMain']")
	private List<WebElement> divSRPContentProductsList;
	
	@FindBy(xpath = "//div[@data-testid='divSRPContentProducts']")
	private WebElement divSRPContentProducts;

	@FindBy(xpath = "//div[@data-testid='divProductWrapper']/div/div")
	private List<WebElement> divProductWrapperList;

	@FindBy(xpath = "//div[@data-testid='divProductWrapper']")
	private WebElement divProductWrapper;

	@FindBys(value = { @FindBy(xpath = "//span[@data-testid='spnSRPShopName']") })
	private List<WebElement> spnSRPShopName;

	@FindBy(name = "goldmerchant")
	private WebElement divGoldMerchant;

	@FindBy(xpath = "//i[@data-testid='imgSRPProdTabShopBadgeNonTopAds']")
	private List<WebElement> bdgGoldMerchantNonTopAds;

	@FindBy(xpath = "//i[@data-testid='imgSRPProdTabShopBadgeTopAds']")
	private List<WebElement> bdgGoldMerchantTopAds;

	@FindBy(name = "official")
	private WebElement divOfficialMerchant;

	@FindBy(xpath = "//i[@data-testid='imgSRPProdTabShopBadgeOSNonTopAds']")
	private List<WebElement> bdgOfficialMerchantNonTopAds;

	@FindBy(xpath = "//i[@data-testid='imgSRPProdTabShopBadgeOSTopAds']")
	private List<WebElement> bdgOfficialMerchantTopAds;

	public SearchPageFactory(WebDriver driver, ThreadLocal<ExtentTest> test, WebDriverWait wait, Actions action) {
		this.driver = driver;
		this.test = test;
		this.wait = wait;
		this.action = action;
		PageFactory.initElements(driver, this);
	}

	public void clickProductTab() {
		btnSRPProductTab.click();
		test.get().log(Status.PASS, "Clicked the product tab");
	}

	public void clickShopTab() {
		btnSRPShopTab.click();
		test.get().log(Status.PASS, "Clicked the shop tab");
	}

	public void clickGoldMerchantFilter() {
		divGoldMerchant.click();
		test.get().log(Status.PASS, "Clicked the gold merchant filter");
	}

	public void clickOfficialMerchantFilter() {
		divOfficialMerchant.click();
		test.get().log(Status.PASS, "Clicked the official merchant filter");
	}

	public void clickRandomProduct() {
		Random random = new Random();

		int min = 0;
		int max = divSRPContentProductsList.size();
		int value = random.nextInt(max + min) + min;
		WebElement obj = divSRPContentProductsList.get(value);
		
		wait.until(ExpectedConditions.visibilityOf(obj));
		action.moveToElement(obj);
		action.perform();
		obj.click();
		test.get().log(Status.PASS, "Clicked the product");
	}

	public void getSpecificMerchant(String merchantName) {
		spnSRPShopName.forEach(element -> {
			if (element.getText().equals(merchantName)) {
				element.click();
				test.get().log(Status.PASS, "Clicked the merchant");
			} else {
				System.out.println("Merchant not found!");
			}
		});
	}
}
