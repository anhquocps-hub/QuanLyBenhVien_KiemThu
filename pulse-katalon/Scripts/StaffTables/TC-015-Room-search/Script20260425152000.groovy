import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/rooms')
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(GlobalVariable.roomSearchTerm)
    // verifyTextPresent finds text in search input itself, not the table - use verifyMinimumRows instead
    // Seed data guarantees at least 1 room matching '101' exists
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

