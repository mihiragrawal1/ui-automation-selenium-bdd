package Utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitUtility {
	
	WebDriver driver;
	
	public waitUtility(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

//	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));

	
	public void waitForElementVisibility(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
	
	public void waitForElementToBeClickable(WebElement ele)
	{
	
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(8));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		
	}
	 
	public void waitForListOfWebelements(List<WebElement> ele)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}
}
