package com.hcl.gcs.testscripts.repairmaintainother;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/* TestCaseId:TYSS_GCS_WandF_03
 * Claim Type: Repair_Maint_Other
 * Description: To Verify Repair Maint. (others) and approve by Rm,L4,ES for voucher INR 12000 and below.  
 * @author Aatish Slathia
 * 
 */

public class TYSS_GCS_RMandO_01 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from  Repair_Maint_Other where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To Verify Repair Maint. (others) and approve by Rm,L4,ES for voucher INR 12000 and below ")
	public synchronized void TC_TYSS_GCS_RMandO_01(String slNo, String employeeCode, String category,
			String description, String amount, String reason, String esaEmpcode, String fileFormat,
			String approvedAmount, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_APPROVE_HOMEPAGE" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4HEAD_APPROVE_HOMEPAGE" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_APPROVE_HOMEPAGE" } };

		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
