package com.tokopedia.consumers.pagesfactory;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.tokopedia.consumers.utils.Helper;

import lombok.Getter;

@Getter
public class DetailMerchantPageFactory {
	WebDriver driver;
	ThreadLocal<ExtentTest> test;
	
	@FindBy(xpath = "//div[@data-testid='pdpFlexWrapperContainer']")
	WebElement pdpFlexWrapperContainer;

	@FindBy(xpath = "//div[@data-testid='shopsearchbar']/input")
	WebElement inpSearchProduct;

	@FindBy(xpath = "//div[@data-testid='shopsearchbar']/button")
	WebElement btnSearchProduct;

	public DetailMerchantPageFactory(WebDriver driver, ThreadLocal<ExtentTest> test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	public String getMerchantName() throws IOException {
		String merchantName = pdpFlexWrapperContainer.findElement(By.xpath("//h1")).getText();
		String imgPathRaw = Helper.takeScreenshot(driver, "getMerchantName");
		test.get().log(Status.PASS, "Getting merchant name: " + merchantName, MediaEntityBuilder.createScreenCaptureFromPath(imgPathRaw).build());
		return merchantName;
		
	}
}
