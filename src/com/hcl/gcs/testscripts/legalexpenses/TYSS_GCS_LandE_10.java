package com.hcl.gcs.testscripts.legalexpenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 *TestCaseId:TYSS_GCS_LandE_10
 *Claim Type:Legal Expenses
 *TestScript Name: TYSS_GCS_LandE_10
 *Description: Verify Legal Expenses claim submit by employee and approved by RM, L3 head, L1 head referback, employee resubmit the claim then approved by RM, L3 head, L1 head reject the claim for voucher claim INR 120001 - 600000
 *
 *Author : Aatish,Sajal
 */

public class TYSS_GCS_LandE_10 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  Legal_Expenses where SlNo ='10'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Legal Expenses claim submit by employee and approved by RM, L3 head, L1 head referback, employee resubmit the claim then approved by RM, L3 head, L1 head reject the claim for voucher claim INR 120001 - 600000")
	public synchronized void TC_TYSS_GCS_LandE_10(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3head_APPROVE_HOMEPAGE" },
				{ "L1 Head", "ReferbackClaimThroughClaimNumber" }, { "Employee", "L1head_referback_claimid" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3head_APPROVE_HOMEPAGE" },
				{ "L1 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L1head_reject_HOMEPAGE" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}
