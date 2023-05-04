package com.hcl.gcs.testscripts.hra_advance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_HandA_03
 * Claim Type: HRA_Advance. 
 * TestScript Name: TYSS_GCS_HandA_03
 * Description: To verify HRA Advance Claim Submit by the Employee and Approved
 * by RM, ReferBack and Reject by ES for Voucher Claim upto INR 999999.
 * 
 * Author: Aatish Slathia
 */

public class TYSS_GCS_HA_03 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  HRA_Advance where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify HRA Advance Claim Submit by the Employee and Approved by RM, ReferBack and Reject by ES for Voucher Claim upto INR 999999.")
	public synchronized void TC_TYSS_GCS_HA_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_ClaimID" },

				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_Reject_HomePage" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
