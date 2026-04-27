import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/patients')
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    // FI-RUN3-04: simulate gender filter option value corrupted by code fault
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('patient-filter-gender', 'Male_INVALID')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('patient-filter-apply')
    // Seed data guarantees at least 1 male patient (John Patient)
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

