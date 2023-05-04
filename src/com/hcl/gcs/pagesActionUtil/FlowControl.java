package com.hcl.gcs.pagesActionUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.hcl.gcs.baseutil.BaseTest;

public class FlowControl extends BaseTest {

	public static void flowPath(String[][] arrFlowControl, Map<String, String> mapDataKeyValues, WebDriver driver) {
		Map<String, String[]> mapEmpLoginDetails = WebActionUtil
				.getLoginDetailsForEmployeeByEmpCode(mapDataKeyValues.get("employeeCode"));
		
		String employeeLogin[] = mapEmpLoginDetails.get("Employee");	
		Map<String, String[]> mapOtherLoginDetails = null;
		Map<String, String> mapRolesIds = new LinkedHashMap<>();
		String action = null;
		String role = null;
		
		for (String[] roleAction : arrFlowControl) {
			role = roleAction[0];
			action = roleAction[1];

			if (role.equalsIgnoreCase("Employee")) {
				if (action.equalsIgnoreCase("InitiateClaim")) {
					mapRolesIds = PagesActionUtil.initiateClaim(employeeLogin[0], employeeLogin[1], mapDataKeyValues);
					WebActionUtil.closeChildTab();
					mapOtherLoginDetails = WebActionUtil.getLoginDetailsForOtherRoles(mapRolesIds,
							mapDataKeyValues.get("esaEmpcode"));
				} else {
					checkClaimStatus(action, employeeLogin[0], employeeLogin[1], mapDataKeyValues);
				}
			} else if (role.equalsIgnoreCase("RM")) {

				String[] loginroleRM = mapOtherLoginDetails.get("RM/AM");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleRM[0], loginroleRM[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleRM[0], loginroleRM[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleRM[0], loginroleRM[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {
					PagesActionUtil.approveClaim(loginroleRM[0], loginroleRM[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleRM[0], loginroleRM[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleRM[0], loginroleRM[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}

			} else if (role.equalsIgnoreCase("ES Approver")) {
				String[] loginroleES = mapOtherLoginDetails.get("ES Approver");

				if (action.equalsIgnoreCase("ApproveClaim")) {
					PagesActionUtil.esApprove(loginroleES[0], loginroleES[1], mapDataKeyValues, driver);

					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaim")) {
					PagesActionUtil.esReferBack(loginroleES[0], loginroleES[1], mapDataKeyValues, driver);

					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaim")) {
					PagesActionUtil.esReject(loginroleES[0], loginroleES[1], mapDataKeyValues, driver);
					WebActionUtil.closeChildTab();
				}
			} else if (role.equalsIgnoreCase("L1 Head")) {
				String[] loginroleL1 = mapOtherLoginDetails.get("L1 Head");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleL1[0], loginroleL1[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleL1[0], loginroleL1[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleL1[0], loginroleL1[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleL1[0], loginroleL1[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleL1[0], loginroleL1[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleL1[0], loginroleL1[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			} else if (role.equalsIgnoreCase("L2 Head")) {
				String[] loginroleL2 = mapOtherLoginDetails.get("L2 Head");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleL2[0], loginroleL2[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleL2[0], loginroleL2[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleL2[0], loginroleL2[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleL2[0], loginroleL2[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleL2[0], loginroleL2[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleL2[0], loginroleL2[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			} else if (role.equalsIgnoreCase("L3 Head")) {
				String[] loginroleL3 = mapOtherLoginDetails.get("L3 Head");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleL3[0], loginroleL3[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleL3[0], loginroleL3[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleL3[0], loginroleL3[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleL3[0], loginroleL3[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleL3[0], loginroleL3[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleL3[0], loginroleL3[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			} else if (role.equalsIgnoreCase("L4 Head")) {
				String[] loginroleL4 = mapOtherLoginDetails.get("L4 Head");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleL4[0], loginroleL4[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleL4[0], loginroleL4[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleL4[0], loginroleL4[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleL4[0], loginroleL4[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleL4[0], loginroleL4[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleL4[0], loginroleL4[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			} else if (role.equalsIgnoreCase("BU Head")) {
				String[] loginroleBuHead = mapOtherLoginDetails.get("BUHead");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleBuHead[0], loginroleBuHead[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleBuHead[0], loginroleBuHead[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleBuHead[0], loginroleBuHead[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleBuHead[0], loginroleBuHead[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleBuHead[0], loginroleBuHead[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleBuHead[0], loginroleBuHead[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			} else if (role.equalsIgnoreCase("BUHR")) {
				String[] loginroleBUHR = mapOtherLoginDetails.get("BUHR");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleBUHR[0], loginroleBUHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleBUHR[0], loginroleBUHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleBUHR[0], loginroleBUHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleBUHR[0], loginroleBUHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleBUHR[0], loginroleBUHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleBUHR[0], loginroleBUHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			} else if (role.equalsIgnoreCase("LOB HR")) {
				String[] loginroleLOBHR = mapOtherLoginDetails.get("LOB HR");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleLOBHR[0], loginroleLOBHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleLOBHR[0], loginroleLOBHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleLOBHR[0], loginroleLOBHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleLOBHR[0], loginroleLOBHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleLOBHR[0], loginroleLOBHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleLOBHR[0], loginroleLOBHR[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			} else if (role.equalsIgnoreCase("TechCEED")) {
				String[] loginroleTechCeed = mapOtherLoginDetails.get("TechCEED");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			} else if (role.equalsIgnoreCase("EXP_APP")) {
				String[] loginroleTechCeed = mapOtherLoginDetails.get("EXP_APP");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {

					PagesActionUtil.approveClaimThroughClaimNumber(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleTechCeed[0], loginroleTechCeed[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			}

			else if (role.equalsIgnoreCase("ISG_App")) {
				String[] loginroleISG = mapOtherLoginDetails.get("ISG_App");
				if (action.equalsIgnoreCase("ApproveClaimThroughClaimNumber")) {
					PagesActionUtil.approveClaimThroughClaimNumber(loginroleISG[0], loginroleISG[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughClaimNumber")) {
					PagesActionUtil.referBackClaimThroughClaimNumber(loginroleISG[0], loginroleISG[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughClaimNumber")) {
					PagesActionUtil.rejectClaimThroughClaimNumber(loginroleISG[0], loginroleISG[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ApproveClaimThroughIcon")) {

					PagesActionUtil.approveClaim(loginroleISG[0], loginroleISG[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("ReferBackClaimThroughIcon")) {
					PagesActionUtil.referBackClaim(loginroleISG[0], loginroleISG[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				} else if (action.equalsIgnoreCase("RejectClaimThroughIcon")) {
					PagesActionUtil.rejectClaim(loginroleISG[0], loginroleISG[1],
							prop_constants.getProperty("non_travel_approval"), mapDataKeyValues);
					WebActionUtil.closeChildTab();
				}
			}

		}
	}

	public static void checkClaimStatus(String checkStatus, String employeeCode, String password,
			Map<String, String> mapDataKeyValues) {

		String[] role_status = checkStatus.split("_");
		String role = role_status[0].toUpperCase();
		String status = role_status[1].toUpperCase();
		String option = role_status[2].toUpperCase();

		switch (role) {
		case "RM": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_am"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_am"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_am"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {

					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_am"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_am"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_am"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}

			}
			break;
		}

		case "L1HEAD": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_l1_head"),
							prop_constants.getProperty("non_travel_claims"));

					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_l1_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_l1_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_l1_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_l1_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_l1_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}

			}
			break;
		}

		case "L2HEAD": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_l2_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_l2_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_l2_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_l2_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_l2_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_l2_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}

			}
			break;
		}

		case "L3HEAD": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_l3_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_l3_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_l3_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_l3_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_l3_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_l3_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}

			}
			break;
		}

		case "L4HEAD": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_l4_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_l4_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_l4_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_l4_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REJECT": {
				switch (option) {

				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_l4_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_l4_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}

			}
			break;

		}
		case "LOBHR": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_lobhr"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_lobhr"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_lobhr"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_lobhr"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_lobhr"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_lobhr"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}

			}
			break;

		}
		case "ISGAPPROVER": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_isg_approver"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_isg_approver"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_isg_approver"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_isg_approver"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_isg_approver"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_isg_approver"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}

			}
			break;

		}

		case "BUHR": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_bu_hr"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_bu_hr"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_bu_hr"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_bu_hr"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_bu_hr"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_bu_hr"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}

			}
			break;

		}

		case "BUHEAD": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_bu_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_bu_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_bu_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_bu_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_bu_head"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_bu_head"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;
			}

			}
			break;

		}
		case "TECHCEED": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_tech_ceed"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_tech_ceed"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_tech_ceed"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_tech_ceed"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_tech_ceed"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_tech_ceed"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}

			}
			break;

		}
		case "EXPAPP": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_exp_app"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_exp_app"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_exp_app"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_exp_app"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_exp_app"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_exp_app"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}

			}
			break;

		}

		case "ESAPPROVER": {
			switch (status) {
			case "APPROVE": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("approved_by_es"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateApproveStatusClaim(employeeCode, password,
							prop_constants.getProperty("approved_by_es"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REFERBACK": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_es"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateReferBackStatusAndupdateClaim(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("referred_back_by_es"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}
			case "REJECT": {
				switch (option) {
				case "HOMEPAGE": {
					PagesActionUtil.validateStatusHomePage(employeeCode, password, mapDataKeyValues,
							prop_constants.getProperty("rejected_by_es"),
							prop_constants.getProperty("non_travel_claims"));
					WebActionUtil.closeChildTab();
					break;
				}
				case "CLAIMID": {
					PagesActionUtil.validateRejectStatusClaim(employeeCode, password,
							prop_constants.getProperty("rejected_by_es"));
					WebActionUtil.closeChildTab();
					break;
				}

				}
				break;

			}

			}
			break;

		}

		}

	}
}