import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/appointments')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('appointment-tab-confirmed')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('appointment-tab-cancelled')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTablePresent'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

