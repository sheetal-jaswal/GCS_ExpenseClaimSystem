package com.hcl.gcs.testscripts.visaexpense;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestcaseId: TYSS_GCS_VE_O_03
 * Claim Type: Visa Expense
 * TestScript Name: TYSS_GCS_VE_O_03
 * Description: Verify The Status of Visa Expense claim Submitted By Employee And Reject By RM
 * */
public class TYSS_GCS_VE_O_03 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from VisaExpenses where  SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify The Status of Visa Expense claim Submitted By Employee And Reject By RM")
	public synchronized void TC_TYSS_GCS_VE_O_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount, String country,
			String visaType, String visaExpenseType, String iAuraCaseNumber, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, country, visaType, visaExpenseType,
				iAuraCaseNumber, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_Reject_Homepage" }, };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
