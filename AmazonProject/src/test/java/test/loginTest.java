package test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Utilities.jsonReader;
import baseComponent.baseTest;
import pageObjects.landingPage_po;
import pageObjects.signinPage_po;

@Listeners(Listener.ListenersClass.class)
public class loginTest extends baseTest {
	
	WebDriverWait wait;
	landingPage_po lPage;
	signinPage_po sPage ;
	
	@BeforeMethod
	public void setup()
	{
		 lPage = new landingPage_po(driver);
		 sPage = new signinPage_po(driver);
		 wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}


	@Test(retryAnalyzer=Utilities.RetryAnalyzerClass.class,priority = 1, dataProvider = "getValidLoginData", description = "Validate user login with valid credetials",
			groups = "login/logout")
	public void validUserTest(HashMap<String, String> input) {
		lPage.goToSignInPage();
		sPage.userSignin(input.get("number"), input.get("pass"));
		wait.until(ExpectedConditions.urlToBe(afterSigninUrl));
		AssertJUnit.assertEquals(driver.getCurrentUrl(), afterSigninUrl);
	}

	@Test(retryAnalyzer=Utilities.RetryAnalyzerClass.class,priority = 2, dataProvider = "getInvalidLoginData", description = "Validate user login with invalid credetials",
			groups = "login/logout")
	
	public void invalidUserTest(HashMap<String, String> input) {
		lPage.goToSignInPage();
		sPage.enterMobileNumberAndContinue(input.get("number"));
		AssertJUnit.assertEquals(sPage.getErrorMsg().toLowerCase(), "incorrect phone number");

	}
	
	@Test(retryAnalyzer=Utilities.RetryAnalyzerClass.class,description="Validate user login with empty credentials",priority=3,groups="login/logout")
	public void emptyCredentialsTest()
	{		
		lPage.goToSignInPage();
		sPage.enterMobileNumberAndContinue(" ");
		AssertJUnit.assertTrue(driver.getCurrentUrl().contains("www.amazon.in/ap/signin?"));
	}

	@Test(retryAnalyzer=Utilities.RetryAnalyzerClass.class,description="validate user logout/signout when clicked on signout button",priority=3,groups="login/logout",dataProvider="getValidLoginData")
	public void logoutTest(HashMap<String,String> input)
	{
		lPage.goToSignInPage();
		sPage.userSignin(input.get("number"), input.get("pass"));
		wait.until(ExpectedConditions.urlToBe(afterSigninUrl));
		AssertJUnit.assertEquals(driver.getCurrentUrl(), afterSigninUrl);
		lPage.userSignOut();
		AssertJUnit.assertTrue(driver.getCurrentUrl().contains("www.amazon.in/ap/signin?"));
		
	}
	@DataProvider
	public Object[][] getValidLoginData() throws IOException {
		jsonReader reader = new jsonReader();
		List<HashMap<String, String>> data = reader
				.readFromJsonFile(System.getProperty("user.dir") + "/src/test/resources/data/validLoginData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };    

	}

	@DataProvider
	public Object[][] getInvalidLoginData() throws IOException {
		jsonReader reader = new jsonReader();
		List<HashMap<String, String>> data = reader
				.readFromJsonFile(System.getProperty("user.dir") + "/src/test/resources/data/invalidLoginData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
