package com.tokopedia.consumers.testclasses;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tokopedia.consumers.parent.ParentTest;

public class ItemsTestCase extends ParentTest {

	@Test(description = "Search item and pick random item", priority = 0, groups = { "item" }, testName = "Search item")
	public void searchItemTabProduct() {
		
		homePageFactory.setSearchBar("iPhone XR");

		homePageFactory.clickButtonSearch();

		wait.until(ExpectedConditions.visibilityOf(searchPageFactory.getDivSRPContentProducts()));
		
		Assert.assertEquals(searchPageFactory.getTopadsCPMWrapper().isDisplayed(), true);

		Assert.assertEquals(searchPageFactory.getDivSRPContentProducts().isDisplayed(), true);
	}

	@Test(priority = 2, groups = {"item"}, testName = "Search item by power merchant")
	public void searchItemFilteringByPowerMerchant() {
		searchPageFactory.clickGoldMerchantFilter();
		
		wait.until(ExpectedConditions.visibilityOf(searchPageFactory.getDivSRPContentProducts()));
		
		int gmnta = searchPageFactory.getBdgGoldMerchantNonTopAds().size();
		
		int gmta = searchPageFactory.getBdgGoldMerchantTopAds().size();
		
		int tp = searchPageFactory.getDivSRPContentProductsList().size();
		
		Assert.assertEquals(gmnta + gmta, tp);
		
	}

	@Test(priority = 1, groups = {"item"}, testName = "Search item by official store")
	public void filteringItemByOfficialStore() {
		searchPageFactory.clickOfficialMerchantFilter();

		wait.until(ExpectedConditions.visibilityOf(searchPageFactory.getDivSRPContentProducts()));

		int omnta = searchPageFactory.getBdgOfficialMerchantNonTopAds().size();

		int omta = searchPageFactory.getBdgOfficialMerchantTopAds().size();

		int tp = searchPageFactory.getDivSRPContentProductsList().size();

		Assert.assertEquals(omnta + omta, tp);
		
		searchPageFactory.clickOfficialMerchantFilter();
	}
	
	@Test(priority = 3, groups = {"item"}, testName = "Check detail item")
	public void checkDetailItem() {
		searchPageFactory.clickRandomProduct();

		wait.until(ExpectedConditions.visibilityOf(detailProductPageFactory.getBtnSkipOnBoarding()));

		detailProductPageFactory.clickBtnSkipOnBoarding();

		wait.until(ExpectedConditions.visibilityOf(detailProductPageFactory.getBtnSkipOnBoarding1()));

		detailProductPageFactory.clickBtnSkipOnBoarding1();

		wait.until(ExpectedConditions.visibilityOf(detailProductPageFactory.getBtnSkipOnBoarding2()));

		detailProductPageFactory.clickBtnSkipOnBoarding2();

		Assert.assertEquals(detailProductPageFactory.getLblPDPDetailProductName().isDisplayed(), true);
	}
}
