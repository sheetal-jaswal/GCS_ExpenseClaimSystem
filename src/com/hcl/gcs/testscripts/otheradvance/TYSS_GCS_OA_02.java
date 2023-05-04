package com.hcl.gcs.testscripts.otheradvance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_OA_02
 * Claim Type: Other Advance.
 * TestScript Name: To verify HRA Advance Claim Submit by the Employee and RM Refer Backs and rejects the Claim for Voucher Claim upto INR 999999.
 * 
 * Author: Aatish Slathia
 */

public class TYSS_GCS_OA_02 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  OtherAdvance where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify Other Advance Claim Submit by the Employee, ES Refer back and Rejects the claim for Voucher Claim upto INR 999999.")
	public synchronized void TC_TYSS_GCS_OA_02(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_Reject_HomePage" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}
