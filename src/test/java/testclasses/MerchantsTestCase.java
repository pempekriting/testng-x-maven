package testclasses;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ParentTest;

public class MerchantsTestCase extends ParentTest {
	@Test(description = "Search merchant", priority = 0, groups = { "merchant" }, testName = "Search Merchant")
	public void searchMerchantOnShopTab() throws IOException {
		String merchantName = "Nokia Mobile Official";

		homePageFactory.setSearchBar(merchantName);

		homePageFactory.clickButtonSearch();

		wait.until(ExpectedConditions.elementToBeClickable(searchPageFactory.getBtnSRPShopTab()));

		searchPageFactory.clickShopTab();

		wait.until(ExpectedConditions.visibilityOf(searchPageFactory.getDivShopContainer()));

		searchPageFactory.getSpecificMerchant(merchantName);

		wait.until(ExpectedConditions.visibilityOf(detailMerchantPageFactory.getPdpFlexWrapperContainer()));

		Assert.assertEquals(merchantName, detailMerchantPageFactory.getMerchantName());
	}
}
