package com.hcl.gcs.testscripts.catalystexpenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/**
 * TestCaseId: TYSS_GCS_CE_03 Claim Type : Catalyst Expense Claim TestScript
 * Name: TYSS_GCS_CE_03 Description: Verify Catalyst Expense Claim submit by
 * Employee,ES refer backs the claim, Employee Resubmits the Claim, ES rejects
 * the claim for Voucher Claim upto INR 50000.
 * 
 * Author: Manish Kumar C D
 */

public class TYSS_GCS_CE_03 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  CatalystExpenses where  SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Catalyst Expense Claim submit by Employee,ES Referbacks the claim, Employee Resubmits the Claim,ES rejects the claim for Voucher Claim upto INR 50000")
	public synchronized void TC_TYSS_GCS_CE_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "BU Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHEAD_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_REJECT_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
