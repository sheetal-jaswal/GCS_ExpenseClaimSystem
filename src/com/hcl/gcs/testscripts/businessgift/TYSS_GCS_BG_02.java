package com.hcl.gcs.testscripts.businessgift;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;

/*
 *TestCaseId : TYSS_GCS_BG_02
 *Claim Type : BusinessGift
 *TestScript Name : TYSS_GCS_BG_02
 *Description: Verify the status of employee after getting refered back RM and rejected by RM for voucher claim of INR 7000 and below.
 *Author : Vikas K C.
 * 
 */

public class TYSS_GCS_BG_02 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from BusinessGift where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify the status of employee after getting refered back RM and rejected by RM for voucher claim of INR 7000 and below.")
	public synchronized void TC_TYSS_GCS_BG_02(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames)   {

		String[] keys = new String[] { "employeeCode", "category", "description", "amount", "reason", "esaEmpcode",
				"fileFormat", "approvedAmount", "checkBoxNames" };
		String[] values = new String[] { employeeCode, category, description, amount, reason, esaEmpcode, fileFormat,
				approvedAmount, checkBoxNames };
		Map<String, String> mapDataKeyValues = IntStream.range(0, keys.length).boxed()
				.collect(Collectors.toMap(i -> keys[i], i -> values[i]));

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_REFERBACK_ClaimID" },
				{ "RM", "RejectClaimthroughClaimNumber" }, { "Employee", "RM_Reject_ClaimID" }, };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
