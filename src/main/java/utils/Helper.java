package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	public static WebDriver driver;
	
	
	public Helper(WebDriver driver) {
		Helper.driver = driver;
	}
	
	public static String takeScreenshot(String fileName) throws IOException {
		String directory = System.getProperty("user.dir") + "/Screenshots/";
		File testDirectory = new File(directory);
		fileName = fileName + ".png";
		
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + directory + " is created!");
			} else {
				System.out.println("Failed to create directory: " + directory);
			}
		}
	
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		return directory + fileName;
	}
}
