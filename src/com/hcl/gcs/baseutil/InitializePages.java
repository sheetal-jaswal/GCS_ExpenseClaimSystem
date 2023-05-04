
package com.hcl.gcs.baseutil;

import org.openqa.selenium.WebDriver;

import com.hcl.gcs.pages.ApproverSection_Page;
import com.hcl.gcs.pages.ESHome_Page;
import com.hcl.gcs.pages.EsForAdditionalInfo_page;
import com.hcl.gcs.pages.Home_Page;
import com.hcl.gcs.pages.Login_Page;
import com.hcl.gcs.pages.Logout_Page;
import com.hcl.gcs.pages.NewCashClaim_Page;
import com.hcl.gcs.pages.PendingAction_Page;
import com.hcl.gcs.pages.ViewCashClaim_Page;
import com.hcl.gcs.util.WebActionUtil;

/**
 * Description: Initializes all pages with driver instance ,Explicit Time out,
 * WebAactionUtility using variables driver,ETO,WebactionUtil
 * 
 * @author : Shreya U,Vivek Dogra,Aatish Slathia
 */
public class InitializePages {
	public Login_Page loginpage;
	public NewCashClaim_Page newCashClaimPage;
	public ESHome_Page eshomePage;
	public Home_Page homePage;
	public ViewCashClaim_Page viewCashClaimPage;
	public ApproverSection_Page approverSectionPage;
	public EsForAdditionalInfo_page esForAdditionalInfoPage;
	public Logout_Page logoutPage;
	public PendingAction_Page pendingActionPage;

	public InitializePages(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		loginpage = new Login_Page(driver, ETO, WebActionUtil);
		viewCashClaimPage = new ViewCashClaim_Page(driver, ETO, WebActionUtil);
		eshomePage = new ESHome_Page(driver, ETO, WebActionUtil);
		homePage = new Home_Page(driver, ETO, WebActionUtil);
		newCashClaimPage = new NewCashClaim_Page(driver, ETO, WebActionUtil);
		approverSectionPage = new ApproverSection_Page(driver, ETO, WebActionUtil);
		esForAdditionalInfoPage = new EsForAdditionalInfo_page(driver, ETO, WebActionUtil);
		logoutPage = new Logout_Page(driver, ETO, WebActionUtil);
		pendingActionPage = new PendingAction_Page(driver, ETO, WebActionUtil);
	}

}
