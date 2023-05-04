package com.hcl.gcs.testscripts.specializedtrainingexpense;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 *TestCaseId : TYSS_GCS_STE_02
 *Claim Type : Specialized Training Expense
 *TestScript Name : TYSS_GCS_STE_02
 *Description: Verify 'Specialized Training Expense' the status of employee after getting refer back by RM, BU Head approvers and rejected by ES
 *Author : Vikas
 * 
 */

public class TYSS_GCS_STE_02 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from SpecializedTrainingExpense where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify 'Specialized Training Expense' the status of employee after getting referback by RM, BU Head approvers and rejected by ES")
	public synchronized void TC_TYSS_GCS_STE_02(String slNo, String employeeCode, String category, String description,
			String amount, String universityName, String courseName, String modeOfTraining, String courseStartDate,
			String courseEndDate, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, universityName, courseName, modeOfTraining, courseStartDate, courseEndDate, reason, esaEmpcode,
				fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_ReferBack_ClaimID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_HOMEPAGE" },
				{ "BU Head", "ReferBackClaimThroughClaimNumber" }, { "Employee", "BUHead_ReferBack_ClaimID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "BU Head", "ApproveClaimThroughClaimNumber" },
				{ "BUHR", "ReferBackClaimThroughClaimNumber" }, { "Employee", "BUHR_ReferBack_ClaimID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "BU Head", "ApproveClaimThroughClaimNumber" },
				{ "BUHR", "ApproveClaimThroughClaimNumber" }, { "ES Approver", "RejectClaim" },
				{ "Employee", "ESAPPROVER_REJECT_HomePage" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}
