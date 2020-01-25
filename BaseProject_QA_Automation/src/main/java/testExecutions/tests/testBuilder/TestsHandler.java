package testExecutions.tests.testBuilder;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import testExecutions.appInteraction.genericMethods.BasePageAndroid;
import testExecutions.appInteraction.genericMethods.BasePage_Mobile;
import testExecutions.appInteraction.pagesTests.resources.Information;

public class TestsHandler extends Information{
	
    private String testName;
    private int OS;
    private WebDriver driver;
    private AppiumDriver<?> app_driver;
	private BasePage_Mobile bp;
    private int testIndex;
    private boolean[] results;
    private long startTime = System.currentTimeMillis();
    String result;
    
    public TestsHandler(AppiumDriver<?> app_driver, int OS, String testName) {

        this.app_driver = app_driver;
        this.OS = OS;
        this.testName = testName;
        this.bp = new BasePageAndroid((AndroidDriver<?>) app_driver);
    }
    public void execute(int index) throws InterruptedException, IOException, InvalidFormatException {
    	
        testIndex = initiateTest();
        results = new TestsCreator(app_driver, OS,  testName).testExecutor(index);
        concludeTest(testIndex, index);

    }
    private int initiateTest() throws InterruptedException {

        testNames.add(testName);
        testIndex = testNames.indexOf(testName);

        logger.info("\n--------------------------\nTest " + (testIndex+1) + " in progress...\n--------------------------\n");

        bp.captureScreenshot(this.app_driver,OK,testName);

        return testIndex;

    }
    private void concludeTest(int testIndex, int index) throws InterruptedException {

        if(results[2]) {
            logger.error("\nSomething went wrong during the execution, such that test case was not properly executed\n");
            bp.captureScreenshot(app_driver,BLOCKED,testName);
           // bpa.captureScreenshot(app_driver,BLOCKED,testName);
            result = "BLOCKED";

        } else if (results[1]) {
            result = "KO";
            // bpa.captureScreenshot(app_driver,KO,testName);
            bp.captureScreenshot(app_driver,KO,testName);
        } else if (results[0]) {
            result = "KO";
            bp.captureScreenshot(app_driver,KO,testName);
        } else {
            result = "OK";
            bp.captureScreenshot(app_driver,OK,testName);
           // bpa.captureScreenshot(app_driver,OK,testName);
        }


        testResults.add(result);

        executedTestsIndexes.add(testIndex);

        logger.info("\nTest result = '"+result+"'\n");
        long estimatedTime = countTestTime();
        testTimes.add(estimatedTime);
        logger.info("estimatedTime="+estimatedTime+"ms");

      
    }

    private long countTestTime() {
        long estimatedTime = System.currentTimeMillis()-startTime;
        return estimatedTime;
    }
}
