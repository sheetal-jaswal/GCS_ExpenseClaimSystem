package com.hcl.gcs.testscripts.broadband;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_BB_02
 * Claim Type: BroadBandExpenses
 * TestScript Name: TYSS_GCS_BB_02
 * Description: To Verify Broadband claim submit by the employee, referback and reject by ES and check the status of Employee.
 * 
 * Author: Suganthini
 */
public class TYSS_GCS_BB_02 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from BroadBandExpenses where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:To Verify Broadband claim submit by the employee and referback and reject by ES ")
	public synchronized void TC_TYSS_GCS_BB_02(String slNo, String employeeCode, String category, String amount,
			String mobileNo, String billFromDate, String billToDate, String description, String reason,
			String esaEmpcode, String fileFormat, String approvedAmount, String checkBoxNames) {
		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, mobileNo, billFromDate, billToDate, reason, esaEmpcode, fileFormat, approvedAmount,
				checkBoxNames);
		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_REJECT_HOMEPAGE" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}
