package com.hcl.gcs.pagesActionUtil;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.pages.Category;

public class PagesActionUtil extends BaseTest {
	static String claimNumber = null;

	/**
	 * 
	 * Description Method to fetch the map of enumCategories
	 * 
	 * @author Vikas
	 */
	public synchronized static Map<String, Category> getCategoryValues() {
		String[] strCategories = new String[] { "Books & Periodicals", " Online External Trainings",
				"Books & Periodicals(Enable only for GWS)", "Broadband Expenses", "Business Entertainment",
				"Business Gift", "Cab Breakdown Exp", "Catalyst Advance", "Catalyst Expenses", "CEO’s Champion Club",
				"Cell Phone", "Conveyance", "Courier Charges", "COVID 19 Evacuation Advance", "Covid Test (RTPCR)",
				"COVID-19", "EFC – Grey Cells Council", "EFC – Sports Council", "EFC- Ambassador Council",
				"EFC- Community Services Council", "EFC- Talent Council", "EFC- Wellness Council",
				"EFC- Women First Council", "HE Advance Other", "HRA Advance", "Laptop/Asset Purchase",
				"Legal Expenses", "Miscellaneous expenses", "Other advance", "Other(Expense Related) advance",
				"PG Accomodation – Return to Office", "Post & Telegram", "Printing & Stationery/Off.Supplies",
				"Relocation Expense – Accommodation Charges", "Relocation Expenses", "Rep. & Maint(Bldg.)",
				"Repair Maint. (others)", "Salary/Medical Advance", "Specialized Training Expense", "Staff Recruitment",
				"Staff Welfare", "Technical\\Domain\\Process Certification Expense", "Transfer Claim", "Visa Expenses",
				"Visa Expenses Old", "Zero Expense" };
		Category[] enumCategories = new Category[] { Category.BOOKS_PERIODICALS, Category.ONLINE_EXTERNAL_TRAININGS,
				Category.BOOK_PERIODICALS_GWS, Category.BROADBAND_EXPENSES, Category.BUISNESS_ENTERTAINEMENT,
				Category.BUISNESS_GIFT, Category.CAB_BREAKDOWN_EXP, Category.CATALYST_ADVANCE,
				Category.CATALYST_EXPENSES, Category.CEO_CHAMPION_CLUB, Category.CELL_PHONE, Category.CONVEYANCE,
				Category.COURIER_CHARGES, Category.COVID19_EVACUATION_ADVANCE, Category.COVID_TEST, Category.COVID_19,
				Category.EFC_GREY_CELLS_COUNCIL, Category.EFC_SPORTS_COUNCIL, Category.EFC_AMBASSADOR_COUNCIL,
				Category.EFC_COMMUNITY_SERVICES_COUNCIL, Category.EFC_TALENT_COUNCIL, Category.EFC_WELLNESS_COUNCIL,
				Category.EFC_WOMAN_FIRST_COUNCIL, Category.HE_ADVANCE_OTHER, Category.HRA_ADVANCE,
				Category.LAPTOP_ASSET_PURCHASE, Category.LEGAL_EXPENSES, Category.MISCELLANEOUS_EXPENSES,
				Category.OTHER_ADVANCE, Category.OTHER_EXPENSE_RELATED_ADVANCE,
				Category.PG_ACCOMODATION_RETURN_TO_OFFICE, Category.POST_AND_TELEGRAM,
				Category.PRINTING_STATIONERY_OFF_SUPPLIES, Category.RELOCATION_EXPENSE_ACCOMODATION_CHARGE,
				Category.RELOCATION_EXPENSES, Category.REP_AND_MAINT_BLDG, Category.REPAIR_MAINT_OTHERS,
				Category.SALARY_MEDICAL_ADVANCE, Category.SPECIALIZED_TRAINING_EXPENSE, Category.STAFF_RECRUITMENT,
				Category.STAFF_WELFARE, Category.TECHNICAL_CERTIFICATION, Category.TRANSFER_CLAIM,
				Category.VISA_EXPENSES, Category.VISA_EXPENSES_OLD, Category.ZERO_EXPENSES };

		Map<String, Category> mapCatValues = IntStream.range(0, strCategories.length).boxed()
				.collect(Collectors.toMap(i -> strCategories[i], i -> enumCategories[i]));

		return mapCatValues;

	}

