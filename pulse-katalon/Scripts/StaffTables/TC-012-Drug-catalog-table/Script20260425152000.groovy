import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/drugs')
    // Run2 data-agnostic: validate catalog behavior, not a hardcoded seed name.
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

