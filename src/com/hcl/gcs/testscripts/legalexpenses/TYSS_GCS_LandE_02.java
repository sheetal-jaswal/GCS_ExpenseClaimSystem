package com.hcl.gcs.testscripts.legalexpenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*TestCaseId:TYSS_GCS_LandE_02
 *Claim Type:Legal Expenses
 *TestScript Name: TYSS_GCS_LandE_02
 *Description: To Verify Legal Expenses claim submit by Employee RM Refer Back the claim, Employee Resubmit the claim  RM Reject the claim voucher INR 12000 and below
 *Author : Aatish Slathia
 * 
 */

public class TYSS_GCS_LandE_02 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  Legal_Expenses where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To Verify Legal Expenses claim submit by Employee RM Refer Back the claim, Employee Resubmit the claim  RM Reject the claim voucher INR 12000 and below ")
	public synchronized void TC_TYSS_GCS_LandE_02(String slNo, String employeeCode, String category, String description,
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
