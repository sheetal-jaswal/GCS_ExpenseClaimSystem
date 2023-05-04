package com.hcl.gcs.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.gcs.util.WebActionUtil;

/**
 * Description: This class implements the methods for Logout from GCS
 * Application
 * 
 * @author Vivek Dogra
 * 
 */

public class Logout_Page {
	public WebDriver driver;
	public com.hcl.gcs.util.WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Logout_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Login Head Text */
	@FindBy(xpath = "//b[text()='Global Claims System']")
	private WebElement txtloginHeadtext;

	/* Profile Dropdown */
	@FindBy(xpath = "//div[@class='glb-pnl-informationDropIcon']")
	private WebElement ddProfile;

	/* Logout Button */
	@FindBy(xpath = "//span[text()='LOGOUT ']")
	private WebElement btnLogout;

	/* Are You Sure You Want To Logout PopUp */
	@FindBy(xpath = "//p[contains(text(),'Are you sure you want to logout?')]")
	private WebElement popUpAreYouSureYouWantToLogout;

	/* Yes Button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnLogoutYes;

	/* No Button */
	@FindBy(xpath = "//button[text()='NO']")
	private WebElement btnLogoutNo;

	/* Logout Profile Icon */
	@FindBy(xpath = "//div[@class='glb-pnl-informationDropIcon']//span")
	private WebElement iconlogoutprofile;

	/* Logout Option */
	@FindBy(xpath = "//a[@class='logout_alert']//span[text()='LOGOUT ']")
	private WebElement optlogout;

	/* Yes Button */
	@FindBy(xpath = "//div[@class='sa-confirm-button-container']//button[text()='YES']")
	private WebElement btnYes;

	/* Back to Login Button */
	@FindBy(xpath = "//span[text()='BACK TO Login']")
	private WebElement btnBackTologin;

	/**
	 * Description Method to Select an option from the the profile Dropdown.
	 * 
	 * @author vivek dogra
	 * @param expectedLogoutUrl
	 */
	public synchronized void selectProfiledd(String expectedLogoutUrl) {
		try {
			WebActionUtil.waitForElement(ddProfile, "Profile Dropdown");
			WebActionUtil.actionClick(ddProfile, "Profile Dropdown");
			clkLogoutbtn(expectedLogoutUrl);
		} catch (Exception e) {
			WebActionUtil.error("Unable to MouseOver Profile Dropdown");
			WebActionUtil.fail("Unable to MouseOver Profile Dropdown");
			Assert.fail("Unable to MouseOver Profile Dropdown");
		}
	}

	/**
	 * Description Method to Click on Logout Button
	 * 
	 * @author vivek dogra
	 * @param expectedLogoutUrl
	 */
	public synchronized void clkLogoutbtn(String expectedLogoutUrl) {
		try {
			WebActionUtil.waitForElement(btnLogout, "Logout Button");
			WebActionUtil.actionClick(btnLogout, "Logout Button");

			clkAreYouSureYouWantToLogoutYesBtn(expectedLogoutUrl);

			WebActionUtil.poll(1000l);
			try {
				List<String> lstWindowHandles = new ArrayList<String>(driver.getWindowHandles());
				if (lstWindowHandles.size() > 3) {
					driver.switchTo().window(lstWindowHandles.get(3));
					driver.close();
					driver.switchTo().window(lstWindowHandles.get(2));
				}
			} catch (Exception e) {
				List<String> lstWindowHandles = new ArrayList<String>(driver.getWindowHandles());
				if (lstWindowHandles.size() > 4) {
					driver.switchTo().window(lstWindowHandles.get(4));
					driver.close();
					driver.switchTo().window(lstWindowHandles.get(3));
				}
			}
		} catch (Exception e) {
			WebActionUtil.error("Unable to click on Logout Button");
			WebActionUtil.fail("Unable to click on Logout Button");
			Assert.fail("Unable to click on Logout Button");
		}
	}

	/**
	 * Description Method to Click on Are You Sure You Want To Logout Yes Button
	 * 
	 * 
	 * @author vivek dogra
	 * @param expectedLogoutUrl
	 */
	public synchronized void clkAreYouSureYouWantToLogoutYesBtn(String expectedLogoutUrl) {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(btnLogoutYes, "Yes Button");
			WebActionUtil.clickOnElement(btnLogoutYes, "Yes Button", "Unable to click on Yes Button");
			new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe(expectedLogoutUrl));
			String actualLogoutUrl = driver.getCurrentUrl();

			if (actualLogoutUrl.contentEquals(expectedLogoutUrl)) {
				WebActionUtil.info("Logout Page is displayed., url= " + actualLogoutUrl);
				WebActionUtil.validationinfo("Logout Page is displayed, url= " + actualLogoutUrl, "green");

			} else {
				WebActionUtil.error("Unable to Validate the Logout Page");
				WebActionUtil.validationinfo("Logout Page is not displayed, url= " + actualLogoutUrl, "red");

			}
			WebActionUtil.waitForElement(btnBackTologin, "Back to Login Button");
			WebActionUtil.poll(3000);
			WebActionUtil.clickOnElement(btnBackTologin, "Back to Login Button",
					"Unable to click Back to Login Button");
			WebActionUtil.validationinfo("The URL is : " + driver.getCurrentUrl(), "blue");
			WebActionUtil.isElementVisible(txtloginHeadtext, "Login Page Head Text");
			WebActionUtil.validationinfo("Login Page is displayed", "blue");
		} catch (Exception e) {
			WebActionUtil.error("Unable to click on Yes Button");
			WebActionUtil.fail("Unable to click on Yes Button ");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description Method to Click on Are You Sure You Want To Logout No Button
	 * 
	 * @author vivek dogra
	 */
	public synchronized void clkAreYouSureYouWantToLogoutNoBtn() {
		try {
			WebActionUtil.validateisElementDisplayed(popUpAreYouSureYouWantToLogout,
					"Are You Sure You Want To Logout PopUp", "Are You Sure You Want To Logout PopUp is displayed",
					"Are You Sure You Want To Logout PopUp is not displayed", "blue");

			WebActionUtil.waitForElement(btnLogoutNo, "No Button");
			WebActionUtil.clickOnElement(btnLogoutNo, "No Button", "Unable to click on No Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on No Button");
			Assert.fail("Unable to click on No Button");
		}
	}

}
