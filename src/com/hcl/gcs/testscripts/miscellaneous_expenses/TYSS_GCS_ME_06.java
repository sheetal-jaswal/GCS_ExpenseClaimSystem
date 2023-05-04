package com.hcl.gcs.testscripts.miscellaneous_expenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_ME_06
 * Claim Type: EFC Grey Cells Council
 * TestScript Name: TYSS_GCS_ME_06
 * Description: Verify Miscellaneous expenses claim submit by employee and approved by RM, L3 head referback than employee resubmit the claim, RM approve it L3 head reject the claim for voucher claim INR 12001 - 30000
 * 
 * Author: Sajal jain
 */
public class TYSS_GCS_ME_06 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from MiscellaneousExpenses where SlNo ='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Miscellaneous expenses claim submit by employee and approved by RM, L3 head referback than employee resubmit the claim, RM approve it L3 head reject the claim for voucher claim INR 12001 - 30000")
	public synchronized void TC_TYSS_GCS_ME_06(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ReferbackClaimThroughClaimNumber" }, { "Employee", "L3head_referback_claimid" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L3head_reject_homepage" }, };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}