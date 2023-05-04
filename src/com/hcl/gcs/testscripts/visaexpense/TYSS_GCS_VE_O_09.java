package com.hcl.gcs.testscripts.visaexpense;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestcaseId: TYSS_GCS_VE_O_9
 * Claim Type: Visa Expense
 * TestScript Name: TYSS_GCS_VE_O_9
 * Description:Verify the Visa Expense claim submitted by employee and approved by RM ,L4Head and reject by ISG
 * */
public class TYSS_GCS_VE_O_09 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from VisaExpenses where SlNo ='9'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:Verify the Visa Expense claim submitted by employee and approved by RM ,L4Head and reject by ISG")
	public synchronized void TC_TYSS_GCS_VE_O_09(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount, String country,
			String visaType, String visaExpenseType, String iAuraCaseNumber, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, country, visaType, visaExpenseType,
				iAuraCaseNumber, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4Head_Approve_Homepage" },
				{ "ISG_App", "RejectClaimThroughClaimNumber" }, { "Employee", "ISGApprover_Reject_Homepage" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
