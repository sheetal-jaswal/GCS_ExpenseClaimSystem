package com.hcl.gcs.testscripts.talentcouncil;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * Description: This test case is to Verify EFC Talent Council claim submit by Employee Approved And by RM Refer back and Reject Voucher Up to 999999
 * 
 * @author Vivek Dogra
 */
public class TYSS_GCS_TalentCouncil_02 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from EFCTalentCouncil where SlNo ='2'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify EFC Talent Council claim submit by Employee Approved And by RM Refer back and Reject Voucher Up to 999999")
	public synchronized void TC_TYSS_GCS_TalentCouncil_02(String slNo, String employeeCode, String category,
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