	/**
	 * Description: Method to initiate a claim for a particular Category
	 * 
	 * @author vikas.kc
	 * 
	 * @param empCode
	 * @param password
	 * @param mapDataKeyValues
	 * @param pages
	 * @param category
	 * @return
	 */
	public synchronized static Map<String, String> initiateClaim(String empCode, String password,
			Map<String, String> mapDataKeyValues) {

		String[] checkBoxNames = mapDataKeyValues.get("checkBoxNames").split(",");
		if (mapDataKeyValues.get("category").equalsIgnoreCase("Zero Expense")) {
			/* Login to application as Employee */
			pages.loginpage.loginToApplication(empCode, password);

			/* Click on Advance Settlement tab */
			pages.homePage.clkRaiseNewClaimTab("Advance Settlement");

			/* Click on Advance Settlement radio button */
			pages.newCashClaimPage.clkAdvanceClaim();

			/* Select expense type */
			pages.newCashClaimPage.selectExpenses(mapDataKeyValues.get("category"));

			/* Fetches the data to be filled for the particular category from Excel sheet */
			String[] data = getDataToFillCategoryDetails(getCategoryValues().get(mapDataKeyValues.get("category")),
					mapDataKeyValues);

			/* Fill category fields */
			pages.newCashClaimPage.fillCategoryDetails(getCategoryValues().get(mapDataKeyValues.get("category")), data);

			/* Click on add expense */
			pages.newCashClaimPage.clkAddExpense();

			/* Enter reason */
			pages.newCashClaimPage.setApproveRemark(mapDataKeyValues.get("reason"));

			/* Closes the Feedback System Popup */
			closeFeedbackWindow();

			/* Check declaration check box */
			pages.newCashClaimPage.clkCheckBoxs(checkBoxNames);
			
			/* Click on submit */
			pages.newCashClaimPage.clkSubmitButton();

			/* Click on yes button */
			pages.newCashClaimPage.clkOnYesButton();

			WebActionUtil.poll(2000);

			/* Refresh the current Page */
			WebActionUtil.driver.navigate().refresh();

			WebActionUtil.waitForAngularPageToLoad();

			/* Closes the Feedback System Popup */
			closeFeedbackWindow();

			/* Fetch claim number */
			
				claimNumber = pages.homePage.getClaimNumber(mapDataKeyValues.get("category"),
					mapDataKeyValues.get("amount"));
			/* Click on claim number */
			pages.homePage.clkClaimNumberMyClaim(claimNumber);

			/* Closes the Feedback System Popup */
			closeFeedbackWindow();

			/* Retrieves the flow from View cash claim page */
			Map<String, String> roles = pages.viewCashClaimPage.getRolesFromFlow();

			/* Closes the Feedback System Popup */
			closeFeedbackWindow();

			/* Logout as employee */
			pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

			return roles;
		} else {
			/* Login to application as Employee */
			pages.loginpage.loginToApplication(empCode, password);

			/* Click on More claim tab */
			pages.homePage.clkMoreClaimsTab(prop_constants.getProperty("expectedNewCashClaimUrl"));

			/* Select expense type */
			pages.newCashClaimPage.selectExpenses(mapDataKeyValues.get("category"));

			String[] data = getDataToFillCategoryDetails(getCategoryValues().get(mapDataKeyValues.get("category")),
					mapDataKeyValues);
			/* Fill category fields */
			pages.newCashClaimPage.fillCategoryDetails(getCategoryValues().get(mapDataKeyValues.get("category")), data);

			/* Click on add expense and upload bill */
			pages.newCashClaimPage
					.clkAddExpenseAndUploadBill(WebActionUtil.getSampleFilePath(mapDataKeyValues.get("fileFormat")));

			if (mapDataKeyValues.get("category").equalsIgnoreCase("Broadband Expenses")
					&& Integer.parseInt(mapDataKeyValues.get("amount")) > 650) {
				/* Clicks on Entitlement Yes Button */
				pages.newCashClaimPage.clkOnEntitleMentYesButton();

			} else if (mapDataKeyValues.get("category").equalsIgnoreCase("Cell Phone")
					&& (Integer.parseInt(mapDataKeyValues.get("amount")) > 236
							|| Integer.parseInt(mapDataKeyValues.get("internationalCharge")) > 0)) {
				/* Clicks on Entitlement Yes Button */
				pages.newCashClaimPage.clkOnEntitleMentYesButton();

				WebActionUtil.poll(4000);
			}

			/* Enter reason */
			pages.newCashClaimPage.setApproveRemark(mapDataKeyValues.get("reason"));

			/* Check declaration check box */
			pages.newCashClaimPage.clkCheckBoxs(checkBoxNames);

			/* Click on submit */
			pages.newCashClaimPage.clkSubmitButton();

			/* Click on yes button */
			pages.newCashClaimPage.clkOnYesButton();

			WebActionUtil.poll(1000);

			/* Refresh the current Page */
			WebActionUtil.driver.navigate().refresh();

			/* Closes the Feedback System Popup */
			closeFeedbackWindow();
			closeFeedbackWindow();	
			/* Fetch claim number */
			if(mapDataKeyValues.get("category").equalsIgnoreCase("Cab Breakdown Exp"))
			{
				claimNumber = pages.homePage.getCabBreakDownClaimNumber(mapDataKeyValues.get("category"));
			}
			else	
			{
			claimNumber = pages.homePage.getClaimNumber(mapDataKeyValues.get("category"),
					mapDataKeyValues.get("amount"));
			}
			
			
			closeFeedbackWindow();
			/* Closes the Feedback System Popup */
			closeFeedbackWindow();
			
			/* Click on claim number */
			pages.homePage.clkClaimNumberMyClaim(claimNumber);
			
			/* Closes the Feedback System Popup */
			closeFeedbackWindow();

			Map<String, String> roles = pages.viewCashClaimPage.getRolesFromFlow();

			/* Closes the Feedback System Popup */
			closeFeedbackWindow();

			/* Logout as employee */
			pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

			return roles;
		}

	}

