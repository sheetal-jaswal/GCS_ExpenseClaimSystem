package com.hcl.gcs.testscripts.visaexpensesold;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 *TestCaseId : TYSS_GCS_VEO_01
 *Claim Type : Visa Expenses Old
 *TestScript Name : TYSS_GCS_VEO_01
 *Description: Verify 'Visa Expenses Old' the status of the employee after getting approved by RM, ES approvers
 *Author : Vikas
 * 
 */

public class TYSS_GCS_VEO_01 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from VisaExpensesOld where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify 'Visa Expenses Old' the status of the employee after getting approved by RM, ES approvers")
	public synchronized void TC_TYSS_GCS_VEO_01(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_ClaimID" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_Approve_CLAIMID" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
