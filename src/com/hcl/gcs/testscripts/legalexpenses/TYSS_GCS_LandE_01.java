package com.hcl.gcs.testscripts.legalexpenses;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*TestCaseId:TYSS_GCS_LandE_01
 *Claim Type:Legal Expenses
 *TestScript Name: TYSS_GCS_BP_01
 *Description: To Verify Legal Expenses claim submit by RM and approved by RM,L4 Head, and ES for voucher claim INR 1200 and below
 *Author : Aatish Slathia
 * 
 */

public class TYSS_GCS_LandE_01 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  Legal_Expenses where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To Verify Legal Expenses claim submit by RM and approved by RM,L4 Head, and ES for voucher claim INR 1200 and below.")
	public synchronized void TC_TYSS_GCS_LandE_01(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames)  {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4HEAD_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_APPROVE_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
