package com.hcl.gcs.testscripts.visaexpense;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestcaseId: TYSS_GCS_VE_O_13
 * Claim Type: Visa Expense
 * TestScript Name: TYSS_GCS_VE_O_13
 * Description: Verify the status of visa expenses claim after getting approved by RM,L4 Head,L2 Head ,ISG_Approver And ESA
 * */
public class TYSS_GCS_VE_O_13 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from VisaExpenses where  SlNo ='13'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify the status of visa expenses claim after getting approved by RM,L4 Head,L2 Head ,ISG_Approver And ESA")
	public synchronized void TC_TYSS_GCS_VE_O_13(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount, String country,
			String visaType, String visaExpenseType, String iAuraCaseNumber, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, country, visaType, visaExpenseType,
				iAuraCaseNumber, checkBoxNames);
		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4Head_Approve_Homepage" },
				{ "L2 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L2Head_Approve_Homepage" },
				{ "ISG_App", "ApproveClaimThroughClaimNumber" }, { "Employee", "ISGApprover_Approve_Homepage" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESApprover_Approve_Homepage" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
