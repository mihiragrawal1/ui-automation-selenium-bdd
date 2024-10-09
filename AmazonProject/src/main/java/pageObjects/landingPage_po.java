package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.waitUtility;

public class landingPage_po extends waitUtility{
	
	WebDriver driver;
	
	public landingPage_po(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@id=\"nav-tools\"]/a[2]")
	WebElement signInSection;
	
	@FindBy(xpath="//div[@id='nav-flyout-ya-signin']//span[@class='nav-action-inner']")
	WebElement signInButton;
	
	@FindBy(xpath="//span[text()='Sign Out']")
	WebElement signOutBtn;
	
	@FindBy(css="input[id='twotabsearchtextbox']")
	WebElement searchBar;
	
	@FindBy(css="a[id='nav-cart']")
	WebElement cartButton;
	
	
	public void goToSignIn()
	{
		waitForElementVisibility(signInSection);
		Actions a=new Actions(driver);
		a.moveToElement(signInSection).perform();
		
	}
	
	public void clickSignin()
	{
		waitForElementToBeClickable(signInButton);
		signInButton.click();
		
	}
	
	public void signOut()
	{
		waitForElementVisibility(signOutBtn);
		signOutBtn.click();
		
	}
	
	public void clickSignout()
	{
		waitForElementVisibility(signOutBtn);
		signOutBtn.click();
	}
	
	
	
	public void goToSignInPage()
	{
		waitForElementVisibility(signInSection);
		Actions a=new Actions(driver);
		a.moveToElement(signInSection).perform();
		waitForElementToBeClickable(signInButton);
		signInButton.click();
	}
	
	public void userSignOut()
	{
		waitForElementVisibility(signInSection);
		Actions a=new Actions(driver);
		a.moveToElement(signInSection).perform();
		waitForElementVisibility(signOutBtn);
		signOutBtn.click();
	}

	public void searchProduct(String productName)
	{
		waitForElementVisibility(searchBar);
		searchBar.sendKeys(productName);
		searchBar.sendKeys(Keys.ENTER);
	}
	
	public void goToCart()
	{
		waitForElementVisibility(cartButton);
		cartButton.click();
	}

}
