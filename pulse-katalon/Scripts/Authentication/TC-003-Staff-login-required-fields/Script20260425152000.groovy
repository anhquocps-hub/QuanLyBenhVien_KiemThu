import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.openAt'(GlobalVariable.staffBaseUrl + '/login')
    WebUI.verifyElementHasAttribute(CustomKeywords.'pulseclinic.WebUiKeywords.byTestId'('login-email-input'), 'required', GlobalVariable.defaultTimeout as int)
    WebUI.verifyElementHasAttribute(CustomKeywords.'pulseclinic.WebUiKeywords.byTestId'('login-password-input'), 'required', GlobalVariable.defaultTimeout as int)
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('login-submit-button')
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

