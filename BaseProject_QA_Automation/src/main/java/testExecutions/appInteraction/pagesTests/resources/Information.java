package testExecutions.appInteraction.pagesTests.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;



public abstract class Information {


    /***** MODIFIABLE DATA *****/

    /**
     * DEVICE'S INFORMATION (FILLED DURING THE SETUP OPERATION)
     * **/

    public String current_device = "FIREFOX";
    public String udid = "";
    public String version = "";
    public String platform = "";
    public String port = "";
    public static String currentLanguage;


    /**
     * REPORTING ARRAYS
     */

    // List at which we define the name of the test cases
    public static List<String> testNames = new ArrayList<String>();
    // List at which we define the description of the test cases
    ////// public static List<String> testDescriptions = new ArrayList<String>();
    // Array at which it is stored the results of each test case
    public static List<String> testResults = new ArrayList<String>();
    // List at which are stored the expected results of the tests
    ////// public static List<String> expectedResults = new ArrayList<String>();
    // List at which it is stored the index of the executed test
    public static List<Integer> executedTestsIndexes = new ArrayList<Integer>();
    // List at which the difference images in pixels are stored
    ////// public static List<Integer> image_difference_pixels = new ArrayList<Integer>();
    // List at which it is stored the time taken to run each test
    public static List<Long> testTimes = new ArrayList<Long>();
    // List at which the performance test names are stored
    ////// public static List<String> performance_Tests = new ArrayList<>();
    // List at which are stored the times of the performance tests
    ////// public static List<Double> performance_Times = new ArrayList<>();
    // List at which are stored the expected/accepted times of the performance tests
    ////// public static List<Double> performance_Acceptable_Times = new ArrayList<>();

    /**************************
     *  STATIC DATA ***
     *  **********************/

    /**
     * LOGGER OBJECTS INITIALIZATION
     * **/

    // Custom logs
    public static final Logger logger = Logger.getLogger("infoLogger");
    // Logs retrieved during performance testings
    public static final Logger loggerPerformance = Logger.getLogger("performanceLogger");
    
    /**
     * USEFUL PATHS
     * **/
    /**
     * OTHER RESOURCES
     * **/
    final public int FIREFOX = 0;
    final public int ANDROID = 0;
    
    final public int OK = 0;
    final public int KO = 1;
    final public int BLOCKED = 2;

    final public String app_package_name = "world.hello.myapplication";

    // Path to logs files for each device
    final public String logsPath = "./results/logs/executionLogs.log";
    final public String logsPerformancePath = "./results/logs/performanceLogs.log";
    final public String logsAppiumPath = "./appiumLogs.log";

    // Path to the CSV report location
    final public String csvReportPath = "./results/report_" + System.getenv("DEVICE");

    // Path to the screenshots folder
    final public String screenShotsPath = "src/main/java/results/screenshots";

    // Path to the appInteraction.pagesTests.resources.apps folder
    final public String appsPath = "./testExecutions/appInteraction/pagesTests/resources/apps/";

    // Path to the elements.xml file
    final public String route_elements = "src/main/java/testExecutions/appInteraction/pagesTests/resources/elements.xml";

    // Path to Strings files used by the application
    final public String route_strings = "./testExecutions/appInteraction/pagesTests/resources/strings/strings_";

    // Path to the devices lab file (.xlsx)
    final public String devicesLabFilePath = "./testExecutions/data/devices_lab.xlsx";

    // Path to the output devices file (.json)
    final public String outputJsonPath = "./testExecutions/data/devices2.json";

    // Path to devices.json file
    final public String devicesJsonPath = "./testExecutions/appInteraction/pagesTests/resources/devices.json";

    // Path to app
    final public String pathAppName = "src/main/java/testExecutions/appInteraction/pagesTests/resources/apps/myapplication-debug.apk";

    // Path to read Excel
    final public String readExcel_path = "src/main/java/testExecutions/appInteraction/pagesTests/resources/ChartSample.xlsx";

    // Path to read Excel
    final public String writeExcel_path = "src/main/java/testExecutions/appInteraction/pagesTests/resources/myapplication-debug.xlsx";
    
    //l
    final public String propPath = "src/main/java/testExecutions/appInteraction/pagesTests/resources/config.properties";
    /**
     * My own definition
     */
    // Drivers
    public AndroidDriver<?> app_driver;
    public WebDriver driver;
    
    // Properties
    public Properties prop;
    
    
}
