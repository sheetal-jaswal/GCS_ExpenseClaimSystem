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

/*TestCaseId:TYSS_GCS_SC_02
 *Claim Type:EFC sports council
 *TestScript Name: TYSS_GCS_SC_02
 *Description:This test case is to to verify Sports council claim is referredback and rejected by RM
 *Author : Abhilash
 * 
 */
public class TYSS_GCS_SC_02 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  EFCSportsCouncil where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify  EFCSportsCouncil claim submitted by employee referreback and rejected by RM")
	public synchronized void TC_TYSS_GCS_SC_02(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkboxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferbackClaimThroughClaimNumber" }, { "Employee", "RM_REFERBACK_CLAIMID" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_REJECT_CLAIMID" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
