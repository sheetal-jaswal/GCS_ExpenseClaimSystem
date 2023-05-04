package com.hcl.gcs.testscripts.other_expense_related_advance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_OERA_01
 * Claim Type: Other Expense Related Advance
 * TestScript Name: TYSS_GCS_OERA_01
 * Description: Verify Other expense related advance claim submit by employee, approved by RM, ES for voucher claim upto INR 999999
 * 
 * Author: Sajal jain
 */
public class TYSS_GCS_OERA_01 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from OtherExpenseRelatedAdvance where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Other expense related advance claim submit by employee, approved by RM, ES for voucher claim upto INR 999999")
	public synchronized void TC_TYSS_GCS_OERA_01(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_approve_homepage" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESApprover_approve_homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}