import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-032: Patient search combined with gender filter
// Business rule: Search and filter constraints are additive - results must satisfy BOTH conditions.
// Expected: verifyRowsOrEmptyState passes; no crash when combining free-text search with dropdown filter.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/patients')
    // Apply gender filter first
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('patient-filter-gender', 'Male')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('patient-filter-apply')
    // FI-RUN2-03: simulate corrupted search combined with filter
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(GlobalVariable.patientSearchTerm + '##XBROKEN')
    // Corrupted search returns 0 results even when filter is correctly applied
    // "John Patient" is Male - filter=Male + search="John Patient" must return ≥1 row
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
