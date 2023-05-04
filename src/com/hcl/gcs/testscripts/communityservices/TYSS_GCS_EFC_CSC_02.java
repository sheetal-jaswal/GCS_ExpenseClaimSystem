package com.hcl.gcs.testscripts.communityservices;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*TestCaseId:TYSS_GCS_EFC_CSC_02
 *Claim Type:EFC Community Services
 *TestScript Name: TYSS_GCS_EFC_CSC_02
 * Description: This test case is to Verify EFC Community_Services claim submit by Employee Refer back and Reject by RM Voucher Up to 999999.
 * @author Vivek Dogra
 */

public class TYSS_GCS_EFC_CSC_02 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from CommunityServices where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify EFC Community_Services claim submit by Employee Referback and Reject by RM Voucher Up to 999999")
	public synchronized void TC_TYSS_GCS_EFC_CSC_02(String slNo, String employeeCode, String category,
			String description, String amount, String reason, String esaEmpcode, String fileFormat,
			String approvedAmount, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferBackClaimThroughClaimNumber" }, { "Employee", "RM_Referback_Claimid" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_Reject_Homepage" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}