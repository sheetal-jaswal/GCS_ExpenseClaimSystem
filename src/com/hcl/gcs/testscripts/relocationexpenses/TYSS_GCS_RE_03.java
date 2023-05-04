package com.hcl.gcs.testscripts.relocationexpenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 *TestCaseId : TYSS_GCS_RE_03
 *Claim Type : Relocation Expenses
 *TestScript Name : TYSS_GCS_RE_03
 *Description: In 'Relocation Expenses' Verify the status of Employee after getting approved by BUHR and rejected by RM
 *Author : Vikas
 * 
 */

public class TYSS_GCS_RE_03 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from RelocationExpenses where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: In 'Relocation Expenses' Verify the status of Employee after getting approved by BUHR and rejected by RM ")
	public synchronized void TC_TYSS_GCS_RE_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames, String endDate) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames, endDate);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "BUHR", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHR_Approve_ClaimID" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_REJECT_ClaimID" }, };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}
