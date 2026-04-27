import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/patients')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTablePresent'()
    int initialRows = CustomKeywords.'pulseclinic.WebUiKeywords.tableRowCount'()
    if (initialRows == 0) {
        KeywordUtil.markFailedAndStop('Patient table has no data before search; cannot validate positive search flow.')
    }
    String probe = (WebUI.executeJavaScript(
        "const cell=document.querySelector('[data-testid=\"data-table-row\"] td'); return cell ? cell.textContent.trim() : '';",
        null
    ) ?: '').toString()
    if (!probe) {
        KeywordUtil.markFailedAndStop('Could not extract patient search probe from first row.')
    }
    CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(probe)
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

