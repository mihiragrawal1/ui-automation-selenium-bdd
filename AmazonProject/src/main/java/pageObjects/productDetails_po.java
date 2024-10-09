package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.waitUtility;

public class productDetails_po extends waitUtility{

	WebDriver driver;
	
	public productDetails_po(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@id='a-accordion-auto-9']//input[@id='add-to-cart-button']")
	WebElement addToCartButton;
	
	@FindBy(css="input[aria-labelledby=\"attach-sidesheet-view-cart-button-announce\"]")
	WebElement cartButton;
	
	
	public void addProductTocart()
	{
		waitForElementVisibility(addToCartButton);
		addToCartButton.click();
	}
	
	
	public void goToCartPage()
	{
		waitForElementVisibility(cartButton);
		waitForElementToBeClickable(cartButton);
		cartButton.click();
		
	}
}
