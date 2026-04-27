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
    // FI-RUN2-02: simulate error message hidden by code fault (display:none)
    // In the broken scenario, error element is rendered but invisible - this assertion detects it
    WebUI.verifyElementNotVisible(CustomKeywords.'pulseclinic.WebUiKeywords.byTestId'('login-error'))
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

