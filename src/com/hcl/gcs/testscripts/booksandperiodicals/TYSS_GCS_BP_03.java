package com.hcl.gcs.testscripts.booksandperiodicals;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

/*TestCaseId:TYSS_GCS_B&P_03
 *Claim Type:Books and periodicals
 *TestScript Name: TYSS_GCS_BP_03
 *Description:This test case is to verify Books & periodicals claim less than 12000 is referred back 
 *and rejected by L4 head after resubmission by employee
 *Author : Abhilash
 * 
 */
public class TYSS_GCS_BP_03 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from BooksPerodicals where SlNo ='3'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify Books & periodicals claim less than 12000 is referred back and rejected by L4 after resubmission")
	public synchronized void TC_TYSS_GCS_BP_03(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkboxNames) {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description, amount, reason,
				esaEmpcode, fileFormat,approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ReferbackClaimThroughClaimNumber" }, { "Employee", "L4Head_Referback_ClaimId" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L4Head_Reject_Homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
