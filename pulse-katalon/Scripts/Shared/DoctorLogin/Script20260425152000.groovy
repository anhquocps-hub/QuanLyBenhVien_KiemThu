import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.util.KeywordUtil

try {
    CustomKeywords.'pulseclinic.WebUiKeywords.loginAsDoctor'()
} finally {
    CustomKeywords.'pulseclinic.WebUiKeywords.closeBrowser'()
}

