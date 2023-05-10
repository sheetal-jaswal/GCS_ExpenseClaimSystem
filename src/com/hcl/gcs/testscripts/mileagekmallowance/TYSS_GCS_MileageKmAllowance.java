package com.hcl.gcs.testscripts.mileagekmallowance;

import java.util.Map;

import org.testng.annotations.Test;

import com.hcl.gcs.baseutil.BaseTest;
import com.hcl.gcs.dataprovider.DataProviderFactory;
import com.hcl.gcs.dataprovider.DataProviderFileRowFilter;
import com.hcl.gcs.pagesActionUtil.FlowControl;
import com.hcl.gcs.pagesActionUtil.PagesActionUtil;

public class TYSS_GCS_MileageKmAllowance extends BaseTest{
	
	@DataProviderFileRowFilter(file = "./data/GCSTestData.xlsx", sql = "Select * from Mileage where SlNo ='1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Description:To Verify Broadband claim submit by the employee and Approve by ES and check the status of employee")
	public synchronized void TC_TYSS_GCS_BB_01(String slNo, String employeeCode, String category, String amount,
			String mobileNo, String billFromDate, String endLocation, String startLocation,String typesOfAllowance,String miles,
 String description, String reason,
			String esaEmpcode, String fileFormat, String approvedAmount, String checkBoxNames) throws InterruptedException {
		Map<String, String> mapDataKeyValues = PagesActionUtil.getCategoryMapData(employeeCode, category,typesOfAllowance,miles, description,
				amount, mobileNo, billFromDate, endLocation,startLocation, reason, esaEmpcode, fileFormat, approvedAmount,
				checkBoxNames);
		String[][] arrFlowControl = new String[][] { { "Employee", "InitiateClaim" }};
		FlowControl.flowPath_Geo(arrFlowControl, mapDataKeyValues, driver);

}
}
