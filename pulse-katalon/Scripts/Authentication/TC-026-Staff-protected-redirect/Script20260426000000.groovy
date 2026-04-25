import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// TC-026: Staff protected pages redirect to /login without authentication
// Business rule: StaffAuthGuard checks for JWT token; missing token -> redirect to /login.
// Expected: Navigating directly to /patients (without login) lands on /login.
try {
    WebUI.openBrowser('')
    WebUI.maximizeWindow()
    // Navigate directly to a protected staff page without logging in first
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/patients')
    // StaffAuthGuard (client-side) detects no token and redirects to /login
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyUrlContains'('/login')
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}
