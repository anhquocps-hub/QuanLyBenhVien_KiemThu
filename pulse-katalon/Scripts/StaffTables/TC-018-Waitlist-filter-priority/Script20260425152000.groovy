import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/wait-list')
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    // FI-RUN3-07: simulate waitlist priority filter option value corrupted by code fault
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('waitlist-filter-priority', 'NORMAL_INVALID')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('waitlist-filter-apply')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

