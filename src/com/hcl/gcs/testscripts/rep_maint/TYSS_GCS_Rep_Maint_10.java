package com.hcl.gcs.testscripts.rep_maint;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;
/*
 * TestCaseId: TYSS_GCS_Rep_Maint_10
 * Claim Type: Rep&Maint
 * TestScript Name: TYSS_GCS_Rep_Maint_10
 * Description:This test case is to Verify Rep&Maint Charges claim submit by Employee and Refer and Rejected by L1 Head Voucher Up to 120001  
 * Author: Vivek Dogra
 */

public class TYSS_GCS_Rep_Maint_10 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from RepMaint where SlNo ='10'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Rep&Maint Charges claim submit by Employee and Refer and Rejected by L1 Head Voucher Up to 120001 ")
	public synchronized void TC_TYSS_Rep_Maint_10(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3Head_Approve_Homepage" },
				{ "L1 Head", "ReferBackClaimThroughClaimNumber" }, { "Employee", "L1Head_Referback_Claimid" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3Head_Approve_Homepage" },
				{ "L1 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L1Head_Reject_Homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
