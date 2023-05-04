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

/*TestCaseId:TYSS_GCS_B&P_09
 *Claim Type:Books and periodicals
 *TestScript Name: TYSS_GCS_BP_09
 *Description:This test case is to verify Books & periodicals claim for 120001-600000 is approved by RM,L3,L1 and ES.
 *Author : Abhilash
 * 
 */
public class TYSS_GCS_BP_09 extends BaseTest {
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from BooksPerodicals where SlNo ='9'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description: To verify Books & periodicals claim for 120000-600000 is approved by RM,L3,L1 and ES")
	public synchronized void TC_TYSS_GCS_BP_09(String slNo, String employeeCode, String category, String description,
			String amount, String reason, String esaEmpcode, String fileFormat, String approvedAmount,
			String checkboxNames)   {

		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category, description, amount, reason,
				esaEmpcode, fileFormat,approvedAmount, checkboxNames);

		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" },
				{ "RM", "ApproveClaimThroughClaimNumber" }, { "Employee", "RM_Approve_Homepage" },
				{ "L3 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L3Head_Approve_Homepage" },
				{ "L1 Head", "ApproveClaimThroughClaimNumber" }, { "Employee", "L1Head_Approve_Homepage" },
				{ "ES Approver", "ApproveClaim" }, { "Employee", "ESAPPROVER_Approve_Homepage" } };
		FlowControl.flowPath(arrFlowControl, mapDataKeyValues, driver);

	}

}
