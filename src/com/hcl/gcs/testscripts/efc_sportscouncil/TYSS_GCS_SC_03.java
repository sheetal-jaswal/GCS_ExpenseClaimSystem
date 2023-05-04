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

/*TestCaseId:TYSS_GCS_SC_03
*Claim Type:EFC sports council
*TestScript Name: TYSS_GCS_SC_03
*Description:This test case is to verify Sports council claim is referred back and rejected by ES
*Author : Abhilash
* 
*/
public class TYSS_GCS_SC_03 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from EFCSportsCouncil where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify EFC SportsCouncil claim submitted by employee ,referred back and rejected by ES")
	public synchronized void TC_TYSS_GCS_SC_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkboxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_CLAIMID" },
				{ "ES Approver", "ReferbackClaim" }, { "Employee", "ESAPPROVER_Referback_CLAIMID" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESAPPROVER_Reject_Homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
