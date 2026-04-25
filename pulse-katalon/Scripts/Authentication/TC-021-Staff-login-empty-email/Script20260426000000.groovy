import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-021: Staff login with empty email
// Business rule: Email field marked as required; browser HTML5 validation blocks submission.
// Expected: URL stays on /login after clicking Submit with blank email.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.openAt'(GlobalVariable.staffBaseUrl + '/login')
    // Deliberately skip email - only fill password
    CustomKeywords.'pulseclinic.WebUiKeywords.setTextByTestId'('login-password-input', 'anypassword')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('login-submit-button')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyUrlContains'('/login')
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
