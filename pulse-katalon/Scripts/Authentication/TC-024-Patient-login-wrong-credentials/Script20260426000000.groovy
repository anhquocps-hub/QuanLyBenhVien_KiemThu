import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-024: Patient login with wrong credentials
// Business rule: Backend returns 401 for bad credentials; UI must display an error.
// Expected: login-error element visible with error message.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.openAt'(GlobalVariable.patientBaseUrl + '/login')
    CustomKeywords.'pulseclinic.WebUiKeywords.setTextByTestId'('login-email-input', 'wrong.patient@example.com')
    CustomKeywords.'pulseclinic.WebUiKeywords.setTextByTestId'('login-password-input', 'wrongpassword')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('login-submit-button')
    WebUI.waitForElementVisible(CustomKeywords.'pulseclinic.WebUiKeywords.byTestId'('login-error'), GlobalVariable.defaultTimeout as int)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
