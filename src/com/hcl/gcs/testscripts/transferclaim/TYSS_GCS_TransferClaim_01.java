package com.hcl.gcs.testscripts.transferclaim;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/**
 * TestCaseId: TYSS_GCS_TransferClaim_01 Claim Type : Transfer Claim TestScript
 * Name: TYSS_GCS_TransferClaim_01 Description: Verify Employee can raise New
 * Transfer Claim request for Voucher Claim upto INR 999999.
 * 
 * Author: Manish Kumar C D
 */

public class TYSS_GCS_TransferClaim_01 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  TransferClaim where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Employee can raise New Transfer Claim request for Voucher Claim upto INR 999999")
	public synchronized void TC_TYSS_GCS_TransferClaim_01(String slNo, String employeeCode, String category,
			String description, String amount, String reason, String esaEmpcode, String fileFormat,
			String approvedAmount, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "BUHR", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHR_APPROVE_HOMEPAGE" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_APPROVE_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
