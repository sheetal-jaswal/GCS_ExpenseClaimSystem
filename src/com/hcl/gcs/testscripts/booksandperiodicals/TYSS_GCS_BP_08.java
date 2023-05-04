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

/*TestCaseId:TYSS_GCS_B&P_08
 *Claim Type:Books and periodicals
 *TestScript Name: TYSS_GCS_BP_08
 *Description:This test case is to verify Books & periodicals claim for 30001-120000 is referred back and rejected by L2 head
 *Author : Abhilash
 * 
 */
public class TYSS_GCS_BP_08 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from BooksPerodicals where SlNo ='8'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify Books & periodicals claim for 30000-120000 is referred back and rejected by L2 head")
	public synchronized void TC_TYSS_GCS_BP_08(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkboxNames)   {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description, amount, reason,
				esaEmpcode, fileFormat,approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4Head_Approve_Homepage" },
				{ "L2 Head", "ReferbackClaimThroughClaimNumber" }, { "Employee", "L2Head_Referback_ClaimId" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L4 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L4Head_Approve_Homepage" },
				{ "L2 Head", "RejectClaimThroughClaimNumber" }, { "Employee", "L2Head_Reject_Homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
