package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.waitUtility;

public class signinPage_po extends waitUtility {

	WebDriver driver;

	public signinPage_po(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='ap_email']")
	WebElement mobileNumberFeild;

	@FindBy(id = "ap_password")
	WebElement passwordFeild;

	@FindBy(xpath = "//input[@id='continue']")
	WebElement continueButton;

	@FindBy(id = "signInSubmit")
	WebElement signinButton;

	@FindBy(xpath = "//h4[text()=\"Incorrect phone number\"]")
	WebElement incorrectCredentialsMsg;

	@FindBy(css = "div[id='auth-email-invalid-claim-alert']")
	WebElement emptyCredentialMsg;

	public void enterMobileNumberAndContinue(String number) {
		waitForElementVisibility(mobileNumberFeild);
		mobileNumberFeild.sendKeys(number);
		continueButton.click();
	}

	public void enterPassword(String pass) {
		waitForElementVisibility(passwordFeild);
		passwordFeild.sendKeys(pass);
		signinButton.click();
	}

	public String getErrorMsg() {
		waitForElementVisibility(incorrectCredentialsMsg);
		return incorrectCredentialsMsg.getText();
	}

	public String getemptyCredentialsMsg() {
		waitForElementVisibility(emptyCredentialMsg);
		return emptyCredentialMsg.getText();
	}
	
	
	
	
	public void userSignin(String number,String pass)
	{
		waitForElementVisibility(mobileNumberFeild);
		mobileNumberFeild.sendKeys(number);
		continueButton.click();
		waitForElementVisibility(passwordFeild);
		passwordFeild.sendKeys(pass);
		signinButton.click();
		
		
	}
}
