package com.hcl.gcs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.gcs.util.WebActionUtil;

/**
 * Description: This class implements the methods for Login
 * 
 * @author Vivek
 * 
 */
public class Login_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Login_Page(WebDriver driver, long ETO, WebActionUtil webActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = webActionUtil;
		this.ETO = ETO;
	}

	/* Login Header text */
	@FindBy(xpath = "//b[text()='Global Claims System']")
	private WebElement txtloginHeadtext;

	/* Employee code Text Box */
	@FindBy(id = "txtEmpCode")
	private WebElement tbEmpCode;

	/* Password Text Box */
	@FindBy(id = "txtPassword")
	private WebElement tbPassword;

	/* Option DropDown */
	@FindBy(id = "ddlOption")
	private WebElement ddOption;

	/* Login Button */
	@FindBy(id = "butLogin")
	private WebElement btnLogin;

	/* Raise new claim request */
	@FindBy(xpath = "//p[text()='Raise New Claim']")
	private WebElement txtRaiseNewClaim;

	/**
	 * Description: This method login to the application
	 * 
	 * @author Vivek dogra
	 * @param emailIempcode
	 * @param password
	 */
	public synchronized void loginToApplication(String empcode, String password) {
		try {
			try {
				WebActionUtil.isElementVisible(txtloginHeadtext, "Login Page Head text");
				WebActionUtil.validationinfo("Login page is displayed", "blue");
				WebActionUtil.validationinfo("The URL is : " + driver.getCurrentUrl(), "blue");
			} catch (Exception e) {
				WebActionUtil.fail("Unable to Log In to the Application");
				Assert.fail("Unable to Log In to the Application");
			}
			WebActionUtil.typeText(tbEmpCode, empcode, "Employee code Textbox");
			WebActionUtil.validateEnteredValue1(WebActionUtil.getTextUsingJS("txtEmpCode"), empcode, "Empcode Textbox",
					empcode + " is entered into Empcode TextBox", "Unable to enter " + empcode + " in Empcode TextBox",
					"blue");
			WebActionUtil.typeText(tbPassword, password, "Password Textbox");
			WebActionUtil.validateEnteredValue1(WebActionUtil.getTextUsingJS("txtPassword"), password,
					"Password Textbox", password + " is entered into Password TextBox",
					"Unable to enter " + password + " in Password Textbox", "blue");
			WebActionUtil.clickOnElement(btnLogin, "Login Button", "Unable to click Login Button");
			WebActionUtil.waitForVisibilityOfElement(txtRaiseNewClaim, "Raise New Claim Text", 10l);
			WebActionUtil.validateisElementDisplayed(txtRaiseNewClaim, "Raise New Claim Text",
					empcode + " EmpID Log In is successful", "Unable to Log In with EmpID " + empcode, "green");
			WebActionUtil.validationinfo("The URL of landing page is : " + driver.getCurrentUrl(), "blue");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to Log In to the Application");
			Assert.fail("Unable to Log In to the Application");
			Assert.fail("Unable to LogIn to the Application");
		}

	}
}