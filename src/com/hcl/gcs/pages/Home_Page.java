package com.hcl.gcs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hcl.gcs.util.WebActionUtil;

/**
 * Description: This class implements the methods For Home Page
 * 
 * @author Manish Kumar C D
 */
public class Home_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Home_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Spinner */
	@FindBy(xpath = "//div[@class='loader2 material-spinner']")
	private WebElement spinner;

	/* Advance Spinner */
	@FindBy(xpath = "//div[@id='Advance_go_spiner']")
	private WebElement advanceSpinner;

	/* My Approvals Tab */
	@FindBy(xpath = "//li[@id='approvalid']/a[text()='My Approvals']")
	private WebElement tabMyApprovals;

	/* My Claims Tab */
	@FindBy(xpath = "//li[@id='travelid']/a[text()='My Claims']")
	private WebElement tabMyClaims;

	/* More Claims Tab */
	@FindBy(xpath = "//a[@id='gcsMoreClaims']/span[text()='More Claims']")
	private WebElement tabMoreClaims;

	/* Hamburger drop down */
	@FindBy(xpath = "//span[@class='icon-menu3 glb-pnl-localNav-ico']")
	private WebElement ddHamburger;

	/* Hamburger List Text */
	private List<WebElement> txtHamburgerList(String menuValue) {
		String xpath = "//div[@class='glb-pnl-localNav-box1-col1']/ul/li/a[text()[normalize-space()='" + menuValue
				+ "']]";
		return driver.findElements(By.xpath(xpath));
	}

	/* Profile Drop down */
	@FindBy(xpath = "//div[@class='glb-pnl-informationDropIcon']")
	private WebElement ddProfile;

	/* Logout Button */
	@FindBy(xpath = "//span[text()='LOGOUT ']")
	private WebElement btnLogout;

	/* Are You Sure You Want To Logout PopUp */
	@FindBy(xpath = "//div[@class='sweet-alert  showSweetAlert visible']")
	private WebElement popUpAreYouSureYouWantToLogout;

	/* Yes Button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnLogoutYes;

	/* No Button */
	@FindBy(xpath = "//button[text()='NO']")
	private WebElement btnLogoutNo;

	/* Claim Number Text */
	private WebElement txtClaimNumber() {
		String xpath = "(//span[text()='Claim Number :']/parent::li/a)[1]";
		return driver.findElement(By.xpath(xpath));
	}

	/* Loading My Approval text */
	@FindBy(xpath = "//div[@id='ntcApproval']/descendant::div/h3[contains(text(),'Loading')]")
	private WebElement txtLoadingMyApproval;

	/* Loading My Claim text */
	@FindBy(xpath = "//div[@id='ntc']/descendant::div/h3[contains(text(),'Loading')]")
	private WebElement txtLoadingMyClaim;

	/* My Claims Minor Tabs */
	private List<WebElement> txtMyClaimsType(String myClaimsType) {
		String xpath = "//div[@id='travel']/ul/li/a[text()[normalize-space()='" + myClaimsType + "']]";
		return driver.findElements(By.xpath(xpath));
	}

	/* My Approvals Minor Tabs */
	private List<WebElement> txtMyApprovalsType(String myApprovalsType) {
		String xpath = "//div[@id='approval']/ul/li/a[text()[normalize-space()='" + myApprovalsType + "']]";
		return driver.findElements(By.xpath(xpath));
	}

	/* Raise New Claim Tabs */
	private List<WebElement> txtRaiseNewClaimType(String raiseNewClaimType) {
		String xpath = "//div[@class='raise-claim headerChange']/ul/li/a/span[text()='" + raiseNewClaimType + "']";
		return driver.findElements(By.xpath(xpath));
	}

	/* Submit button */
	@FindBy(id = "ntaSubmit")
	private WebElement btnSubmit;

	/* Yes button */
	@FindBy(xpath = "//button[text()='YES']")
	private WebElement btnYes;

	/* No button */
	@FindBy(xpath = "//button[text()='NO']")
	private WebElement btnNo;

	/* Are you sure you want to proceed text */
	@FindBy(xpath = "//p[text()='Are you sure you want to proceed?']")
	private WebElement txtAreYouSureYouWantToProceed;

	/* Approved Successfully toast message */
	@FindBy(xpath = "//span[contains(text(),'Approved Successfully.')]")
	private WebElement txtApprovedSuccessfully;

	/* Rejected Successfully toast message */
	@FindBy(xpath = "//span[contains(text(),'Rejected Successfully.')]")
	private WebElement txtRejectedSuccessfully;

	/* Referred Back Successfully toast message */
	@FindBy(xpath = "//span[contains(text(),'Referred Back Successfully.')]")
	private WebElement txtReferredBackSuccessfully;

	/* Export to excel button */
	@FindBy(xpath = "//button[text()='EXPORT TO EXCEL']")
	private WebElement btnExportToExcel;

	/* View All Claims link */
	@FindBy(xpath = "//a[@class='viewAll']")
	private WebElement lnkViewAllClaims;

	/* Get Enter remarks Text box xpath for given claim number */
	private WebElement getEnterRemarksTb(String claimNumber) {
		String xpath = "//a[text()='" + claimNumber + "']/ancestor::tr/td/input";
		return driver.findElement(By.xpath(xpath));
	}

	/* Get Approve xpath for given claim number */
	private WebElement getApprove(String claimNumber) {
		String xpath = "//a[text()='" + claimNumber
				+ "']/ancestor::tr/td/descendant::span/label[@data-original-title='Approve']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Get Reject xpath for given claim number */
	private WebElement getReject(String claimNumber) {
		String xpath = "//a[text()='" + claimNumber
				+ "']/ancestor::tr/td/descendant::span/label[@data-original-title='Reject']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Get Refer Back xpath for given claim number */
	private WebElement getReferBack(String claimNumber) {
		String xpath = "//a[text()='" + claimNumber + "']/ancestor::tr/td/descendant::span/label[@title='Refer Back']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Get Claim Number xpath for given claim number */
	private WebElement getClaimNumber(String claimNumber) {
		String xpath = "//a[text()='" + claimNumber + "']";
		return driver.findElement(By.xpath(xpath));
	}

	/* Approver Section Text */
	@FindBy(xpath = "//li[@class='pagination-item active']")
	private WebElement txtApproverSection;

	/* View Cash Claim Text */
	@FindBy(xpath = "//li[@class='pagination-item active']")
	private WebElement txtViewCashClaim;

	/* Get Status for given claim number */
	private WebElement getStatusTxt(String claimNumber) {
		String xpath = "//a[text()='" + claimNumber
				+ "']/ancestor::tr/descendant::span[text()='Status :']/following-sibling::span";
		return driver.findElement(By.xpath(xpath));
	}

	/* Cost center text */
	//@FindBy(xpath = "//p[@class='editname ng-binding']")
	@FindBy(xpath="//div[@class='form-group group']")
	private WebElement txtCostCenter;

	/* Cost center text */
	@FindBy(xpath = "//div[@class='form-group group']/p[@class='ng-binding']")
	private WebElement txtforCabBreakDown;
	/**
	 * Description Method to click on My Approvals Tab
	 * 
	 * @author Manish Kumar C D
	 * @param myApprovalsType
	 */
	public synchronized void clkMyApprovalsTab(String myAppovalsType) {
		try {
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(tabMyApprovals, "My Approvals Tab");
			WebActionUtil.clickOnElement(tabMyApprovals, "My Approvals Tab", "Unable to click on My Approvals Tab");

			for (WebElement myApprovalsMinorTabs : txtMyApprovalsType(myAppovalsType)) {
				String myApprovalsMinorTab = myApprovalsMinorTabs.getText();

				WebActionUtil.info("" + myApprovalsMinorTab + "");

			}

			clkMyApprovalsMinorTab(myAppovalsType);

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on My Approvals Tab");
			Assert.fail("Unable to click on My Approvals Tab");
		}
	}

	/**
	 * Description Method to select a minor tab under My Approvals Tab
	 * 
	 * @author Manish Kumar C D
	 * @param myAppovalsType
	 */
	public synchronized void clkMyApprovalsMinorTab(String myAppovalsType) {
		try {

			for (WebElement myApprovalsMinorTabs : txtMyApprovalsType(myAppovalsType)) {
				String myApprovalsMinorTab = myApprovalsMinorTabs.getText();
				if (myApprovalsMinorTab.contentEquals(myAppovalsType)) {
					WebActionUtil.actionClick(myApprovalsMinorTabs, "" + myAppovalsType + "");
					WebActionUtil.validationinfo("" + myAppovalsType + " is selected", "blue");
				}
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on My Approvals Minor Tab");
			Assert.fail("Unable to click on Approvals Minor Tab");
		}
	}

	/**
	 * Description Method to click on My Claims Tab
	 * 
	 * @author Manish Kumar C D
	 * @param myClaimsType
	 */
	public synchronized void clkMyClaimsTab(String myClaimsType) {
		try {
			WebActionUtil.waitForElement(tabMyClaims, "My Claims Tab");
			WebActionUtil.clickOnElement(tabMyClaims, "My Claims Tab", "Unable to click on My Claims Tab");

			for (WebElement myClaimsMinorTabs : txtMyClaimsType(myClaimsType)) {
				String myClaimsMinorTab = myClaimsMinorTabs.getText();

				WebActionUtil.info("" + myClaimsMinorTab + "");

			}

			clkMyClaimsMinorTab(myClaimsType);

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on My Claims Tab");
			Assert.fail("Unable to click on My Claims Tab");
		}
	}

	/**
	 * Description Method to to select a minor tab under My Claims Tab
	 * 
	 * @author Manish Kumar C D
	 * @param myClaimsType
	 */
	public synchronized void clkMyClaimsMinorTab(String myClaimsType) {
		try {

			for (WebElement myClaimsMinorTabs : txtMyClaimsType(myClaimsType)) {
				String myClaimsMinorTab = myClaimsMinorTabs.getText();
				if (myClaimsMinorTab.contentEquals(myClaimsType)) {
					WebActionUtil.actionClick(myClaimsMinorTabs, "" + myClaimsType + "");
					WebActionUtil.validationinfo("" + myClaimsType + " is selected", "blue");
				}
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on My Claims Minor Tab");
			Assert.fail("Unable to click on My Claims Minor Tab");
		}
	}

	/**
	 * Description Method to click on More Claims Tab
	 * 
	 * @author Manish Kumar C D
	 * @param expectedNewCashClaimUrl
	 */
	public synchronized void clkMoreClaimsTab(String expectedNewCashClaimUrl) {
		try {
			WebActionUtil.waitForAngularPageToLoad();
			WebActionUtil.waitForElement(tabMoreClaims, "More Claims Tab");
			WebActionUtil.clickOnElement(tabMoreClaims, "More Claims Tab", "Unable to click on More Claims Tab");
			WebActionUtil.switchToNewTab();
			String actualNewCashClaimUrl = driver.getCurrentUrl();
			if (actualNewCashClaimUrl.contentEquals(expectedNewCashClaimUrl)) {
				WebActionUtil.info("New Claim Page is Displayed., url= " + actualNewCashClaimUrl);
				WebActionUtil.validationinfo("New Claim Page is Displayed., url= " + actualNewCashClaimUrl, "blue");
			} else {
				WebActionUtil.info("Unable to Validate the New Claim Page");
			}

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on More Claims Tab");
			Assert.fail("Unable to click on More Claims Tab");
		}
	}

	/**
	 * Description Method to click on a Tab present under Raise New Claim text
	 * 
	 * @author Manish Kumar C D
	 * @param raiseNewClaimType
	 */
	public synchronized void clkRaiseNewClaimTab(String raiseNewClaimType) {
		try {

			for (WebElement raiseNewClaimTypeTabs : txtRaiseNewClaimType(raiseNewClaimType)) {
				String raiseNewClaimTypeTab = raiseNewClaimTypeTabs.getText();
				if (raiseNewClaimTypeTab.contentEquals(raiseNewClaimType)) {
					WebActionUtil.clickOnElementUsingJS(raiseNewClaimTypeTabs, raiseNewClaimType + " Tab");
					WebActionUtil.validationinfo(raiseNewClaimType + " is selected", "blue");
				}
			}
			WebActionUtil.poll(2000);
			WebActionUtil.switchToNewTab();
			WebActionUtil.poll(2000);

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on " + raiseNewClaimType + " Tab");
			Assert.fail("Unable to click on " + raiseNewClaimType + " Tab");
		}
	}

	/**
	 * Description Method to Select an option from the Hamburger Menu Dropdown
	 * present next to the profile Dropdown
	 * 
	 * @author Manish Kumar C D
	 * @param menuValue
	 */
	public synchronized void selectHamburgerMenuDd(String menuValue) {
		try {
			WebActionUtil.waitForElement(ddHamburger, "Hamburger Menu Dropdown");
			WebActionUtil.actionMouseOver(ddHamburger, "Hamburger Menu Dropdown");
			for (WebElement listHamburgerTxt : txtHamburgerList(menuValue)) {
				String optionHamburgerMenu = listHamburgerTxt.getText();
				if (optionHamburgerMenu.contentEquals(menuValue)) {
					WebActionUtil.actionClick(listHamburgerTxt, "Hamburger Menu Option");
					WebActionUtil.validationinfo(optionHamburgerMenu + " is selected", "blue");
					break;
				} else {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select Hamburger Menu Option");
			Assert.fail("Unable to Select Hamburger Menu Option");
		}
	}

	/**
	 * Description Method to Select an option from the profile Dropdown
	 * 
	 * @author Manish Kumar C D
	 * @param expectedLogoutUrl
	 * @param role
	 */
	public synchronized void selectProfiledd(String expectedLogoutUrl, String role) {
		try {
			WebActionUtil.waitForElement(ddProfile, "Profile Dropdown");
			WebActionUtil.actionMouseOver(ddProfile, "Profile Dropdown");
			clkLogoutbtn(expectedLogoutUrl, role);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to Select Profile Dropdown");
			Assert.fail("Unable to Select Profile Dropdown");
		}
	}

	/**
	 * Description Method to Click on Logout Button
	 * 
	 * @author Manish Kumar C D
	 * @param expectedLogoutUrl
	 * @param role
	 */
	public synchronized void clkLogoutbtn(String expectedLogoutUrl, String role) {
		try {
			WebActionUtil.waitForElement(btnLogout, "Logout Button");
			WebActionUtil.actionClick(btnLogout, "Logout Button");
			clkAreYouSureYouWantToLogoutYesBtn(expectedLogoutUrl, role);
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Logout Button ");
			Assert.fail("Unable to click on Logout Button");
		}
	}

	/**
	 * Description Method to Click on Are You Sure You Want To Logout Yes Button
	 * 
	 * 
	 * @author Manish Kumar C D
	 * @param expectedLogoutUrl
	 * @param role
	 */
	public synchronized void clkAreYouSureYouWantToLogoutYesBtn(String expectedLogoutUrl, String role) {
		try {
			WebActionUtil.validateisElementDisplayed(popUpAreYouSureYouWantToLogout,
					"Are You Sure You Want To Logout PopUp", "Are You Sure You Want To Logout PopUp is displayed",
					"Are You Sure You Want To Logout PopUp is not displayed", "blue");

			WebActionUtil.waitForElement(btnLogoutYes, "Yes Button");
			WebActionUtil.clickOnElement(btnLogoutYes, "Yes Button", "Unable to click on Yes Button");

			String actualLogoutUrl = driver.getCurrentUrl();
			if (actualLogoutUrl.contentEquals(expectedLogoutUrl)) {
				WebActionUtil.info("Logged out as " + role + " and Logout Page is Displayed., url= " + actualLogoutUrl);
				WebActionUtil.validationinfo(
						"Logged out as " + role + " and Logout Page is Displayed., url= " + actualLogoutUrl, "blue");
			} else {
				WebActionUtil.info("Unable to Validate the Logout Page");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes Button ");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description Method to Click on Are You Sure You Want To Logout No Button
	 * 
	 * @author Manish Kumar C D
	 * @param expectedLogoutUrl
	 */
	public synchronized void clkAreYouSureYouWantToLogoutNoBtn(String expectedLogoutUrl) {
		try {
			WebActionUtil.validateisElementDisplayed(popUpAreYouSureYouWantToLogout,
					"Are You Sure You Want To Logout PopUp", "Are You Sure You Want To Logout PopUp is displayed",
					"Are You Sure You Want To Logout PopUp is not displayed", "blue");

			WebActionUtil.waitForElement(btnLogoutNo, "No Button");
			WebActionUtil.clickOnElement(btnLogoutNo, "No Button", "Unable to click on No Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on No Button ");
			Assert.fail("Unable to click on No Button");
		}
	}

	/**
	 * Description This Method implements to enter data in Remarks text box
	 * 
	 * @author Sajal
	 * @param claimNumber
	 * @param remarks
	 */
	public synchronized void setRemarks(String claimNumber, String remarks) {
		try {
			WebActionUtil.waitForElement(getEnterRemarksTb(claimNumber), "Remarks Text box");
			WebActionUtil.typeText(getEnterRemarksTb(claimNumber), remarks, "Remarks Text box");
		} catch (Exception e) {
			WebActionUtil.fail("Unable to enter data in Remarks Text box");
			WebActionUtil.error("Unable to enter data in Remarks Text box");
			Assert.fail("Unable to enter data in Remarks Text box");
		}
	}

	/**
	 * Description Method to click on Approve icon
	 * 
	 * @author Sajal jain
	 * @param claimNumber
	 */
	public synchronized void clkApproveIcn(String claimNumber) {
		try {
			WebActionUtil.waitForElement(getApprove(claimNumber), "Approve Icon");
			WebActionUtil.clickOnElement(getApprove(claimNumber), "Approve Icon", "Unable to click on Approve Icon");
			clkSubmitBtn();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Approve Icon");
			Assert.fail("Unable to click on Approve Icon");
		}
	}

	/**
	 * Description Method to click on Reject icon
	 * 
	 * @author Sajal jain
	 * @param claimNumber
	 */
	public synchronized void clkRejectIcn(String claimNumber) {
		try {
			WebActionUtil.waitForElement(getReject(claimNumber), "Reject Icon");
			WebActionUtil.clickOnElement(getReject(claimNumber), "Reject Icon", "Unable to click on Reject Icon");
			clkSubmitBtn();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Reject Icon");
			Assert.fail("Unable to click on Reject Icon");
		}
	}

	/**
	 * Description Method to click on Refer Back icon
	 * 
	 * @author Sajal jain
	 * @param claimNumber
	 */
	public synchronized void clkReferBackIcn(String claimNumber) {
		try {
			WebActionUtil.waitForElement(getReferBack(claimNumber), "Refer Back Icon");
			WebActionUtil.clickOnElement(getReferBack(claimNumber), "Refer Back Icon",
					"Unable to click on Refer Back Icon");
			clkSubmitBtn();
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Refer Back Icon");
			Assert.fail("Unable to click on Refer Back Icon");
		}
	}

	/**
	 * Description Method to click on Submit button
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkSubmitBtn() {
		try {
			WebActionUtil.waitForElement(btnSubmit, "Submit Button");
			WebActionUtil.clickOnElement(btnSubmit, "Submit Button", "Unable to click on Submit Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Submit Button");
			Assert.fail("Unable to click on Submit Button");
		}
	}

	/**
	 * Description Method to click on Yes button
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkYesBtn() {
		try {
			WebActionUtil.validateisElementDisplayed(txtAreYouSureYouWantToProceed,
					"Are you sure you want to proceed text", "Are you sure you want to proceed pop up is displayed",
					"Are you sure you want to proceed pop up is not displayed", "blue");
			WebActionUtil.waitForElement(btnYes, "Yes Button");
			WebActionUtil.clickOnElement(btnYes, "Yes Button", "Unable to click on Yes Button");

		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Yes Button");
			Assert.fail("Unable to click on Yes Button");
		}
	}

	/**
	 * Description Method to click on No button
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkNoBtn() {
		try {
			WebActionUtil.validateisElementDisplayed(txtAreYouSureYouWantToProceed,
					"Are you sure you want to proceed text", "Are you sure you want to proceed pop up is displayed",
					"Are you sure you want to proceed pop up is not displayed", "blue");
			WebActionUtil.waitForElement(btnNo, "No Button");
			WebActionUtil.clickOnElement(btnNo, "No Button", "Unable to click on No Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on No Button");
			Assert.fail("Unable to click on No Button");
		}
	}

	/**
	 * Description Method to validate Rejected Successfully toast message
	 * 
	 * @author Sajal jain
	 */
	public synchronized void validateRejectedSuccessfully() {
		try {
			WebActionUtil.waitForElement(txtRejectedSuccessfully, "Rejected Successfully text");
			WebActionUtil.validateisElementDisplayed(txtRejectedSuccessfully, "Rejected Successfully text",
					"Rejected Successfully pop up is displayed", "Rejected Successfully pop up is not displayed",
					"green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Rejected Successfully text");
			Assert.fail("Unable to validate Rejected Successfully text");
		}
	}

	/**
	 * Description Method to validate Referred Back Successfully toast message
	 * 
	 * @author Sajal jain
	 */
	public synchronized void validateReferredBackSuccessfully() {
		try {
			WebActionUtil.waitForElement(txtReferredBackSuccessfully, "Referred Back Successfully text");
			WebActionUtil.validateisElementDisplayed(txtReferredBackSuccessfully, "Referred Back Successfully text",
					"Referred Back Successfully pop up is displayed",
					"Referred Back Successfully pop up is not displayed", "green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Referred Back Successfully text");
			Assert.fail("Unable to validate Referred Back Successfully text");
		}
	}

	/**
	 * Description Method to validate Approved Successfully toast message
	 * 
	 * @author Sajal jain
	 */
	public synchronized void validateApprovedSuccessfully() {
		try {
			WebActionUtil.waitForElement(txtApprovedSuccessfully, "Approved Successfully text");
			WebActionUtil.validateisElementDisplayed(txtApprovedSuccessfully, "Approved Successfully text",
					"Approved Successfully pop up is displayed", "Approved Successfully pop up is not displayed",
					"green");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to validate Approved Successfully text");
			Assert.fail("Unable to validate Approved Successfully text");
		}
	}

	/**
	 * Description Method to click on Export To Excel button
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkExportToExcelBtn() {
		try {
			WebActionUtil.waitForElement(btnExportToExcel, "Export To Excel Button");
			WebActionUtil.clickOnElement(btnExportToExcel, "Export To Excel Button",
					"Unable to click on Export To Excel Button");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Export To Excel Button");
			Assert.fail("Unable to click on Export To Excel Button");
		}
	}

	/**
	 * Description Method to click on View All Claims link
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkViewAllClaimsLnk() {
		try {
			WebActionUtil.waitForElement(lnkViewAllClaims, "View All Claims link");
			WebActionUtil.clickOnElement(lnkViewAllClaims, "View All Claims link",
					"Unable to click on View All Claims link");
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on View All Claims link");
			Assert.fail("Unable to click on View All Claims link");
		}
	}

	/**
	 * Description Method to click on Claim number text under My Approvals
	 * 
	 * @author Sajal jain
	 * @param claimNumber
	 */
	public synchronized void clkClaimNumberMyApproval(String claimNumber) {
		try {

			try {
				if (txtLoadingMyApproval.isDisplayed()) {
					new WebDriverWait(driver, 9).until(ExpectedConditions.invisibilityOf(txtLoadingMyApproval));
				}
			} catch (TimeoutException e) {
				try {
					WebActionUtil.waitForElement(tabMyApprovals, "My Approvals Tab");
					WebActionUtil.clickOnElement(tabMyApprovals, "My Approvals Tab",
							"Unable to click on My Approvals Tab");
					WebActionUtil.scrollToElement(txtLoadingMyApproval, "Loading Text in My Approval Tab");
					new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(txtLoadingMyApproval));
				} catch (Exception r) {
					WebActionUtil.fail("Page didnt load completely in given 60 seconds");
					Assert.fail("Page didnt load completely in given 60 seconds");
				}
			} catch (Exception e) {
				WebActionUtil.error(e.getMessage());
			}
			WebActionUtil.poll(2000);
			WebActionUtil.waitForElement(getClaimNumber(claimNumber), "Claim number : " + claimNumber);
//			WebActionUtil.clickOnElement(getClaimNumber(claimNumber), "Claim number : " + claimNumber,
//					"Unable to click on Claim number " + claimNumber + " in My Approvals Tab");
			getClaimNumber(claimNumber).click();
			WebActionUtil.info("Clicked on Claim number " + claimNumber + " in My Approvals Tab");
			WebActionUtil.extentinfo("Clicked on Claim number " + claimNumber + " in My Approvals Tab");
			WebActionUtil.switchToNewTab();
			WebActionUtil.waitForElement(txtApproverSection, "Approver Section text");
			WebActionUtil.validatetext("Approver Section", txtApproverSection, "Approver Section text",
					"Approver Section Page is displayed", "Approver Section Page is not displayed", "blue");
		} catch (StaleElementReferenceException e) {
			try {
				WebActionUtil.clickOnElementUsingJS(getClaimNumber(claimNumber),
						"Claim number " + claimNumber + " in My Approvals Tab");
				WebActionUtil.switchToNewTab();
				WebActionUtil.waitForElement(txtApproverSection, "Approver Section text");
				WebActionUtil.validatetext("Approver Section", txtApproverSection, "Approver Section text",
						"Approver Section Page is displayed", "Approver Section Page is not displayed", "blue");
			} catch (Exception r) {
				WebActionUtil.error(r.getMessage());
				WebActionUtil.fail("Unable to click on Claim number " + claimNumber + " in My Approvals Tab");
				Assert.fail("Unable to click on Claim number " + claimNumber + " in My Approvals Tab");
			}
		} catch (ElementClickInterceptedException e) {
			try {
				WebActionUtil.clickOnElementUsingJS(getClaimNumber(claimNumber),
						"Claim number " + claimNumber + " in My Approvals Tab");
				WebActionUtil.switchToNewTab();
				WebActionUtil.waitForElement(txtApproverSection, "Approver Section text");
				WebActionUtil.validatetext("Approver Section", txtApproverSection, "Approver Section text",
						"Approver Section Page is displayed", "Approver Section Page is not displayed", "blue");
			} catch (Exception r) {
				WebActionUtil.error(r.getMessage());
				WebActionUtil.fail("Unable to click on Claim number " + claimNumber + " in My Approvals Tab");
				Assert.fail("Unable to click on Claim number " + claimNumber + " in My Approvals Tab");
			}
		} catch (Exception ex) {
			try {
				WebActionUtil.waitForElement(tabMyApprovals, "My Approvals Tab");
				WebActionUtil.clickOnElement(tabMyApprovals, "My Approvals Tab", "Unable to click on My Approvals Tab");
				WebActionUtil.scrollToElement(txtLoadingMyApproval, "Loading Text in My Approval Tab");
				new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(txtLoadingMyApproval));
				WebActionUtil.clickOnElementUsingJS(getClaimNumber(claimNumber),
						"Claim number " + claimNumber + " in My Approvals Tab");
				WebActionUtil.switchToNewTab();
				WebActionUtil.waitForElement(txtApproverSection, "Approver Section text");
				WebActionUtil.validatetext("Approver Section", txtApproverSection, "Approver Section text",
						"Approver Section Page is displayed", "Approver Section Page is not displayed", "blue");
			} catch (Exception r) {
				WebActionUtil.error(r.getMessage());
				WebActionUtil.fail("Unable to click on Claim number " + claimNumber + " in My Approvals Tab");
				Assert.fail("Unable to click on Claim number " + claimNumber + " in My Approvals Tab");
			}
		}
	}

	/**
	 * Description Method to click on Claim number text under My Claim
	 * 
	 * @author Sajal jain
	 * @param claimNumber
	 */
	public synchronized void clkClaimNumberMyClaim(String claimNumber) {
		try {
			try {
				WebActionUtil.scrollToElement(txtLoadingMyClaim, "Loading Text in My Claim Tab");
				new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOf(txtLoadingMyClaim));
			} catch (Exception e) {
				WebActionUtil.fail("Page didnt load completely in given 60 seconds");
				Assert.fail("Page didnt load completely in given 60 seconds");
			}
			WebActionUtil.waitForElement(getClaimNumber(claimNumber), "Claim number : " + claimNumber);
			WebActionUtil.clickOnElement(getClaimNumber(claimNumber), "Claim number : " + claimNumber,
					"Unable to click on Claim number " + claimNumber + " in My Claims Tab");
			WebActionUtil.switchToNewTab();
			if (advanceSpinner.isDisplayed()) {
				WebActionUtil.waitForInvisibilityOfElement(advanceSpinner, "Spinner", (long) 60);
			}
			WebActionUtil.waitForElement(txtViewCashClaim, "View Cash Claim text");
			WebActionUtil.validatetext("View Cash Claim", txtViewCashClaim, "View Cash Claim text",
					"View Cash Claim Page is displayed", "View Cash Claim Page is not displayed", "blue");
			Assert.assertTrue(WebActionUtil.refreshPageIfElementNotDisplayed(txtCostCenter, "Cost Center Text"));
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to click on Claim number " + claimNumber + " in My Claims Tab");
			Assert.fail("Unable to click on Claim number " + claimNumber + " in My Claims Tab");
		} catch (AssertionError e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("View Cash claim not loaded properly");
			Assert.fail("View Cash claim not loaded properly");
		}
	}

	/**
	 * Description Method to Validate Status text
	 * 
	 * @author Sajal jain
	 * @param claimNumber
	 * @param expectedStatus
	 */
	public synchronized void validateStatusTxt(String claimNumber, String expectedStatus) {
		try {
			String expectedStatuses = expectedStatus.toLowerCase();
			WebActionUtil.waitForElement(getStatusTxt(claimNumber), "Status text");
			String statusText = getStatusTxt(claimNumber).getText().toLowerCase();
			WebActionUtil.info("Expected status is " + expectedStatuses + " Actual status is " + statusText);
			boolean value = (statusText.contains(expectedStatuses)) || statusText.equals("rejected");
			Assert.assertTrue(value);
			WebActionUtil.validationinfo(expectedStatuses + " status is displayed", "green");

		} catch (AssertionError e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail(expectedStatus + " status is not displayed");
			Assert.fail(expectedStatus + " status is not displayed");
		}

	}

	/**
	 * Description: Method to Return a Claim Number
	 * 
	 * @author Manish Kumar C D
	 * @param category
	 * @param amount
	 */
	public synchronized String getClaimNumber(String category, String amount) {
		String path = "";
		try {
			path = "//span[contains(text(),'" + category + "')]/ancestor::tr/td[3]//span[contains(text(),'" + amount
					+ "')]/ancestor::tr/td//a";
			driver.navigate().refresh();
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));

		} catch (StaleElementReferenceException e) {
			try {
				new WebDriverWait(driver, 20)
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));
			} catch (Exception r) {
				WebActionUtil.error(r.getMessage());
				WebActionUtil.fail("Unable to fetch Claim Number");
				Assert.fail("Unable to fetch Claim number");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fetch Claim Number");
			Assert.fail("Unable to fetch Claim number");
		}
		return driver.findElement(By.xpath(path)).getText();
	}
	/**
	 * Description: Method to Return a Claim Number for Cab breakdown
	 * 
	 * @author Manish Kumar C D
	 * @param category
	 * @param amount
	 */
	public synchronized String getCabBreakDownClaimNumber(String category) {
		String path = "";
		try {
			path = "//span[contains(text(),'" + category + "')]/ancestor::tr/td[3]/ancestor::tr/td//a";
			driver.navigate().refresh();
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));

		} catch (StaleElementReferenceException e) {
			try {
				new WebDriverWait(driver, 20)
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));
			} catch (Exception r) {
				WebActionUtil.error(r.getMessage());
				WebActionUtil.fail("Unable to fetch Claim Number");
				Assert.fail("Unable to fetch Claim number");
			}
		} catch (Exception e) {
			WebActionUtil.error(e.getMessage());
			WebActionUtil.fail("Unable to fetch Claim Number");
			Assert.fail("Unable to fetch Claim number");
		}
		return driver.findElement(By.xpath(path)).getText();
	}

	
}
