package com.hcl.gcs.testscripts.repairmaintainother;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * Description: To Verify Repair Maint. (others) submit by the Employee, L3 Head Refer Back the claim  Employee resubmit the claim L3 Head reject the claim voucher INR 12000 - 30000.
 * 
 * @author Aatish Slathia,Manish Kumar C D.
 * 
 */

public class TYSS_GCS_RMandO_06 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  Repair_Maint_Other where SlNo ='6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To Verify Repair Maint. (others) submit by the Employee, L3 Head Refer Back the claim  Employee resubmit the claim L3 Head reject the claim voucher INR 12000 - 30000.")
	public synchronized void TC_TYSS_GCS_RMandO_06(String slNo, String employeeCode, String category,
			String description, String amount, String reason, String esaEmpcode, String fileFormat,
			String approvedAmount, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ReferBackClaimThroughClaimNumber" }, { "Employee", "L3HEAD_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L3HEAD_REJECT_HOMEPAGE" }, };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}
