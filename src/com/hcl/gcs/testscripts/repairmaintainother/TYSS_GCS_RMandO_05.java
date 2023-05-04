package com.hcl.gcs.testscripts.repairmaintainother;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*
 * Description: To Verify Repair Maint. (others) Employee and Approved by RM,L3 Head, ES for the claim voucher INR 12000 and below.
 * 
 * @author Aatish Slathia,Manish Kumar C D.
 * 
 */

public class TYSS_GCS_RMandO_05 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  Repair_Maint_Other where SlNo ='5'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To Verify Reair Maint. (others) claim submit by Employee, Rm, L3, and ES  the claim voucher INR 12000 - 30000.")
	public synchronized void TC_TYSS_GCS_RMandO_05(String slNo, String employeeCode, String category,
			String description, String amount, String reason, String esaEmpcode, String fileFormat,
			String approvedAmount, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaimThroughClaimNumber" }, { "Employee", "ES_APPROVE_HOMEPAGE" }, };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
