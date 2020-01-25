package testExecutions.appInteraction.pagesTests.Apps;

import java.io.IOException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import testExecutions.appInteraction.pages.Apps.AND_LoginPage;

public class AND_LoginTest extends AND_LoginPage {
    AndroidDriver<?> driver;

    public AND_LoginTest(AppiumDriver<?> driver) {
        super(driver);
        this.driver = (AndroidDriver<?>) driver;
    }
    
    public boolean AND_TryLogin(String user, String password, String expected_result) throws InterruptedException, IOException {
        boolean fail = false;
        try {
			fail = login(user,password);
		} catch (Exception e) {
			 logger.info(e);
		}
        return fail;
    }
}
