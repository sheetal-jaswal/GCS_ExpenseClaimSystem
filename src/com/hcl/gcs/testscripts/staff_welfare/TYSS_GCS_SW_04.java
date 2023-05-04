package com.hcl.gcs.testscripts.staff_welfare;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_SW_04
 * Claim Type: Staff Welfare
 * TestScript Name: TYSS_GCS_SW_04
 * Description: Verify Staff welfare claim submit by employee, approved by RM, L4 head, referback by ES, employee resubmit the claim, ES rejects the claim for voucher claim INR 21000 and below
 * 
 * Author: Sajal jain
 */
public class TYSS_GCS_SW_04 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from StaffWelfare where SlNo ='4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Staff welfare claim submit by employee, approved by RM, L4 head, referback by ES, employee resubmit the claim, ES rejects the claim for voucher claim INR 21000 and below")
	public synchronized void TC_TYSS_GCS_SW_04(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4head_Approve_homepage" },
				{ "ES Approver", "ReferbackClaim" }, { "Employee", "ESApprover_referback_claimid" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESApprover_reject_homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}