import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.openAt'(GlobalVariable.staffBaseUrl + '/login')
    CustomKeywords.'pulseclinic.WebUiKeywords.setTextByTestId'('login-email-input', 'wrong.staff@hospital.com')
    CustomKeywords.'pulseclinic.WebUiKeywords.setTextByTestId'('login-password-input', 'wrong-password')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('login-submit-button')
    WebUI.waitForElementPresent(CustomKeywords.'pulseclinic.WebUiKeywords.byTestId'('login-error'), GlobalVariable.defaultTimeout as int)
    WebUI.verifyElementVisible(CustomKeywords.'pulseclinic.WebUiKeywords.byTestId'('login-error'))
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

