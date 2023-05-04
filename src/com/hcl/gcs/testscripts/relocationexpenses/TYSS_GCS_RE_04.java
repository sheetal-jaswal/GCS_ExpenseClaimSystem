package com.hcl.gcs.testscripts.relocationexpenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 *TestCaseId : TYSS_GCS_RE_04
 *Claim Type : Relocation Expenses
 *TestScript Name : TYSS_GCS_RE_04
 *Description:  In 'Relocation Expenses' Verify the status of employee after getting refered back by BUHR, RM and rejected by ES 
 *Author : Vikas
 * 
 */
public class TYSS_GCS_RE_04 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from RelocationExpenses where SlNo ='4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: In 'Relocation Expenses' Verify the status of employee after getting referedback by BUHR, RM and rejected by ES ")
	public synchronized void TC_TYSS_GCS_RE_04(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames, String endDate) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames, endDate);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "BUHR", "ReferBackClaimThroughClaimNumber" }, { "Employee", "BUHR_ReferBack_ClaimID" },

				{ "BUHR", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHR_Approve_HomePage" },

				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_ReferBack_ClaimID" },

				{ "BUHR", "ApproveClaimThroughClaimNumber" }, { "Employee", "BUHR_Approve_HomePage" },

				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_HomePage" },

				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESAPPROVER_REFERBACK_CLAIMID" },

				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_REJECT_HomePage" } };
//        	{ "Employee", "VerifyStatus_BUHR" },

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}

}
