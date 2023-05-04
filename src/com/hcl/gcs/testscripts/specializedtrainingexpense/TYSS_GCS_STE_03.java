package com.hcl.gcs.testscripts.specializedtrainingexpense;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 *TestCaseId : TYSS_GCS_STE_03
 *Claim Type : Specialized Training Expense
 *TestScript Name : TYSS_GCS_STE_03
 *Description: Verify 'Specialized Training Expense' the status in employee after reject by RM
 *Author : Vikas
 * 
 */

public class TYSS_GCS_STE_03 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from SpecializedTrainingExpense where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify 'Specialized Training Expense' the status in employee after reject by RM")
	public synchronized void TC_TYSS_GCS_STE_03(String slNo, String employeeCode, String category, String description,
			String amount, String universityName, String courseName, String modeOfTraining, String courseStartDate,
			String courseEndDate, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, universityName, courseName, modeOfTraining, courseStartDate, courseEndDate, reason, esaEmpcode,
				fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_Reject_ClaimID" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}
