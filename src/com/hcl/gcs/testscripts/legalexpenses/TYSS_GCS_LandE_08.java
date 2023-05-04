package com.hcl.gcs.testscripts.legalexpenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*TestCaseId:TYSS_GCS_LandE_08
 *Claim Type:Legal Expenses
 *TestScript Name: TYSS_GCS_LandE_08
*Description: Verify Legal Expenses claim submit by employee,approved by RM, L4 head and referback by L2 head ,employee resubmit the claim, then approved by RM, L4 head, reject by L2 head for voucher claim INR 30001 - 120000
*
*Author : Aatish,Sajal
 */

public class TYSS_GCS_LandE_08 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  Legal_Expenses where SlNo ='8'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Legal Expenses claim submit by employee,approved by RM, L4 head and referback by L2 head ,employee resubmit the claim, then approved by RM, L4 head, reject by L2 head for voucher claim INR 30001 - 120000")
	public synchronized void TC_TYSS_GCS_LandE_08(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4head_APPROVE_HOMEPAGE" },
				{ "L2 Head", "ReferbackClaimThroughClaimNumber" }, { "Employee", "L2head_referback_claimid" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4head_APPROVE_HOMEPAGE" },
				{ "L2 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L2head_reject_homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}
