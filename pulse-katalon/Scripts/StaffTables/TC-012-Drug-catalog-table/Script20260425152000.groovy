import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/drugs')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTextPresent'('Paracetamol')
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

