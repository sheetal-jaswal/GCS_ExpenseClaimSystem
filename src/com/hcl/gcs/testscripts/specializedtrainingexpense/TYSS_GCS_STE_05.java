package com.hcl.gcs.testscripts.specializedtrainingexpense;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 *TestCaseId : TYSS_GCS_STE_05
 *Claim Type : Specialized Training Expense
 *TestScript Name : TYSS_GCS_STE_05
 *Description: Verify 'Specialized Training Expense' the status in employee after getting approved by RM, BU Head and reject by BUHR
 *Author : Vikas
 * 
 */

public class TYSS_GCS_STE_05 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from SpecializedTrainingExpense where SlNo ='5'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify 'Specialized Training Expense' the status in employee after getting approved by RM, BU Head and reject by BUHR")
	public synchronized void TC_TYSS_GCS_STE_05(String slNo, String employeeCode, String category, String description,
			String amount, String universityName, String courseName, String modeOfTraining, String courseStartDate,
			String courseEndDate, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, universityName, courseName, modeOfTraining, courseStartDate, courseEndDate, reason, esaEmpcode,
				fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_HOMEPAGE" },
				{ "BU Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHead_Approve_HOMEPAGE" },
				{ "BUHR", "RejectClaimThroughClaimNumber" }, { "Employee", "BUHR_Reject_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}
