package com.hcl.gcs.testscripts.repairmaintainother;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/**
 * Description: To Verify Repair Maint. (others) Claim Submit by Employee, L4
 * Head Refer back the claim Employee Resubmit the claim, L4 Head Rejects the
 * claim for Voucher claim INR 120001 and below.
 * 
 * @author Aatish Slathia
 * 
 */

public class TYSS_GCS_RMandO_03 extends BaseTest {

	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  Repair_Maint_Other where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To Verify Repair Maint. (others) Claim Submit by Employee, L4 Head Refer back the claim Employee Resubmit the claim, L4 Head Rejects the claim for Voucher claim INR 120001 and below.")
	public synchronized void TC_TYSS_GCS_RMandO_03(String slNo, String employeeCode, String category,
			String description, String amount, String reason, String esaEmpcode, String fileFormat,
			String approvedAmount, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ReferBackClaimThroughClaimNumber" }, { "Employee", "L4Head_REFERBACK_CLAIMID" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L4Head_REJECT_HOMEPAGE" }

		};

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}
