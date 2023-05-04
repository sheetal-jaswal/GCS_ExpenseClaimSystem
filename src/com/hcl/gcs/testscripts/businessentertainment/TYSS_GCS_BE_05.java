package com.hcl.gcs.testscripts.businessentertainment;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;
/**
 *TestCaseId: TYSS_GCS_BE_05
 *Claim Type : Business Entertainment claim
 *TestScript Name: TYSS_GCS_BE_05
 *Description: Verify Business Entertainment Claim submit by Employee, L2Head referbacks and rejects for Voucher Claim upto INR 100001-999999.
 *            
 *Author: Manish Kumar C D
 */

public class TYSS_GCS_BE_05 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  BusinessEntertainment where SlNo ='5'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Business Entertainment Claim submit by Employee, L2Head referbacks and rejects for Voucher Claim upto INR 100001-999999")
	public synchronized void TC_TYSS_GCS_BE_05(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames)   {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description, amount, reason, esaEmpcode,
				fileFormat, approvedAmount,checkBoxNames );


		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, 
				{ "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, 
				{ "Employee", "L4HEAD_APPROVE_HOMEPAGE" },
				{ "L2 Head", "ReferBackClaimThroughClaimNumber" }, 
				{ "Employee", "L2HEAD_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, 
				{ "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, 
				{ "Employee", "L4HEAD_APPROVE_HOMEPAGE" },
				{ "L2 Head", "RejectClaimThroughClaimNumber" }, 
				{ "Employee", "L2HEAD_REJECT_HOMEPAGE" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
