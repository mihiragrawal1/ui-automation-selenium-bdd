package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.waitUtility;

public class Cartpage_po extends waitUtility {

	WebDriver driver;

	public Cartpage_po(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[data-action='delete']")
	List<WebElement> removeFromcartButton;

	@FindBy(xpath = "//span[@class=\"a-list-item\"]/a")
	List<WebElement> productsInCart;
	
	@FindBy(css="div[class=\"a-row sc-cart-header\"] h2")
	WebElement emptyCartMsgElement;
	

	public List<String> getListOfItemInCart() {

		waitForListOfWebelements(productsInCart);
		List<String> productavailableIncart = productsInCart.stream().map(m -> m.getText().toLowerCase())
				.collect(Collectors.toList());
		return productavailableIncart;

	}
	
	
	public WebElement emptyCart()
	{
		waitForElementVisibility(emptyCartMsgElement);
		return emptyCartMsgElement;
	}

	public void removeFromCart(String itemToRemove) {
		waitForListOfWebelements(productsInCart);
		
		for(int i=0;i<productsInCart.size();i++)
		{
			if(productsInCart.get(i).getText().trim().equalsIgnoreCase(itemToRemove.trim()))
			{
				removeFromcartButton.get(i).click();
			}
		}
	}

}
