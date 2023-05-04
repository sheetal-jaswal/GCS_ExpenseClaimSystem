package com.hcl.gcs.testscripts.catalyst_advance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_CA_01
 * Claim Type: Catalyst Advance
 * TestScript Name: TYSS_GCS_CA_01
 * Description: Verify Catalyst advance claim submit by employee, approved by BU head, ES Approver for voucher claim upto INR 50000.
 * 
 * Author: Sajal jain
 */
public class TYSS_GCS_CA_01 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from CatalystAdvance where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Catalyst advance claim submit by employee, approved by BU head, ES Approver for voucher claim upto INR 50000")
	public synchronized void TC_TYSS_GCS_CA_01(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "BU Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHEAD_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESApprover_Approve_homepage" }, };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
