package testExecutions.appInteraction.genericMethods;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class BasePageAndroid extends BasePage_Mobile{

    private AndroidDriver<?> driver;

    public BasePageAndroid(AndroidDriver<?> driver) {
        this.driver = driver;
    }



	/**
     * MAIN METHODS
     */
	
	@Override
	public void clickElement(String current_page, String element) {
		driver.findElement(returnElement(current_page,element,"ANDROID")).click();
	}

	@Override
	public By getElement(String current_page, String element) {
		return returnElement(current_page,element,"ANDROID");
	}

	@Override
	public void sendKeysToElement(String current_page, String element, String text) {
		driver.findElement(returnElement(current_page,element,"ANDROID")).sendKeys(text);
	}
	
	@Override
	public void clearElement(String current_page, String element) {
		driver.findElement(returnElement(current_page,element,"ANDROID")).clear();
		
	}

	@Override
	public String getTextFromElement(String current_page, String element) {
		return driver.findElement(returnElement(current_page,element,"ANDROID")).getText();
	}


	@Override
	public void waitForVisibilityOf(By locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForClickabilityOf(By locator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollToText(String text) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollScreen(String direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void swipeCarousel(String direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableWifi() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableWifi() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableAirplane() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableAirplane() {
		// TODO Auto-generated method stub
		
	}


}
