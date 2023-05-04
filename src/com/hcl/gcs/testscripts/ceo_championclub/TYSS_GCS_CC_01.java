package com.hcl.gcs.testscripts.ceo_championclub;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.testng.annotations.Test;
import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*TestCaseId:TYSS_GCS_C&C_01
 *Claim Type:CEO Champion's Club
 *TestScript Name: TYSS_GCS_CC_01
 *Description:This test case is to verify  CEO's champion club claim submitted by employee and approved by RM,ES for voucher claim upto INR 999999.
 *Author : Abhilash
 * 
 */
public class TYSS_GCS_CC_01 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from CEOChampionsClub where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify CEO's champion club claim submitted by employee and approved by RM,ES for voucher claim upto INR 999999.")
	public synchronized void TC_TYSS_GCS_CC_01(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkboxNames)  {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_CLAIMID" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_Approve_CLAIMID" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
