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
 * TestCaseId: TYSS_GCS_EGCC_02
 * Claim Type: EFC Grey Cells Council
 * TestScript Name: TYSS_GCS_EGCC_02
 * Description: Verify EFC-Grey Cells council claim submit by the employee, RM refer back the claim, employee resubmit the claim,RM reject the claim for voucher claim upto INR 999999
 * 
 * Author: Sajal jain
 */
public class TYSS_GCS_EGCC_02 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from EFCGreyCellsCouncil where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify EFC-Grey Cells council claim submit by the employee, RM refer back the claim, employee resubmit the claim,RM reject the claim for voucher claim upto INR 999999")
	public synchronized void TC_TYSS_GCS_EGCC_02(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ReferbackClaimThroughClaimNumber" }, { "Employee", "RM_referback_claimid" },
				{ "RM", "RejectClaimThroughClaimNumber" }, { "Employee", "RM_reject_homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);
	}
}
