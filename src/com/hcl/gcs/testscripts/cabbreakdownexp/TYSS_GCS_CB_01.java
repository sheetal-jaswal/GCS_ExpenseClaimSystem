package com.hcl.gcs.testscripts.cabbreakdownexp;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 *TestCaseId : TYSS_GCS_CB_01
 *Claim Type : Cab Break down Expense.
 *TestScript Name : TYSS_GCS_CB_01
 *Description: In 'Cab Break down Expense' Verify the status of employee after getting approved by RM and ES approvers for voucher claim upto INR 999999.
 *Author : Vikas K C.
 * 
 */

public class TYSS_GCS_CB_01 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from CabBreakdownExp where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: In 'Cab Break down Expense' Verify the status of employee after getting approved by RM and ES approvers for voucher claim upto INR 999999.")
	public synchronized void TC_TYSS_GCS_CB_01(String slNo, String employeeCode, String category, String description,
			String transportID, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				transportID, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_HomePage" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_Approve_HomePage" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}
