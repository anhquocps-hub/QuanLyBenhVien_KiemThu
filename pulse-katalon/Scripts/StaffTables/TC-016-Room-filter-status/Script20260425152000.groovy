import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/rooms')
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    // FI-RUN3-08 (NEW Room fault): simulate room status filter option value corrupted
    // This is a new fault not present in run1 - targets Room management feature
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('room-filter-status', 'available_INVALID')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('room-filter-apply')
    // Seed data seeds all rooms as available (isAvailable=true), so at least 1 must appear
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

