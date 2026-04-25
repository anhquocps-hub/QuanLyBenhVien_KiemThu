import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData


import com.katalon.execution.application.ExecutionMain

Map<String, String> suiteProperties = new HashMap<String, String>();

suiteProperties.put('rerunTestFailImmediately', 'false')
suiteProperties.put('retryCount', '0')
suiteProperties.put('name', 'Baseline')
suiteProperties.put('description', 'Baseline suite for Pulse Clinic web. Expected result before fault injection: 100% PASS.')
suiteProperties.put('id', 'Test Suites/Baseline')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.windows.keyword.contribution.WindowsDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.testng.keyword.internal.TestNGDriverCleaner())



RunConfiguration.setExecutionSettingFile("D:\\UNI_DOCS\\HK6\\KCPM\\QuanLyBenhVien_KiemThu\\pulse-katalon\\Reports\\20260426_024044\\Baseline\\20260426_024044\\execution.properties")

TestCaseMain.beforeStart()

new ExecutionMain().init();

TestCaseMain.startTestSuite('Test Suites/Baseline', suiteProperties, new File("D:\\UNI_DOCS\\HK6\\KCPM\\QuanLyBenhVien_KiemThu\\pulse-katalon\\Reports\\20260426_024044\\Baseline\\20260426_024044\\testCaseBinding"))
