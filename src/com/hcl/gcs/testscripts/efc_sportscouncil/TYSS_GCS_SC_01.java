package com.hcl.gcs.testscripts.efc_sportscouncil;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*TestCaseId:TYSS_GCS_SC_01
 *Claim Type:EFC sports council
 *TestScript Name: TYSS_GCS_SC_01
 *Description:This test case is to verify  Sports council claim submitted by employee and approved by RM,ES
 *Author : Abhilash
 * 
 */
public class TYSS_GCS_SC_01 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from EFCSportsCouncil where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify EFC sports council claim submitted by employee and approved by RM,ES")
	public synchronized void TC_TYSS_GCS_SC_01(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkboxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_CLAIMID" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_Approve_CLAIMID" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
