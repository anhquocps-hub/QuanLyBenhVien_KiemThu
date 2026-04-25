import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/drugs')
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(GlobalVariable.drugSearchTerm)
    // verifyTextPresent finds text in search input itself, not the table - use verifyMinimumRows instead
    // Seed data guarantees at least 1 drug matching 'Paracetamol' exists
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

