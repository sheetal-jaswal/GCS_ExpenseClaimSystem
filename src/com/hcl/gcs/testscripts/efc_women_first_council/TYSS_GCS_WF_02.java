package com.hcl.gcs.testscripts.efc_women_first_council;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*TestCaseId:TYSS_GCS_WandF_02
 *Claim Type:EFC_Women_First_Council
 *TestScript Name: TYSS_GCS_WandF_02
 *Description: To verify EFC :Women first Council Claim Submit by the Employe, RM Refer Back and Reject the claim for the voucher upto 999999
 *Author : Aatish Slathia, Manish Kumar C D.
 * 
 */

public class TYSS_GCS_WF_02 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  EFC_Women_First_Council where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify EFC :Women first Council Claim Submit by the Employe, RM Refer Back and Reject the claim for the voucher upto 999999")
	public synchronized void TC_TYSS_GCS_WF_02(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] {

				{ "Employee", "InitiateClaim" }, { "RM", "ReferBackClaimThroughClaimNumber" },
				{ "Employee", "InitiateClaim" }, { "RM", "RejectClaimThroughClaimNumber" },
				{ "Employee", "RM_Reject_ClaimID" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}
