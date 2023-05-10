package com.hcl.gcs.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
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
 * Description: This class implements the methods for New cash claim Page.
 * 
 * @author Aatish,Suganthini
 * 
 **/
public class NewCashClaim_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public NewCashClaim_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Project radio button */
	@FindBy(xpath = "//li[@class='active']")
	private WebElement rbProject;

	/* YourCastCenter radio button */
	@FindBy(id = "ycostli")
	private WebElement rbYourCostCenter;

	/* AnotherCastCenter radio button */
	@FindBy(id = "acostli")
	private WebElement rbAnotherCostCenter;

	/* Project Code/Name drop down */
	@FindBy(id = "proExchange")
	private WebElement sddProjectCode;

	/* Cell phone and BroadBand EntitleMent link */
	@FindBy(id = "cellphoneentilement")
	private WebElement lnkCellPhoneAndBroadBandEntitleMent;

	/*Advance Claim Radio Button */
	@FindBy(xpath = "//li[@id='fixed-box_1']/div/preceding-sibling::input/parent::li")
	private WebElement rbAdvanceClaim;

	/* Add Expenses Button */
	@FindBy(xpath = "//button[@class='btn primary-button add-button']")
	private WebElement btnAddExpenses;

	/* Update Expenses Button */
	@FindBy(xpath = "//button[@class='btn primary-button update-button']")
	private WebElement btnUpdateExpenses;

	/* Cancel Button  */
	@FindBy(xpath = "//div[@id='ADDexpenceButton']/descendant::button[@id='btncancel']")
	private WebElement btnCancel;

	/* Created Expenses name in Expenses details */
	@FindBy(xpath = "//div[@class='details-list ng-binding ng-scope']")
	private WebElement txtExpenseDetails;

	/* Edit icon */
	@FindBy(xpath = "//h2[text()='Expense Details']/parent::div/following-sibling::div/descendant::span[@class='gcs-edit action-icon']")
	private WebElement icnEdit;

	/* Delete icon */
	@FindBy(xpath = "//h2[text()='Expense Details']/parent::div/following-sibling::div/descendant::span[@class='gcs-delete action-icon']")
	private WebElement icnDelete;

	/* Drag and Drop Link */
	@FindBy(xpath = "//div[@id='dropDrag']/descendant::div[@class='dz-default dz-message']/span")
	private WebElement lnkDragDrop;

	/* Upload bill */
	@FindBy(xpath = "//div[@id='dropDragCell']/descendant::div[@class='dz-default dz-message']/span")
	private WebElement lnkUploadBill;

	/* text Sample file format */
	@FindBy(xpath = "//a[@class='colorBlue']")
	private WebElement txtSampleFileFormat;

	/* Reason Text area */
	@FindBy(id = "textarea1")
	private WebElement tbReason;

	/* Raised Declared check box */
	@FindBy(xpath = "//label[contains(text(),'I declare that I have not raised')]/preceding-sibling::input")
	private WebElement cbDeclaredRaised;

	/* Verified Declared check box */
	@FindBy(xpath = "//label[contains(text(),'I declare that I have verified')]/preceding-sibling::input")
	private WebElement cbDeclaredVerified;

	/* Read and understood Declared check box */
	@FindBy(xpath = "//label[contains(text(),'I declare that I have read and understood')]")
	private WebElement cbDeclaredReadAndUnderstood;

	/* Affirm check box */
	@FindBy(xpath = "//label[contains(text(),'I affirm')]")
	private WebElement cbAffirm;

	/* Submit button */
	@FindBy(id = "submit")
	private WebElement btnSubmit;

	/* Save and Draft button */
	@FindBy(id = "saveDraft")
	private WebElement btnSaveDraft;

	/* Category select class */
	@FindBy(id = "expenseCategory")
	private WebElement sddCategroy;

	// common category

	/* amount text box */
	@FindBy(id = "ExpAmount")
	private WebElement tbAmount;

	/* Description text box */
	@FindBy(name = "remarks")
	private WebElement tbDescription;

	// Specialized Training Expense

	/* University Name text box */
	@FindBy(id = "stUniversityName")
	private WebElement tbUniversityName;

	/* Course Name text box */
	@FindBy(id = "stCourseName")
	private WebElement tbCourseName;

	/* Mode of training text box */
	@FindBy(id = "stModeOfTraining")
	private WebElement tbModeOfTraining;

	/* Course started date text box */
	@FindBy(id = "stCourseFromDate")
	private WebElement tbCourseStartDate;

	/* Course end date text box */
	@FindBy(id = "stCourseToDate")
	private WebElement tbCourseToDate;

	// Visa Expenses

	/* Country drop down */
	@FindBy(id = "counrtyVisaList")
	private WebElement sddCountry;

	/* Visa type drop down */
	@FindBy(id = "visaType")
	private WebElement sddVisaType;

	/* Visa Expense type drop down */
	@FindBy(id = "visaExpenseType")
	private WebElement sddVisaExpenseType;

	/* iAura case number text box */
	@FindBy(id = "iAura")
	private WebElement tbiAuraCaseNumber;

	// HE Advance other

	/* Higher education University Name dropdown */
	@FindBy(id = "higheducationuniversity")
	private WebElement sddHEUniversityName;

	/* Higher education Course Name dropdown */
	@FindBy(id = "higheducationcoursename")
	private WebElement sddHECourseName;

	/* Semester dropdown */
	@FindBy(id = "higheducationsemester")
	private WebElement sddsemester;

	/* Enrollment no */
	@FindBy(id = "higheducationenroll")
	private WebElement tbEnrollment;

	/* Higher education From date */
	@FindBy(id = "higheducationFromDate")
	private WebElement tbHEFromDate;

	/* Higher education To date */
	@FindBy(id = "higheducationtoDate")
	private WebElement tbHEToDate;

	// BroadbandExpenses

	/* Mobile number textbox */
	@FindBy(id = "BroandBandMobile")
	private WebElement tbMobileNo;

	/* Bill form date textbox */
	@FindBy(id = "Broadbandfromdate")
	private WebElement tbBillFromDate;

	/* Bill to date textbox */
	@FindBy(id = "Broadbandtodate")
	private WebElement tbBillToDate;

	// Cab breakdown Expenses

	/* transport id dropdown */
	@FindBy(id = "cabTransportID")
	private WebElement sddTransportId;

	// Cell phone expenses

	/* Mobile number textbox */
	@FindBy(id = "cellPhoneMobile")
	private WebElement tbCellMobileNo;

	/* cell phone Bill form date textbox */
	@FindBy(id = "cellFromDate")
	private WebElement tbcellBillFromDate;

	/* cell phone Bill to date textbox */
	@FindBy(id = "cellToDate")
	private WebElement tbcellBillToDate;

	/* cell plan dropdown */
	@FindBy(id = "Cellplan")
	private WebElement sddCellPlan;

	/* International amount text box */
	@FindBy(id = "internationalAmount")
	private WebElement tbInternationalCharge;

	/* International charge check box */
	@FindBy(xpath = "//label[contains(text(),'Do you want to claim international calling charges')]/preceding-sibling::input")
	private WebElement chkInternationalCharge;

	// conveyance

	/* Transport mode drop down */
	@FindBy(id = "conveyance")
	private WebElement sddTransportMode;

	/* Distance text box */
	@FindBy(id = "conveyanceDistance")
	private WebElement tbDistance;

	/* Expense Date textbox */
	@FindBy(id = "conveyanceFromDate")
	private WebElement tbExpenseDate;

	/* Conveyance remark textbox */
	@FindBy(id = "conveyanceRemarksText")
	private WebElement tbConveyanceRemark;

	// COVID 19 Evacuation advance

	/* Expected Date of travel textbox */
	@FindBy(id = "covidAdvanceTravelDate")
	private WebElement tbExpectedDate;

	/* Certification Date */
	@FindBy(id = "technicalCertifyFromDate")
	private WebElement tbCertificationDate;

	/* advance Spinner */
	@FindBy(xpath = "//div[@id='Advance_go_spiner']")
	private WebElement advanceSpinner;

	/* Yes button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnYes;

	/* Yes button */
	@FindBy(xpath = "//button[text()='NO']")
	private WebElement btnNo;

	/* Claim submit Toast Message */
	@FindBy(xpath = "//span[text()='Claim submitted successfully.']")
	private WebElement ClaimSubmitedToastMessage;

	/* Check box */
	@FindBy(xpath = "//div[@class='checkbox declareText']/descendant::input")
	private List<WebElement> lstCheckBoxs;
	
	/* Claim submit Toast Message */
	@FindBy(xpath = "//a[@id='#defcost' and text()='Your Cost Center']")
	private WebElement yourCostCenterBtn;
	
	/* Claim submit Toast Message */
	@FindBy(xpath = "//a[@id='#defcost' and text()='Your Cost Center']")
	private WebElement selectCurency;
	
	/* Bill form date textbox */
	@FindBy(id = "expensedate")
	private WebElement expensedate;
	
	/* Start Location textbox */
	@FindBy(id = "txtstrt")
	private WebElement stateLocation;
	
	/* End Location textbox */
	@FindBy(id = "txtend")
	private WebElement endLocation;
	
	/* Miles/km textbox */
	@FindBy(id = "txtmiles")
	private WebElement txtmiles;
	
	/* Miles/km textbox */
	@FindBy(xpath = "//*[@id='selectedIBSCarType']")
	private WebElement allowanceDdn;
	
	
	@FindBy(xpath = "//*[@id='No']")
	private WebElement saveClaimRadioBtn;
	
	
	@FindBy(xpath = "//*[@id='ccNo']")
	private WebElement customeRadioBtn;
	
	@FindBy(xpath = "//*[@id='ADDexpenceButton']/button")
	private WebElement ADDexpenceButton;
	
	@FindBy(xpath = "//*[@id='txtAmount']")
	private WebElement amountTxt;
	
	@FindBy(xpath = "//*[@id='chkDeclaration']")
	private WebElement chkDeclaration;


	/**
	 * Description: Method to click on check box
	 * 
	 * @author Vikas
	 * @param checkBoxNames
	 */
	public synchronized void clkCheckBoxs(String[] checkBoxNames) {
		int i = 0;

		for (WebElement weCheckBox : lstCheckBoxs) {
			WebActionUtil.waitForElement(weCheckBox, "Check box");
			WebActionUtil.clickCheckBox(weCheckBox, checkBoxNames[i++] + ": Check box");
		}
	}

	/**
	 * Description:This Method implements to validate advance settlement page
	 * 
	 * @author Suganthini
	 * @param expectedAdvanceSettlementUrl
	 */

	public synchronized void validateAdvanceSettlementPage(String expectedAdvanceSettlementUrl) {

		try {

			String actualAdvanceSettlementUrl = driver.getCurrentUrl();
			if (actualAdvanceSettlementUrl.equals(expectedAdvanceSettlementUrl)) {
				WebActionUtil.info("Advance Settlement Page is displayed, url =" + actualAdvanceSettlementUrl);
				WebActionUtil.validationinfo("Advance Settlement Page is displayed, url =" + actualAdvanceSettlementUrl,
						"blue");
			} else {

				if (!expectedAdvanceSettlementUrl.equals(actualAdvanceSettlementUrl)) {
					WebActionUtil.validationinfo("Url not matching,Expected=" + expectedAdvanceSettlementUrl
							+ ",Actual=" + actualAdvanceSettlementUrl, "red");
					WebActionUtil.error("Url not matching,Expected=" + expectedAdvanceSettlementUrl + ",Actual="
							+ actualAdvanceSettlementUrl);
				}

			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.info("Unable to validate Advance Settlement Page");
			Assert.fail("Unable to validate Advance Settlement Page");
		}
	}

	/**
	 * Description: This Method implements to select ProjectCode
	 * 
	 * @author Suganthini
	 * @param projectCode
	 */
	public synchronized void selectProjectCode(String projectCode) {
		try {
			WebActionUtil.waitForElement(sddProjectCode, "Select expenses");
			WebActionUtil.selectByText(sddProjectCode, projectCode);

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Project Code");
			WebActionUtil.error("Unable to click on Project Code");
			Assert.fail("Unable to click on Project Code");
		}

	}

	/**
	 * Description:This Method implements to click on 'Project' Radio Button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkProjectRb() {
		try {

			WebActionUtil.waitForElement(rbProject, "Project Radio Button");
			WebActionUtil.clickOnElement(rbProject, "Project Radio Button ", "Unable to click on 'Project' Radio Button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Project Radio Button");
			WebActionUtil.error("Unable to click on Project Radio Button");
			Assert.fail("Unable to click on Project Radio Button");
		}

	}

	/**
	 * Description:This Method implements to click on 'Your Cost Center' Radio Button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkYourCostCenterRb() {
		try {

			WebActionUtil.waitForElement(rbYourCostCenter, "Your Cost Center Radio Button");
			WebActionUtil.clickOnElement(rbYourCostCenter, "Your Cost Center Radio Button ",
					"Unable to click on 'Your Cost Center' Radio Button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Your Cost Center Radio Button");
			WebActionUtil.error("Unable to click on Your Cost Center Radio Button");
			Assert.fail("Unable to click on Your Cost Center Radio Button");
		}

	}

	/**
	 * Description:This Method implements to click on 'Another Cast Center'
	 * Radio Button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkAnotherCostCenterRb() {
		try {

			WebActionUtil.waitForElement(rbAnotherCostCenter, "Another Cost Center Radio Button");
			WebActionUtil.clickOnElement(rbAnotherCostCenter, "Another Cost Center Radio Button ",
					"Unable to click on 'Another Cost Center' Radio Button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Another Cost Center Radio Button");
			WebActionUtil.error("Unable to click on Another Cost Center Radio Button");
			Assert.fail("Unable to click on 'Another Cost Center' Radio Button");
		}

	}

	/**
	 * Description:This Method implements to click on 'Cell Phone and BroadBand
	 * EntitleMent' Link
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkCellPhoneAndBroadBandLnk() {
		try {

			WebActionUtil.waitForElement(lnkCellPhoneAndBroadBandEntitleMent,
					"Cell Phone and BroadBand EntitleMent Link");
			WebActionUtil.clickOnElement(lnkCellPhoneAndBroadBandEntitleMent,
					"Cell Phone and BroadBand EntitleMent Link",
					"Unable to click on 'Cell Phone and BroadBand EntitleMent' Link");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Cell Phone and BroadBand EntitleMent Link");
			WebActionUtil.error("Unable to click on Cell Phone and BroadBand EntitleMent Link");
			Assert.fail("Unable to click on Cell Phone and BroadBand EntitleMent Link");
		}

	}

	/**
	 * Description:This Method implements to click on Advance Claim Radio Button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkAdvanceClaim() {
		try {

			WebActionUtil.waitForElement(rbAdvanceClaim, "Advance Claim Radio Button");
			WebActionUtil.clickOnElement(rbAdvanceClaim, "Advance Claim Radio Button",
					"Unable to click on Advance claim Radio Button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Advance Claim Radio Button");
			WebActionUtil.error("Unable to click on Advance Claim Radio Button");
			Assert.fail("Unable to click on Advance Claim Radio Button");
		}

	}

	/**
	 * Description:This Method implements to click on AddExpense button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkAddExpense() {
		try {
			WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long) 60);
			WebActionUtil.waitForElement(btnAddExpenses, "Add Expenses Button");
			WebActionUtil.clickOnElement(btnAddExpenses, "Add Expenses Button",
					"Unable to click on Add Expenses Button");
			WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long) 60);
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Add Expenses Button");
			WebActionUtil.error("Unable to click on Add Expenses Button");
			Assert.fail("Unable to click on Add Expenses Button");
		}

	}

	/**
	 * Description:This Method implements to click on AddExpense Button and Upload
	 * Bill
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkAddExpenseAndUploadBill(String imagePath) {
		List<WebElement> lstBeforeExpense = driver
				.findElements(By.xpath("//div[@id='ADDexpenceButton']/preceding::div[@id='dropDragCell']"));
		List<WebElement> lstAfterExpense = driver
				.findElements(By.xpath("//div[@id='ADDexpenceButton']/following::div[@id='dropDrag']"));
		try {
			WebActionUtil.poll(2000);
			if (lstAfterExpense.size() == 1 && lstAfterExpense.get(0).isDisplayed()) {
				clkAddExpense();
				clkUploadBillAfterExpenses(imagePath);
			} else if (lstBeforeExpense.size() == 1 && lstBeforeExpense.get(0).isDisplayed()) {
				clkUploadBillBeforeExpenses(imagePath);
				clkAddExpense();

			}
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Add Expenses button and Upload bill");
			WebActionUtil.error("Unable to click on Add Expenses button and Upload bill");
			Assert.fail("Unable to click on Add Expenses button and Upload bill");
		}
	}

	/**
	 * Description:This Method implements to click on Update Expenses button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkUpdateExpense() {
		try {
			WebActionUtil.waitForElement(btnUpdateExpenses, "Update Expenses Button");
			WebActionUtil.clickOnElement(btnUpdateExpenses, "Update Expenses Button",
					"Unable to click on Update Expenses Button");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Update Expenses Button");
			WebActionUtil.error("Unable to click on Update Expenses Button");
			Assert.fail("Unable to click on Update Expenses Button");
		}

	}

	/**
	 * Description:This Method implements to click on Cancel button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkCancel() {
		try {
			WebActionUtil.waitForElement(btnCancel, "Cancel Button");
			WebActionUtil.clickOnElement(btnCancel, "Cancel Button", "Unable to click on Cancel Button");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Cancel Button");
			WebActionUtil.error("Unable to click on Cancel Button");
			Assert.fail("Unable to click on Cancel Button");
		}

	}

	/**
	 * Description:This Method implements to Click on Created Expense Details Text.
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkExpenseName() {
		try {
			WebActionUtil.waitForElement(txtExpenseDetails, "Expense Details Text");
			WebActionUtil.clickOnElement(txtExpenseDetails, "Expense Details Text",
					"Unable to click on Expense Details Text ");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Expense Details Text");
			WebActionUtil.error("Unable to click on Expense Details Text");
			Assert.fail("Unable to click on Expense Details Text");
		}

	}

	/**
	 * Description:This Method implements to Click on Edit icon
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkEditIcn() {
		try {
			WebActionUtil.waitForElement(txtExpenseDetails, "Expense Details Text");
			WebActionUtil.clickOnElement(txtExpenseDetails, "Expense Details Text",
					"Unable to click on Expense Details Text ");

			WebActionUtil.waitForElement(icnEdit, "Edit Icon");
			WebActionUtil.clickOnElement(icnEdit, "Edit Icon", "Unable to click on Edit Icon ");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Edit Icon");
			WebActionUtil.error("Unable to click on Edit Icon");
			Assert.fail("Unable to click on Edit Icon");
		}

	}

	/**
	 * Description:This Method implements to Click on Delete icon
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkDeleteIcn() {
		try {
			WebActionUtil.waitForElement(txtExpenseDetails, "Expense Details Text");
			WebActionUtil.clickOnElement(txtExpenseDetails, "Expense Details Text",
					"Unable to click on Expense Details Text ");

			WebActionUtil.waitForElement(icnDelete, "Delete Icon");
			WebActionUtil.clickOnElement(icnDelete, "Delete Icon", "Unable to click on Delete Icon ");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Delete Icon");
			WebActionUtil.error("Unable to click on Delete Icon");
			Assert.fail("Unable to click on Delete Icon");
		}

	}

	/**
	 * Description: This Method is used to clicks on Drag and Drop to upload your bills here
	 * 
	 * @author Suganthini
	 * @param imagePath
	 */

	private synchronized void clkUploadBillAfterExpenses(String imagePath) {
		try {

			WebActionUtil.waitForElement(lnkDragDrop, "Upload Bill Link");
			WebActionUtil.clickOnElement(lnkDragDrop, "Upload Bill Link", "Unable to click on Upload Bill Link");
			WebActionUtil.upload(imagePath);
		
			
//			try {
//				if(advanceSpinner.isDisplayed() ) {
//					WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long)60);
//				}}catch (Exception e) {
//					WebActionUtil.error(e.getMessage());
//					WebActionUtil.fail("Spinner is still visible");
//					Assert.fail("Spinner is still visible");
//				}
//			String filename = txtSampleFileFormat.getText();
//			WebActionUtil.validatetext(filename, txtSampleFileFormat, "File name", filename + " file is uploaded",
//					filename + " file is not uploaded", "blue");
//
//			try {
//				if(advanceSpinner.isDisplayed() ) {
//					WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long)60);
//				}}catch (Exception e) {
//					WebActionUtil.error(e.getMessage());
//					WebActionUtil.fail("Spinner is still visible");
//					Assert.fail("Spinner is still visible");
//				}
//			
//			Robot r = new Robot();
//			r.keyPress(KeyEvent.VK_TAB);
//			r.keyRelease(KeyEvent.VK_TAB);
//
//			r.keyPress(KeyEvent.VK_TAB);
//			r.keyRelease(KeyEvent.VK_TAB);
//
//			r.keyPress(KeyEvent.VK_TAB);
//			r.keyRelease(KeyEvent.VK_TAB);
//
//			r.keyPress(KeyEvent.VK_ENTER);
//			r.keyRelease(KeyEvent.VK_ENTER);
//			
//			try {
//			if(advanceSpinner.isDisplayed() ) {
//				WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long)60);
//			}}catch (Exception e) {
//				WebActionUtil.error(e.getMessage());
//				WebActionUtil.fail("Spinner is still visible");
//				Assert.fail("Spinner is still visible");
			
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Upload Your Bill");
			Assert.fail("Unable to click on Upload your Bill");
		}
	}

	/**
	 * Description: This Method clicks on upload bills here
	 * 
	 * @author Suganthini
	 * @param imagePath
	 */

	private synchronized void clkUploadBillBeforeExpenses(String imagePath) {
		try {

			WebActionUtil.waitForElement(lnkUploadBill, "Upload Bill Link");
			WebActionUtil.clickOnElement(lnkUploadBill, "Upload Bill Link", "Unable to click on Upload Bill Link");
			WebActionUtil.upload(imagePath);

			try {
				if(advanceSpinner.isDisplayed() ) {
					WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long)60);
				}}catch (Exception e) {
					WebActionUtil.error(e.getMessage());
					WebActionUtil.fail("Spinner is still visible");
					Assert.fail("Spinner is still visible");
				}
			String filename = txtSampleFileFormat.getText();
			WebActionUtil.validatetext(filename, txtSampleFileFormat, "File name", filename + " file is uploaded",
					filename + " file is not uploaded", "blue");

			try {
				if(advanceSpinner.isDisplayed() ) {
					WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long)60);
				}}catch (Exception e) {
					WebActionUtil.error(e.getMessage());
					WebActionUtil.fail("Spinner is still visible");
					Assert.fail("Spinner is still visible");
				}
			
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);

			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

			try {
				if(advanceSpinner.isDisplayed() ) {
					WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long)60);
				}}catch (Exception e) {
					WebActionUtil.error(e.getMessage());
					WebActionUtil.fail("Spinner is still visible");
					Assert.fail("Spinner is still visible");
				}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Upload Your Bill");
			Assert.fail("Unable to click on Upload Your Bill");
		}
	}

