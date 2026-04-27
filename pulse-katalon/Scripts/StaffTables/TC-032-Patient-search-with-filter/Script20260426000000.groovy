import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

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
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTablePresent'()
    int filteredRows = CustomKeywords.'pulseclinic.WebUiKeywords.tableRowCount'()
    if (filteredRows == 0) {
        KeywordUtil.markFailedAndStop('No rows after applying Male filter; cannot validate combined search+filter positive flow.')
    }
    String probe = (WebUI.executeJavaScript(
        "const cell=document.querySelector('[data-testid=\"data-table-row\"] td'); return cell ? cell.textContent.trim() : '';",
        null
    ) ?: '').toString()
    if (!probe) {
        KeywordUtil.markFailedAndStop('Could not extract patient probe for combined search+filter test.')
    }
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(probe)
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
