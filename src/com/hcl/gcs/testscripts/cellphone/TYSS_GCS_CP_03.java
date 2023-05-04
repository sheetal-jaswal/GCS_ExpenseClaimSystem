package com.hcl.gcs.testscripts.cellphone;

import java.util.Map;
import org.testng.annotations.Test;
import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_CP_03
 * Claim Type: CellPhone
 * TestScript Name: TYSS_GCS_CP_03
 * Description: To Verify cell phone claim submit by Band E5 and Band E5 above employees to claim Entitlement amount of INR 236 per month and reject by RM (without adding international calling charges).  
 * Author: Suganthini
 */
public class TYSS_GCS_CP_03 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from CellPhone where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:To Verify cellphone claim submit by Band E5 and Band E5 above employees to claim Entitlement amount of INR 235 per month and reject by RM (without adding international calling charges) ")
	public synchronized void TC_TYSS_GCS_CP_03(String slNo, String employeeCode, String category, String amount,
			String mobileNo, String billFromDate, String billToDate, String plan, String description, String reason,
			String esaEmpcode, String fileFormat, String approvedAmount, String checkBoxNames,
			String internationalCharge)  {
		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, mobileNo, billFromDate, billToDate, plan, reason, esaEmpcode, fileFormat, approvedAmount,
				checkBoxNames, internationalCharge);
		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_REJECT_HOMEPAGE" }

		};
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}
