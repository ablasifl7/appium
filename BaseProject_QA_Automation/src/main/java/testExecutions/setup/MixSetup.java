package testExecutions.setup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import helper.LoggerHelper;
import io.appium.java_client.android.AndroidDriver;
import testExecutions.tests.testBuilder.TestsHandler;

public class MixSetup extends Setup {
	
    @SuppressWarnings("rawtypes")
	public AndroidDriver prepareAppium() throws IOException, InterruptedException {
        super.platform = "ANDROID";
        super.udid = "RQ3001UB2D";
        super.version = "6.0";
        super.port = "4723";
        super.current_device = "sony_xe5";
    	
        /** Set the APK's path **/
        File appDir = new File(new File("").getAbsolutePath()+"/src/main/java/testExecutions/appInteraction/pagesTests/resources/apps");   
        File app = new File(appDir, "myapplication-debug.apk");
        
    	
        DesiredCapabilities capabilities = setCapabilities(app);
        /** Instantation of the driver object **/
       

        app_driver =  new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities);
    
        return (AndroidDriver) app_driver;
        
    }

	@Override
	public WebDriver prepareSelenium() throws MalformedURLException, InterruptedException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void execute_TestSheet(WebDriver driver, AndroidDriver<?> app_driver, String platform, String path, String sheetname, boolean man_lines, int num_testcases) throws InterruptedException, InvalidFormatException, IOException {
		String testName = "login";

		new TestsHandler(app_driver, ANDROID, testName).execute(0);
		app_driver.quit();
		
	}
	
	
    /** Set Capabilities **/
    private DesiredCapabilities setCapabilities(File app) {

        DesiredCapabilities capabilities =  DesiredCapabilities.android();

        logger.info("Device name: " + current_device);
        logger.info("Port: " + port);
        logger.info("OS Version: " + version);
        logger.info("Platform: " + platform);
        logger.info("UDID: " + udid);

        /** Mandatory capabilities (retrieved from the devices.json file) **/
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("VERSION", version);
        capabilities.setCapability("deviceName", current_device);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("port", port);

        //capabilities.setCapability("app", app);

        /** Set the APK's package and activity **/
        capabilities.setCapability("appPackage", app_package_name);
        //capabilities.setCapability("appActivity", super.main_activity);

        /** Other capabilities **/
        capabilities.setCapability("browserName", ""); // DO NOT REMOVE
        capabilities.setCapability("language", "es");
        capabilities.setCapability("unicodeKeyboard",true);
        capabilities.setCapability("autoAcceptAlerts", true); // For automatically accepting alerts (notifications, location, etc.)
        capabilities.setCapability("newCommandTimeout", 60 * 5);

        capabilities.setCapability("noReset",false);

        capabilities.setCapability("app", (new File("")).getAbsolutePath()+"/"+pathAppName);

        return capabilities;

    }

}
