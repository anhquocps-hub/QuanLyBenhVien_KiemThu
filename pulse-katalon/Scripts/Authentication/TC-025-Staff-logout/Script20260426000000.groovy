import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-025: Staff logout
// Business rule: Clicking Logout clears the JWT cookie and localStorage, then redirects to /login.
// Expected: After clicking logout-button, URL changes to /login.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyUrlContains'('/dashboard')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('logout-button')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyUrlContains'('/login')
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
