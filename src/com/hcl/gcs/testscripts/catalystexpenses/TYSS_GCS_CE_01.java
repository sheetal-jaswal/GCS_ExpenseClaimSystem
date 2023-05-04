package com.hcl.gcs.testscripts.catalystexpenses;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;

/**
 * TestCaseId: TYSS_GCS_CE_01 Claim Type : Catalyst Expense Claim TestScript
 * Name: TYSS_GCS_CE_01 Description: Verify Catalyst Expense Claim submit by
 * Employee and Approved by BUHead,ES for Voucher Claim upto INR 50000.
 * 
 * Author: Manish Kumar C D
 */

public class TYSS_GCS_CE_01 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  CatalystExpenses where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Catalyst Expense Claim submit by Employee and Approved by BUHead,ES for Voucher Claim upto INR 50000")
	public synchronized void TC_TYSS_GCS_CE_01(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		String[] keys = new String[] { "employeeCode", "category", "description", "amount", "reason", "esaEmpcode",
				"fileFormat", "approvedAmount", "checkBoxNames" };
		String[] values = new String[] { employeeCode, category, description, amount, reason, esaEmpcode, fileFormat,
				approvedAmount, checkBoxNames };
		Map<String, String> mapDataKeyValues = IntStream.range(0, keys.length).boxed()
				.collect(Collectors.toMap(i -> keys[i], i -> values[i]));

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "BU Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHEAD_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_APPROVE_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}
