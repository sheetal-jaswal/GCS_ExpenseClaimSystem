package com.hcl.gcs.testscripts.legalexpenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*TestCaseId:TYSS_GCS_LandE_03
 *Claim Type:Legal Expenses
 *TestScript Name: TYSS_GCS_LandE_03
 *Description: To Verify Legal Expenses claim submit by Employee L4 Head Refer Back Employe resubmit the claim and L4 HeadReject the  claim
 *Author : Aatish Slathia
 * 
 */

public class TYSS_GCS_LandE_03 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  Legal_Expenses where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To Verify Legal Expenses claim submit by Employee L4 Head Refer Back Employe resubmit the claim and L4 HeadReject the  claim")
	public synchronized void TC_TYSS_GCS_LandE_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ReferBackClaimThroughClaimNumber" }, { "Employee", "L4HEAD_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L4HEAD_REJECT_CLAIMID" }, };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}