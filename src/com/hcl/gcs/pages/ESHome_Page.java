package com.hcl.gcs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.gcs.util.WebActionUtil;

/**
 * Description: This class implements the methods for ES Home Page
 * 
 * @author suganthini
 */
public class ESHome_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public ESHome_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Toggle Drop down */
	@FindBy(xpath = "//li[@class='dropdown glb-pnl-localNav']/descendant::span")
	private WebElement ddToggle;

	/* Pending action Link */
	@FindBy(xpath = "//a[text()='Pending Action']")
	private WebElement lnkPendingAction;

	/* profile Drop down */
	@FindBy(xpath = "//span[@class='glb-pnl-name ng-binding']")
	private WebElement ddProfile;

	/* Logout button */
	@FindBy(xpath = "//span[@class='log_text']")
	private WebElement btnLogout;

	/* Yes Button of Logout pop up */
	@FindBy(xpath = "//p[contains(text(),'logout')]/parent::div/descendant::button[text()='YES']")
	private WebElement btnYesLogout;

	/* No Button of Logout pop up */
	@FindBy(xpath = "//p[contains(text(),'logout')]/parent::div/descendant::button[text()='NO']")
	private WebElement btnNoLogout;

	/* text logout */
	@FindBy(xpath = "//h2[text()='You have been logged out.']")
	private WebElement txtLogout;

	/**
	 * Description:This Method implements to click on Pending Action
	 * 
	 * @author suganthini
	 */
	public synchronized void clkPendingAction() {
		try {
			WebActionUtil.actionMouseOver(ddToggle, "Menu");
			WebActionUtil.waitForElement(lnkPendingAction, "Pending Action Link");
			WebActionUtil.clickOnElement(lnkPendingAction, "Pending Action Link",
					"Unable to click on Pending Action Link");
			WebActionUtil.waitForAngularPageload();
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Pending Action Link");
			Assert.fail("Unable to click on Pending Action Link");
		}

	}

	/**
	 * Description:This Method implements to log out from the application
	 * 
	 * @author suganthini
	 */
	public synchronized void logout() {
		try {
			WebActionUtil.actionMouseOver(ddProfile, "Profile");
			WebActionUtil.waitForElement(btnLogout, "Logout Button");
			WebActionUtil.clickOnElement(btnLogout, "Logout Button", "Unable to click on Logout Button");
			new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(btnLogout));

			WebActionUtil.clickOnElement(btnYesLogout, "Yes Button", "Unable to click on Yes Button of Logout popup");
			WebActionUtil.waitForElement(txtLogout, "Logout Text");
			WebActionUtil.validatetext("You have been logged out.", txtLogout, "Logout Text",
					"Logged out from the Account", "Unable to Log out from the Account", "green");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to Loout from the Application");
			Assert.fail("Unable to Logout from the Application");
		}

	}	

}
