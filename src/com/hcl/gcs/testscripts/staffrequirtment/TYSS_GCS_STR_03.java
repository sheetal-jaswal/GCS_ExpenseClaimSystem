package com.hcl.gcs.testscripts.staffrequirtment;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_STR_03
 * Claim Type: StaffRequirtment
 * TestScript Name: TYSS_GCS_STR_03
 * Description:To verify Staff Recruitment Claim Submit by the Employee, L4 Head Refer Backs the Claim. Employee Re-Submit the Claim, L4 Head Rejects the Claim for Voucher Claim INR 12000 and below   
 * Author: Suganthini
 */
public class TYSS_GCS_STR_03 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from StaffRequirtment where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:To verify Staff Recruitment Claim Submit by the Employee, L4 Head Refer Backs the Claim. Employee Re-Submit the Claim, L4 Head Rejects the Claim for Voucher Claim INR 12000 and below")
	public synchronized void TC_TYSS_GCS_STR_03(String slNo, String employeeCode, String category, String amount,
			String description, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ReferBackClaimThroughClaimNumber" }, { "Employee", "L4HEAD_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L4HEAD_REJECT_HOMEPAGE" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}
