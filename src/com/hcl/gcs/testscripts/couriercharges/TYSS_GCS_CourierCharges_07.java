package com.hcl.gcs.testscripts.couriercharges;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/**
 * Description: This test case is to Verify Courier Charges claim submit by Emp
 * Approved by RM,L4 Head,L2 Head,ES Voucher Upto 30001
 * 
 * @author Vivek Dogra
 */
public class TYSS_GCS_CourierCharges_07 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from CourierCharges where SlNo ='7'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: Verify Courier Charges claim submit by Emp Approved by RM,L4 Head,L2 Head,ES Voucher Upto 30001")
	public synchronized void TC_TYSS_GCS_CourierCharges_07(String slNo, String employeeCode, String category,
			String description, String amount, String reason, String esaEmpcode, String fileFormat,
			String approvedAmount, String checkBoxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description,
				amount, reason, esaEmpcode, fileFormat, approvedAmount, checkBoxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4Head_Approve_Homepage" },
				{ "L2 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L2Head_Approve_Homepage" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ES_Approve_Homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}
}