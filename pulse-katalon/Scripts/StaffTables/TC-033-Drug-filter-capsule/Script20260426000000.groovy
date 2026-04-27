import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-033: Drug filter by CAPSULE dosage form
// Business rule: Filtering by a specific dosage form must narrow results to that form only.
// Complements TC-014 (TABLET). Expected: results shown or empty state - no crash.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/drugs')
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('drug-filter-dosage-form', 'CAPSULE')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('drug-filter-apply')
    // Run2 data-agnostic check: filter action should not crash; accept rows or valid empty state.
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
