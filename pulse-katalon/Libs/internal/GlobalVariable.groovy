package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object patientBaseUrl
     
    /**
     * <p></p>
     */
    public static Object hmsBaseUrl
     
    /**
     * <p></p>
     */
    public static Object staffBaseUrl
     
    /**
     * <p></p>
     */
    public static Object doctorBaseUrl
     
    /**
     * <p></p>
     */
    public static Object staffEmail
     
    /**
     * <p></p>
     */
    public static Object doctorEmail
     
    /**
     * <p></p>
     */
    public static Object patientEmail
     
    /**
     * <p></p>
     */
    public static Object staffPassword
     
    /**
     * <p></p>
     */
    public static Object doctorPassword
     
    /**
     * <p></p>
     */
    public static Object patientPassword
     
    /**
     * <p></p>
     */
    public static Object patientSearchTerm
     
    /**
     * <p></p>
     */
    public static Object drugSearchTerm
     
    /**
     * <p></p>
     */
    public static Object roomSearchTerm
     
    /**
     * <p></p>
     */
    public static Object defaultTimeout
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables('default')
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
    
            patientBaseUrl = selectedVariables['patientBaseUrl']
            hmsBaseUrl = selectedVariables['hmsBaseUrl']
            staffBaseUrl = selectedVariables['staffBaseUrl']
            doctorBaseUrl = selectedVariables['doctorBaseUrl']
            staffEmail = selectedVariables['staffEmail']
            doctorEmail = selectedVariables['doctorEmail']
            patientEmail = selectedVariables['patientEmail']
            staffPassword = selectedVariables['staffPassword']
            doctorPassword = selectedVariables['doctorPassword']
            patientPassword = selectedVariables['patientPassword']
            patientSearchTerm = selectedVariables['patientSearchTerm']
            drugSearchTerm = selectedVariables['drugSearchTerm']
            roomSearchTerm = selectedVariables['roomSearchTerm']
            defaultTimeout = selectedVariables['defaultTimeout']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
