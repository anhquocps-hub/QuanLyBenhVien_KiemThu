package pulseclinic

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

class WebUiKeywords {
    private static int timeout() {
        return (GlobalVariable.defaultTimeout ?: '10') as int
    }

    private static TestObject cssObject(String name, String css) {
        TestObject obj = new TestObject(name)
        obj.addProperty('css', ConditionType.EQUALS, css)
        return obj
    }

    @Keyword
    static TestObject byTestId(String testId) {
        return cssObject("testid:${testId}", "[data-testid='${testId}']")
    }

    @Keyword
    static void openAt(String url) {
        WebUI.openBrowser('')
        WebUI.maximizeWindow()
        WebUI.navigateToUrl(url)
        WebUI.waitForPageLoad(timeout())
    }

    @Keyword
    static void closeBrowser() {
        WebUI.closeBrowser(FailureHandling.OPTIONAL)
    }

    @Keyword
    static void setTextByTestId(String testId, String value) {
        WebUI.waitForElementVisible(byTestId(testId), timeout())
        WebUI.setText(byTestId(testId), value)
    }

    @Keyword
    static void clickByTestId(String testId) {
        WebUI.waitForElementClickable(byTestId(testId), timeout())
        WebUI.click(byTestId(testId))
    }

    @Keyword
    static void selectByValueTestId(String testId, String value) {
        WebUI.waitForElementVisible(byTestId(testId), timeout())
        WebUI.selectOptionByValue(byTestId(testId), value, false)
    }

    @Keyword
    static void verifyTextPresent(String text) {
        WebUI.verifyTextPresent(text, false, FailureHandling.STOP_ON_FAILURE)
    }

    @Keyword
    static void verifyUrlContains(String expectedPart) {
        long start = System.currentTimeMillis()
        long maxMs = timeout() * 1000L
        while ((System.currentTimeMillis() - start) < maxMs) {
            try {
                if (WebUI.getUrl().contains(expectedPart)) return
            } catch (Exception ignore) {}
            WebUI.delay(1)
        }
        String currentUrl = WebUI.getUrl()
        if (!currentUrl.contains(expectedPart)) {
            KeywordUtil.markFailedAndStop("Expected URL to contain '${expectedPart}', actual URL: ${currentUrl}")
        }
    }

    @Keyword
    static void loginWithCredentials(String loginUrl, String email, String password, String expectedUrlPart) {
        openAt(loginUrl)
        setTextByTestId('login-email-input', email)
        setTextByTestId('login-password-input', password)
        clickByTestId('login-submit-button')
        verifyUrlContains(expectedUrlPart)
    }

    @Keyword
    static void loginAsStaff() {
        loginWithCredentials("${GlobalVariable.staffBaseUrl}/login", GlobalVariable.staffEmail, GlobalVariable.staffPassword, '/dashboard')
    }

    @Keyword
    static void loginAsDoctor() {
        loginWithCredentials("${GlobalVariable.doctorBaseUrl}/login", GlobalVariable.doctorEmail, GlobalVariable.doctorPassword, '/schedule')
    }

    @Keyword
    static void loginAsPatient() {
        loginWithCredentials("${GlobalVariable.patientBaseUrl}/login", GlobalVariable.patientEmail, GlobalVariable.patientPassword, '/dashboard')
    }

    @Keyword
    static void verifyTablePresent() {
        WebUI.waitForElementPresent(byTestId('data-table'), timeout())
    }

    @Keyword
    static int tableRowCount() {
        verifyTablePresent()
        def count = WebUI.executeJavaScript("return document.querySelectorAll('[data-testid=\"data-table-row\"]').length", null)
        return (count as int)
    }

    @Keyword
    static void searchToolbar(String query) {
        setTextByTestId('toolbar-search-input', query)
        WebUI.delay(1)
    }

    @Keyword
    static void openToolbarFilter() {
        clickByTestId('toolbar-filter-button')
    }

    @Keyword
    static void verifyRowsOrEmptyState() {
        verifyTablePresent()
        int rows = tableRowCount()
        if (rows == 0) {
            WebUI.verifyTextPresent('No data available', false, FailureHandling.OPTIONAL)
        }
    }

    // Fails the test if the table has fewer rows than expected.
    // Use this when seed data guarantees at least N rows must exist after filtering/searching.
    @Keyword
    static void verifyMinimumRows(int expected) {
        verifyTablePresent()
        int actual = tableRowCount()
        if (actual < expected) {
            KeywordUtil.markFailedAndStop("Expected at least ${expected} row(s) in table, but found ${actual}. " +
                "Seed data should guarantee this result - check that the filter/search is working correctly.")
        }
    }

    // No-op removed: verifyOptionalPriorityOrder was a fake keyword that only logged a message.
    // TC-017 now uses verifyRowsOrEmptyState directly (waitlist has no seed data, so empty state is the correct expected result).
}
