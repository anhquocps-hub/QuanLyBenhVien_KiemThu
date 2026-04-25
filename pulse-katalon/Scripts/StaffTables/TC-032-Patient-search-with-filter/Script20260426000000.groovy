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
    // Then also apply search query on top of active filter
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(GlobalVariable.patientSearchTerm)
    // "John Patient" is Male - filter=Male + search="John Patient" must return ≥1 row
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
