package com.hcl.gcs.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hcl.gcs.util.WebActionUtil;

/**
 * Description: This class implements the methods for Approver Section page
 * 
 * @author Sajal
 */
public class ApproverSection_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public ApproverSection_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Approve button */
	@FindBy(id = "approve")
	private WebElement btnApprove;

	/* Reject button */
	@FindBy(id = "reject")
	private WebElement btnReject;

	/* Refer back button */
	@FindBy(id = "referBack")
	private WebElement btnReferBack;

	/* Remarks text area */
	@FindBy(id = "textarea")
	private WebElement taRemarks;

	/* Yes button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnYes;

	/* No button */
	@FindBy(xpath = "//button[text()='NO']")
	private WebElement btnNo;

	/* Are you sure you want to proceed text */
	@FindBy(xpath = "//p[text()='Are you sure you want to proceed?']")
	private WebElement txtAreYouSureYouWantToProceed;

	/* Claim Approved Successfully toast message */
	@FindBy(xpath = "//span[text()='Claim approved successfully.']")
	private WebElement txtClaimApprovedSuccessfully;

	/* Claim Rejected Successfully toast message */
	@FindBy(xpath = "//span[text()='Claim rejected successfully.']")
	private WebElement txtClaimRejectedSuccessfully;

	/* Claim referred back successfully toast message */
	@FindBy(xpath = "//span[text()='Claim referred back successfully.']")
	private WebElement txtClaimReferredBackSuccessfully;

	/* Work flow link */
	@FindBy(xpath = "//a[text()='Workflow']")
	private WebElement lnkWorkFlow;

	/* Claim history link */
	@FindBy(xpath = "//a[text()='Claim History']")
	private WebElement lnkClaimHistory;

	/* Supporting document link */
	@FindBy(xpath = "//p[text()='Supporting Document ']/following-sibling::div/descendant::a")
	private WebElement lnkSupportingDocument;

	/* Expense Details text */
	@FindBy(xpath = "//div[contains(@class,'ng-binding ng-scope')]")
	private List<WebElement> txtExpenseDetails;

	/* Claim Number text */
	@FindBy(xpath = "//th[text()='Claim No.']/ancestor::thead/following-sibling::tbody/descendant::tr/descendant::td[1]")
	private WebElement txtClaimNumber;

	/* Claim Amount text */
	@FindBy(xpath = "//th[text()='Claimed Amount']/ancestor::thead/following-sibling::tbody/descendant::tr/descendant::td[3]")
	private WebElement txtClaimAmount;

	/* SAP code text */
	@FindBy(xpath = "//span[text()='SAP Code : ']/descendant::strong")
	private WebElement txtSapCode;

	/* Claim Details text */
	@FindBy(xpath = "//p[@class='claimHead']")
	private WebElement txtClaimDetails;

	/* Claim History Status text */
	@FindBy(xpath = "//div[contains(@class,'history-details')]/descendant::h2")
	private List<WebElement> txtClaimHistoryStatus;

	/* Claim History Name text */
	@FindBy(xpath = "//div[contains(@class,'history-details')]/descendant::p[1]")
	private List<WebElement> txtClaimHistoryName;

	/* Spinner */
	@FindBy(xpath = "//div[@class='loader2 material-spinner']")
	private WebElement spinner;

	/* Advanced Spinner */
	@FindBy(xpath = "//div[@id='Advance_go_spiner']")
	private WebElement advancedSpinner;

	/**
	 * Description: Method to click on Supporting document link
	 * 
	 * @author Sajal jain
	 * @param uploadedDocument
	 */
	public synchronized void clkSupportingDocumentLnk(String uploadedDocument) {
		try {
			WebActionUtil.waitForElement(lnkSupportingDocument, "Supporting Document Link");
			WebActionUtil.validateisElementDisplayed(lnkSupportingDocument, "Supporting Document Link",
					"Supporting Document Link is displayed", "Supporting Document Link is not displayed", "blue");
			WebActionUtil.clickOnElement(lnkSupportingDocument, "Supporting Document Link",
					"Unable to click on Supporting Document Link");
			validateSupportingDocument(uploadedDocument);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Supporting Document Link");
			Assert.fail("Unable to click on Supporting Document Link");
		}
	}

	/**
	 * Description Method to validate whether Supporting Document is downloaded for
	 * .msg/.xps/.oxps and document is displayed in new tab for all other extension
	 * 
	 * @author Sajal jain
	 * @param uploadedDocument
	 */
	private synchronized void validateSupportingDocument(String uploadedDocument) {
		uploadedDocument = uploadedDocument.toUpperCase();
		try {
			if (uploadedDocument.endsWith(".XPS") || uploadedDocument.contains("MSG")) {
				String downloadedDocumentName = null;
				String userDir = System.getProperty("user.home");
				String[] split = uploadedDocument.split("[.]");
				downloadedDocumentName = WebActionUtil.getDownloadedDocumentName(userDir + "/Downloads",
						"." + split[1]);

				if (downloadedDocumentName.toUpperCase().contains(split[0])) {
					WebActionUtil.info(uploadedDocument + " document is downloaded successfully");
					WebActionUtil.validationinfo(uploadedDocument + " document is downloaded successfully", "green");
				} else {
					WebActionUtil.error(uploadedDocument + " document is not downloaded");
					WebActionUtil.fail(uploadedDocument + " document is not downloaded");
				}
			} else {
				WebActionUtil.switchToNewTab();
				WebActionUtil.waitForAngularPageToLoad();
				String currentUrl = WebActionUtil.driver.getCurrentUrl();
				String currentUrlLowerCase = currentUrl.toLowerCase();
				String uploadedDocumentLowerCase = uploadedDocument.toLowerCase();
				if (currentUrlLowerCase.endsWith(uploadedDocumentLowerCase)) {
					WebActionUtil.info(uploadedDocument + " document is displayed");
					WebActionUtil.validationinfo(uploadedDocument + " document is displayed", "green");
				} else {
					WebActionUtil.error(uploadedDocument + " document is not displayed");
					WebActionUtil.fail(uploadedDocument + " document is not displayed");
				}
				WebActionUtil.driver.close();
				WebActionUtil.switchToMainTab();
				WebActionUtil.waitForAngularPageToLoad();
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate " + uploadedDocument + " Supporting Document");
			Assert.fail("Unable to validate " + uploadedDocument + " Supporting Document");
		}
	}

	/**
	 * Description Method to click on Claim history link
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkClaimHistoryLnk() {
		try {
			WebActionUtil.waitForElement(lnkClaimHistory, "Claim History Link");
			WebActionUtil.clickOnElement(lnkClaimHistory, "Claim History Link",
					"Unable to click on Claim History Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Claim History Link");
			Assert.fail("Unable to click on Claim History Link");
		}
	}

	/**
	 * Description Method to click on Work flow link
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkWorkFlowLnk() {
		try {
			WebActionUtil.waitForElement(lnkWorkFlow, "Work Flow Link");
			WebActionUtil.clickOnElement(lnkWorkFlow, "Work Flow Link", "Unable to click on Work Flow Link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Work Flow Link");
			Assert.fail("Unable to click on Work Flow Link");
		}
	}

	/**
	 * Description Method to validate Claim approved successfully toast message
	 * 
	 * @author Sajal jain
	 */
	public synchronized void validateClaimApprovedSuccessfully() {
		try {
			WebActionUtil.waitForElement(txtClaimApprovedSuccessfully, "Claim approved successfully text");
			WebActionUtil.validateisElementDisplayed(txtClaimApprovedSuccessfully, "Claim approved successfully text",
					"Claim approved successfully pop up is displayed",
					"Claim approved successfully pop up is not displayed", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Claim approved successfully text");
			Assert.fail("Unable to validate Claim approved successfully text");
		}
	}

	/**
	 * Description Method to validate Claim rejected successfully toast message
	 * 
	 * @author Sajal jain
	 */
	public synchronized void validateClaimRejectedSuccessfully() {
		try {
			WebActionUtil.waitForElement(txtClaimRejectedSuccessfully, "Claim rejected successfully text");
			WebActionUtil.validateisElementDisplayed(txtClaimRejectedSuccessfully, "Claim rejected successfully text",
					"Claim rejected successfully pop up is displayed",
					"Claim rejected successfully pop up is not displayed", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Claim rejected successfully text");
			Assert.fail("Unable to validate Claim rejected successfully text");
		}
	}

	/**
	 * Description Method to validate Claim referred back successfully toast message
	 * 
	 * @author Sajal jain
	 */
	public synchronized void validateClaimReferredBackSuccessfully() {
		try {
			WebActionUtil.waitForElement(txtClaimReferredBackSuccessfully, "Claim referred back successfully text");
			WebActionUtil.validateisElementDisplayed(txtClaimReferredBackSuccessfully,
					"Claim referred back successfully text", "Claim referred back successfully pop up is displayed",
					"Claim referred back successfully pop up is not displayed", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Claim referred back successfully text");
			Assert.fail("Unable to validate Claim referred back successfully text");
		}
	}

	/**
	 * Description Method to click on Yes button in Are you sure you want to proceed
	 * pop up
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkYesBtn() {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(txtAreYouSureYouWantToProceed, "Are you sure you want to proceed text");
			WebActionUtil.validateisElementDisplayed(txtAreYouSureYouWantToProceed,
					"Are you sure you want to proceed text", "Are you sure you want to proceed pop up is displayed",
					"Are you sure you want to proceed pop up is not displayed", "blue");
			WebActionUtil.waitForElement(btnYes, "Yes Button");
			WebActionUtil.clickOnElement(btnYes, "Yes Button", "Unable to click on Yes Button");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes Button");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description Method to click on No button in Are you sure you want to proceed
	 * pop up
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkNoBtn() {
		try {
			WebActionUtil.validateisElementDisplayed(txtAreYouSureYouWantToProceed,
					"Are you sure you want to proceed text", "Are you sure you want to proceed pop up is displayed",
					"Are you sure you want to proceed pop up is not displayed", "blue");
			WebActionUtil.waitForElement(btnNo, "No Button");
			WebActionUtil.clickOnElement(btnNo, "No Button", "Unable to click on No Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on No Button");
			Assert.fail("Unable to click on No Button");
		}
	}

	/**
	 * Description Method to click on Approve Button
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkApproveBtn() {
		try {
			WebActionUtil.waitForElement(btnApprove, "Approve Button");
			WebActionUtil.clickOnElement(btnApprove, "Approve Button", "Unable to click on Approve Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Approve Button");
			Assert.fail("Unable to click on Approve Button");
		}
	}

	/**
	 * Description Method to click on Reject Button
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkRejectBtn() {
		try {
			WebActionUtil.waitForElement(btnReject, "Reject Button");
			WebActionUtil.clickOnElement(btnReject, "Reject Button", "Unable to click on Reject Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Reject Button");
			Assert.fail("Unable to click on Reject Button");
		}
	}

	/**
	 * Description Method to click on Refer back Button
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkReferBackBtn() {
		try {
			WebActionUtil.waitForElement(btnReferBack, "Refer Back Button");
			WebActionUtil.clickOnElement(btnReferBack, "Refer Back Button", "Unable to click on Refer Back Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Refer Back Button");
			Assert.fail("Unable to click on Refer Back Button");
		}
	}

	/**
	 * Description This Method implements to enter data in Remarks text area
	 * 
	 * @author Sajal
	 * @param remarks
	 */
	public synchronized void setRemarksTa(String remarks) {
		try {
			WebActionUtil.waitForElement(taRemarks, "Remarks Text area");
			WebActionUtil.typeText(taRemarks, remarks, "Remarks Text area");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to enter data in Remarks Text area");
			WebActionUtil.error("Unable to enter data in Remarks Text area");
			Assert.fail("Unable to enter data in Remarks Text area");
		}
	}

	/**
	 * Description:This Method implements to validate Claim Details data
	 * 
	 * @author Sajal
	 * @param claimNo
	 * @param sapCode
	 * @param claimedAmount
	 */
	public synchronized void validateClaimDetailsData(String claimNo, String sapCode, String claimedAmount) {
		try {
			if (spinner.isDisplayed()) {
				WebActionUtil.waitForInvisibilityOfElement(spinner, "Spinner", (long) 60);
			}
			if (advancedSpinner.isDisplayed()) {
				WebActionUtil.waitForInvisibilityOfElement(advancedSpinner, "Advanced Spinner", (long) 60);
			}
			WebActionUtil.validateisElementDisplayed(txtClaimDetails, "Claim Details Text",
					"Claim Details is displayed", "Claim Details is not displayed", "blue");

			ArrayList<Boolean> arraylist = new ArrayList<Boolean>();
			WebActionUtil.waitForVisibilityOfElement(txtClaimNumber, "Claim Number Text", (long) 60);

			arraylist.add(txtSapCode.getText().contentEquals(sapCode));
			arraylist.add(txtClaimNumber.getText().contentEquals(claimNo));
			arraylist.add(txtClaimAmount.getText().contains(claimedAmount));
			if (arraylist.contains(true)) {
				WebActionUtil.validationinfo("Claim Details data is as expected", "green");
				WebActionUtil.info("Claim Details data is as expected");
			} else {
				WebActionUtil.fail("Claim Details data is not as expected");
				WebActionUtil.error("Claim Details data is not as expected");
				throw new Exception();
			}
		} catch (Exception e) {
			WebActionUtil.fail("Unable to validate Claim Details data");
			WebActionUtil.error("Unable to validate Claim Details data");
			Assert.fail("Unable to validate Claim Details data");
		}
	}

	/**
	 * Description:This Method implements to validate Claim History details
	 * 
	 * @author Sajal
	 * @param claimNo
	 * @param sapCode
	 * @param claimedAmount
	 * @param expectedHistory
	 */
	public synchronized void validateClaimHistory(String claimNo, String sapCode, String claimedAmount,
			List<String[]> expectedHistory) {
		try {
			List<String[]> actualList = fetchHistoryData();
			boolean flag = false;
			for (int i = 0; i < expectedHistory.size(); i++) {
				if (!Arrays.equals(expectedHistory.get(i), actualList.get(i))) {
					flag = true;
					if (flag) {
						WebActionUtil
								.error(Arrays.toString(expectedHistory.get(i)) + " is not present in History Pop up");
						WebActionUtil
								.fail("Expected history is not matching with the actual history in the application");
						Assert.fail("Expected history is not matching with the actual history in the application");
					}
				}
			}
			WebActionUtil.info("Expected history is matching with the actual history in the application");
			WebActionUtil.pass("Expected history is matching with the actual history in the application");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to validate Claim Details data");
			WebActionUtil.error("Unable to validate Claim Details data");
			Assert.fail("Unable to validate Claim Details data");
		}
	}

	/**
	 * Description:This Method implements to return Claim History Data
	 * 
	 * @author Sajal
	 * @return lstHistoryData
	 */
	public synchronized List<String[]> fetchHistoryData() {
		List<String[]> lstHistoryData = new ArrayList<>();
		for (int i = txtClaimHistoryStatus.size() - 1; i >= 0; i--) {
			String strDetail = txtClaimHistoryStatus.get(i).getText();
			String strName = txtClaimHistoryName.get(i).getText();
			String[] strHistoryDetailsSplit = strDetail.split(" ");
			String status = "";
			String role = "";
			if (strDetail.contains("Submitted")) {
				status = strHistoryDetailsSplit[0];
			} else {
				status = strHistoryDetailsSplit[0];
				role = strHistoryDetailsSplit[2];
			}
			String empcode = strName.substring(strName.indexOf("(") + 1, strName.indexOf(")"));
			if (role.equals("")) {
				lstHistoryData.add(new String[] { status, empcode });
			} else {
				lstHistoryData.add(new String[] { status, role, empcode });
			}
		}
		return lstHistoryData;
	}
	/**
	 * Description:This Method implements to validate Claim Details data
	 * 
	 * @author Sajal
	 * @param claimNo
	 * @param sapCode
	 * @param claimedAmount
	 */
	public synchronized void validateClaimDetailsDataForBreakDown(String claimNo, String sapCode) {
		try {
			if (spinner.isDisplayed()) {
				WebActionUtil.waitForInvisibilityOfElement(spinner, "Spinner", (long) 60);
			}
			if (advancedSpinner.isDisplayed()) {
				WebActionUtil.waitForInvisibilityOfElement(advancedSpinner, "Advanced Spinner", (long) 60);
			}
			WebActionUtil.validateisElementDisplayed(txtClaimDetails, "Claim Details Text",
					"Claim Details is displayed", "Claim Details is not displayed", "blue");

			ArrayList<Boolean> arraylist = new ArrayList<Boolean>();
			WebActionUtil.waitForVisibilityOfElement(txtClaimNumber, "Claim Number Text", (long) 60);

			arraylist.add(txtSapCode.getText().contentEquals(sapCode));
			arraylist.add(txtClaimNumber.getText().contentEquals(claimNo));
			if (arraylist.contains(true)) {
				WebActionUtil.validationinfo("Claim Details data is as expected", "green");
				WebActionUtil.info("Claim Details data is as expected");
			} else {
				WebActionUtil.fail("Claim Details data is not as expected");
				WebActionUtil.error("Claim Details data is not as expected");
				throw new Exception();
			}
		} catch (Exception e) {
			WebActionUtil.fail("Unable to validate Claim Details data");
			WebActionUtil.error("Unable to validate Claim Details data");
			Assert.fail("Unable to validate Claim Details data");
		}
	}

}