import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-034: Waitlist filter by EMERGENCY priority
// Business rule: Critical-priority patients must always be filterable for quick triage access.
// Complements TC-018 (NORMAL). Valid values: ALL, EMERGENCY, PRIORITY, NORMAL.
// Expected: results shown or empty state - no crash.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/wait-list')
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    // FI-RUN3-07: simulate waitlist priority filter option value corrupted by code fault
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('waitlist-filter-priority', 'EMERGENCY_INVALID')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('waitlist-filter-apply')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
