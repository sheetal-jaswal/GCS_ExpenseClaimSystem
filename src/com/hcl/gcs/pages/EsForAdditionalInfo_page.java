package com.hcl.gcs.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.gcs.util.WebActionUtil;

/**
 * Description: This class implements the methods for ES For AdditionalInfo page
 * 
 * @author Abhilash
 */
public class EsForAdditionalInfo_page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public EsForAdditionalInfo_page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Description text box */
	@FindBy(id = "txtDescription")
	private WebElement tbDescription;

	/* Approved amount text box */
	@FindBy(id = "txtApprovedAmt")
	private WebElement tbApprovedAmount;

	/* Add new expense button */
	@FindBy(id = "bttnAddNewExpense")
	private WebElement btnAddNewExpense;

	/* Edit link */
	@FindBy(xpath = "//img[@title='Edit expense category']/parent::a")
	private WebElement lnkEdit;

	/* Remarks text box */
	@FindBy(id = "txtRemarks")
	private WebElement tbRemarks;

	/* Edit Amount text box */
	@FindBy(id = "dgClaimDetails_ctl02_txtEditAppAmt")
	private WebElement tbEditAmount;

	/* save link */
	@FindBy(xpath = "//img[@title='Update expense category']/parent::a")
	private WebElement lnkSave;

	/* cancel link */
	@FindBy(xpath = "//img[@title='Cancel']/parent::a")
	private WebElement lnkCancel;

	/* Edit Description text box */
	@FindBy(id = "dgClaimDetails_ctl02_txtEditDesc")
	private WebElement tbEditDescription;

	/* View files button */
	@FindBy(id = "btnViewUpload")
	private WebElement btnViewFiles;

	/* Approve button */
	@FindBy(xpath = "//input[@id='butApprove']")
	private WebElement btnApprove;

	/* Refer back button */
	@FindBy(id = "butReferBack")
	private WebElement btnReferBack;

	/* Additional info button */
	@FindBy(id = "butAddlInfo")
	private WebElement btnAdditionalInfo;

	/* Additional approve button */
	@FindBy(id = "butAddlApproval")
	private WebElement btnAdditionalApprove;

	/* Reject button */
	@FindBy(id = "butReject")
	private WebElement btnReject;

	/* Disburse button */
	@FindBy(id = "butDisburse")
	private WebElement btnDisburse;

	/* Save button */
	@FindBy(id = "butSave")
	private WebElement btnSave;

	/* Expense category drop down */
	@FindBy(id = "dgClaimDetails_ctl02_dropEditExtCategory")
	private WebElement ddExpenseCategory;

	/* claim number text */
	@FindBy(id = "lblVoucherNo")
	private WebElement txtClaimNO;

	/* claim amount text */
	@FindBy(id = "lblTotalClaimed")
	private WebElement txtClaimAmount;

	/* attached document link */
	@FindBy(id = "dgUpload_ctl02_lnkView")
	private WebElement lnkAttachedDocument;

	/**
	 * Description:This Method implements to click on Edit link under claim details
	 * 
	 * @author Abhilash
	 * @param expenseCategory
	 * @param description
	 */
	public synchronized void clkEditLink(String expenseCategory, String description) {
		try {
			WebActionUtil.waitForAngularPageload();
			WebActionUtil.scrollToElement(lnkEdit, "Edit Link");
			WebActionUtil.waitForElement(lnkEdit, "Edit Link");
			WebActionUtil.clickOnElementUsingJS(lnkEdit, "Edit Link");
			validateClaimDetailsEdit(expenseCategory, description);
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Edit Link under claim details");
			WebActionUtil.error("Unable to click on Edit Link under claim details");
			Assert.fail("Unable to click on Edit Link under claim details");
		}
	}

	/**
	 * Description:This Method implements to validate claim details
	 * 
	 * @author Abhilash
	 * @param expenseCategory
	 * @param description
	 */
	private synchronized void validateClaimDetailsEdit(String expenseCategory, String description) {
		try {
			WebActionUtil.waitForAngularPageload();
			ArrayList<Boolean> arraylist = new ArrayList<Boolean>();
			WebActionUtil.waitForElement(ddExpenseCategory, "Expense Category");
			Select select = new Select(ddExpenseCategory);
			// arraylist.add(select.getFirstSelectedOption().getText().contains(expenseCategory));
			// arraylist.add(WebActionUtil.getTextUsingJS("dgClaimDetails_ctl02_txtEditDesc").equalsIgnoreCase(description));
			if (!arraylist.contains(false))
				WebActionUtil.validationinfo("Claim details is displayed", "blue");
			else
				throw new Exception();
		} catch (Exception e) {
			WebActionUtil.fail("Claim details is not displayed");
			WebActionUtil.error("Claim details is not displayed");
			Assert.fail("Claim details is not displayed");
		}

	}

	/**
	 * Description:This Method implements to set Approve amount
	 * 
	 * @author Abhilash
	 * @param approvedAmount
	 */
	public synchronized void setApprovedAmount(String approvedAmount) {
		try {
			WebActionUtil.waitForElement(tbEditAmount, "Approved Amount");
			WebActionUtil.clearText(tbEditAmount, "Approved Amount");
			WebActionUtil.typeText(tbEditAmount, approvedAmount, "Approved Amount");
			WebActionUtil.validateEnteredValue1(approvedAmount,
					WebActionUtil.getTextUsingJS("dgClaimDetails_ctl02_txtEditAppAmt"), "Approved Amount",
					"Entered Amount is displayed in claim amount field",
					"Entered Amount is not displayed in claim amount field", "blue");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to enter approve amount in Claim amount field");
			WebActionUtil.error("Unable to enter approve amount in Claim amount field");
			Assert.fail("Unable to enter approve amount in Claim amount field");
		}
	}

	/**
	 * Description:This Method implements to click on save link under claim details
	 * 
	 * @author Abhilash
	 */
	public synchronized void clkSaveLink() {
		try {
			WebActionUtil.waitForElement(lnkSave, "Save Link");
			WebActionUtil.clickOnElement(lnkSave, "Save Link", "Unable to click on Save Link under claim details");
			// WebActionUtil.validateisElementDisplayed(lnkEdit, "Edit Link", "Entered claim
			// details is saved", "Entered claim details is not saved", "blue");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Save Link under claim details");
			WebActionUtil.error("Unable to click on Save Link under claim details");
			Assert.fail("Unable to click on Save Link under claim details");
		}
	}

	/**
	 * Description:This Method implements to validate Es for additional info page
	 * 
	 * @author Abhilash
	 * @param expectedTitle
	 */
	public synchronized void validateESforAdditionalInfoPage(String expectedTitle) {
		try {
			WebActionUtil.waitForAngularPageload();
			new WebDriverWait(driver, 15).until(ExpectedConditions.titleContains(expectedTitle));
			Assert.assertEquals(driver.getTitle().trim(), expectedTitle);
			WebActionUtil.validationinfo("ES for Additional Info Action Page is displayed", "green");

		} catch (Exception e) {
			WebActionUtil.fail("ES for Additional Info Action Page is not displayed");
			WebActionUtil.error("ES for Additional Info Action Page is not displayed");
			Assert.fail("ES for Additional Info Action Page is not displayed");
		}
	}

	/**
	 * Description:This Method implements to verify claim details
	 * 
	 * @author Abhilash
	 * @param claimNo
	 * @param claimedAmount
	 */
	public synchronized void validateClaim(String claimNo, String claimedAmount) {
		try {
			ArrayList<Boolean> arraylist = new ArrayList<Boolean>();
			WebActionUtil.waitForElement(txtClaimNO, "Claim number Text");
			arraylist.add(txtClaimNO.getText().equals(claimNo));
			arraylist.add(txtClaimAmount.equals(claimedAmount));
			if (!arraylist.contains(false))
				WebActionUtil.validationinfo("Claim is verified", "blue");
			else
				throw new Exception();
		} catch (Exception e) {
			WebActionUtil.fail("Unable to validate the Claim number");
			WebActionUtil.error("Unable to validate the Claim number");
			Assert.fail("Unable to validate the Claim number");
		}

	}

	/**
	 * Description:This Method implements to verify Added attachment
	 * 
	 * @author Abhilash
	 * @param attachedDocumentName
	 */
	public synchronized void validateAttachedVoucher(String attachedDocumentName) {
		try {
			if (lnkAttachedDocument.getText().contains(attachedDocumentName))
				WebActionUtil.validationinfo("Added attachment is displayed", "blue");
			else
				throw new Exception();
		} catch (Exception e) {
			WebActionUtil.fail("Added attachment is not displayed");
			WebActionUtil.error("Added attachment is not displayed");
			Assert.fail("Added attachment is not displayed");
		}

	}

	/**
	 * Description: This Method implements to set remarks
	 * 
	 * @author Abhilash
	 * @param remarks
	 */
	public synchronized void setRemarks(String remarks) {
		try {
			WebActionUtil.waitForElement(tbRemarks, "Remarks Textbox");
//			WebActionUtil.scrollToElement(tbRemarks, "Remarks Textbox");
			WebActionUtil.typeText(tbRemarks, remarks, "Remarks Textbox");
			WebActionUtil.validateEnteredValue1(remarks, WebActionUtil.getTextUsingJS("txtRemarks"), "Remarks Textbox",
					remarks + " is displayed in Remarks Textbox", remarks + " is not displayed in Remarks Textbox",
					"blue");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to enter " + remarks + " in Remarks Textbox");
			WebActionUtil.error("Unable to enter " + remarks + " in Remarks Textbox");
			Assert.fail("Unable to enter " + remarks + " in Remarks Textbox");
		}
	}

	/**
	 * Description:This Method implements to click on Approve button
	 * 
	 * @author Abhilash
	 */
	public synchronized void clkApproveButton() {
		try {
			WebActionUtil.waitForElement(btnApprove, "Approve Button");
			// WebActionUtil.jsExecutor.executeScript("document.getElementById('butApprove').click()");
			WebActionUtil.clickOnElementUsingJS(btnApprove, "Approve Button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Approve Button");
			WebActionUtil.error("Unable to click on Approve Button");
			Assert.fail("Unable to click on Approve Button");
		}
	}

	/**
	 * Description:This Method implements to click on Refer Back button
	 * 
	 * @author Abhilash
	 */
	public synchronized void clkReferBackButton() {
		try {
			WebActionUtil.waitForElement(btnReferBack, "Refer Back Button");
			WebActionUtil.clickOnElementUsingJS(btnReferBack, "Refer Back Button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Refer Back Button");
			WebActionUtil.error("Unable to click on Refer Back Button");
			Assert.fail("Unable to click on Refer Back Button");
		}
	}

	/**
	 * Description:This Method implements to click on Reject button
	 * 
	 * @author Abhilash
	 */
	public synchronized void clkRejectButton() {
		try {
			WebActionUtil.waitForElement(btnReject, "Reject Button");
			WebActionUtil.clickOnElementUsingJS(btnReject, "Reject Button");
//		WebActionUtil.jsExecutor.executeScript("document.getElementById('butReject').click()");
//		WebActionUtil.clickOnElementUsingJS(btnReject, "Reject Button");
//			WebActionUtil.clickOnElement(btnReject, "Reject Button",
//					"Unable to click on Reject Button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Reject Button");
			WebActionUtil.error("Unable to click on Reject Button");
			Assert.fail("Unable to click on Reject Button");
		}
	}
}
