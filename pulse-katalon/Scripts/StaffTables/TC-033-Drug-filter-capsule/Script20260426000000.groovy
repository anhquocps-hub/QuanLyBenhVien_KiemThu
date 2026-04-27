import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-033: Drug filter by CAPSULE dosage form
// Business rule: Filtering by a specific dosage form must narrow results to that form only.
// Complements TC-014 (TABLET). Expected: results shown or empty state - no crash.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/drugs')
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    // FI-RUN3-05: simulate dosage form filter option value corrupted by code fault
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('drug-filter-dosage-form', 'CAPSULE_INVALID')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('drug-filter-apply')
    // Seed data guarantees 2 CAPSULE drugs exist
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
