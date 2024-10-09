package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.waitUtility;

public class SearchResult_po extends waitUtility{

	WebDriver driver;
	public SearchResult_po(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
	
	
	@FindBy(xpath="//div[@data-cy='title-recipe']/h2/a")
	List<WebElement> searchResult;
	
	
	
	
	
	public List<String> getProductNameInResult()
	{
		waitForListOfWebelements(searchResult);
		List<String> name=searchResult.stream().map(n->n.getText()).collect(Collectors.toList());
		return name;
		
	}

	
	public void selectParticularProduct(String proName)
	{
//		
		waitForListOfWebelements(searchResult);
		for(int i=0;i<searchResult.size();i++)
		{
			//(searchResult.get(i).getText().contains(proName) || 
			WebElement productName=searchResult.get(i);
			if(productName.getText().trim().equalsIgnoreCase(proName.trim()))
			{
				System.out.println(productName.getText());
				JavascriptExecutor js=(JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", productName);

				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(productName));
				productName.click();
//				js.executeScript("arguments[0].click();", productName);
				break;
				
			}
		}
	}
}
