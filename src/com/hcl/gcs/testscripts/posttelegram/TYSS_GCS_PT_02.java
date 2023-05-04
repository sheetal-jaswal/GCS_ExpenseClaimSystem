package com.hcl.gcs.testscripts.posttelegram;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/**
 * TestCaseId: TYSS_GCS_PT_02 Claim Type : Post Telegram claim TestScript Name:
 * TYSS_GCS_PT_02 Description: Verify Post Telegram claim submit by Employee, RM
 * referbacks the claim,Employee resubmits the claim,RM rejects the claim for
 * the Voucher Claim INR 12000 and below.
 * 
 * Author: Manish Kumar C D
 */

public class TYSS_GCS_PT_02 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  PostandTelegram where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Post Telegram claim submit by Employee, RM referbacks the claim,Employee resubmits the claim,RM rejects the claim for the Voucher Claim INR 12000 and below")
	public synchronized void TC_TYSS_GCS_PT_02(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_REFERBACK_CLAIMID" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_REJECT_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
