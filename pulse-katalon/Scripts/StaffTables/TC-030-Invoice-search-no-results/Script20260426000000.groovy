import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-030: Invoice search returns no results
// Business rule: When no invoice patient matches the query, table shows 0 rows (empty state).
// Expected: verifyRowsOrEmptyState passes with 0 rows after searching gibberish.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/invoices')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTablePresent'()
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'('XYZNOTEXIST99999')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
