import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsStaff'()
    WebUI.navigateToUrl(GlobalVariable.staffBaseUrl + '/patients')
    CustomKeywords.'pulseclinic.WebUiKeywords.verifyTablePresent'()
    int initialRows = CustomKeywords.'pulseclinic.WebUiKeywords.tableRowCount'()
    if (initialRows == 0) {
        // Keep test transparent: if list is empty, validate empty-state behavior instead of forcing seeded name.
        CustomKeywords.'pulseclinic.WebUiKeywords.verifyRowsOrEmptyState'()
    } else {
        // Data-agnostic happy path: pick first visible row value, search it, expect at least one result.
        String probe = (WebUI.executeJavaScript(
            "const cell=document.querySelector('[data-testid=\"data-table-row\"] td'); return cell ? cell.textContent.trim() : '';",
            null
        ) ?: '').toString()
        if (!probe) {
            KeywordUtil.markFailedAndStop('Could not read probe value from patient table first row.')
        }
        CustomKeywords.'pulseclinic.WebUiKeywords.searchToolbar'(probe)
        CustomKeywords.'pulseclinic.WebUiKeywords.verifyMinimumRows'(1)
    }
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

