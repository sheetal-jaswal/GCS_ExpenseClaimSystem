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
 * TestcaseId: TYSS_GCS_ZE_02
 * Claim Type: Zero Expense
 * TestScript Name: TYSS_GCS_ZE_02
 * Description: Verify the status of Zero expenses claim after getting Referack by RM, ESA and Reject by ESA
 * */
public class TYSS_GCS_ZE_02 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from ZeroExpense where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify the status of Zero expenses claim after getting Referack by RM, ESA and Reject by ESA")
	public synchronized void TC_TYSS_GCS_ZE_02(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String approvedAmount, String checkBoxNames) {

		String[] keys = new String[] { "employeeCode", "category", "description", "amount", "reason", "esaEmpcode",
				"approvedAmount", "checkBoxNames" };
		String[] values = new String[] { employeeCode, category, description, amount, reason, esaEmpcode,
				approvedAmount, checkBoxNames };
		Map<String, String> mapDataKeyValues = IntStream.range(0, keys.length).boxed()
				.collect(Collectors.toMap(i -> keys[i], i -> values[i]));

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferbackClaimThroughClaimNumber" }, { "Employee", "RM_ReferBack_Claimid" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_ClaimNumber" },
				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_REJECT_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
