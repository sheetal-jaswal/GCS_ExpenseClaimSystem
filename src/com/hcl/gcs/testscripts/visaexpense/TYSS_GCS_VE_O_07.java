package com.hcl.gcs.testscripts.visaexpense;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestcaseId: TYSS_GCS_VE_O_07
 * Claim Type: Visa Expense
 * TestScript Name: TYSS_GCS_VE_O_07
 * Description:Verify The Status Of VisaExpense Claim After Getting Referback By RM,L4 head,ISG Approver, ESA , and Reject by ESA . 
 * */
public class TYSS_GCS_VE_O_07 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from VisaExpenses where SlNo ='7'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify The Status Of VisaExpense Claim After Getting Referback By RM,L4 head,ISG Approver, ESA , and Reject by ESA")
	public synchronized void TC_TYSS_GCS_VE_O_07(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount, String country,
			String visaType, String visaExpenseType, String iAuraCaseNumber, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, country, visaType, visaExpenseType,
				iAuraCaseNumber, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_ReferBack_ClaimId" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ReferBackClaimThroughClaimNumber" }, { "Employee", "L4Head_ReferBack_ClaimId" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4Head_Approve_Homepage" },
				{ "ISG_App", "ReferBackClaimThroughClaimNumber" }, { "Employee", "ISGApprover_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4Head_Approve_Homepage" },
				{ "ISG_App", "ApproveClaimThroughClaimNumber" }, { "Employee", "ISGApprover_Approve_Homepage" },
				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_REJECT_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
