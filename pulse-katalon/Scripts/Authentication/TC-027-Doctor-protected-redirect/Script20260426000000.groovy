import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-027: Doctor protected pages redirect to /login without authentication
// Business rule: DoctorAuthGuard checks for JWT token; missing token -> redirect to /login.
// Expected: Navigating directly to /schedule (without login) lands on /login.
try {
    WebUI.openBrowser('')
    WebUI.maximizeWindow()
    // Navigate directly to a protected doctor page without logging in first
    WebUI.navigateToUrl(GlobalVariable.doctorBaseUrl + '/schedule')
    // DoctorAuthGuard (client-side) detects no token and redirects to /login
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyUrlContains'('/login')
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
