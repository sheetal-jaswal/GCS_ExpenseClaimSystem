package com.hcl.gcs.testscripts.technicalcertification;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;
/*
 * 
 * TestCaseId: TYSS_GCS_TC_04
 * Claim Type: Technical Certification
 * TestScript Name: TYSS_GCS_TC_04
 * Description:To verify Technical Certification Claim is rejected by LOB HR for more than 3 months work flow.   
 * Author: Abhilash
 *
 */

public class TYSS_GCS_TC_04 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from TechnicalCertification where SlNo ='4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify Technical certification expenses is rejected by LOB HR for more than 3 months")
	public synchronized void TC_TYSS_GCS_TC_04(String slNo, String employeeCode, String category, String description,
			String amount, String certificationDate, String reason, String esaEmpcode, String fileFormat,
			String approvedAmount, String checkboxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, certificationDate, reason, esaEmpcode, fileFormat, approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_HOMEPAGE" },
				{ "LOB HR", "RejectClaimThroughClaimNumber" }, { "Employee", "LOBHR_Reject_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
