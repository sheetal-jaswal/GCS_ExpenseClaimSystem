package com.hcl.gcs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.gcs.util.WebActionUtil;

/**
 * Description: This class implements methods for Pending Action Page.
 * 
 * @author Vivek dogra
 */

public class PendingAction_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public PendingAction_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Search Text Box */
	@FindBy(xpath = "//textarea[@name='txtVoucherNo']")
	private WebElement tbSearch;

	/* Search Button */
	@FindBy(id = "butSearch")
	private WebElement btnSearch;

	/* Approve Button */
	@FindBy(id = "butApprove")
	private WebElement btnApprove;

	/* Disburse Button */
	@FindBy(id = "butDisburse")
	private WebElement btnDisburse;

	/* Pending page Head Text */
	@FindBy(xpath = "//span[contains(text(),'Pending Action')]")
	private WebElement txtPendingPageHead;

	/* App Home Link */
	@FindBy(xpath = "//a[text()='App Home']")
	private WebElement lnkAppHome;

	/* ES Status Text */
	@FindBy(xpath = "(//td[text()='Status'])[2]/parent::tr/parent::tbody/tr[@class='global-rA']/td[8]")
	private WebElement txtEsStatus;

	/* particular claim Number */
	private WebElement selectClaim(String claimNo) {
		String xpath = "//tr[@class='global-rA']//a[text()='" + claimNo + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/**
	 * Description: This method implements for search particular claim number
	 * 
	 * @author Vivek dogra
	 * @param claimNo
	 */
	public synchronized void searchClaimNo(String claimNo) {
		try {
			if (WebActionUtil.isElementVisible(txtPendingPageHead, "Pending Page Head Title Text"))
				WebActionUtil.validationinfo("Pending Action page is displayed", "blue");
			WebActionUtil.validationinfo("The URL of Pending Action page is : " + driver.getCurrentUrl(), "blue");
			if (WebActionUtil.isElementVisible(tbSearch, "Search Textbox"))
				WebActionUtil.clickOnElement(tbSearch, "Search Textbox", "Unable to click on Search Textbox");
			WebActionUtil.typeText(tbSearch, claimNo, "Search Textbox");
			WebActionUtil.validateEnteredValue1(WebActionUtil.getTextUsingJS("txtVoucherNo"), claimNo, "Search Textbox",
					"ClaimNo " + claimNo + " is entered in Search Textbox ",
					"Unable to enter ClaimNo " + claimNo + " in Search Textbox ", "blue");
			WebActionUtil.clickOnElement(btnSearch, "Search Button", "Unable to click on Search Button");
//			WebActionUtil.waitForAngularPageToLoad();
			WebActionUtil.poll(3000);
			WebActionUtil.clickOnElement(selectClaim(claimNo), "Particular claimNo", "Unable to click on " + claimNo);
			WebActionUtil.switchToNewTab2();

		} catch (Exception e) {
			WebActionUtil.fail("Unable to Search " + claimNo + " Claim Number");
			Assert.fail("Unable to Search " + claimNo + " Claim Number");
		}

	}

	/**
	 * Description: This method implements for validate claimNo Approve by Es Status
	 * under pending action page.
	 * 
	 * @author Vivek dogra
	 * @param approveEsStatus
	 */
	public synchronized void validateEsStatus(String approveEsStatus) {
		String esStatus = txtEsStatus.getText();
		try {
			if (esStatus.contentEquals(approveEsStatus)) {
				WebActionUtil.validationinfo("Claim is " + esStatus, "green");
			}
		} catch (Exception e) {
			WebActionUtil.fail("Unable to validate " + esStatus + " by Es");
			Assert.fail("Unable to validate " + esStatus + " by Es");
		}

	}

	/**
	 * Description: This method implements to click on App Home link
	 * 
	 * @author Vivek dogra
	 */
	public synchronized void clkAppHomeLink() {
		try {
			WebActionUtil.isElementVisible(lnkAppHome, "App Home Link");
			WebActionUtil.clickOnElement(lnkAppHome, "App Home Link", "Unable to click on App Home Link");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on App Home Link");
			Assert.fail("Unable to click on App Home Link");
		}

	}

}
