package com.hcl.gcs.testscripts.technicalcertification;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_TC_02
 * Claim Type: Technical Certification
 * TestScript Name: TYSS_GCS_TC_02
 * Description:To verify Technical Certification Claim is referred back  by all the approvers and Rejected by ES Approver for more than 3 months work flow.   
 * Author: Abhilash
 *
 */

public class TYSS_GCS_TC_02 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from TechnicalCertification where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify Technical Certification Claim is referred back  by all the approvers and Rejected by ES Approver for more than 3 months work flow.")
	public synchronized void TC_TYSS_GCS_TC_02(String slNo, String employeeCode, String category, String description,
			String amount, String certificationDate, String reason, String esaEmpcode, String fileFormat,
			String approvedAmount, String checkboxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, certificationDate, reason, esaEmpcode, fileFormat, approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferbackClaimThroughClaimNumber" }, { "Employee", "RM_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_HOMEPAGE" },
				{ "LOB HR", "ReferbackClaimThroughClaimNumber" }, { "Employee", "LOBHR_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "LOB HR", "ApproveClaimThroughClaimNumber" },
				{ "TechCEED", "ReferbackClaimThroughClaimNumber" }, { "Employee", "TechCEED_REFERBACK_CLAIMID" },
			//	{ "RM", "ApproveClaimThroughClaimNumber" }, { "LOB HR", "ApproveClaimThroughClaimNumber" },
				{ "TechCEED", "ApproveClaimThroughClaimNumber" }, { "ES Approver", "ReferbackClaim" },
				{ "Employee", "ESAPPROVER_Referback_ClaimID" }, { "ES Approver", "RejectClaim" },
				{ "Employee", "ESAPPROVER_Reject_Homepage" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
