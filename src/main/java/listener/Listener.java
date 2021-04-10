package listener;

import java.io.IOException;

import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.ExtentManager;
import utils.Helper;

public class Listener implements IInvokedMethodListener, ITestListener, ISuiteListener {

	public static ExtentReports extent = ExtentManager.createInstance();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	ExtentTest extentTest;

	@Override
	public synchronized void onStart(ISuite suite) {
		// When <suite> tag starts
		System.out.println("onStart: before suite starts");
	}

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test start");
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " started!"));
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		try {
			String imgPathRaw = Helper.takeScreenshot(result.getMethod().getMethodName());
			System.out.println((result.getMethod().getMethodName() + " passed!"));
			test.get().pass("Test passed", MediaEntityBuilder.createScreenCaptureFromPath(imgPathRaw).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		try {
			String imgPathRaw = Helper.takeScreenshot(result.getMethod().getMethodName());
			test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(imgPathRaw).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test finish");
	}

	@Override
	public synchronized void onFinish(ISuite suite) {
		// When <suite> tag completes
		System.out.println("onFinish: after suite completes");
		extent.flush();
	}
}