	/**
	 * Description: Method to Re-Initiate the claim that is referred back.
	 * 
	 * @author vikas.kc
	 * @param empCode
	 * @param password
	 * @param mapDataKeyValues
	 */
	public synchronized static void validateReferBackStatusAndupdateClaim(String empCode, String password,
			Map<String, String> mapDataKeyValues, String referBackMessage) {
		String[] checkBoxNames = mapDataKeyValues.get("checkBoxNames").split(",");
		/* Login to application as Employee */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on claim number */
		pages.homePage.clkClaimNumberMyClaim(claimNumber);

		/* Validate refer back status */
		pages.viewCashClaimPage.validateReferBackText(referBackMessage);

		/* Click on edit expense */
		pages.viewCashClaimPage.clkEditIcn(mapDataKeyValues.get("category"));

		/* retrieves the data for the particular category from Excel sheet */
		String[] data = getDataForEditCategoryDetails(getCategoryValues().get(mapDataKeyValues.get("category")),
				mapDataKeyValues);

		/* Edit category fields */
		pages.viewCashClaimPage.editCategoryDetails(getCategoryValues().get(mapDataKeyValues.get("category")), data);

		/* Click on update expense */
		pages.viewCashClaimPage.clkUpdateExpenseButton();

		if (mapDataKeyValues.get("category").equalsIgnoreCase("Broadband Expenses")
				&& Integer.parseInt(mapDataKeyValues.get("amount")) > 650) {
			/* Click on Entitlement Yes button */
			pages.newCashClaimPage.clkOnEntitleMentYesButton();

		} else if (mapDataKeyValues.get("category").equalsIgnoreCase("Cell Phone")
				&& (Integer.parseInt(mapDataKeyValues.get("amount")) > 236
						|| Integer.parseInt(mapDataKeyValues.get("internationalCharge")) > 0)) {
			/* Click on Entitlement Yes button */
			pages.newCashClaimPage.clkOnEntitleMentYesButton();
		}

		/* Enter reason */
		pages.viewCashClaimPage.setReasonForClaimTextfield(mapDataKeyValues.get("reason"));

		/* Check declaration check box */
		pages.viewCashClaimPage.clkCheckBoxs(checkBoxNames);

		if (mapDataKeyValues.get("category").equalsIgnoreCase("Cell Phone")
				&& (Integer.parseInt(mapDataKeyValues.get("amount")) > 236
						|| Integer.parseInt(mapDataKeyValues.get("internationalCharge")) > 0)) {
			WebActionUtil.decreaseWindowSize(3);
			WebActionUtil.poll(3000);
			/* Click on submit button */
			pages.viewCashClaimPage.clkSubmitButtonUsingJS();
			WebActionUtil.increaseWindowSize(3);
			WebActionUtil.poll(3000);
		} else {
			/* Click on submit button */
			pages.viewCashClaimPage.clkSubmitButton();
		}

		/* Closes the Feedback System Popup */
		closeFeedbackWindow();

		/* Click on yes button */
		pages.viewCashClaimPage.clkOnYesButton();

		/* Closes the Feedback System Popup */
		closeFeedbackWindow();

		closeFeedbackWindow();

		/* Logout as employee */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to validate approval status of the claim.
	 * 
	 * @author vikas.kc
	 * @param empCode
	 * @param password
	 * @param mapDataKeyValues
	 */
	public synchronized static void validateApproveStatusClaim(String empCode, String password, String approveStatus) {
		/* Login to application as Employee */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on claim number */
		pages.homePage.clkClaimNumberMyClaim(claimNumber);

		/* Validate approve status */
		pages.viewCashClaimPage.validateApproveText(approveStatus);

		/* Logout as employee */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));
	}

	/**
	 * Description: Method to validate rejection status of the claim.
	 * 
	 * @author vikas.kc
	 * @param empCode
	 * @param password
	 * @param mapDataKeyValues
	 */
	public synchronized static void validateRejectStatusClaim(String empCode, String password, String rejectStatus) {
		/* Login to application as Employee */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on claim number */
		pages.homePage.clkClaimNumberMyClaim(claimNumber);

		/* Validate reject status */
		pages.viewCashClaimPage.validateRejectText(rejectStatus);

		/* Logout as employee */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));
	}

	/**
	 * Description: This Method implements to fill category details for a particular
	 * Category.
	 * 
	 * @author Suganthini
	 * @param category
	 * @param data
	 * @return
	 */
	public static synchronized String[] getDataToFillCategoryDetails(Category category,
			Map<String, String> mapDataKeyValues) {
		String[] data = null;
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
			data = new String[2];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("description");
			break;
		}
		case BROADBAND_EXPENSES: {
			data = new String[5];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("mobileNumber");
			data[2] = mapDataKeyValues.get("fromDate");
			data[3] = mapDataKeyValues.get("toDate");
			data[4] = mapDataKeyValues.get("description");
			break;
		}
		case COVID19_EVACUATION_ADVANCE: {
			data = new String[2];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("expectedDateOfTravel");
			break;
		}
		case HE_ADVANCE_OTHER: {
			data = new String[8];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("universityName");
			data[2] = mapDataKeyValues.get("courseName");
			data[3] = mapDataKeyValues.get("semester");
			data[4] = mapDataKeyValues.get("enrollmentNo");
			data[5] = mapDataKeyValues.get("startDate");
			data[6] = mapDataKeyValues.get("endDate");
			data[7] = mapDataKeyValues.get("description");
			break;
		}
		case SPECIALIZED_TRAINING_EXPENSE: {
			data = new String[7];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("universityName");
			data[2] = mapDataKeyValues.get("courseName");
			data[3] = mapDataKeyValues.get("modeOfTraining");
			data[4] = mapDataKeyValues.get("courseStartDate");
			data[5] = mapDataKeyValues.get("courseEndDate");
			data[6] = mapDataKeyValues.get("description");
			break;
		}
		case VISA_EXPENSES: {
			data = new String[6];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("country");
			data[2] = mapDataKeyValues.get("visaType");
			data[3] = mapDataKeyValues.get("visaExpenseType");
			data[4] = mapDataKeyValues.get("iAuraCaseNumber");
			data[5] = mapDataKeyValues.get("description");
			break;
		}
		case CAB_BREAKDOWN_EXP: {
			data = new String[2];
			data[0] = mapDataKeyValues.get("transportID");
			data[1] = mapDataKeyValues.get("description");
			break;
		}
		case CELL_PHONE: {
			data = new String[7];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("mobileNumber");
			data[2] = mapDataKeyValues.get("billFromDate");
			data[3] = mapDataKeyValues.get("billToDate");
			data[4] = mapDataKeyValues.get("plan");
			data[5] = mapDataKeyValues.get("description");
			data[6] = mapDataKeyValues.get("internationalCharge");
			break;
		}
		case CONVEYANCE: {
			data = new String[6];
			data[0] = mapDataKeyValues.get("transportMode");
			data[1] = mapDataKeyValues.get("totalDistanceInKM");
			data[2] = mapDataKeyValues.get("expenseDate");
			data[3] = mapDataKeyValues.get("description");
			data[4] = mapDataKeyValues.get("amount");
			data[5] = mapDataKeyValues.get("reason");
			break;
		}
		case TECHNICAL_CERTIFICATION:
			data = new String[3];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("certificationDate");
			data[2] = mapDataKeyValues.get("description");
			break;

		}
		return data;
	}

	/**
	 * Description: Method to validate Status of the claim in Home Page.
	 *
	 * @param empCode
	 * @param expectedStatus
	 * @param password
	 * @param mapDataKeyValues
	 * @param myclaimstype
	 * 
	 * @author Manish Kumar C D
	 */
	public synchronized static void validateStatusHomePage(String empCode, String password,
			Map<String, String> mapDataKeyValues, String expectedStatus, String myclaimstype) {

		/* Login To Application */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on My Claims Tab */
		pages.homePage.clkMyClaimsTab(myclaimstype);

		/* Validates the status of the Claim */
		pages.homePage.validateStatusTxt(claimNumber, expectedStatus);

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Refer Back a claim with the help of Referback icon.
	 *
	 * @param empCode
	 * @param password
	 * @param myApprovalMinorTab
	 * @param mapDataKeyValues
	 * 
	 * @author Manish Kumar C D
	 */
	public synchronized static void referBackClaim(String empCode, String password, String myApprovalMinorTab,
			Map<String, String> mapDataKeyValues) {

		/* Login To Application */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on My Approvals Tab */
		pages.homePage.clkMyApprovalsTab(myApprovalMinorTab);

		/* Enter the Remarks in Remarks Text box */
		pages.homePage.setRemarks(claimNumber, prop_constants.getProperty("referback_remarks"));

		/* Click on ReferBack Icon */
		pages.homePage.clkReferBackIcn(claimNumber);

		/* Click on Submit Button */
		pages.homePage.clkSubmitBtn();

		/* Click on Yes button */
		pages.approverSectionPage.clkYesBtn();

		/* validates the ReferredBack Successfully toast message */
		pages.homePage.validateReferredBackSuccessfully();

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Reject a claim with the help of Reject icon.
	 *
	 * @param empCode
	 * @param password
	 * @param myApprovalMinorTab
	 * @param mapDataKeyValues
	 * 
	 * @author Manish Kumar C D
	 */
	public synchronized static void rejectClaim(String empCode, String password, String myApprovalMinorTab,
			Map<String, String> mapDataKeyValues) {

		/* Login To Application */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on My Approvals Tab */
		pages.homePage.clkMyApprovalsTab(myApprovalMinorTab);

		/* Enter the Remarks in Remarks Text box */
		pages.homePage.setRemarks(claimNumber, prop_constants.getProperty("rejected_remarks"));

		/* Click on Reject Icon */
		pages.homePage.clkRejectIcn(claimNumber);

		/* Click on Submit Button */
		pages.homePage.clkSubmitBtn();

		/* Click on Yes button */
		pages.approverSectionPage.clkYesBtn();

		/* validates the Rejected Successfully toast message */
		pages.homePage.validateRejectedSuccessfully();

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Approve a claim with the help of Approve icon.
	 *
	 * @param empCode
	 * @param password
	 * @param myApprovalMinorTab
	 * @param mapDataKeyValues
	 * 
	 * @author Manish Kumar C D
	 */
	public synchronized static void approveClaim(String empCode, String password, String myApprovalMinorTab,
			Map<String, String> mapDataKeyValues) {

		/* Login To Application */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on My Approvals Tab */
		pages.homePage.clkMyApprovalsTab(myApprovalMinorTab);

		/* Enter the Remarks in Remarks Text box */
		pages.homePage.setRemarks(claimNumber, prop_constants.getProperty("approve_remarks"));

		/* Click on Approve Icon */
		pages.homePage.clkApproveIcn(claimNumber);

		/* Click on Submit Button */
		pages.homePage.clkSubmitBtn();

		/* Click on Yes button */
		pages.approverSectionPage.clkYesBtn();

		/* validates the Approved Successfully toast message */
		pages.homePage.validateApprovedSuccessfully();

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Approve a claim after clicking on claim number
	 *
	 * @param empCode
	 * @param password
	 * @param myApprovalMinorTab
	 * @param mapDataKeyValues
	 * 
	 * @author Sajal Jain
	 */
	public synchronized static void approveClaimThroughClaimNumber(String empCode, String password,
			String myApprovalMinorTab, Map<String, String> mapDataKeyValues) {

		/* Login To Application */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on My Approvals Tab */
		pages.homePage.clkMyApprovalsTab(myApprovalMinorTab);

		/* Click on the ClaimNumber under MyApprovals Tab */
		pages.homePage.clkClaimNumberMyApproval(claimNumber);

		/* Validate the data in Approver Section Page */
		if(mapDataKeyValues.get("category").equalsIgnoreCase("Cab Breakdown Exp"))
		{
			pages.approverSectionPage.validateClaimDetailsDataForBreakDown(claimNumber, mapDataKeyValues.get("employeeCode"));
		}
		else
		{
		pages.approverSectionPage.validateClaimDetailsData(claimNumber,
				mapDataKeyValues.get("employeeCode"), mapDataKeyValues.get("amount"));
		}
		if (mapDataKeyValues.get("category").equalsIgnoreCase("Broadband Expenses")
				|| mapDataKeyValues.get("category").equalsIgnoreCase("Cell Phone")) {
			/* Click on the Document Link */
			pages.approverSectionPage.clkSupportingDocumentLnk(mapDataKeyValues.get("fileFormat"));

		} else if (mapDataKeyValues.get("category").equalsIgnoreCase("Zero Expense")) {

		} else {
			/* Click on the Document Link */
			pages.approverSectionPage
					.clkSupportingDocumentLnk(WebActionUtil.getSampleFileName(mapDataKeyValues.get("fileFormat")));
		}

		/* Enter the remarks under Remarks textarea field */
		pages.approverSectionPage.setRemarksTa(prop_constants.getProperty("approve_remarks"));

		/* Click on Approve Button */
		pages.approverSectionPage.clkApproveBtn();

		/* Click on Yes Button */
		pages.approverSectionPage.clkYesBtn();

		/* Validate the approval toast notification */
		pages.approverSectionPage.validateClaimApprovedSuccessfully();

		/* Logout from the application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Reject a claim after clicking on claim number
	 *
	 * @param empCode
	 * @param password
	 * @param myApprovalMinorTab
	 * @param mapDataKeyValues
	 * 
	 * @author Sajal Jain
	 */
	public synchronized static void rejectClaimThroughClaimNumber(String empCode, String password,
			String myApprovalMinorTab, Map<String, String> mapDataKeyValues) {

		/* Login To Application */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on My Approvals Tab */
		pages.homePage.clkMyApprovalsTab(myApprovalMinorTab);

		/* Click on the ClaimNumber under MyApprovals Tab */
		pages.homePage.clkClaimNumberMyApproval(claimNumber);
		
		/* Validate the data in Approver Section Page */
		if(mapDataKeyValues.get("category").equalsIgnoreCase("Cab Breakdown Exp"))
		{
			pages.approverSectionPage.validateClaimDetailsDataForBreakDown(claimNumber, mapDataKeyValues.get("employeeCode"));
		}
		else
		{
		
		pages.approverSectionPage.validateClaimDetailsData(claimNumber,
		
				mapDataKeyValues.get("employeeCode"), mapDataKeyValues.get("amount"));
		}
		if (mapDataKeyValues.get("category").equalsIgnoreCase("Broadband Expenses")
				|| mapDataKeyValues.get("category").equalsIgnoreCase("Cell Phone")) {
			/* Click on the Document Link */
			pages.approverSectionPage.clkSupportingDocumentLnk(mapDataKeyValues.get("fileFormat"));

		} else if (mapDataKeyValues.get("category").equalsIgnoreCase("Zero Expense")) {

		} else {
			/* Click on the Document Link */
			pages.approverSectionPage
					.clkSupportingDocumentLnk(WebActionUtil.getSampleFileName(mapDataKeyValues.get("fileFormat")));
		}

		/* Enter the Remarks in the Remarks Textarea field */
		pages.approverSectionPage.setRemarksTa(prop_constants.getProperty("rejected_remarks"));

		/* Click on Reject button */
		pages.approverSectionPage.clkRejectBtn();

		/* Click on Yes button */
		pages.approverSectionPage.clkYesBtn();

		/* Validate reject toast Message */
		pages.approverSectionPage.validateClaimRejectedSuccessfully();

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Refer Back a claim after clicking on claim number
	 *
	 * @param empCode
	 * @param password
	 * @param myApprovalMinorTab
	 * @param mapDataKeyValues
	 * 
	 * @author Sajal Jain
	 */
	public synchronized static void referBackClaimThroughClaimNumber(String empCode, String password,
			String myApprovalMinorTab, Map<String, String> mapDataKeyValues) {

		/* Login To Application */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on My Approvals Tab */
		pages.homePage.clkMyApprovalsTab(myApprovalMinorTab);

		/* Click on the ClaimNumber under MyApprovals Tab */
		pages.homePage.clkClaimNumberMyApproval(claimNumber);

		/* Validate the data in Approver Section Page */
		
		if(mapDataKeyValues.get("category").equalsIgnoreCase("Cab Breakdown Exp"))
		{
			pages.approverSectionPage.validateClaimDetailsDataForBreakDown(claimNumber, mapDataKeyValues.get("employeeCode"));
		}
		else
		{
		
		pages.approverSectionPage.validateClaimDetailsData(claimNumber,
		
				mapDataKeyValues.get("employeeCode"), mapDataKeyValues.get("amount"));
		}
		if (mapDataKeyValues.get("category").equalsIgnoreCase("Broadband Expenses")
				|| mapDataKeyValues.get("category").equalsIgnoreCase("Cell Phone")) {
			/* Click on the Document Link */
			pages.approverSectionPage.clkSupportingDocumentLnk(mapDataKeyValues.get("fileFormat"));

		} else if (mapDataKeyValues.get("category").equalsIgnoreCase("Zero Expense")) {

		} else {
			/* Click on the Document Link */
			pages.approverSectionPage
					.clkSupportingDocumentLnk(WebActionUtil.getSampleFileName(mapDataKeyValues.get("fileFormat")));
		}

		/* Enter the remarks in the Remarks Textarea field */
		pages.approverSectionPage.setRemarksTa(prop_constants.getProperty("referback_remarks"));

		/* Click on Referback button */
		pages.approverSectionPage.clkReferBackBtn();

		/* Click on Yes Button */
		pages.approverSectionPage.clkYesBtn();

		/* Validate the referback toast message */
		pages.approverSectionPage.validateClaimReferredBackSuccessfully();

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Approve the claim by ES Approver
	 * 
	 * 
	 * @param empCode
	 * @param password
	 * @param mapDataKeyValues
	 * @return
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized static void esApprove(String esaEmpCode, String password, Map<String, String> mapDataKeyValues,
			WebDriver driver) {
		/* Login to application with ES Approver credentials */
		pages.loginpage.loginToApplication(esaEmpCode, password);

		/* Click on Pending Action present under Hamburger menu */
		pages.eshomePage.clkPendingAction();

		/* Click on search button by entering the particular claim number */
		pages.pendingActionPage.searchClaimNo(claimNumber);

		/* Validate the ES for Additional Info page */
		pages.esForAdditionalInfoPage.validateESforAdditionalInfoPage("CVCSAction");

		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		eventFiringWebDriver.executeScript("document.getElementById('leftPane').scrollTop=2200");

		/* Click on Edit Link */
		pages.esForAdditionalInfoPage.clkEditLink(mapDataKeyValues.get("category"),
				mapDataKeyValues.get("description"));
		eventFiringWebDriver.executeScript("document.getElementById('leftPane').scrollTop=2200");

		if (mapDataKeyValues.get("category").equals(prop_constants.getProperty("relocationExpensesClaim"))) {
			WebActionUtil.select_CalendarDate("txtEndDate", mapDataKeyValues.get("endDate"), "End Date");
		}

		/* Enter the amount to be approved in Approved Amount Textarea field */
		pages.esForAdditionalInfoPage.setApprovedAmount(mapDataKeyValues.get("approvedAmount"));

		/* Click on Save Link */
		pages.esForAdditionalInfoPage.clkSaveLink();

		eventFiringWebDriver.executeScript("document.getElementById('leftPane').scrollTop=2200");

		driver.findElement(By.name("lblNarration")).click();
		try {
			Robot r = new Robot();
			for (int i = 0; i < 4; i++) {
				r.keyPress(KeyEvent.VK_TAB);
				r.keyRelease(KeyEvent.VK_TAB);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Enter the remarks in remarks textfield */
		pages.esForAdditionalInfoPage.setRemarks(prop_constants.getProperty("approve_remarks"));

		/* Click on Approve button */
		pages.esForAdditionalInfoPage.clkApproveButton();

		/* Validate the status of the claim in Pending Action Page */
		pages.pendingActionPage.validateEsStatus(prop_constants.getProperty("approved_by_es"));

		/* Click on App Home Link */
		pages.pendingActionPage.clkAppHomeLink();

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Approve the claim by ES Approver
	 * 
	 * 
	 * @param empCode
	 * @param password
	 * @param mapDataKeyValues
	 * @return
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized static void esApprove1(String esaEmpCode, String password, Map<String, String> mapDataKeyValues,
			WebDriver driver) {
		/* Login to application with ES Approver credentials */
		pages.loginpage.loginToApplication(esaEmpCode, password);

		/* Click on Pending Action present under Hamburger menu */
		pages.eshomePage.clkPendingAction();

		/* Click on search button by entering the particular claim number */
		pages.pendingActionPage.searchClaimNo(claimNumber);

		/* Validate the ES for Additional Info page */
		pages.esForAdditionalInfoPage.validateESforAdditionalInfoPage("CVCSAction");

		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		eventFiringWebDriver.executeScript("document.getElementById('leftPane').scrollTop=2200");

		/* Click on Edit Link */
		pages.esForAdditionalInfoPage.clkEditLink(mapDataKeyValues.get("category"),
				mapDataKeyValues.get("description"));
		eventFiringWebDriver.executeScript("document.getElementById('leftPane').scrollTop=2200");

		/* Enter the amount to be approved in Approved Amount Textarea field */
		pages.esForAdditionalInfoPage.setApprovedAmount(mapDataKeyValues.get("approvedAmount"));

		/* Click on Save Link */
		pages.esForAdditionalInfoPage.clkSaveLink();

		eventFiringWebDriver.executeScript("document.getElementById('leftPane').scrollTop=2200");

		driver.findElement(By.name("lblNarration")).click();
		try {
			Robot r = new Robot();
			for (int i = 0; i < 4; i++) {
				r.keyPress(KeyEvent.VK_TAB);
				r.keyRelease(KeyEvent.VK_TAB);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* Enter the remarks in remarks textfield */
		pages.esForAdditionalInfoPage.setRemarks(prop_constants.getProperty("approve_remarks"));

		/* Click on Approve button */
		pages.esForAdditionalInfoPage.clkApproveButton();

		/* Validate the status of the claim in Pending Action Page */
		pages.pendingActionPage.validateEsStatus(prop_constants.getProperty("approved_by_es"));

		/* Click on App Home Link */
		pages.pendingActionPage.clkAppHomeLink();

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Refer back a claim by ES Approver
	 * 
	 * 
	 * @param empCode
	 * @param password
	 * @param mapDataKeyValues
	 * @return
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized static void esReferBack(String esaEmpCode, String password,
			Map<String, String> mapDataKeyValues, WebDriver driver) {

		/* Login to application with ES Approver credentials */
		pages.loginpage.loginToApplication(esaEmpCode, password);

		/* Click on Pending Action present under Hamburger menu */
		pages.eshomePage.clkPendingAction();

		/* Click on search button by entering the particular claim number */
		pages.pendingActionPage.searchClaimNo(claimNumber);

		/* Validate the ES for Additional Info page */
		pages.esForAdditionalInfoPage.validateESforAdditionalInfoPage("CVCSAction");

		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		eventFiringWebDriver.executeScript("document.getElementById('leftPane').scrollTop=2200");

		WebActionUtil.poll(1000);

		driver.findElement(By.name("lblNarration")).click();
		try {
			Robot r = new Robot();
			for (int i = 0; i < 5; i++) {
				r.keyPress(KeyEvent.VK_TAB);
				r.keyRelease(KeyEvent.VK_TAB);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		if (mapDataKeyValues.get("category").equalsIgnoreCase("Broadband Expenses")
//				|| mapDataKeyValues.get("category").equalsIgnoreCase("Cell Phone")) {
//			/* Validates the attached voucher */
//		//pages.esForAdditionalInfoPage.validateAttachedVoucher(mapDataKeyValues.get("fileFormat"));
//
//		} else if (mapDataKeyValues.get("category").equalsIgnoreCase("Zero Expense") || mapDataKeyValues.get("category").equalsIgnoreCase("Cab Breakdown Exp")) 
//				{
//
//		} else {
//			/* Validates the attached voucher */
////			pages.esForAdditionalInfoPage
////					.validateAttachedVoucher(WebActionUtil.getSampleFileName(mapDataKeyValues.get("fileFormat")));
//		}

		/* Enter the remarks in Remarks textarea field */
		pages.esForAdditionalInfoPage.setRemarks(prop_constants.getProperty("referback_remarks"));

		/* Click on referback button */
		pages.esForAdditionalInfoPage.clkReferBackButton();

		/* Angular Page Load */
		WebActionUtil.waitForAngularPageToLoad();

		/* Validate the status of the claim in Pending action Page */
		pages.pendingActionPage
				.validateEsStatus(prop_constants.getProperty("referred_back_by_es_for_additional_information"));

		/* Click on App Home Link */
		pages.pendingActionPage.clkAppHomeLink();

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to Reject the claim by ES Approver
	 * 
	 * 
	 * @param empCode
	 * @param password
	 * @param mapDataKeyValues
	 * @return
	 * 
	 * @author Vivek Dogra
	 */
	public synchronized static void esReject(String esaEmpCode, String password, Map<String, String> mapDataKeyValues,
			WebDriver driver) {

		/* Login to application with ES Approver credentials */
		pages.loginpage.loginToApplication(esaEmpCode, password);

		/* Click on Pending Action present under Hamburger menu */
		pages.eshomePage.clkPendingAction();

		/* Click on search button by entering the particular claim number */
		pages.pendingActionPage.searchClaimNo(claimNumber);

		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		eventFiringWebDriver.executeScript("document.getElementById('leftPane').scrollTop=2200");

		driver.findElement(By.name("lblNarration")).click();
		try {
			Robot r = new Robot();
			for (int i = 0; i < 4; i++) {
				r.keyPress(KeyEvent.VK_TAB);
				r.keyRelease(KeyEvent.VK_TAB);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mapDataKeyValues.get("category").equalsIgnoreCase("Broadband Expenses")
				|| mapDataKeyValues.get("category").equalsIgnoreCase("Cell Phone")) {
			/* Validates the attached voucher */
			pages.esForAdditionalInfoPage.validateAttachedVoucher(mapDataKeyValues.get("fileFormat"));

		} else if (mapDataKeyValues.get("category").equalsIgnoreCase("Zero Expense")|| mapDataKeyValues.get("category").equalsIgnoreCase("Cab Breakdown Exp")) {

		} else {
			/* Validates the attached voucher */
			pages.esForAdditionalInfoPage
					.validateAttachedVoucher(WebActionUtil.getSampleFileName(mapDataKeyValues.get("fileFormat")));
		}

		/* Enter the remarks in Remarks textarea field */
		pages.esForAdditionalInfoPage.setRemarks(mapDataKeyValues.get("reason"));

		/* Click on reject button */
		pages.esForAdditionalInfoPage.clkRejectButton();

		WebActionUtil.waitForAngularPageToLoad();

		/* Validate the status of the claim in Pending action Page */
		pages.pendingActionPage.validateEsStatus(prop_constants.getProperty("rejected_by_es"));

		/* Click on App Home Link */
		pages.pendingActionPage.clkAppHomeLink();

		/* Logout from the Application */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: Method to update the claim
	 * 
	 * @author vikas.kc
	 * @param empCode
	 * @param password
	 * @param mapDataKeyValues
	 */
	public synchronized static void updateClaim(String empCode, String password, Map<String, String> mapDataKeyValues,
			String referbackStatus) {
		/* Login to application as Employee */
		pages.loginpage.loginToApplication(empCode, password);

		/* Click on claim number */
		pages.homePage.clkClaimNumberMyClaim(claimNumber);

		/* Validate refer back status */
		pages.viewCashClaimPage.validateReferBackText(referbackStatus);

		/* Click on edit expense */
		pages.viewCashClaimPage.clkEditIcn(mapDataKeyValues.get("category"));

		/* Retrieve data from Excel sheet for the particular claim to be updated */
		String[] data = { mapDataKeyValues.get("amount"), mapDataKeyValues.get("reason") };

		/* Edit category fields */
		pages.viewCashClaimPage.editCategoryDetails(getCategoryValues().get(mapDataKeyValues.get("category")), data);

		/* Click on update expense */
		pages.viewCashClaimPage.clkUpdateExpenseButton();

		/* Enter reason */
		pages.viewCashClaimPage.setReasonForClaimTextfield(mapDataKeyValues.get("reason"));

		/* Check declaration check box */
		pages.viewCashClaimPage.clkDeclareCheckbox();

		/* Click on submit */
		pages.viewCashClaimPage.clkSubmitButton();

		/* Click on yes button */
		pages.viewCashClaimPage.clkOnYesButton();

		/* Logout as employee */
		pages.logoutPage.selectProfiledd(prop_constants.getProperty("expectedLogoutUrl"));

	}

	/**
	 * Description: This Method implements to edit category details
	 * 
	 * @author vikas.kc
	 * @param category
	 * @param data
	 * @return
	 */
	public static synchronized String[] getDataForEditCategoryDetails(Category category,
			Map<String, String> mapDataKeyValues) {
		String[] data = null;
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
			data = new String[1];
			data[0] = mapDataKeyValues.get("amount");
			break;
		}
		case BROADBAND_EXPENSES: {
			data = new String[1];
			data[0] = mapDataKeyValues.get("amount");
			break;
		}
		case COVID19_EVACUATION_ADVANCE: {
			data = new String[2];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("expectedDateOfTravel");
			break;
		}
		case HE_ADVANCE_OTHER: {
			data = new String[1];
			data[0] = mapDataKeyValues.get("amount");
			break;
		}
		case SPECIALIZED_TRAINING_EXPENSE: {
			data = new String[1];
//			data[0] = mapDataKeyValues.get("amount");
			data[0] = mapDataKeyValues.get("courseStartDate");
			break;
		}
		case VISA_EXPENSES: {
			data = new String[4];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("country");
			data[2] = mapDataKeyValues.get("visaType");
			data[3] = mapDataKeyValues.get("visaExpenseType");
			break;
		}
		case CAB_BREAKDOWN_EXP: {
			data = new String[1];
			data[0] = mapDataKeyValues.get("transportID");
			break;
		}
		case CELL_PHONE: {
			data = new String[1];
			data[0] = mapDataKeyValues.get("amount");
			break;
		}
		case CONVEYANCE: {
			data = new String[1];
			data[0] = mapDataKeyValues.get("expenseDate");
			break;
		}
		case TECHNICAL_CERTIFICATION: {
			data = new String[2];
			data[0] = mapDataKeyValues.get("amount");
			data[1] = mapDataKeyValues.get("certificationDate");
			break;
		}
		}
		return data;
	}

	/**
	 * Description: Method to close Feedback Window
	 * 
	 * @author vikas.kc
	 * 
	 */
	public synchronized static void closeFeedbackWindow() {

		WebActionUtil.poll(2000l);

		String currentwinhandle = WebActionUtil.driver.getWindowHandle();
		Set<String> winHandles = WebActionUtil.driver.getWindowHandles();

		for (String winhandle : winHandles) {

			WebActionUtil.driver.switchTo().window(winhandle);
			String title = WebActionUtil.driver.getTitle();

			if (title.contains("Feedback System")) {

				WebActionUtil.driver.close();
				WebActionUtil.info("FeedBack System is closed Successfully.");
				WebActionUtil.driver.switchTo().window(currentwinhandle);

				break;
			}
		}

	}

	public static synchronized Map<String, String> getCategoryMapData(String... values) {

		Map<String, String> mapDataKeyValues = null;
		switch (PagesActionUtil.getCategoryValues().get(values[1])) {
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

		case REP_AND_MAINT_BLDG:
		case REPAIR_MAINT_OTHERS:
		case SALARY_MEDICAL_ADVANCE:
		case STAFF_RECRUITMENT:
		case STAFF_WELFARE:
		case TRANSFER_CLAIM:
		case VISA_EXPENSES_OLD:
		case ZERO_EXPENSES: {
			String[] keys = new String[] { "employeeCode", "category", "description", "amount", "reason", "esaEmpcode",
					"fileFormat", "approvedAmount", "checkBoxNames" };
			mapDataKeyValues = IntStream.range(0, keys.length).boxed()
					.collect(Collectors.toMap(i -> keys[i], i -> values[i]));
			break;
		}
		case RELOCATION_EXPENSES: {
			String[] keys = new String[] { "employeeCode", "category", "description", "amount", "reason", "esaEmpcode",
					"fileFormat", "approvedAmount", "checkBoxNames", "endDate" };
			mapDataKeyValues = IntStream.range(0, keys.length).boxed()
					.collect(Collectors.toMap(i -> keys[i], i -> values[i]));
			break;
		}
		case BROADBAND_EXPENSES: {
			String[] keys = new String[] { "employeeCode", "category", "description", "amount", "mobileNumber",
					"fromDate", "toDate", "reason", "esaEmpcode", "fileFormat", "approvedAmount", "checkBoxNames" };
			mapDataKeyValues = IntStream.range(0, keys.length).boxed()
					.collect(Collectors.toMap(i -> keys[i], i -> values[i]));
			break;
		}
		case COVID19_EVACUATION_ADVANCE: {

			break;
		}
		case HE_ADVANCE_OTHER: {

			break;
		}
		case SPECIALIZED_TRAINING_EXPENSE: {
			String[] keys = new String[] { "employeeCode", "category", "description", "amount", "universityName",
					"courseName", "modeOfTraining", "courseStartDate", "courseEndDate", "reason", "esaEmpcode",
					"fileFormat", "approvedAmount", "checkBoxNames" };
			mapDataKeyValues = IntStream.range(0, keys.length).boxed()
					.collect(Collectors.toMap(i -> keys[i], i -> values[i]));
			break;
		}
		case VISA_EXPENSES: {
			String[] keys = new String[] { "employeeCode", "category", "description", "amount", "reason", "esaEmpcode",
					"fileFormat", "approvedAmount", "country", "visaType", "visaExpenseType", "iAuraCaseNumber",
					"checkBoxNames" };
			mapDataKeyValues = IntStream.range(0, keys.length).boxed()
					.collect(Collectors.toMap(i -> keys[i], i -> values[i]));
			break;
		}
		case CAB_BREAKDOWN_EXP: {
			String[] keys = new String[] { "employeeCode", "category", "description", "transportID",
					"reason", "esaEmpcode", "fileFormat", "approvedAmount", "checkBoxNames" };
			mapDataKeyValues = IntStream.range(0, keys.length).boxed()
					.collect(Collectors.toMap(i -> keys[i], i -> values[i]));
			break;
		}
		case CELL_PHONE: {

			String[] keys = new String[] { "employeeCode", "category", "description", "amount", "mobileNumber",
					"billFromDate", "billToDate", "plan", "reason", "esaEmpcode", "fileFormat", "approvedAmount",
					"checkBoxNames", "internationalCharge" };
			mapDataKeyValues = IntStream.range(0, keys.length).boxed()
					.collect(Collectors.toMap(i -> keys[i], i -> values[i]));

			break;
		}
		case CONVEYANCE: {
			String[] keys = new String[] { "employeeCode", "category", "description", "transportMode", "amount",
					"expenseDate", "totalDistanceInKM", "reason", "esaEmpcode", "fileFormat", "approvedAmount",
					"checkBoxNames" };
			mapDataKeyValues = IntStream.range(0, keys.length).boxed()
					.collect(Collectors.toMap(i -> keys[i], i -> values[i]));
			break;
		}
		case TECHNICAL_CERTIFICATION: {
			String[] keys = new String[] { "employeeCode", "category", "description", "amount", "certificationDate",
					"reason", "esaEmpcode", "fileFormat", "approvedAmount", "checkBoxNames" };
			mapDataKeyValues = IntStream.range(0, keys.length).boxed()
					.collect(Collectors.toMap(i -> keys[i], i -> values[i]));
			break;
		}

		}

		return mapDataKeyValues;
	}
}
