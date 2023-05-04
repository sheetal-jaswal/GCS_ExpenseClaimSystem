package com.hcl.gcs.testscripts.transferclaim;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/**
 * TestCaseId: TYSS_GCS_TransferClaim_04 Claim Type : Transfer Claim TestScript
 * Name: TYSS_GCS_TransferClaim_04 Description: Verify Employee can raise New
 * Transfer Claim request EHS approver reject the Voucher Claim upto INR 999999.
 * 
 * Author: Manish Kumar C D
 */

public class TYSS_GCS_TransferClaim_04 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  TransferClaim where SlNo ='4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Employee can raise New Transfer Claim request EHS approver reject the Voucher Claim upto INR 999999")
	public synchronized void TC_TYSS_GCS_TransferClaim_04(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames)  {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode,  category, description, amount, reason, esaEmpcode,
				fileFormat, approvedAmount, checkBoxNames );


		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "BUHR", "ReferBackClaimThroughClaimNumber" }, { "Employee", "BUHR_REFERBACK_CLAIMID" },
				{ "BUHR", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHR_APPROVE_HOMEPAGE" },
				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_REFERBACK_CLAIMID" },
				{ "BUHR", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHR_APPROVE_HOMEPAGE" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_REJECT_HOMEPAGE" }, };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
