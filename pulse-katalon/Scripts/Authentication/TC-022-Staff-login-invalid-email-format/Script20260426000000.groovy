import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-022: Staff login with invalid email format (no @ sign)
// Business rule: Input type="email" enforces RFC-compliant format at browser level.
// Expected: HTML5 email format validation fires; URL stays on /login.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.openAt'(GlobalVariable.staffBaseUrl + '/login')
    CustomKeywords.'pulseclinic.WebUiKeywords.setTextByTestId'('login-email-input', 'notanemail')
    CustomKeywords.'pulseclinic.WebUiKeywords.setTextByTestId'('login-password-input', 'anypassword')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('login-submit-button')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyUrlContains'('/login')
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
