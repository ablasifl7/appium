package testExecutions.appInteraction.pages.Apps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import testExecutions.appInteraction.genericMethods.BasePageAndroid;
import testExecutions.appInteraction.genericMethods.BasePage_Mobile;
import testExecutions.appInteraction.pagesTests.resources.Information;

public class AND_LoginPage extends Information {

    public static String current_page = "Android_LoginScreen";
    public BasePage_Mobile basePage; 
    AppiumDriver<?> driver;
    
	public AND_LoginPage(AppiumDriver<?> driver) {
        this.driver = driver;
        this.basePage = new BasePageAndroid((AndroidDriver<?>) this.driver);
	}
    public boolean login(String user, String password){
        basePage.sendKeysToElement(current_page,"and_user_login", user);
        basePage.sendKeysToElement(current_page,"and_password_login",password);
        basePage.clickElement(current_page,"and_enter_button_login");	
    
        return false;
    }
}
