package com.hcl.gcs.testscripts.staff_welfare;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_SW_10
 * Claim Type: Staff Welfare
 * TestScript Name: TYSS_GCS_SW_10
 * Description: Verify Staff welfare claim submit by employee, approved by RM, L3 head, referback by L1 head, employee resubmit the claim, approved by RM, L3 head, L1 head rejects the claim for voucher claim INR 280001-999999
 * Author: Sajal jain
 */
public class TYSS_GCS_SW_10 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from StaffWelfare where SlNo ='10'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Staff welfare claim submit by employee, approved by RM, L3 head, referback by L1 head, employee resubmit the claim, approved by RM, L3 head, L1 head rejects the claim for voucher claim INR 280001-999999")
	public synchronized void TC_TYSS_GCS_SW_10(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3head_Approve_homepage" },
				{ "L1 Head", "ReferBackClaimThroughClaimNumber" }, { "Employee", "L1head_Referback_claimid" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3head_Approve_homepage" },
				{ "L1 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L1head_Reject_homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}