package com.hcl.gcs.testscripts.conveyance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_CV_01
 * Claim Type: Conveyance
 * TestScript Name: TYSS_GCS_CV_01
 * Description:  To verify Own two wheeler conveyance claim submit by the employee and Approved by RM,ES for voucher claim upto 999km. 
 * Author: Suganthini
 */
public class TYSS_GCS_CV_01 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from Conveyance where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify Own two wheeler conveyance claim submit by the employee and Approved by RM,ES for voucher claim upto 999km")
	public synchronized void TC_TYSS_GCS_CV_01(String slNo, String employeeCode, String category, String transportMode,
			String amount, String expenceDate, String totalDistance, String description, String reason,
			String esaEmpcode, String fileFormat, String approvedAmount, String checkBoxNames) {
		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				transportMode, amount, expenceDate, totalDistance, reason, esaEmpcode, fileFormat, approvedAmount,
				checkBoxNames);
		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_APPROVE_HOMEPAGE" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
