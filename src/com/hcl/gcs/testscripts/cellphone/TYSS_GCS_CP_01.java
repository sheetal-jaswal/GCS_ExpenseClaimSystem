package com.hcl.gcs.testscripts.cellphone;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_CP_01
 * Claim Type: CellPhone
 * TestScript Name: TYSS_GCS_CP_01
 * Description: To Verify cell phone claim submit by Band E5 and Band E5 above employees to claim Entitlement amount of INR 236 per month and Approved by RM and ES.  
 * Author: Suganthini
 */
public class TYSS_GCS_CP_01 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from CellPhone where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:To Verify cell phone claim submit by Band E5 and Band E5 above employees to claim Entitlement amount of INR 236 per month and Approved by RM and ES.")
	public synchronized void TC_TYSS_GCS_CP_01(String slNo, String employeeCode, String category, String amount,
			String mobileNo, String billFromDate, String billToDate, String plan, String description, String reason,
			String esaEmpcode, String fileFormat, String approvedAmount, String checkBoxNames,
			String internationalCharge) {
		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, mobileNo, billFromDate, billToDate, plan, reason, esaEmpcode, fileFormat, approvedAmount,
				checkBoxNames, internationalCharge);
		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_APPROVE_CLAIMID" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}
