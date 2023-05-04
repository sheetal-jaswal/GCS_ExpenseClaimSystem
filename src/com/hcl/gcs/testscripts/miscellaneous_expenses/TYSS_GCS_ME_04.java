package com.hcl.gcs.testscripts.miscellaneous_expenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_ME_04
 * Claim Type: Miscellaneous Expenses
 * TestScript Name: TYSS_GCS_ME_04
 * Description: Verify Miscellaneous expenses claim submit by employee and approved by RM, L4 head, referback by ES ,employee resubmit the claim then reject by ES for voucher claim INR 12000 and below
 * 
 * Author: Sajal jain
 */
public class TYSS_GCS_ME_04 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from MiscellaneousExpenses where SlNo ='4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Miscellaneous expenses claim submit by employee and approved by RM, L4 head, referback by ES ,employee resubmit the claim then reject by ES for voucher claim INR 12000 and below")
	public synchronized void TC_TYSS_GCS_ME_04(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4head_APPROVE_HOMEPAGE" },
				{ "ES Approver", "referbackClaim" }, { "Employee", "ESApprover_referback_claimid" },
				{ "ES Approver", "rejectClaim" }, { "Employee", "ESApprover_reject_homepage" }, };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}