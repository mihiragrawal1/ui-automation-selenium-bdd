package test;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.jsonReader;
import baseComponent.baseTest;
import pageObjects.Cartpage_po;
import pageObjects.SearchResult_po;
import pageObjects.landingPage_po;
import pageObjects.productDetails_po;
import pageObjects.signinPage_po;

public class CartFunctionalityTest extends baseTest {

	WebDriverWait wait;
	landingPage_po lPage;
	signinPage_po sPage;
//	String productToPurchase = "Apple iPhone 13 (128GB) - starlight";
	String productToPurchase = "Xiaomi Smart TV A 80 cm (32) HD Ready Smart Google LED TV L32MA-AIN (Black)";

	@BeforeMethod
	public void setup() {
		lPage = new landingPage_po(driver);
		sPage = new signinPage_po(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	

	@Test(retryAnalyzer=Utilities.RetryAnalyzerClass.class,dataProvider = "getValidLoginDataset", description = "Validate if user can add a product of choice to cart")
	public void validateAddToCartFunctionality(HashMap<String, String> input) throws InterruptedException {
		lPage.goToSignInPage();
		sPage.userSignin(input.get("number"), input.get("pass"));
		lPage.searchProduct(productToPurchase);
		SearchResult_po searchResultPageObj = new SearchResult_po(driver);
		searchResultPageObj.selectParticularProduct(productToPurchase);
		productDetails_po productDetailsObj = new productDetails_po(driver);
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> tab = tabs.iterator();
		String parent = tab.next();
		String childTab = tab.next();
		driver.switchTo().window(childTab);
		productDetailsObj.addProductTocart();
		productDetailsObj.goToCartPage();

		Cartpage_po cartPageObj = new Cartpage_po(driver);

		List<String> productInCart = cartPageObj.getListOfItemInCart();
//		System.out.println(productInCart);
		Assert.assertTrue(productInCart.contains(productToPurchase.toLowerCase()));

	}

	@Test(retryAnalyzer=Utilities.RetryAnalyzerClass.class,dependsOnMethods = "validateAddToCartFunctionality", dataProvider = "getValidLoginDataset", description = "Valiadte if user can delete/remove a product from cart")
	public void validateRemovingItemFromCart(HashMap<String, String> input) throws InterruptedException {
		lPage.goToSignInPage();
		sPage.userSignin(input.get("number"), input.get("pass"));
		lPage.goToCart();
		Cartpage_po cartPageObj = new Cartpage_po(driver);
		cartPageObj.removeFromCart(productToPurchase);
		Assert.assertTrue(cartPageObj.emptyCart().isDisplayed());
		Assert.assertTrue(cartPageObj.emptyCart().getText().toLowerCase().contains("cart is empty"));

//		if (cartPageObj.emptyCart().isDisplayed()) {
//			Assert.assertTrue(cartPageObj.emptyCart().getText().toLowerCase().trim().contains("cart is empty"));
//			Assert.assertTrue(true, "Product successfully removed from cart");
//		} else {
//			Assert.assertFalse(cartPageObj.getListOfItemInCart().contains(productToPurchase));
//
//		}

	}

	@DataProvider
	public Object[][] getValidLoginDataset() throws IOException {
		jsonReader reader = new jsonReader();
		List<HashMap<String, String>> data = reader
				.readFromJsonFile(System.getProperty("user.dir") + "/src/test/resources/data/validLoginData.json");
		return new Object[][] { { data.get(0) },{ data.get(1) } };
	}
}
