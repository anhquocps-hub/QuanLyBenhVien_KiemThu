import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/rooms')
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(GlobalVariable.roomSearchTerm)
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTextPresent'(GlobalVariable.roomSearchTerm)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

