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

/*TestCaseId:TYSS_GCS_C&C_02
 *Claim Type:CEO Champion's Club
 *TestScript Name: TYSS_GCS_CC_02
 *Description:This test case is to to verify CEO's champion club claim is referred back and rejected by RM for voucher claim upto INR 999999.
 *Author : Abhilash
 * 
 */
public class TYSS_GCS_CC_02 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from CEOChampionsClub where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify CEO's champion club claim is referredback and rejected by RM for voucher claim upto INR 999999.")
	public synchronized void TC_TYSS_GCS_CC_02(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkboxNames)  {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferbackClaimThroughClaimNumber" }, { "Employee", "RM_REFERBACK_CLAIMID" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_REJECT_CLAIMID" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