//	/**
//	 * Description: Method to click on Declared I have Verified check box
//	 * 
//	 * 
//	 * @author Suganthini
//	 *
//	 */
//	public synchronized void clkDeclaredIHaveVerifiedCheckbox() {
//		try {
//			WebActionUtil.waitForElement(cbDeclaredVerified, "Declare I have verified check box");
//			WebActionUtil.clickCheckBox(cbDeclaredVerified, "Declare I have verified check box");
//		} catch (Exception e) {
//			WebActionUtil.error(e.getMessage());
//			WebActionUtil.fail("Unable to click on Declare I have verified check box");
//			Assert.fail("Unable to click on Declare I have verified check box");
//		}
//	}
//
//	/**
//	 * Description: Method to click on Declared Read and Understood check box
//	 * 
//	 * 
//	 * @author Suganthini
//	 *
//	 */
//	public synchronized void clkDeclaredIHaveReadAndUnderstoodCheckBox() {
//		try {
//			WebActionUtil.waitForElement(cbDeclaredReadAndUnderstood, "Declare I have Read and Understood check box");
//			WebActionUtil.clickCheckBox(cbDeclaredReadAndUnderstood, "Declare I have Read and Understood check box");
//		} catch (Exception e) {
//			WebActionUtil.error(e.getMessage());
//			WebActionUtil.fail("Unable to click on Declare I have Read and Understood check box");
//			Assert.fail("Unable to click on Declare I have Read and Understood check box");
//		}
//	}
//
//	/**
//	 * Description: Method to click on Declared I have not raised check box
//	 * 
//	 * 
//	 * @author Suganthini
//	 *
//	 */
//	public synchronized void clkDeclaredIHaveNotRaisedCheckbox() {
//		try {
//			WebActionUtil.waitForElement(cbDeclaredRaised, "Declare I have not raised check box");
//			WebActionUtil.clickCheckBox(cbDeclaredRaised, "Declare I have not raised check box");
//		} catch (Exception e) {
//			WebActionUtil.error(e.getMessage());
//			WebActionUtil.fail("Unable to click on Declare I have not raised check box");
//			Assert.fail("Unable to click on Declare I have not raised check box");
//		}
//	}
//
//	/**
//	 * Description: Method to click on Affirm check box
//	 * 
//	 * 
//	 * @author Suganthini
//	 *
//	 */
//	public synchronized void clkAffirmCheckbox() {
//		try {
//			WebActionUtil.waitForElement(cbAffirm, "Affirm check box");
//			WebActionUtil.clickCheckBox(cbAffirm, "Affirm check box");
//		} catch (Exception e) {
//			WebActionUtil.error(e.getMessage());
//			WebActionUtil.fail("Unable to click on Affirm check box");
//			Assert.fail("Unable to click on Affirm check box");
//		}
//	}
//
	/**
	 * Description:This Method implements to click on Submit button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkSubmitButton() {
		try {
			WebActionUtil.waitForAngularPageToLoad();
			WebActionUtil.waitForElement(btnSubmit, "Submit Button");
			WebActionUtil.clickOnElement(btnSubmit, "Submit Button", "Unable to click on Submit Button");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Submit Button");
			WebActionUtil.error("Unable to click on Submit Button");
			Assert.fail("Unable to click on Submit Button");
		}

	}

	/**
	 * Description:This Method implements to click on Save as Draft button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkSaveAsDraftButton() {
		try {
			WebActionUtil.waitForElement(btnSaveDraft, "SaveAs Draft Button");
			WebActionUtil.clickOnElement(btnSaveDraft, "SaveAs Draft Button",
					"Unable to click on SaveAs Draft Button");

		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on SaveAs Draft Button");
			WebActionUtil.error("Unable to click on SaveAs Draft Button");
			Assert.fail("Unable to click on SaveAs Draft Button");
		}

	}

	/**
	 * Description:This Method implements clicks on Yes button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkOnYesButton() {

		try {
			WebActionUtil.waitForAngularPageToLoad();
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(btnYes, "Yes Button");
//			WebActionUtil.validateisElementDisplayed(btnYes, btnYes,"Yes button", "Yes Button is Displayed", "Yes Button is Not Displayed");	
//			new WebDriverWait(driver,20).until(ExpectedConditions.invisibilityOf(btnYes));
			WebActionUtil.clickOnElement(btnYes, "Yes Button", "Unable to click on Yes Button");
//			WebActionUtil.waitForElement(ClaimSubmitedToastMessage, "Claim Submitted Toast Message");
//			WebActionUtil.validateisElementDisplayed(ClaimSubmitedToastMessage, "Claim Submitted Success Message",
//					"Claim Submitted Success Message is Displayed", "Claim Submitted Success Messagee is not Dispalyed",
//					"green");
//			WebActionUtil.switchToMainTab();

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes Button");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description:This Method implements clicks on Yes button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkOnEntitleMentYesButton() {

		try {

			WebActionUtil.waitForElement(btnYes, "Yes Button");
			WebActionUtil.poll(1000);
//			WebActionUtil.validateisElementDisplayed(btnYes, btnYes,"Yes button", "Yes Button is Displayed", "Yes Button is Not Displayed");	
//			new WebDriverWait(driver,20).until(ExpectedConditions.invisibilityOf(btnYes));
			WebActionUtil.clickOnElement(btnYes, "Yes Button", "Unable to click on Yes Button");

			WebActionUtil.poll(1000);

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes Button");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description: This Method implements clicks on No button
	 * 
	 * @author Suganthini
	 */
	public synchronized void clkOnNoButton() {

		try {
			WebActionUtil.waitForElement(btnNo, "No Button");
			WebActionUtil.clickOnElement(btnNo, "No Button", "Unable to click on No Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on No Button");
			Assert.fail("Unable to click on No Button");
		}
	}

	/**
	 * Description: This Method implements to Select Expenses
	 * 
	 * @author Suganthini
	 * @param categroy
	 */
	public synchronized void selectExpenses(String categroy) {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(sddCategroy, "Select Expenses");
			WebActionUtil.selectByText(sddCategroy, categroy);
			validateSelectedExpenses(categroy);
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Select Expenses");
			WebActionUtil.error("Unable to click on Select Expenses");
			Assert.fail("Unable to click on Select Expenses");
		}

	}

	/**
	 * Description: This Method implements to verify Selected Expenses
	 * 
	 * @author Suganthini
	 */
	private synchronized void validateSelectedExpenses(String categroy) {
		try {
			Select s = new Select(sddCategroy);
			WebElement option = s.getFirstSelectedOption();
			String actualExpenses = option.getText().trim();
			Assert.assertEquals(categroy, actualExpenses);
			WebActionUtil.pass(categroy + " is selected");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to select " + categroy);
			WebActionUtil.error("Unable to select " + categroy);
			Assert.fail("Unable to select " + categroy);
		}

	}

	/**
	 * Description: fill the Common category
	 * 
	 * @author Suganthini
	 * @param amount
	 * @param description
	 * 
	 */

	public synchronized void fillCommonCategory(String amount, String description) {
		try {

//			try {
//				
//					if (spinner.isDisplayed()) {
//						new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(spinner));
//					
//				}}catch(Exception e) {
//					
//				}
			setAmount(amount);
			setDescription(description);
			WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long) 60);
			WebActionUtil.validationinfo("All the Common category details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Common category details");
			Assert.fail("Unable to fill the Common category details");
		}
	}

	/**
	 * Description: fill the Cab breakdown expenses
	 * 
	 * @author Suganthini
	 * @param transportId
	 * @param description
	 * 
	 */

	public synchronized void fillCabBreakDownExpenses(String transportId, String description) {
		try {

			try {

				if (advanceSpinner.isDisplayed()) {
					new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(advanceSpinner));

				}
			} catch (Exception e) {

			}

			WebActionUtil.waitForElement(sddTransportId, "select Transport Id");
			WebActionUtil.selectByText(sddTransportId, transportId);

			setDescription(description);

			WebActionUtil.validationinfo("All the Cab breakdown expenses details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Cab breakdown expenses details");
			Assert.fail("Unable to fill the Cab breakdown expenses details");
		}
	}

	/**
	 * Description: fill the COVID 19 Evacuation Advance
	 * 
	 * @author Suganthini
	 * @param amount
	 * @param expectedDate
	 * 
	 */

	public synchronized void fillCovid19EvacuationAdvance(String amount, String expactedDate) {
		try {

			try {

				if (advanceSpinner.isDisplayed()) {
					new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(advanceSpinner));

				}
			} catch (Exception e) {

			}
			setAmount(amount);

			WebActionUtil.waitForElement(tbExpectedDate, "Expected Date Textbox");
			WebActionUtil.selectCalendarRangeDates("covidAdvanceTravelDate", expactedDate);
			String expectedfromdate = WebActionUtil.getTextUsingJS("covidAdvanceTravelDate");
			WebActionUtil.validateEnteredValue(expectedfromdate, expactedDate, "Expected Date Textbox",
					expactedDate + " entered in Expense date Textbox",
					expactedDate + " not entered in Expected date Textbox", "blue");

			WebActionUtil.validationinfo("All the COVID 19 Evacuation Advance details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the COVID 19 Evacuation Advance details");
			Assert.fail("Unable to fill the COVID 19 Evacuation Advance details");
		}
	}

	/**
	 * Description: fill the Technical Certification Expense.
	 * 
	 * @author Suganthini
	 * @param amount
	 * @param CertificateDate
	 * 
	 */

	public synchronized void fillTheTechnicalCertificationExpense(String amount, String certifiationDate,
			String description) {
		try {

			try {

				if (advanceSpinner.isDisplayed()) {
					new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(advanceSpinner));

				}
			} catch (Exception e) {

			}
			setAmount(amount);

			WebActionUtil.waitForElement(tbCertificationDate, "Certification Date Textbox");
			WebActionUtil.selectCalendarRangeDates("technicalCertifyFromDate", certifiationDate);
			String expectedfromdate = WebActionUtil.getTextUsingJS("technicalCertifyFromDate");
			WebActionUtil.validateEnteredValue(expectedfromdate, certifiationDate, "Expected Date Textbox",
					certifiationDate + " entered in Expense date Textbox",
					certifiationDate + " not entered in Expected date Textbox", "blue");
			setDescription(description);

			WebActionUtil.validationinfo("All the details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Technical Certification Expense details");
			Assert.fail("Unable to fill the Technical Certification Expense details");
		}
	}

	/**
	 * Description: fill the visa Expenses
	 * 
	 * @author Suganthini
	 * @param amount
	 * @param country
	 * @param visaType
	 * @param visaExpensesType
	 * @param iAuraNumber
	 * @param Description
	 * 
	 */

	public synchronized void fillTheVisaExpenses(String amount, String country, String visaType, String visaExpenseType,
			String iAuraNumber, String description) {
		try {

			try {

				if (advanceSpinner.isDisplayed()) {
					new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(advanceSpinner));

				}
			} catch (Exception e) {

			}
			setAmount(amount);

			WebActionUtil.waitForElement(sddCountry, "select country");
			WebActionUtil.selectByText(sddCountry, country);

			WebActionUtil.waitForElement(sddVisaType, "select Visa Type");
			WebActionUtil.selectByText(sddVisaType, visaType);

			WebActionUtil.waitForElement(sddVisaExpenseType, "select Visa Expense Type");
			WebActionUtil.selectByText(sddVisaExpenseType, visaExpenseType);

			WebActionUtil.waitForElement(tbiAuraCaseNumber, "iAuraCase Number is Enter in Textbox");
			WebActionUtil.typeText(tbiAuraCaseNumber, iAuraNumber, "iAuraCase Number is Enter in Textbox");
			WebActionUtil.extentinfo(iAuraNumber + " is entered in iAuraCase number Textbox");

			setDescription(description);

			WebActionUtil.validationinfo("All the Visa Expense details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Visa Expense details");
			Assert.fail("Unable to fill the Visa Expense details");
		}
	}

	/**
	 * Description: fill the HE Advance Other
	 * 
	 * @author Suganthini
	 * @param amount
	 * @param heuniversity
	 * @param hecoursename
	 * @param semester
	 * @param enrollment
	 * @param fromDate
	 * @param toDate
	 * @param description
	 * 
	 * 
	 */

	public synchronized void fillTheHEAdvanceOther(String amount, String heUniversity, String heCoursename,
			String semester, String enrollment, String fromDate, String toDate, String description) {
		try {

			try {

				if (advanceSpinner.isDisplayed()) {
					new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(advanceSpinner));

				}
			} catch (Exception e) {

			}
			setAmount(amount);

			WebActionUtil.waitForElement(sddHEUniversityName, "select HE University Name");
			WebActionUtil.selectByText(sddHEUniversityName, heUniversity);

			WebActionUtil.waitForElement(sddHECourseName, "select HE Course Name");
			WebActionUtil.selectByText(sddHECourseName, heCoursename);

			WebActionUtil.waitForElement(sddsemester, "select Semester");
			WebActionUtil.selectByText(sddsemester, semester);

			WebActionUtil.waitForElement(tbEnrollment, "Enrollment Textbox");
			WebActionUtil.typeText(tbEnrollment, enrollment, "Enrollment Textbox");
			WebActionUtil.extentinfo(enrollment + " is entred in Enrollment Textbox");

			WebActionUtil.waitForElement(tbHEFromDate, "From Date Textbox");
			WebActionUtil.selectCalendarRangeDates("higheducationFromDate", fromDate);
			String expectedFromDate = WebActionUtil.getTextUsingJS("higheducationFromDate");
			WebActionUtil.validateEnteredValue(expectedFromDate, fromDate, "From Date Textbox",
					fromDate + " entered in from date text box", fromDate + " not entered in from date Textbox",
					"blue");

			WebActionUtil.waitForElement(tbHEToDate, "To Date text box");
			WebActionUtil.selectCalendarRangeDates("higheducationtoDate", toDate);
			String expectedToDate = WebActionUtil.getTextUsingJS("higheducationtoDate");
			WebActionUtil.validateEnteredValue(expectedToDate, toDate, "To Date Textbox",
					toDate + " entered in To date Textbox", toDate + " not entered in To date Textbox", "blue");

			setDescription(description);
			WebActionUtil.validationinfo("All the HE Advance Other details are entered ", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the HE Advance Other details");
			Assert.fail("Unable to fill the HE Advance Other details");
		}
	}

	/**
	 * Description: fill the Specialized Training Expense
	 * 
	 * @author Suganthini
	 * @param amount
	 * @param university
	 * @param courseName
	 * @param modeOfTraining
	 * @param fromDate
	 * @param toDate
	 * @param Description
	 * 
	 */

	public synchronized void fillTheSpecializedTrainingExpense(String amount, String university, String courseName,
			String modeOfTraining, String fromDate, String toDate, String description) {
		try {

			try {

				if (advanceSpinner.isDisplayed()) {
					new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(advanceSpinner));

				}
			} catch (Exception e) {

			}
			setAmount(amount);

			WebActionUtil.waitForElement(tbUniversityName, "University Name Textbox");
			WebActionUtil.typeText(tbUniversityName, university, "University Name Textbox");
			WebActionUtil.extentinfo(university + " is entred in University Name Textbox");

			WebActionUtil.waitForElement(tbCourseName, "Course Name Textbox");
			WebActionUtil.typeText(tbCourseName, courseName, "Course Name Textbox");
			WebActionUtil.extentinfo(courseName + " is entred in Course Name Textbox");

			WebActionUtil.waitForElement(tbModeOfTraining, "Mode of training Textbox");
			WebActionUtil.typeText(tbModeOfTraining, modeOfTraining, "Mode of training Textbox");
			WebActionUtil.extentinfo(modeOfTraining + " is entred in Mode of training Textbox");

			WebActionUtil.waitForElement(tbCourseStartDate, "From Date Textbox");
			WebActionUtil.selectCalendarRangeDates("stCourseFromDate", fromDate);
			String expectedFromDate = WebActionUtil.getTextUsingJS("stCourseFromDate");
			WebActionUtil.validateEnteredValue(expectedFromDate, fromDate, "From Date Textbox",
					fromDate + " entered in from date text box", fromDate + " not entered in from date Textbox",
					"blue");

			WebActionUtil.waitForElement(tbCourseToDate, "To Date text box");
			WebActionUtil.selectCalendarRangeDates("stCourseToDate", toDate);
			String expectedToDate = WebActionUtil.getTextUsingJS("stCourseToDate");
			WebActionUtil.validateEnteredValue(expectedToDate, toDate, "To Date Textbox",
					toDate + " entered in To date Textbox", toDate + " not entered in To date Textbox", "blue");

			setDescription(description);

			WebActionUtil.validationinfo("All the Specialized Training Expense details are Entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Specialized Training Expense details");
			Assert.fail("Unable to fill the Specialized Training Expense details");
		}
	}

	/**
	 * Description: fill the BroadbandExppenses
	 * 
	 * @author Suganthini
	 * @param amount
	 * @param mobileno
	 * @param fromDate
	 * @param toDate
	 * @param Description
	 * 
	 */

	public synchronized void fillTheBroadbandExpense(String amount, String mobileNo, String fromDate, String toDate,
			String description) {
		try {

			try {

				if (advanceSpinner.isDisplayed()) {
					new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOf(advanceSpinner));

				}
			} catch (Exception e) {

			}

			setAmount(amount);

			WebActionUtil.waitForElement(tbMobileNo, "Mobile Number Textbox");
			WebActionUtil.clearText(tbMobileNo, "Mobile Number Textbox");
			WebActionUtil.typeText(tbMobileNo, mobileNo, "Mobile Number Textbox");
			WebActionUtil.extentinfo(mobileNo + " is entered in MobileNo Textbox");

			WebActionUtil.waitForElement(tbBillFromDate, "From Date Textbox");
			WebActionUtil.clickOnElement(tbBillFromDate, "From Date Textbox", "unable to enter FromDate");
			WebActionUtil.clearText(tbBillFromDate, "From Date Textbox");
			WebActionUtil.selectCalendarRangeDates("Broadbandfromdate", fromDate);
			String expectedfromdate = WebActionUtil.getTextUsingJS("Broadbandfromdate");
			WebActionUtil.validateEnteredValue(expectedfromdate, fromDate, "From Date Textbox",
					fromDate + " entered in from date Textbox", fromDate + " not entered in from date Textbox", "blue");

			WebActionUtil.waitForElement(tbBillToDate, "To Date Textbox");
			WebActionUtil.clickOnElement(tbBillToDate, "To Date Textbox", "unable to enter ToDate");
			WebActionUtil.clearText(tbBillToDate, "To Date Textbox");
			WebActionUtil.selectCalendarRangeDates("Broadbandtodate", toDate);
			String expectedtodate = WebActionUtil.getTextUsingJS("Broadbandtodate");
			WebActionUtil.validateEnteredValue(expectedtodate, toDate, "To Date Textbox",
					toDate + " entered in To date Textbox", toDate + " not entered in To date Textbox", "blue");

			WebActionUtil.waitForElement(tbDescription, "Description Textbox");
			WebActionUtil.clickOnElement(tbDescription, "Description Textbox", "unable to enter Description ");
			WebActionUtil.clearText(tbDescription, "Description Textbox");
			WebActionUtil.waitForElement(tbDescription, "Description Text Area");
			WebActionUtil.typeText(tbDescription, description, "Description Text Area");
			WebActionUtil.extentinfo(description + " is entered in Description Text Area");

			WebActionUtil.validationinfo("All the Broadband Expenses details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Broadband Expenses details");
			Assert.fail("Unable to fill the Broadband Expenses details");
		}
	}

	/**
	 * Description: fill the Cell Phone expenses
	 * 
	 * @author Suganthini
	 * @param amount
	 * @param mobileno
	 * @param fromDate
	 * @param toDate
	 * @param plan
	 * @param internationalcharge
	 * @param Description
	 * 
	 */

	public synchronized void fillTheCellPhoneExpense(String amount, String mobileNo, String fromDate, String toDate,
			String plan, String description, String internationalCharge) {
		try {

			try {

				if (advanceSpinner.isDisplayed()) {
					new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(advanceSpinner));

				}
			} catch (Exception e) {

			}

			WebActionUtil.waitForElement(tbAmount, "Amount is Enter in Textbox");
			WebActionUtil.clickOnElement(tbAmount, "Amount Textbox", "Unable to enter Amount");
			WebActionUtil.clearText(tbAmount, "Amount Textbox");
			WebActionUtil.typeText(tbAmount, amount, "Amount is Enter in Textbox");
			WebActionUtil.extentinfo(amount + " is entered in Amount Textbox");

			WebActionUtil.waitForElement(tbCellMobileNo, "Mobile Number Textbox");
			WebActionUtil.clickOnElement(tbCellMobileNo, "Mobile Number Textbox", "Unable to enter Mobile Number");
			WebActionUtil.clearText(tbCellMobileNo, "Mobile Number Textbox");
			WebActionUtil.typeText(tbCellMobileNo, mobileNo, "Mobile Number Textbox");
			WebActionUtil.extentinfo(mobileNo + " is entered in Mobile Number Textbox");

			WebActionUtil.waitForElement(tbcellBillFromDate, "From Date Textbox");
			WebActionUtil.clickOnElement(tbcellBillFromDate, "From Date Textbox", "Unable to enter From Date");
			WebActionUtil.clearText(tbcellBillFromDate, "From Date Textbox");
			WebActionUtil.selectCalendarRangeDates("cellFromDate", fromDate);
			String expectedFromDate = WebActionUtil.getTextUsingJS("cellFromDate");
			WebActionUtil.validateEnteredValue(expectedFromDate, fromDate, "From Date Textbox",
					fromDate + " entered in from date Textbox", fromDate + " not entered in from date Textbox", "blue");

			WebActionUtil.waitForElement(tbcellBillToDate, "To Date Textbox");
			WebActionUtil.clickOnElement(tbcellBillToDate, "To Date Textbox", "Unable to enter To Date");
			WebActionUtil.clearText(tbcellBillToDate, "To Date text box");
			WebActionUtil.selectCalendarRangeDates("cellToDate", toDate);
			String expectedToDate = WebActionUtil.getTextUsingJS("cellToDate");
			WebActionUtil.validateEnteredValue(expectedToDate, toDate, "To Date Textbox",
					toDate + " entered in To date Textbox", toDate + " not entered in To date text box", "blue");

			WebActionUtil.waitForElement(sddCellPlan, "plan dropdown");
			WebActionUtil.selectByText(sddCellPlan, plan);

			WebActionUtil.waitForElement(tbDescription, "Description Text Area");
			WebActionUtil.clickOnElement(tbDescription, "Description Textbox", "Unable to enter Description");
			WebActionUtil.clearText(tbDescription, "Description Textbox");
			WebActionUtil.typeText(tbDescription, description, "Description Text Area");
			WebActionUtil.extentinfo(description + " is entered in Description Text Area");

			if (Integer.parseInt(internationalCharge) > 0) {
				WebActionUtil.waitForElement(chkInternationalCharge, "International Charges Checkbox");
				WebActionUtil.clickCheckBoxUsingJS(chkInternationalCharge, "International Charges CheckBox");
				WebActionUtil.waitForElement(tbInternationalCharge, "International charge Textbox");
				WebActionUtil.typeText(tbInternationalCharge, internationalCharge, "International charge Textbox");
				WebActionUtil.extentinfo(internationalCharge + " is entred International charge Textbox");
			}

			WebActionUtil.validationinfo("All the Cell Phone details are entered", "green");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Cell Phone details");
			Assert.fail("Unable to fill the Cell Phone details");
		}
	}

	/**
	 * Description: fill the conveyance
	 * 
	 * @author Suganthini
	 * @param transportMode
	 * @param distance
	 * @param description
	 * @param remark
	 * 
	 */

	public synchronized void fillTheConveyanceExpense(String transportMode, String distance, String expanceDate,
			String description, String amount, String remark) {
		try {

			try {

				if (advanceSpinner.isDisplayed()) {
					new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(advanceSpinner));

				}
			} catch (Exception e) {

			}

			WebActionUtil.waitForElement(sddTransportMode, "Transport Mode Dropdown");
			WebActionUtil.selectByText(sddTransportMode, transportMode);

			if (transportMode.equals("Taxi/Cab") || transportMode.equals("Auto")) {
				setAmount(amount);

			} else if (transportMode.equals("OTHER")) {
				setAmount(amount);
				WebActionUtil.waitForElement(tbConveyanceRemark, "Other Remark Textbox");
				WebActionUtil.typeText(tbConveyanceRemark, remark, "Other Remark Textbox");
				WebActionUtil.extentinfo(remark + " is entred in Other Remark Textbox");

			}
			WebActionUtil.waitForElement(tbDistance, "Distance Textbox");
			WebActionUtil.clearText(tbDistance, "Distance Textbox");
			WebActionUtil.typeText(tbDistance, distance, "Distance Textbox");
			WebActionUtil.extentinfo(distance + " is entred in Distance Textbox");

			WebActionUtil.waitForElement(tbExpenseDate, "Expense Date Textbox");
			WebActionUtil.selectCalendarRangeDates("conveyanceFromDate", expanceDate);
			String expectedFromDate = WebActionUtil.getTextUsingJS("conveyanceFromDate");
			WebActionUtil.validateEnteredValue(expectedFromDate, expanceDate, "Expense Date Textbox",
					expanceDate + " entered in Expense date Textbox",
					expanceDate + " not entered in Expense date Textbox", "blue");

			setDescription(description);

			WebActionUtil.validationinfo("All the Conveyance Expense details are entered", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fill the Conveyance Expense details");
			Assert.fail("Unable to fill the Conveyance Expense details");
		}
	}

	/**
	 * Description: This Method implements to Enter Amount
	 * 
	 * @author Suganthini
	 * @param amount
	 */
	private synchronized void setAmount(String amount) {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(tbAmount, "Amount  Textbox");
			WebActionUtil.typeText(tbAmount, amount, "Amount  Textbox");
			WebActionUtil.extentinfo(amount + " is entered in Amount Textbox");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Enter Amount ");
			Assert.fail("Unable to Enter Amount");
		}
	}

	/**
	 * Description: This Method implements to Validate selected expenses
	 * 
	 * @author Suganthini
	 * @param claimType
	 */
	public synchronized void validateExpenseDetails(String claimType) {
		try {
			String actualExpences = txtExpenseDetails.getText();
			Assert.assertTrue(actualExpences.contains(claimType));
			WebActionUtil.pass(claimType + " is added in Expense details");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to add " + claimType);
			WebActionUtil.error("Unable to add " + claimType);
			Assert.fail("Unable to add " + claimType);
		}

	}

	/**
	 * Description: This Method implements to fill category details
	 * 
	 * @author Suganthini
	 * @param category
	 * @param data
	 */
	public synchronized void fillCategoryDetails(Category category, String... data) {

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
			fillCommonCategory(data[0], data[1]);
			break;
		}
		case BROADBAND_EXPENSES: {
			fillTheBroadbandExpense(data[0], data[1], data[2], data[3], data[4]);
			break;
		}
		case COVID19_EVACUATION_ADVANCE: {
			fillCovid19EvacuationAdvance(data[0], data[1]);
			break;
		}
		case HE_ADVANCE_OTHER: {
			fillTheHEAdvanceOther(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
			break;
		}
		case SPECIALIZED_TRAINING_EXPENSE: {
			fillTheSpecializedTrainingExpense(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
			break;
		}
		case VISA_EXPENSES: {
			fillTheVisaExpenses(data[0], data[1], data[2], data[3], data[4], data[5]);
			break;
		}
		case CAB_BREAKDOWN_EXP: {
			fillCabBreakDownExpenses(data[0], data[1]);
			break;
		}
		case CELL_PHONE: {
			fillTheCellPhoneExpense(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
			break;
		}
		case CONVEYANCE: {
			fillTheConveyanceExpense(data[0], data[1], data[2], data[3], data[4], data[5]);
			break;
		}
		case TECHNICAL_CERTIFICATION:
			fillTheTechnicalCertificationExpense(data[0], data[1], data[2]);
			break;

		}

	}

	/**
	 * Description: This method implements Enter Reason or Business justification
	 * for the claim
	 * 
	 * @author: Suganthini
	 * @param reason
	 */

	public synchronized void setApproveRemark(String reason) {
		try {
			WebActionUtil.clickOnElement(tbReason, "Reason", "Unable to click on Reason");			

			WebActionUtil.waitForElement(tbReason, "Reason or Business justificaion Text Area");
			WebActionUtil.typeText(tbReason, reason, "Reason or Business justificaion Text Area");
			WebActionUtil.extentinfo(reason + " is entered in Reason or Business justificaion Text Area");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil
					.fail("Unable to type " + reason + " into Enter Reason or Business justificaion Text Area field");
			Assert.fail("Unable to type " + reason + " into Enter Reason or Business justificaion Text Area field");
		}
	}

	/**
	 * Description: This Method implements to Enter Description
	 * 
	 * @author Suganthini
	 * @param description
	 */
	public synchronized void setDescription(String description) {

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
	
	public synchronized void selectExpensesGeo(String categroy) {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.clickOnElement(yourCostCenterBtn, "Select Expenses", "Unable to click on Select Expenses");			
			WebActionUtil.waitForElement(sddCategroy, "Select Expenses");
			WebActionUtil.selectByText(sddCategroy, categroy);
			validateSelectedExpenses(categroy);
			
			
		} catch (Exception e) {
			WebActionUtil.fail("Unable to click on Select Expenses");
			WebActionUtil.error("Unable to click on Select Expenses");
			Assert.fail("Unable to click on Select Expenses");
		}
	}
		
		public synchronized void expenseDateGeo(String categroy) {
			try {
				System.out.println(categroy);
				WebActionUtil.waitForElement(expensedate, "From Date Textbox");
				WebActionUtil.clickOnElement(expensedate, "From Date Textbox", "unable to enter FromDate");
				WebActionUtil.clearText(expensedate, "From Date Textbox");
				WebActionUtil.selectCalendarRangeDates("expensedate", categroy);
				String expectedfromdate = WebActionUtil.getTextUsingJS("expensedate");
				WebActionUtil.validateEnteredValue(expectedfromdate, categroy, "From Date Textbox",
						expectedfromdate + " entered in from date Textbox", expectedfromdate + " not entered in from date Textbox", "blue");				
			} catch (Exception e) {
				WebActionUtil.fail("Unable to click on Select Expenses");
				WebActionUtil.error("Unable to click on Select Expenses");
				Assert.fail("Unable to click on Select Expenses");
			}
		}
			
		public synchronized void setStartLocation(String categroy) {
			try {
				WebActionUtil.waitForElement(stateLocation, "Description Text Area");
				WebActionUtil.typeText(stateLocation, categroy, "Description Text Area");
				WebActionUtil.extentinfo(categroy + " is entered in Description Text Area");
			} catch (Exception e) {
				WebActionUtil.error(e.getMessage());
				WebActionUtil.fail("Unable to type " + categroy + " into Description Text are field");
				Assert.fail("Unable to type " + categroy + " into Description Text are field");
			}
		}
			
			public synchronized void setEndLocation(String categroy) {
				try {
					WebActionUtil.waitForElement(endLocation, "Description Text Area");
					WebActionUtil.typeText(endLocation, categroy, "Description Text Area");
					WebActionUtil.extentinfo(categroy + " is entered in Description Text Area");
				} catch (Exception e) {
					WebActionUtil.error(e.getMessage());
					WebActionUtil.fail("Unable to type " + categroy + " into Description Text are field");
					Assert.fail("Unable to type " + categroy + " into Description Text are field");
				}
			}
			
			public synchronized void setMiles(String categroy) {
				try {
					WebActionUtil.waitForElement(txtmiles, "Description Text Area");
					WebActionUtil.typeText(txtmiles, categroy, "Description Text Area");
					WebActionUtil.extentinfo(categroy + " is entered in Description Text Area");
				} catch (Exception e) {
					WebActionUtil.error(e.getMessage());
					WebActionUtil.fail("Unable to type " + categroy + " into Description Text are field");
					Assert.fail("Unable to type " + categroy + " into Description Text are field");
				}
			}
			
			public synchronized void selectAllowance(String categroy) {
				try {
					WebActionUtil.poll(2000);
					WebActionUtil.clickOnElement(allowanceDdn, "Select Expenses", "Unable to click on Select Expenses");			
					WebActionUtil.waitForElement(allowanceDdn, "Select Expenses");
					WebActionUtil.selectByText(allowanceDdn, categroy);			
				} catch (Exception e) {
					WebActionUtil.fail("Unable to click on Select Expenses");
					WebActionUtil.error("Unable to click on Select Expenses");
					Assert.fail("Unable to click on Select Expenses");
				}
			}
			
			public synchronized void saveClaimDdn() {
				try {
					WebActionUtil.poll(2000);
					WebActionUtil.clickOnElement(saveClaimRadioBtn, "Saves Calim", "Unable to click on Select Expenses");			
					WebActionUtil.clickOnElement(customeRadioBtn, "Chargeable to Customer", "Unable to click on Select Expenses");	
					
				} catch (Exception e) {
					WebActionUtil.fail("Unable to click on Select Expenses");
					WebActionUtil.error("Unable to click on Select Expenses");
					Assert.fail("Unable to click on Select Expenses");
				}
			}
			
			public synchronized void addExpenseBtn() {
				try {
					WebActionUtil.poll(2000);
					WebActionUtil.clickOnElement(ADDexpenceButton, "Saves Calim", "Unable to click on Select Expenses");			
					
				} catch (Exception e) {
					WebActionUtil.fail("Unable to click on Add Expenses");
					WebActionUtil.error("Unable to click on Add Expenses");
					Assert.fail("Unable to click on Add Expenses");
				}
			}
			
			public synchronized void textAmount(String categroy) {
				try {
					WebActionUtil.waitForElement(amountTxt, "Description Text Area");
					WebActionUtil.typeText(amountTxt, categroy, "Description Text Area");
					WebActionUtil.extentinfo(categroy + " is entered in Description Text Area");
				} catch (Exception e) {
					WebActionUtil.error(e.getMessage());
					WebActionUtil.fail("Unable to type " + categroy + " into Description Text are field");
					Assert.fail("Unable to type " + categroy + " into Description Text are field");
				}
			}
			
			public synchronized void clickCheckBox() {
				try {
					WebActionUtil.poll(2000);
					WebActionUtil.clickOnElement(chkDeclaration, "Check Box", "Unable to click on Check Box");			
					
				} catch (Exception e) {
					WebActionUtil.fail("Unable to click on Check Box");
					WebActionUtil.error("Unable to click on Check Box");
					Assert.fail("Unable to click on Check Box");
				}
			}
			
			public synchronized void bacvkward() {
				try {
					Thread.sleep(20000);
					driver.navigate().back();
					
				} catch (Exception e) {
					WebActionUtil.fail("Unable to click on Check Box");
					WebActionUtil.error("Unable to click on Check Box");
					Assert.fail("Unable to click on Check Box");
				}
			}
			
			
			
			
			
			
				



}
