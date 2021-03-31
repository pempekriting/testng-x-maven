package com.tokopedia.consumers.pagesfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import lombok.Getter;

@Getter
public class DetailProductPageFactory {
	WebDriver driver;
	ThreadLocal<ExtentTest> test;
	
	@FindBy(xpath = "//h1[@data-testid='lblPDPDetailProductName']")
	private WebElement lblPDPDetailProductName;
	
	@FindBy(xpath = "//button[@data-testid='btnSkipOnBoarding']")
	private WebElement btnSkipOnBoarding;
	
	@FindBy(xpath = "//div[@data-testid='btnSkipOnBoarding#1']")
	private WebElement btnSkipOnBoarding1;
	
	@FindBy(xpath = "//div[@data-testid='btnSkipOnBoarding#2']")
	private WebElement btnSkipOnBoarding2;
	
	public DetailProductPageFactory(WebDriver driver, ThreadLocal<ExtentTest> test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}
	
	public void clickBtnSkipOnBoarding() {
		btnSkipOnBoarding.click();
		test.get().log(Status.PASS, "Clicked button skip on boarding 1");
	}
	
	public void clickBtnSkipOnBoarding1() {
		btnSkipOnBoarding1.click();
		test.get().log(Status.PASS, "Clicked button skip on boarding 2");
	}
	
	public void clickBtnSkipOnBoarding2() {
		btnSkipOnBoarding2.click();
		test.get().log(Status.PASS, "Clicked button skip on boarding 3");
	}
}
