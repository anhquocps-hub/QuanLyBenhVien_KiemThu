import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-035: Room filter by occupied (unavailable) status
// Business rule: Staff must be able to see currently occupied rooms for bed management.
// Complements TC-016 (available). Expected: results shown or empty state - no crash.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/rooms')
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    // FI-RUN3-08 (NEW Room fault): simulate room status filter option value corrupted
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('room-filter-status', 'occupied_INVALID')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('room-filter-apply')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
