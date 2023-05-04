package com.hcl.gcs.testscripts.broadband;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_BB_01
 * Claim Type: BroadBandExpenses
 * TestScript Name: TYSS_GCS_BB_01
 * Description: To Verify Broadband claim submit by the employee and Approved by RM,ES and check the status of employee.
 * 
 * Author: Suganthini
 */

public class TYSS_GCS_BB_01 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from BroadBandExpenses where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:To Verify Broadband claim submit by the employee and Approve by ES and check the status of employee")
	public synchronized void TC_TYSS_GCS_BB_01(String slNo, String employeeCode, String category, String amount,
			String mobileNo, String billFromDate, String billToDate, String description, String reason,
			String esaEmpcode, String fileFormat, String approvedAmount, String checkBoxNames) {
		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, mobileNo, billFromDate, billToDate, reason, esaEmpcode, fileFormat, approvedAmount,
				checkBoxNames);
		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" }, { "ES Approver", "ApproveClaim" },
				{ "Employee", "ESAPPROVER_APPROVE_CLAIMID" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
