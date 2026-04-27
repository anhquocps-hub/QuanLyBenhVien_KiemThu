import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/invoices')
    // FI-RUN2-03: simulate corrupted search on invoice page
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(GlobalVariable.patientSearchTerm + '##XBROKEN')
    // Invoice search broken - patient name not found in results
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTextPresent'(GlobalVariable.patientSearchTerm)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

