import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/patients')
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(GlobalVariable.patientSearchTerm)
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTextPresent'(GlobalVariable.patientSearchTerm)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

