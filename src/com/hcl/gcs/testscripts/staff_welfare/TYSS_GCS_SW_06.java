package com.hcl.gcs.testscripts.staff_welfare;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_SW_06
 * Claim Type: Staff Welfare
 * TestScript Name: TYSS_GCS_SW_06
 * DDescription: Verify Staff welfare claim submit by employee, approved by RM, referback by L3 head, than employee resubmit the claim, approved by RM, L3 head reject the claim for voucher claim INR 21001 and 52500
 * Author: Sajal jain
 */
public class TYSS_GCS_SW_06 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from StaffWelfare where SlNo ='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Staff welfare claim submit by employee, approved by RM, referback by L3 head, than employee resubmit the claim, approved by RM, L3 head reject the claim for voucher claim INR 21001 and 52500")
	public synchronized void TC_TYSS_GCS_SW_06(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ReferbackClaimThroughClaimNumber" }, { "Employee", "L3head_Referback_claimid" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L3head_reject_homepage" }, };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}