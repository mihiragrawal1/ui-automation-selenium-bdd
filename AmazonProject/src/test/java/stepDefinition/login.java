package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.landingPage_po;
import pageObjects.signinPage_po;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Hooks.hookClass;
import baseComponent.baseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class login {

	private WebDriver driver = hookClass.driver;

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	@Given("User goes to signinpage of application")
	public void user_goes_to_signin_page_of_application() {
		landingPage_po lPage = new landingPage_po(driver);
		lPage.goToSignInPage();
	}

	@When("^User enters (.+) and (.+) and click signin$")
	public void user_enters_and(String number, String password) {
		signinPage_po sPage = new signinPage_po(driver);
		sPage.userSignin(number, password);

	}

	@Then("^User should be on homePage of application that is (.+)$")
	public void user_should_be_on_homepage_of_application_that_is(String expectedUrl) {

		wait.until(ExpectedConditions.urlToBe(expectedUrl));
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

	}

	@When("^User enters invalidNumber (.+)$")
	public void user_enters(String number) {
		signinPage_po sPage = new signinPage_po(driver);
		sPage.enterMobileNumberAndContinue(number);
	}

	@When("User enters emptyNumber ")
	public void user_enters_empty_number() {
		signinPage_po sPage = new signinPage_po(driver);
		sPage.enterMobileNumberAndContinue("");
	}

	@Then("^User should get error message (.+)$")
	public void user_should_get_error_message(String expectedMessage) {
		signinPage_po sPage = new signinPage_po(driver);
		Assert.assertEquals(sPage.getErrorMsg().toLowerCase(), expectedMessage);
	}

	@Then("^User should stay on same signin-page with (.+)$")
	public void user_should_stay_on_same_signin_page_with(String expectedUrl) {
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
	}

	@And("User clicks on signOut")
	public void user_click_on_signout() {
		landingPage_po lPage = new landingPage_po(driver);
		lPage.userSignOut();
		;

	}

	@Then("^User should redirected to signin-page with (.+)$")
	public void user_should_redirect_to_signin(String expectedurl) {
		wait.until(ExpectedConditions.urlContains(expectedurl));
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedurl));

	}

}
