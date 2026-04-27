import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/patients')
    // FI-RUN2-03: simulate corrupted search query - search function appends garbage suffix
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(GlobalVariable.patientSearchTerm + '##XBROKEN')
    // Server returns 0 results for corrupted query - patient name not found in table
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTextPresent'(GlobalVariable.patientSearchTerm)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

