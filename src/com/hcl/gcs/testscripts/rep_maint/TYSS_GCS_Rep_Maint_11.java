package com.hcl.gcs.testscripts.rep_maint;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;
/*
 * TestCaseId: TYSS_GCS_Rep_Maint_11
 * Claim Type: Rep&Maint
 * TestScript Name: TYSS_GCS_Rep_Maint_11
 * Description:This test case is to Verify Rep&Maint Charges claim submit by Employee and Approved by ES Approver Voucher Up to 600003  
 * Author: Vivek Dogra
 */

public class TYSS_GCS_Rep_Maint_11 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from RepMaint where SlNo ='11'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Rep&Maint Charges claim submit by Employee and Approved by ES Approver Voucher Up to 600003 ")
	public synchronized void TC_TYSS_Rep_Maint_11(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ES_Approve_Homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
