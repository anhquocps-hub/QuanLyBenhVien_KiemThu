import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.openAt'(GlobalVariable.hmsBaseUrl)
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTextPresent'('Hospital Management System')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('role-card-staff')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyUrlContains'('/login')
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

