package stepDefinition;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Hooks.hookClass;
import io.cucumber.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Cartpage_po;
import pageObjects.SearchResult_po;
import pageObjects.landingPage_po;
import pageObjects.productDetails_po;
import pageObjects.signinPage_po;
import io.cucumber.java.en.Given;

public class cartFunctionality {

	private WebDriver driver = hookClass.driver;
	SearchResult_po searchResultPageObj = new SearchResult_po(driver);
	productDetails_po productDetailsObj = new productDetails_po(driver);
	Cartpage_po cartPageObj = new Cartpage_po(driver);
	landingPage_po lPage = new landingPage_po(driver);
	signinPage_po sPage = new signinPage_po(driver);

	@Given("User is already loggedIn to application with  valid credentials {string} and {string}")
	public void userloggin(String number, String password) {

		lPage.goToSignInPage();
		sPage.userSignin(number, password);
	}

	@Given("^User search for the product (.+) willing to purchase$")
	public void searchProductWillingToPurchase(String productName) {
		lPage.searchProduct(productName);

	}

	@When("^User add the product to the cart (.+)$")
	public void addProductOfChoiceToCart(String productName) {
		searchResultPageObj.selectParticularProduct(productName);
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> tab = tabs.iterator();
		String parent = tab.next();
		String childTab = tab.next();
		driver.switchTo().window(childTab);
		productDetailsObj.addProductTocart();
	}

	@Then("^Added product (.+) should be available/visible in users-cart$")
	public void addedProductShouldBeAvailableInCart(String productName) {
		productDetailsObj.goToCartPage();
		List<String> productInCart = cartPageObj.getListOfItemInCart();
		Assert.assertTrue(productInCart.contains(productName.toLowerCase()));
	}
}
