package testExecutions.setup;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;
import testExecutions.appInteraction.pagesTests.resources.Information;

public abstract class Setup extends Information {

    public WebDriver driver;
	
    public abstract WebDriver prepareSelenium() throws MalformedURLException, InterruptedException, IOException;

    @SuppressWarnings("rawtypes")
	public abstract AndroidDriver prepareAppium() throws MalformedURLException, InterruptedException, IOException;
    
    
    
    /** Method for configuring the logs system according to the log4j.properties **/
    protected void configureLogsSystem() {

        createLogsFile(logsPath);

        System.setProperty("path",logsPath);

        PropertyConfigurator.configure("log4j.properties");

    }
    /** Method for creating a logs file to the specified path **/
    public void createLogsFile(String path) {
        try {
            File file = new File(path);
            file.delete();
        } catch (Exception e) {
            System.out.println("An error occurred. " + e);
        }
    }
}
