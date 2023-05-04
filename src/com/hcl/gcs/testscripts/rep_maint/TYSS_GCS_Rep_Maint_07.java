package com.hcl.gcs.testscripts.rep_maint;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_Rep_Maint_07
 * Claim Type: Rep&Maint
 * TestScript Name: TYSS_GCS_Rep_Maint_07
 * Description:This test case is to Verify Rep&Maint Charges claim submit by Employee and Approved by L4,L2 Head and ES Voucher Up to 30001  
 * Author: Vivek Dogra
 */

public class TYSS_GCS_Rep_Maint_07 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from RepMaint where SlNo ='7'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Rep&Maint Charges claim submit by Employee and Approved by L4,L2 Head and ES Voucher Up to 30001 ")
	public synchronized void TC_TYSS_Rep_Maint_07(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4Head_Approve_Homepage" },
				{ "L2 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L2Head_Approve_Homepage" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ES_Approve_Homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}