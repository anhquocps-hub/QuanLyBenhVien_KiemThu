import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/drugs')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTablePresent'()
    int initialRows = CustomKeywords.'pulseclinic.WebUiKeywords.tableRowCount'()
    if (initialRows == 0) {
        CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
    } else {
        String probe = (WebUI.executeJavaScript(
            "const cell=document.querySelector('[data-testid=\"data-table-row\"] td'); return cell ? cell.textContent.trim() : '';",
            null
        ) ?: '').toString()
        if (!probe) {
            KeywordUtil.markFailedAndStop('Could not read probe value from drug table first row.')
        }
        CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(probe)
        CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
    }
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

