package extent_test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class YourClass {

    public static ExtentReports initExtentReport(String reportFilePath) {
        // Set up ExtentSparkReporter
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFilePath);
        htmlReporter.config().setDocumentTitle("Ostrum Automation Extent Reports");
        htmlReporter.config().setReportName("Automation Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);

        // Set up ExtentReports and attach ExtentSparkReporter
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Set system information
        extent.setSystemInfo("Organization Name", "Ostrum Tech");
        extent.setSystemInfo("Organization Tag", "Helping everyone fly!");
        extent.setSystemInfo("Employee Name", "Man Singh");
        extent.setSystemInfo("Testing Framework", "TestNG");
        extent.setSystemInfo("System's User Name", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Environment", "Test ENV");
        extent.setSystemInfo("Report Type", "Extent Html Report");
        
        return extent;
    }
 

    public static void main(String[] args) {
        // Define report file path
        String reportFilePath = "ExtentReports\\OstrumExtentReport.html";

        // Call intExtentReport method
        ExtentReports extentReports = initExtentReport(reportFilePath);

        // Create a test
        ExtentTest test = extentReports.createTest("Your Test Name");

       
 
        // Log some information
        test.log(Status.INFO, "This is an informational message.");
        
        // Simulate a test scenario with nested steps
        testPassTestSteps(test);

        // Log a failure
        testFailTestSteps(test);

        // Log a skipped test step
        testSkipTestSteps(test);

        // End test
        test.pass("Test completed successfully.");

        // Flush the report
        extentReports.flush();
    }

    public static void testPassTestSteps(ExtentTest test) {
        test.pass("Step 1 - Passed");
        test.pass("Step 2 - Passed");
        test.pass("Step 3 - Passed");
    }

    public static void testFailTestSteps(ExtentTest test) {
        test.fail("Step 4 - Failed");
        test.fail("Step 5 - Failed");
    }

    public static void testSkipTestSteps(ExtentTest test) {
        test.skip("Step 6 - Skipped");
    }
}
