import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

// TC-017: Waitlist page loads with correct empty state
// Note: No waitlist entries are seeded. The test verifies the page renders correctly
// with an empty table (not a crash, not a blank page). When real waitlist data exists,
// upgrade this test to verify EMERGENCY > PRIORITY > NORMAL row ordering.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/wait-list')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

