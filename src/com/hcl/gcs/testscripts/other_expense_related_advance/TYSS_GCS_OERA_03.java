package com.hcl.gcs.testscripts.other_expense_related_advance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_OERA_03
 * Claim Type: Other Expense Related Advance
 * TestScript Name: TYSS_GCS_OERA_03
 * Description: Verify Other expense related advance claim submit by employee, approved by RM, Referback by ES, employee resubmit the claim, ES reject the claim for voucher claim upto INR 999999
 * 
 * Author: Sajal jain
 */
public class TYSS_GCS_OERA_03 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from OtherExpenseRelatedAdvance where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Other expense related advance claim submit by employee, approved by RM,Referback by ES, employee resubmit the claim, ES reject the claim for voucher claim upto INR 999999")
	public synchronized void TC_TYSS_GCS_OERA_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_approve_homepage" },
				{ "ES Approver", "ReferbackClaim" }, { "Employee", "ESApprover_referback_claimid" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESApprover_reject_homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}