package com.hcl.gcs.testscripts.visaexpense;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestcaseId: TYSS_GCS_VEO_18
 * Claim Type: Visa Expense
 * TestScript Name: TYSS_GCS_VEO_18
 *  Description: Verify The Status Of VisaExpense Claim After Getting Referback By RM and ESA And Approved By ESA  
 * */
public class TYSS_GCS_VE_O_18 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from VisaExpenses where SlNo ='18'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify The Status Of VisaExpense Claim After Getting Referback By RM And Approved By ESA")
	public synchronized void TC_TYSS_GCS_VE_O_18(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount, String country,
			String visaType, String visaExpenseType, String iAuraCaseNumber, String checkBoxNames) {
		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, country, visaType, visaExpenseType,
				iAuraCaseNumber, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_REJECT_HOMEPAGE" },

		};

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
