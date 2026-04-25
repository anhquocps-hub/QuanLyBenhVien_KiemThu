import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-028: Patient search returns no results
// Business rule: When no patient matches the query, table shows 0 rows (empty state).
// Expected: verifyRowsOrEmptyState passes with 0 rows after searching gibberish.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/patients')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTablePresent'()
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'('XYZNOTEXIST99999')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
