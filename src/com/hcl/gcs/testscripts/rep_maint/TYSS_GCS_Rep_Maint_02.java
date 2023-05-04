package com.hcl.gcs.testscripts.rep_maint;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;
/*
 * TestCaseId: TYSS_GCS_Rep_Maint_02
 * Claim Type: Rep&Maint
 * TestScript Name: TYSS_GCS_Rep_Maint_02
 * Description:This test case is to Verify Rep&Maint claim submit by Employee and Refer back ,Reject by RM Voucher Up to 12000  
 * Author: Vivek Dogra
 */

public class TYSS_GCS_Rep_Maint_02 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from RepMaint where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Rep&Maint Charges claim submit by Emp and Refer and Rejcet by RM Voucher Upto 12000 ")
	public synchronized void TC_TYSS_Rep_Maint_02(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_Referback_Claimid" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_Reject_Homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
