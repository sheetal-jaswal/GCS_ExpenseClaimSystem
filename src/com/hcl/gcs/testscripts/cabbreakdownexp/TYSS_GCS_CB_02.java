package com.hcl.gcs.testscripts.cabbreakdownexp;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 *TestCaseId : TYSS_GCS_CB_02
 *Claim Type : Cab Break down Expense.
 *TestScript Name : TYSS_GCS_CB_02
 *Description: In 'Cab Break down Expense' Verify the status of employee after getting refer back by RM, ES and rejected by ES for voucher claim upto 999999.
 *Author : Vikas K C.
 * 
 */

public class TYSS_GCS_CB_02 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from CabBreakdownExp where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: In 'Cab Break down Expense' Verify the status of employee after getting referback by RM, ES and rejected by ES for voucher claim upto 999999.")
	public synchronized void TC_TYSS_GCS_CB_02(String slNo, String employeeCode, String category, String description,
			String transportID, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				transportID, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_ReferBack_ClaimID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_HomePage" },
				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_Reject_HomePage" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
