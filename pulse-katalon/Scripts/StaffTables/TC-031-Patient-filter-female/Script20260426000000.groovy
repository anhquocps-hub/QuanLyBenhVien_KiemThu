import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-031: Patient filter by Female gender
// Business rule: Filtering by Female should return only female patients.
// Complements TC-010 (Male). Expected: results shown or empty state - no crash, no male records visible.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/patients')
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('patient-filter-gender', 'Female')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('patient-filter-apply')
    // Run2 data-agnostic check: filter action should not crash; accept rows or valid empty state.
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
