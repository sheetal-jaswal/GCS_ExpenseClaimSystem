package com.hcl.gcs.testscripts.efc_grey_cells_council;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * TestCaseId: TYSS_GCS_EGCC_03
 * Claim Type: EFC Grey Cells Council
 * TestScript Name: TYSS_GCS_EGCC_03
 * Description: Verify EFC-Grey Cells council claim submit by the employee,approved by RM, refer back by ES then employee resubmit the claim,ES reject the claim for voucher claim upto INR 999999
 *
 * Author: Sajal jain
 */
public class TYSS_GCS_EGCC_03 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from EFCGreyCellsCouncil where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify EFC-Grey Cells council claim submit by the employee,approved by RM, refer back by ES then employee resubmit the claim,ES reject the claim for voucher claim upto INR 999999")
	public synchronized void TC_TYSS_GCS_EGCC_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ReferBackClaim" }, { "Employee", "ESApprover_Referback_claimid" },
				{ "ES Approver", "RejectClaim" }, { "Employee", "ESApprover_Reject_homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
