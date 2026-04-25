import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

// TC-005: Staff login must redirect specifically to /dashboard
// Uses loginWithCredentials (not loginAsStaff) to explicitly verify the destination URL.
try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginWithCredentials'(
        GlobalVariable.staffBaseUrl + '/login',
        GlobalVariable.staffEmail,
        GlobalVariable.staffPassword,
        '/dashboard'
    )
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

