package com.hcl.gcs.testscripts.zeroexpense;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;

/*
 * TestcaseId: TYSS_GCS_ZE_03
 * Claim Type: Zero Expense
 * TestScript Name: TYSS_GCS_ZE_03
 * Description: Verify the status of zero expenses claim after getting Reject by RM
 * */
public class TYSS_GCS_ZE_03 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from ZeroExpense where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify the status of zero expenses claim after getting Reject by RM ")
	public synchronized void TC_TYSS_GCS_ZE_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String approvedAmount, String checkBoxNames) {

		String[] keys = new String[] { "employeeCode", "category", "description", "amount", "reason", "esaEmpcode",
				"approvedAmount", "checkBoxNames" };
		String[] values = new String[] { employeeCode, category, description, amount, reason, esaEmpcode,
				approvedAmount, checkBoxNames };
		Map<String, String> mapDataKeyValues = IntStream.range(0, keys.length).boxed()
				.collect(Collectors.toMap(i -> keys[i], i -> values[i]));

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_Reject_HOMEPAGE" }, };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
