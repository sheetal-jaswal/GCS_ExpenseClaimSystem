package com.hcl.gcs.testscripts.staffrequirtment;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_STR_10
 * Claim Type: StaffRequirtment
 * TestScript Name: TYSS_GCS_STR_10
 * Description:   To Verify staff recruitment claim submit by the employee and and Referback and Reject by L1 head for Voucher Claim INR 120001-600000 
 * Author: Suganthini
 */
public class TYSS_GCS_STR_10 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from StaffRequirtment where SlNo ='10'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To Verify staff recruitment claim submit by the employee and and Referback and Reject by L1 head for Voucher Claim INR 120001-600000 ")
	public synchronized void TC_TYSS_GCS_STR_10(String slNo, String employeeCode, String category, String amount,
			String description, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {
		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);
		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3HEAD_APPROVE_HOMEPAGE" },
				{ "L1 Head", "ReferBackClaimThroughClaimNumber" }, { "Employee", "L1HEAD_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3HEAD_APPROVE_HOMEPAGE" },
				{ "L1 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L1HEAD_REJECT_HOMEPAGE" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
