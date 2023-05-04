package com.hcl.gcs.testscripts.staffrequirtment;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_STR_09
 * Claim Type: StaffRequirtment
 * TestScript Name: TYSS_GCS_STR_09
 * Description:  To verify Staff Recruitment Claim Submit by the Employee and Approved by RM, L3 Head, L1 Head, ES for Voucher Claim INR 120001 - 600000 
 * Author: Suganthini
 */
public class TYSS_GCS_STR_09 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from StaffRequirtment where SlNo ='9'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify Staff Recruitment Claim Submit by the Employee and Approved by RM, L3 Head, L1 Head, ES for Voucher Claim INR 120001 - 600000")
	public synchronized void TC_TYSS_GCS_STR_09(String slNo, String employeeCode, String category, String amount,
			String description, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {
		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);
		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3HEAD_APPROVE_HOMEPAGE" },
				{ "L1 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L1HEAD_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", " ESAPPROVER_APPROVE_CLAIMID" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
