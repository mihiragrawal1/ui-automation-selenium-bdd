package Listener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import baseComponent.baseTest;

public class ListenersClass implements ITestListener {

	public static WebDriver  driver;

	

	@Override
	public void onTestFailure(ITestResult result) {
		if(driver!=null)
		{
			
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "/screenshots/" + result.getName() + ".png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		}
		else {
	        System.out.println("Driver was null, could not take screenshot");

		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed: " + result.getName());
	}

}
