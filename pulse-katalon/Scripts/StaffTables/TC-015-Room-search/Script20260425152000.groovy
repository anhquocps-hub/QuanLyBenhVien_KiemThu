import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/rooms')
    // FI-RUN2-03: simulate corrupted search query
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(GlobalVariable.roomSearchTerm + '##XBROKEN')
    // Client-side filter returns 0 rooms matching corrupted term
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

