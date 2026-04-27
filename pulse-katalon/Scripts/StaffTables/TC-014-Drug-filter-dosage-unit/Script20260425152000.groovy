import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/drugs')
    CustomKeywords.'pulseclinic.WebUiKeywords.openToolbarFilter'()
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('drug-filter-dosage-form', 'TABLET')
    CustomKeywords.'pulseclinic.WebUiKeywords.selectByValueTestId'('drug-filter-unit', 'TABLET')
    CustomKeywords.'pulseclinic.WebUiKeywords.clickByTestId'('drug-filter-apply')
    // Seed data guarantees at least 3 TABLET drugs exist
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

