package testExecutions.test.scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import testExecutions.appInteraction.genericMethods.BasePageAndroid;
import testExecutions.setup.MixSetup;
import testExecutions.tests.ReportGenerator;

public class TestSuite extends MixSetup {

    private MixSetup androidSetup = new MixSetup();
    private AndroidDriver<?> app_driver;
    private BasePageAndroid bpa;
    

	
	   @BeforeClass
	    public void setUp() throws Exception {
	        /** MAKE A SETUP BEFORE EXECUTING TEST CASES **/

	        /** Prepare logs system **/
	        configureLogsSystem();

	        logger.info("\n\t\t\t\t\t-------------------------------\n\t\t\t\t\tBEGINNING OF THE TEST EXECUTION\n\t\t\t\t\t-------------------------------\n");
	        
	        app_driver = androidSetup.prepareAppium();

	        
	        bpa = new BasePageAndroid(app_driver);
	        
	   }
	    @Test (enabled = true)
	    public void testName() throws Exception {
	 
	    	execute_TestSheet(driver, app_driver, platform, "path", "sheet", true, 0);

	      
	    }
	    @AfterClass
	    public void tearDown() throws Exception {
	    	
	    	Thread.sleep(2000);
	    	
	    	logger.info("\nTear down in process...\n");
	    	
	    	createLogsFile(logsPerformancePath);
	    	
	    	bpa.generatePerformanceLogsFile();
	    	
	        ReportGenerator report = new ReportGenerator();
	        report.generateReport();
	    	
	    	this.app_driver.quit();	
	    }
}
