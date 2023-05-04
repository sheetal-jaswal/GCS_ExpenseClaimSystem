package com.hcl.gcs.testscripts.miscellaneous_expenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_ME_11
 * Claim Type: Miscellaneous Expenses
 * TestScript Name: TYSS_GCS_ME_11
 * Description: Verify Miscellaneous expenses claim submit by employee and approved by RM, ES for voucher claim INR 600001 - 999999
 * 
 * Author: Sajal jain
 */
public class TYSS_GCS_ME_11 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from MiscellaneousExpenses where SlNo ='11'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Miscellaneous expenses claim submit by employee and approved by RM, ES for voucher claim INR 600001 - 999999")
	public synchronized void TC_TYSS_GCS_ME_11(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESApprover_APPROVE_HOMEPAGE" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}