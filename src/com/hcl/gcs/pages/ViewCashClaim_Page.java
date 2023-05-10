
package com.hcl.gcs.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
 * Description: This class implements methods for View Cash Claim Page.
 * 
 * @author Vikas K C.
 */

public class ViewCashClaim_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public ViewCashClaim_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Advance Spinner */
	@FindBy(xpath = "//div[@id='Advance_go_spiner']")
	private WebElement advanceSpinner;

	/* Reason / Business justification for this claim (Max. 1000 chars) text area */
	@FindBy(id = "txtVoucherComment")
	private WebElement txtReason;

	/* Declaration check box */
	@FindBy(id = "chkdeclaration")
	private WebElement cbDeclaration;

	/* Declaration check box */
	@FindBy(xpath = "(//input[@id='chkdeclaration'])[1]")
	private WebElement cbFirstDeclaration;

	/* Description text box */
	@FindBy(name = "remarks")
	private WebElement tbDescription;

	/* Submit Button */
	@FindBy(id = "submitbutton")
	private WebElement btnSubmit;

	/* Save as draft Button */
	@FindBy(id = "savebutton")
	private WebElement btnSaveAsDraft;

	/* Cancel Button */
	@FindBy(id = "cancelbutton")
	private WebElement btnCancel;

	/* Back Button */
	@FindBy(id = "backbutton")
	private WebElement btnBack;

	/* Upload file */
	@FindBy(xpath = "//div[@id='Uploaddocx']//span[text()='Drag & Drop to upload your bills here']")
	private WebElement lnkUpload;

	/* Edit icon */
	@FindBy(xpath = "//h2[text()='Expense Details']/parent::div/following-sibling::div/descendant::span[@class='gcs-edit action-icon']")
	private WebElement icnEdit;

	/* Created Expenses name in Expenses details */
	@FindBy(xpath = "//div[@class='details-list ng-binding ng-scope']")
	private WebElement txtExpenseDetails;

	/* Delete icon */
	@FindBy(xpath = "//h2[text()='Expense Details']/parent::div/following-sibling::div/descendant::span[@class='gcs-delete action-icon']")
	private WebElement icnDelete;

	/* Refer back text */
	@FindBy(xpath = "(//div[@class='history-details error-history'])[1]/h2")
	private WebElement txtReferBack;

	/* Approve text */
	@FindBy(xpath = "(//div[@class='history-details'])[1]/h2")
	private WebElement txtApprove;

	/* Reject text */
	@FindBy(xpath = "(//div[@class='history-details reject-Error'])[1]/h2")
	private WebElement txtReject;

	/* update Expenses button */
	@FindBy(id = "btnAdd")
	private WebElement btnUpdateExpenses;

	/* Amount text field */
	@FindBy(id = "ExpAmount")
	private WebElement tbAmount;

	/* Yes button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnYes;

	/* Claim submit Toast Message */
	@FindBy(xpath = "//span[text()='Claim submitted successfully.']")
	private WebElement claimSubmitedToastMessage;

	/* Proceed message text */
	@FindBy(xpath = "//p[@class='lead text-muted ']")
	private WebElement txtProceedMessage;

	/* Document link */
	@FindBy(xpath = "//div[@class='dz-filename']/span/a")
	private List<WebElement> lnkDocument;

	/* claim number */
	@FindBy(xpath = "(//td[@class='ng-binding'])[1]")
	private WebElement txtClaimNO;

	/* text Sample file format */
	@FindBy(xpath = "(//div[@class='dz-filename']/span/a)[1]")
	private WebElement txtSampleFileFormat;

	/* claim amount */
	@FindBy(xpath = "(//td[@class='ng-binding'])[3]")
	private WebElement txtClaimAmount;

	/* Country drop down */
	@FindBy(id = "counrtyVisaList")
	private WebElement sddCountry;

	/* Expense Date text box */
	@FindBy(id = "conveyanceFromDate")
	private WebElement tbExpenseDate;

	/* International amount text box */
	@FindBy(id = "internationalAmount")
	private WebElement tbInternationalCharge;

	/* Visa type drop down */
	@FindBy(id = "visaType")
	private WebElement sddVisaType;

	/* Visa Expense type drop down */
	@FindBy(id = "visaExpenseType")
	private WebElement sddVisaExpenseType;

	/* transport id dropdown */
	@FindBy(id = "cabTransportID")
	private WebElement sddTransportId;

	/* Spinner */
	@FindBy(xpath = "(//div[@class='loader2 material-spinner'])[2]")
	private WebElement spinner;

	/* Select Role */
	public String selectRole(String role) {
		String xpath = "//span[text()='RM']/following-sibling::span";
		return driver.findElement(By.xpath(xpath)).getText();
	}

	/* To fetch approver role */
	@FindBy(xpath = "//ul[@class='workflow']/li/span[1]")
	private List<WebElement> listOfRoles;

	/* Expected Date of travel text box */
	@FindBy(id = "covidAdvanceTravelDate")
	private WebElement tbExpectedDate;

	/* To fetch approver name */
	@FindBy(xpath = "//ul[@class='workflow']/li/span[2]")
	private List<WebElement> listOfApproverNames;

	/* Check box */
	@FindBy(xpath = "//div[@class='checkbox declareText']/descendant::input")
	private List<WebElement> lstCheckBoxs;

	/* Course started date text box */
	@FindBy(id = "stCourseFromDate")
	private WebElement tbCourseStartDate;

	/* Certification Date */
	@FindBy(id = "technicalCertifyFromDate")
	private WebElement tbCertificationDate;

	/**
	 * Description: Method to click on check box
	 * 
	 * @author Vikas K C.
	 * @param checkBoxNames
	 */
	public synchronized void clkCheckBoxs(String[] checkBoxNames) {
		int i = 0;

		for (WebElement weCheckBox : lstCheckBoxs) {
			WebActionUtil.waitForElement(weCheckBox, "Check box");
			WebActionUtil.clickCheckBox(weCheckBox, checkBoxNames[i++] + ": Check box");
		}
	}

	Map<String, String> map = new LinkedHashMap<String, String>();

	/**
	 * Description: Method to fetch all the approvers employee code
	 * 
	 * @author vikas.kc
	 * @return
	 */
	public synchronized Map<String, String> getRolesFromFlow() {
		try {
			try 
			{
				String map="employee";
				
			} catch (Exception e) {

			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fetch the roles");
			Assert.fail("Unable to fetch the roles");
		}
		
		return map;
	}

	/**
	 * Description: Method to return employee code
	 * 
	 * @author vikas.kc
	 * @param approverName
	 * @return
	 */
	public synchronized String getEmpCode(String approverName) {
		return map.get(approverName);
	}

	/**
	 * Description: Method implements to Click on Edit icon
	 * 
	 * @author vikas.kc
	 * @param categoryName
	 */
	public synchronized void clkEditIcn(String categoryName) {
		try {
			WebActionUtil.waitForElement(txtExpenseDetails, "Expense Name");
			WebActionUtil.clickOnElement(txtExpenseDetails, categoryName + " Expense Name",
					"Unable to click on " + categoryName + " Expense Name ");
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(icnEdit, "Edit Icon");
			WebActionUtil.clickOnElement(icnEdit, "Edit Icon", "Unable to click on Edit Icon ");
			if (advanceSpinner.isDisplayed()) {
				WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long) 60);
			}
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Edit Icon");
			WebActionUtil.error("Unable to click on Edit Icon");
			Assert.fail("Unable to click on Edit Icon");
		}
	}

	/**
	 * Description: Method implements to Click on Delete icon
	 * 
	 * @author vikas.kc
	 * @param categoryName
	 */
	public synchronized void clkDeleteIcn(String categoryName) {
		try {
			WebActionUtil.waitForElement(txtExpenseDetails, "Expense Name");
			WebActionUtil.clickOnElement(txtExpenseDetails, categoryName + "Expense Name",
					"Unable to click on " + categoryName + "Expense Name");

			WebActionUtil.waitForElement(icnDelete, "Delete Icon");
			WebActionUtil.clickOnElement(icnDelete, "Delete Icon", "Unable to click on Delete Icon ");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Delete Icon");
			WebActionUtil.error("Unable to click on Delete Icon");
			Assert.fail("Unable to click on Delete Icon");
		}
	}

	/**
	 * Description: Method to validate approve text
	 * 
	 * @author vikas.kc
	 * @param approveMessage
	 */
	public synchronized void validateApproveText(String approveMessage) {
		try {
			driver.navigate().refresh();
			try {
				WebActionUtil.waitForElement(txtApprove, "Approve text");
				Assert.assertEquals(txtApprove.getText().toLowerCase(), approveMessage.toLowerCase());
				WebActionUtil.info(txtApprove.getText() + " status is displayed");
				WebActionUtil.pass(txtApprove.getText() + " status is displayed");
				validateApproveColor();
			} catch (Exception e) {
				driver.navigate().refresh();
				WebActionUtil.waitForInvisibilityOfElement(spinner, "Spinner", (long) 10);
				WebActionUtil.waitForElement(txtApprove, "Approve text");
				Assert.assertEquals(txtApprove.getText().toLowerCase(), approveMessage.toLowerCase());
				WebActionUtil.info(txtApprove.getText() + " status is displayed");
				WebActionUtil.pass(txtApprove.getText() + " status is displayed");
				validateApproveColor();
			}
		} catch (Exception e) {
			WebActionUtil.fail(txtApprove.getText() + " status is not displayed");
			WebActionUtil.error(txtApprove.getText() + " status is not displayed");
			Assert.fail(txtApprove.getText() + " status is not displayed");
		}
	}

	/**
	 * Description: Method to validate reject text
	 * 
	 * @author vikas.kc
	 * @param rejectMessage
	 */
	public synchronized void validateRejectText(String rejectMessage) {
		try {
			driver.navigate().refresh();
			try {
				WebActionUtil.waitForElement(txtReject, "Refer Back Text");
				boolean value = txtReject.getText().toLowerCase().contains(rejectMessage.toLowerCase())
						|| txtReject.getText().toLowerCase().contains("rejected");
				Assert.assertTrue(value);
				WebActionUtil.info(txtReject.getText() + " status is displayed");
				WebActionUtil.pass(txtReject.getText() + " status is displayed");
				validateRejectColor();
			} catch (Exception e) {
				driver.navigate().refresh();
				WebActionUtil.waitForInvisibilityOfElement(spinner, "Spinner", (long) 10);
				WebActionUtil.waitForElement(txtReject, "Refer Back Text");
				boolean value = txtReject.getText().toLowerCase().contains(rejectMessage.toLowerCase())
						|| txtReject.getText().toLowerCase().contains("rejected");
				Assert.assertTrue(value);
				WebActionUtil.info(txtReject.getText() + " status is displayed");
				WebActionUtil.pass(txtReject.getText() + " status is displayed");
				validateRejectColor();
			}
		} catch (Exception e) {
			WebActionUtil.fail(txtReject.getText() + " status is not displayed");
			WebActionUtil.error(txtReject.getText() + " status is not displayed");
			Assert.fail(txtReject.getText() + " status is not displayed");
		}
	}

	/**
	 * Description: Method to validate approve color
	 * 
	 * @author vikas.kc
	 */
	public synchronized void validateApproveColor() {
		try {
			Assert.assertEquals(getColor(), "54, 197, 37");
			WebActionUtil.info("Approved tick mark with green color is displayed");
			WebActionUtil.pass("Approved tick mark with green color is displayed");
		} catch (Exception e) {
			WebActionUtil.fail("Approved tick mark with green color is not displayed");
			WebActionUtil.error("Approved tick mark with green color is not displayed");
			Assert.fail("Approved tick mark with green color is not displayed");
		}
	}

	/**
	 * Description: Method to validate refereed back color
	 * 
	 * @author vikas.kc
	 */
	public synchronized void validateReferredBackColor() {
		try {
			Assert.assertEquals(getColor(), "247, 126, 37");
			WebActionUtil.info("Referred back mark with violet color is displayed");
			WebActionUtil.pass("Referred back mark with violet color is displayed");
		} catch (Exception e) {
			WebActionUtil.fail("Referred back mark with violet color is not displayed");
			WebActionUtil.error("Referred back mark with violet color is not displayed");
			Assert.fail("Referred back mark with violet color is not displayed");
		}
	}

	/**
	 * Description: Method to validate reject color
	 * 
	 * @author vikas.kc
	 */
	public synchronized void validateRejectColor() {
		try {
			Assert.assertEquals(getColor(), "252, 50, 50");
			WebActionUtil.info("Reject mark with red color is displayed");
			WebActionUtil.pass("Reject mark with red color is displayed");
		} catch (Exception e) {
			WebActionUtil.fail("Reject mark with red color is not displayed");
			WebActionUtil.error("Reject mark with red color is not displayed");
			Assert.fail("Reject mark with red color is not displayed");
		}
	}

	/**
	 * Description: Method to click on Update Expenses button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkUpdateExpenseButton() {
		try {
			WebActionUtil.waitForElement(btnUpdateExpenses, "Update Expenses Button");
			WebActionUtil.clickOnElement(btnUpdateExpenses, "Update Expenses Button",
					"Unable to click on Update Expenses Button");
			try {
				if (spinner.isDisplayed()) {
					new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(spinner));
				}
			} catch (Exception e) {
				WebActionUtil.fail("Spinner is still visible");
				WebActionUtil.error("Spinner is still visible");
				Assert.fail("Spinner is still visible");
			}
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Update Expenses Button");
			WebActionUtil.error("Unable to click on Update Expenses Button");
			Assert.fail("Unable to click on Update Expenses Button");
		}
	}

	/**
	 * Description: Method to enter amount into amount text field
	 * 
	 * @author vikas.kc
	 * @param amount
	 */
	public synchronized void setAmountTextfield(String amount) {
		try {
			WebActionUtil.waitForAngularPageToLoad();
			WebActionUtil.clickOnElement(tbAmount, "Amount Textbox",
					"Unable to enter " + amount + " into Amount Textbox");
			WebActionUtil.clearText(tbAmount, "Amount Textbox");
			WebActionUtil.typeText(tbAmount, amount, "Amount Textbox");
			WebActionUtil.extentinfo(amount + " Entered in Amount Textbox");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter " + amount + " into Amount Textbox");
			Assert.fail("Unable to enter " + amount + " into Amount Textbox");
		}
	}

	/**
	 * Description: Method to enter Reason or Business justification for the claim
	 * 
	 * @author: vikas.kc
	 * @param reason
	 */
	public synchronized void setReasonForClaimTextfield(String reason) {
		try {
			WebActionUtil.waitForElement(txtReason, "Reason or Business justification Text Area");
			WebActionUtil.typeText(txtReason, reason, "Reason or Business justification Text Area");
			WebActionUtil.extentinfo(reason + " : Entered in Reason or Business justification Text Area");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to enter Reason or Business justification");
			Assert.fail("Unable to enter Reason or Business justification");
		}
	}

	/**
	 * Description: Method to click on Declared check box
	 * 
	 * @author vikas.kc
	 *
	 */
	public synchronized void clkDeclareCheckbox() {
		try {
			WebActionUtil.waitForElement(cbDeclaration, "Declare Check box");
			WebActionUtil.clickCheckBox(cbDeclaration, "Declare Check box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Declare Check box");
			Assert.fail("Unable to click on Declare Check box");
		}
	}

	/**
	 * Description: Method to click on first Declared check box
	 * 
	 * @author vikas.kc
	 *
	 */
	public synchronized void clkFirstDeclareCheckbox() {
		try {
			WebActionUtil.waitForElement(cbFirstDeclaration, "Declare Check box");
			WebActionUtil.clickCheckBox(cbFirstDeclaration, "Declare Check box");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Declare Check box");
			Assert.fail("Unable to click on Declare Check box");
		}
	}

	/**
	 * Description: Method to click on Submit button
	 * 
	 * @author vikas.kc
	 */
	public synchronized void clkSubmitButton() {
		try {
			WebActionUtil.waitForElement(btnSubmit, "Submit Button");
			WebActionUtil.clickOnElement(btnSubmit, "Submit Button", "Unable to click on Submit Button");
			validateProceedMessageText();
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Submit Button");
			WebActionUtil.error("Unable to click on Submit Button");
			Assert.fail("Unable to click on Submit Button");
		}
	}
	/**
	 * Description: Method to click on Submit button using js
	 * 
	 * @author vikas.kc
	 */
	public synchronized void clkSubmitButtonUsingJS() {
		try {
			WebActionUtil.waitForElement(btnSubmit, "Submit Button");
			WebActionUtil.clickOnElementUsingJS(btnSubmit, "Submit Button");
			validateProceedMessageText();
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Submit Button");
			WebActionUtil.error("Unable to click on Submit Button");
			Assert.fail("Unable to click on Submit Button");
		}
	}

	/**
	 * Description: Method to validate proceed text message pop up
	 * 
	 * @author vikas.kc
	 */
	public synchronized void validateProceedMessageText() {
		try {
			WebActionUtil.waitForElement(txtProceedMessage, "Proceed text message");
			Assert.assertEquals(txtProceedMessage.getText(), "Are you sure you want to proceed?");
			WebActionUtil.info("Proceed text message popup is displayed");
			WebActionUtil.pass("Proceed text message popup is displayed");
		} catch (Exception e) {
			WebActionUtil.fail("Proceed text message popup is not displayed");
			WebActionUtil.error("Proceed text message popup is not displayed");
			Assert.fail("Proceed text message popup is not displayed");
		}
	}

	/**
	 * Description: Method to clicks on Yes button
	 * 
	 * @author vikas.kc
	 */
	public synchronized void clkOnYesButton() {

		try {
			WebActionUtil.poll(1000);
			WebActionUtil.waitForElement(btnYes, "Yes Button");
			WebActionUtil.clickOnElement(btnYes, "Yes Button", "Unable to click on Yes");
			WebElement dots = driver.findElement(By.xpath("//div[@class='la-ball-fall']"));
			try {
				while (true) {
					if (dots.isDisplayed()){
					new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(dots));
					break;
					}
				}
			} catch (Exception e) {
				WebActionUtil.error("Dots still visible");
				WebActionUtil.fail("Dots still visible");
				Assert.fail("Dots still visible");
			}
			validateClaimSubmittedToastMessage();

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes Button");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description: Method to fetch the background color
	 * 
	 * @author vikas.kc
	 * @return expectedColor
	 */
	public synchronized String getColor() {
		String expectedColor = null;
		try {
			WebActionUtil.poll(1000);
			String script = "return window.getComputedStyle(document.querySelector('.history-details'),':before').getPropertyValue('background-color')";
			expectedColor = (String) (((JavascriptExecutor) driver).executeScript(script));
			expectedColor = expectedColor.substring(expectedColor.indexOf("(") + 1, expectedColor.indexOf(")"));
		} catch (Exception e) {
			WebActionUtil.error("Unable to fetch color");
			WebActionUtil.fail("Unable to fetch color");
		}
		return expectedColor;
	}

	/**
	 * Description: Method to validate claim submitted success toast message
	 * 
	 * @author vikas.kc
	 */
	public synchronized void validateClaimSubmittedToastMessage() {
		try {

			WebActionUtil.waitForElement(claimSubmitedToastMessage, "Claim submitted success toast message");
			Assert.assertEquals(claimSubmitedToastMessage.getText(), "Claim submitted successfully.");
			WebActionUtil.info("Claim submitted success toast message is displayed");
			WebActionUtil.pass("Claim submitted success toast message is displayed");
		} catch (Exception e) {
			WebActionUtil.fail("Claim submitted success toast message is not displayed");
			WebActionUtil.error("Claim submitted success toast message is not displayed");
			Assert.fail("Claim submitted success toast message is not displayed");
		}
	}

	/**
	 * Description Method clicks on Upload Document Link
	 * 
	 * @author Vikas K C.
	 * @param imagePath
	 * @param uploadedDocumentType
	 */
	public synchronized void clkUploadorDragandDropYourBillsHereLink(String imagePath, String uploadedDocumentType) {
		try {
			WebActionUtil.waitForElement(lnkUpload, "Upload Document Link");
			WebActionUtil.clickOnElement(lnkUpload, "Upload Document Link", "Unable to click on Upload Document Link");
			WebActionUtil.upload(imagePath);
			String filename = txtSampleFileFormat.getText();
			WebActionUtil.validatetext(filename, txtSampleFileFormat, "File name", filename + " file is uploaded",
					filename + " file is not uploaded", "blue");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Upload Document Link");
			Assert.fail("Unable to click on Upload Document Link");
		}
	}

	/**
	 * Description Method to validate uploaded document is displayed
	 * 
	 * @author vikas.kc
	 * @param uploadedDocumentType
	 */
	public synchronized void validateUploadedDocument(String uploadedDocumentType) {
		try {
			int count = 0;
			String uploadedDocumentLowerCase = uploadedDocumentType.toLowerCase();
			for (WebElement document : lnkDocument) {

				if (document.getText().toLowerCase().endsWith(uploadedDocumentLowerCase)) {
					count++;
				}
			}
			if (count > 0) {
				WebActionUtil.info("Uploaded document is displayed");
				WebActionUtil.validationinfo("Uploaded document is displayed", "blue");
			} else {
				WebActionUtil.error("Uploaded document is not displayed");
				WebActionUtil.fail("Uploaded document is not displayed");

			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Uploaded document");
			Assert.fail("Unable to validate Uploaded document");
		}
	}

	/**
	 * Description: Method to validate claim details
	 * 
	 * @author vikas.kc
	 * @param claimNo
	 * @param claimedAmount
	 */
	public synchronized void validateClaim(String claimNo, String claimedAmount) {
		try {
			Assert.assertEquals(txtClaimNO.getText(), claimNo);
			Assert.assertTrue(txtClaimAmount.getText().contains(claimedAmount));
			WebActionUtil.info("Claim is verified");
			WebActionUtil.validationinfo("Claim is verified", "blue");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to validate the claim");
			WebActionUtil.error("Unable to validate  the claim");
			Assert.fail("Unable to validate the claim");
		}
	}

	/**
	 * Description: Method to edit the Common category
	 * 
	 * @author vikas.kc
	 * @param amount
	 */
	public synchronized void editCommonCategory(String amount) {
		try {
			setAmountTextfield(amount);
			WebActionUtil.validationinfo("Updated details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the details");
			Assert.fail("Unable to edit the details");
		}
	}

	/**
	 * Description: Method to edit the Cab breakdown expenses
	 * 
	 * @author vikas.kc
	 * @param transportId
	 */
	public synchronized void editCabBreakDownExpenses(String transportId) {
		try {
			
			try
			{
			WebActionUtil.waitForElement(sddTransportId, "select Transport Id");
			Select selecvalue = new Select(sddTransportId);
			selecvalue.selectByVisibleText(transportId);
			System.out.println("Select the value properly");
			//WebActionUtil.selectByText(sddTransportId, transportId);
			WebActionUtil.validationinfo("Updated the Cab Break Down Expenses details", "green");
			}
			catch(Exception e)
			{
				WebActionUtil.poll(5000);
				System.out.println("handle");
			}
		} 
		catch (Exception e) {
			WebActionUtil.poll(5000);
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the Cab Break Down Expenses details");
			Assert.fail("Unable to edit the Cab Break Down Expenses details");
		}
	}

	/**
	 * Description: Method to edit the COVID 19 Evacuation Advance
	 * 
	 * @author vikas.kc
	 * @param amount
	 * @param expectedDate
	 */
	public synchronized void editCovid19EvacuationAdvance(String amount, String expactedDate) {
		try {
			setAmountTextfield(amount);

			WebActionUtil.waitForElement(tbExpectedDate, "Expected Date Textbox");
			WebActionUtil.selectCalendarRangeDates("covidAdvanceTravelDate", expactedDate);
			String expectedfromdate = WebActionUtil.getTextUsingJS("covidAdvanceTravelDate");
			WebActionUtil.validateEnteredValue(expectedfromdate, expactedDate, "Expected Date Textbox",
					expactedDate + " entered in Expense Date Textbox",
					expactedDate + " not entered in Expected Date Textbox", "blue");

			WebActionUtil.validationinfo("Updated the Covid 19 Evacuation Advance details", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the Covid 19 Evacuation Advance details");
			Assert.fail("Unable to edit the Covid 19 Evacuation Advance details");
		}
	}

	/**
	 * Description: Method to edit the visa Expenses
	 * 
	 * @author vikas.kc
	 * @param amount
	 * @param country
	 * @param visaType
	 * @param visaExpenseType
	 * 
	 */
	public synchronized void editVisaExpenses(String amount, String country, String visaType, String visaExpenseType) {
		try {
			setAmountTextfield(amount);

			WebActionUtil.waitForElement(sddCountry, "Select Country");
			WebActionUtil.selectByText(sddCountry, country);

			WebActionUtil.waitForElement(sddVisaType, "Select Visa Type");
			WebActionUtil.selectByText(sddVisaType, visaType);

			WebActionUtil.waitForElement(sddVisaExpenseType, "Select Visa Expense Type");
			WebActionUtil.selectByText(sddVisaExpenseType, visaExpenseType);

			WebActionUtil.validationinfo("Updated the Visa Expenses details", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the Visa Expenses details");
			Assert.fail("Unable to edit the Visa Expenses details");
		}
	}

	/**
	 * Description: Method to edit the HE Advance Other
	 * 
	 * @author vikas.kc
	 * @param amount
	 */
	public synchronized void editHEAdvanceOther(String amount) {
		try {
			setAmountTextfield(amount);

			WebActionUtil.validationinfo("Updated the HE Advance Other details", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the HE Advance Other details");
			Assert.fail("Unable to edit the HE Advance Other details");
		}
	}

	/**
	 * Description: Method to edit the Specialized Training Expense
	 * 
	 * @author vikas.kc
	 * @param courseStartDate
	 * 
	 */
	static int count = 1;

	public synchronized void editSpecializedTrainingExpense(String courseStartDate) {
		try {
			String[] splitcourseStartDate = courseStartDate.split(" ");
			int intcourseStartDate = Integer.parseInt(splitcourseStartDate[0]) + count++;

			String strcourseStartDate = String.valueOf(intcourseStartDate);
			String newcourseStartDate = strcourseStartDate + " " + splitcourseStartDate[1];
			WebActionUtil.waitForElement(tbCourseStartDate, "From Date text box");
			WebActionUtil.selectCalendarRangeDates("stCourseFromDate", newcourseStartDate);
			String expectedfromdate = WebActionUtil.getTextUsingJS("stCourseFromDate");
			WebActionUtil.validateEnteredValue(expectedfromdate, newcourseStartDate, "From Date text box",
					newcourseStartDate + " entered into From Date Textbox",
					newcourseStartDate + " not entered into Ffrom Date Textbox", "blue");

			WebActionUtil.validationinfo("Updated the Specialized Training Expense details", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the Specialized Training Expense details");
			Assert.fail("Unable to edit the Specialized Training Expense details");
		}
	}

	/**
	 * Description: Method to edit the BroadbandExppenses
	 * 
	 * @author vikas.kc
	 * @param amount
	 * 
	 */
	public synchronized void editBroadbandExpense(String amount) {
		try {
			try {
				if (spinner.isDisplayed()) {
					new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(spinner));
				}
			} catch (Exception e) {
				WebActionUtil.error("Spinner is still visible");
				WebActionUtil.fail("Spinner is still visible");
				Assert.fail("Spinner is still visible");
			}
			setAmountTextfield(amount);
			WebActionUtil.validationinfo("Updated the Broadband Expense details", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the Broadband Expense details");
			Assert.fail("Unable to edit the Broadband Expense details");
		}
	}

	/**
	 * Description: Method to edit the Cell Phone expenses
	 * 
	 * @author vikas.kc
	 * @param amount
	 */
	public synchronized void editCellPhoneExpense(String amount) {
		try {
			try {
				if (spinner.isDisplayed()) {
					new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(spinner));
				}
			} catch (Exception e) {
				WebActionUtil.error("Spinner is still visible");
				WebActionUtil.fail("Spinner is still visible");
				Assert.fail("Spinner is still visible");
			}
			WebActionUtil.waitForElement(tbAmount, "Amount Textbox");
			WebActionUtil.clickOnElement(tbAmount, "Amount Textbox", "Unable to click Amount Textbox");
			WebActionUtil.clearText(tbAmount, "Amount Textbox");
			WebActionUtil.typeText(tbAmount, amount, "Amount Textbox");
			WebActionUtil.extentinfo(amount + " is entered in Amount Textbox");
			WebActionUtil.validationinfo("Updated the Cell Phone Expenses details", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the Cell Phone Expenses details");
			Assert.fail("Unable to edit the Cell Phone Expenses details");
		}
	}

	/**
	 * Description: Method to edit the conveyance
	 * 
	 * @author Suganthini
	 * @param expenseDate
	 */
	public synchronized void editConveyanceExpense(String expenseDate) {
		try {

			WebActionUtil.waitForElement(tbExpenseDate, "Expense Date Textbox");
			WebActionUtil.selectCalendarRangeDates("conveyanceFromDate", expenseDate);
			String expectedfromdate = WebActionUtil.getTextUsingJS("conveyanceFromDate");
			WebActionUtil.validateEnteredValue(expectedfromdate, expenseDate, "Expense Date Textbox",
					expenseDate + " entered in Expense Date Textbox",
					expenseDate + " not entered in Expense Date Textbox", "blue");

			WebActionUtil.validationinfo("Updated the Conveyance Expense details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the Conveyance Expense details");
			Assert.fail("Unable to edit the Conveyance Expense details");
		}
	}

	/**
	 * Description: This Method implements to edit category details
	 * 
	 * @author Suganthini
	 * @param category
	 * @param data
	 */
	public synchronized void editCategoryDetails(Category category, String... data) {
		switch (category) {
		case BOOKS_PERIODICALS:
		case ONLINE_EXTERNAL_TRAININGS:
		case BOOK_PERIODICALS_GWS:
		case BUISNESS_ENTERTAINEMENT:
		case BUISNESS_GIFT:
		case CATALYST_ADVANCE:
		case CATALYST_EXPENSES:
		case CEO_CHAMPION_CLUB:
		case COURIER_CHARGES:
		case COVID_TEST:
		case COVID_19:
		case EFC_GREY_CELLS_COUNCIL:
		case EFC_SPORTS_COUNCIL:
		case EFC_AMBASSADOR_COUNCIL:
		case EFC_COMMUNITY_SERVICES_COUNCIL:
		case EFC_TALENT_COUNCIL:
		case EFC_WELLNESS_COUNCIL:
		case EFC_WOMAN_FIRST_COUNCIL:
		case HRA_ADVANCE:
		case LAPTOP_ASSET_PURCHASE:
		case LEGAL_EXPENSES:
		case MISCELLANEOUS_EXPENSES:
		case OTHER_ADVANCE:
		case OTHER_EXPENSE_RELATED_ADVANCE:
		case PG_ACCOMODATION_RETURN_TO_OFFICE:
		case POST_AND_TELEGRAM:
		case PRINTING_STATIONERY_OFF_SUPPLIES:
		case RELOCATION_EXPENSE_ACCOMODATION_CHARGE:
		case RELOCATION_EXPENSES:
		case REP_AND_MAINT_BLDG:
		case REPAIR_MAINT_OTHERS:
		case SALARY_MEDICAL_ADVANCE:
		case STAFF_RECRUITMENT:
		case STAFF_WELFARE:
		case TRANSFER_CLAIM:
		case VISA_EXPENSES_OLD:
		case ZERO_EXPENSES: {
			editCommonCategory(data[0]);
			break;
		}
		case BROADBAND_EXPENSES: {
			editBroadbandExpense(data[0]);
			break;
		}
		case COVID19_EVACUATION_ADVANCE: {
			editCovid19EvacuationAdvance(data[0], data[1]);
			break;
		}
		case HE_ADVANCE_OTHER: {
			editHEAdvanceOther(data[0]);
			break;
		}
		case SPECIALIZED_TRAINING_EXPENSE: {
			editSpecializedTrainingExpense(data[0]);
			break;
		}
		case VISA_EXPENSES: {
			editVisaExpenses(data[0], data[1], data[2], data[3]);
			break;
		}
		case CAB_BREAKDOWN_EXP: {
			editCabBreakDownExpenses(data[0]);
			break;
		}
		case CELL_PHONE: {
			editCellPhoneExpense(data[0]);
			break;
		}
		case CONVEYANCE: {
			editConveyanceExpense(data[0]);
			break;
		}
		case TECHNICAL_CERTIFICATION: {
			editTechnicalCertification(data[0], data[1]);
		}
		}
	}

	/**
	 * Description: Method to edit the COVID 19 Evacuation Advance
	 * 
	 * @author vikas.kc
	 * @param amount
	 * @param expectedCertificationDate
	 */
	public synchronized void editTechnicalCertification(String amount, String expectedCertificationDate) {
		try {

			setAmountTextfield(amount);

			WebActionUtil.waitForElement(tbCertificationDate, "Certification Date Textbox");
			WebActionUtil.selectCalendarRangeDates("technicalCertifyFromDate", expectedCertificationDate);
			String expectedfromdate = WebActionUtil.getTextUsingJS("technicalCertifyFromDate");
			WebActionUtil.validateEnteredValue(expectedfromdate, expectedCertificationDate,
					"Certification Date Textbox", expectedCertificationDate + " entered in Certification date Textbox",
					expectedCertificationDate + " not entered in Certification date Textbox", "blue");
			WebActionUtil.validationinfo("Updated the Technical Certification details", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to edit the Technical Certification details");
			Assert.fail("Unable to edit the Technical Certification details");
		}
	}

	/**
	 * Description: Method to validate refer back text
	 * 
	 * @author vikas.kc
	 * @param referredBackMessage
	 */
	public synchronized void validateReferBackText(String referredBackMessage) {
		try {
			driver.navigate().refresh();
			try {
				boolean value = ((txtReferBack.getText().toLowerCase().contains(referredBackMessage.toLowerCase()))
						|| (txtReferBack.getText().toLowerCase()
								.equals("referred back by es for additional information"))
						|| (txtReferBack.getText().toLowerCase().equals("Referred Back".toLowerCase()))
						|| (txtReferBack.getText().toLowerCase().equals("Referred by BU HR".toLowerCase())));
				Assert.assertTrue(value);
				WebActionUtil.info(txtReferBack.getText() + " status is displayed");
				WebActionUtil.pass(txtReferBack.getText() + " status is displayed");
				validateReferredBackColor();
			} catch (Exception e) {
				driver.navigate().refresh();
				WebActionUtil.waitForInvisibilityOfElement(spinner, "Spinner", (long) 10);
				WebActionUtil.waitForElement(txtReferBack, "Refer Back text");
				try {
					txtReferBack.isDisplayed();
				} catch (Exception r) {
					WebActionUtil.fail("View Cash Claim Page not loaded");
					WebActionUtil.error("View Cash Claim Page not loaded");
					Assert.fail("View Cash Claim page not loaded");
				}
				boolean value = ((txtReferBack.getText().toLowerCase().contains(referredBackMessage.toLowerCase()))
						|| (txtReferBack.getText().toLowerCase()
								.equals("referred back by es for additional information"))
						|| (txtReferBack.getText().toLowerCase().equals("Referred Back".toLowerCase()))
						|| (txtReferBack.getText().toLowerCase().equals("Referred by BU HR".toLowerCase())));
				Assert.assertTrue(value);
				WebActionUtil.info(txtReferBack.getText() + " status is displayed");
				WebActionUtil.pass(txtReferBack.getText() + " status is displayed");
				validateReferredBackColor();
			}

		} catch (Exception e) {
			WebActionUtil.fail(txtReferBack.getText() + " status is not displayed");
			WebActionUtil.error(txtReferBack.getText() + " status is not displayed");
			Assert.fail(txtReferBack.getText() + " status is not displayed");
		}
	}
	
	
	/**
	 * Description: This Method implements to Enter Description
	 * 
	 * @author Suganthini
	 * @param description
	 */
	private synchronized void setDescription(String description) {

		try {

			WebActionUtil.waitForElement(tbDescription, "Description Text Area");
			WebActionUtil.typeText(tbDescription, description, "Description Text Area");
			WebActionUtil.extentinfo(description + " is entered in Description Text Area");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to type " + description + " into Description Text are field");
			Assert.fail("Unable to type " + description + " into Description Text are field");
		}
	}
}
