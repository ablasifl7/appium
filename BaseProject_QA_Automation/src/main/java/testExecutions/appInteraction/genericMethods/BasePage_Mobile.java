package testExecutions.appInteraction.genericMethods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import testExecutions.appInteraction.pagesTests.resources.Information;

public abstract class BasePage_Mobile extends Information {


	//public AppiumDriver driver;

    /**
     * MAIN METHODS
     */
    /** Clicks the chosen element stored in the elements.xml file
     *
     * Example:
     * --> clickElement("home","side_menu_button")
     * **/
    public abstract void clickElement(String current_page, String element);

    /** Returns the chosen element stored in the elements.xml file
     *
     * Example:
     * --> getElement("home","side_menu_button")
     * **/
    public abstract By getElement(String current_page, String element);

    /** Introduces text to the chosen element stored in the elements.xml file
     *
     * Example:
     * --> sendKeysToElement("home","login_text_field","user_name")
     * **/
    public abstract void sendKeysToElement(String current_page, String element, String text);

    /** Clear text from TextField to the chosen element stored in the elements.xml file
     * 
     * 
     * **/
    public abstract void clearElement(String current_page, String element);
    
    /** Obtains the text of the chosen element stored in the elements.xml file
     *
     * Example:
     * --> getTextFromElement("home","login_text_field")
     * **/
    public abstract String getTextFromElement(String current_page, String element);


    /**
     * NAVIGATION METHODS
     */


    /** Waits for an element to be visible on the screen (after a configurable time-out it fails)
     *
     * Example:
     * --> waitForVisibilityOf(elem)
     * **/
    public abstract void waitForVisibilityOf(By locator);

    /** Waits for an element to be clickable on the screen (after a configurable time-out it fails)
     *
     * Example:
     * --> waitForClickabilityOf(elem)
     * **/
    public abstract void waitForClickabilityOf(By locator);

    /** Scrolls until finding the specific introduced text
     *
     * Example:
     * --> scrollToText("sample_text")
     * **/
    public abstract void scrollToText(String text) throws InterruptedException;

    /** Scrolls to the specified direction ("up" or "down")
     *
     * Example:
     * --> scrollScreen("up")
     * **/
    public abstract void scrollScreen(String direction);



    /** Swipes a carousel to the specified direction ("left" or "right")
     *
     * Example:
     * --> swipeCarousel("left")
     * **/
    public abstract void swipeCarousel(String direction);


    /**
     * CONNECTIVITY METHODS
     */


    /** Enables mobile data in the device (BY NOW, ONLY FOR ANDROID) **/
    public abstract void enableData();

    /** Disables mobile data in the device (BY NOW, ONLY FOR ANDROID) **/
    public abstract void disableData();

    /** Enables WiFi in the device (BY NOW, ONLY FOR ANDROID) **/
    public abstract void enableWifi();

    /** Disables WiFi in the device (BY NOW, ONLY FOR ANDROID) **/
    public abstract void disableWifi();

    /** Enables airplane mode in the device (BY NOW, ONLY FOR ANDROID) **/
    public abstract void enableAirplane();

    /** Disables airplane mode in the device (BY NOW, ONLY FOR ANDROID) **/
    public abstract void disableAirplane();

    /**
     * AUXILIARY METHODS
     */


    /** Captures a screenshot of type error (boolean error = true) or walkthrough (error = false)
     * and stores it to the corresponding folder (one folder per device)
     *
     * Example:
     * --> captureScreenshot(driver,true)
     */
    public void captureScreenshot(AppiumDriver<?>  driver , int type, String testName) throws InterruptedException {

        Thread.sleep(500);

        String imagesLocation;
        String filename;

        logger.info("\nCapturing a Screenshot...");

        if (type==0) {
            imagesLocation = new File("").getAbsolutePath().replace("\\", "/")+ "/"+screenShotsPath + "/UITest/";
            int id_UI = new File(imagesLocation).listFiles().length;
            filename = imagesLocation + id_UI + "_" + testName + ".jpg";
        } else if (type==1) {
            imagesLocation = new File("").getAbsolutePath().replace("\\", "/")+ "/"+screenShotsPath + "/errors/";
            int id_error = new File(imagesLocation).listFiles().length;
            filename = imagesLocation + id_error + "_" + testName + ".jpg";
            logger.info("For viewing the error, refer to " + filename + "");
        } else if (type==2) {
            imagesLocation = new File("").getAbsolutePath().replace("\\", "/")+ "/"+screenShotsPath + "/externalErrors/";
            filename = imagesLocation +  testName + "_externalError.jpg";
            logger.info("For viewing the external error, refer to " + filename + "");
        } else {
            logger.error("Wrong introduced type! Please, introduce 0='UI test', 1='error' or 2='external error'");
            return;
        }

        File newFile = new File(imagesLocation);
        newFile.mkdirs(); // Insure directory is there

        try {
            Thread.sleep(500);
            //AndroidDriver<?>  augmentedDriver = (AndroidDriver<?>) new Augmenter().augment(driver);
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(filename), true);
        } catch (Exception e) {
            logger.error("Could not take a screenshot to the specified directory ("+filename+")");
        }

        logger.info("...Done\n");

    }
    /** Returns an element from the elements.xml given the platform and the introduced page and element name
    *
    * Example:
    * --> returnElement("home","login_button","MAC")
    */

   public By returnElement(String current_page, String element, String current_platform) {
       String[] elem = getElementXML(current_page, element, current_platform);
       By elementLocation = null;
       if (elem[1].equals("xpath")) {
           elementLocation = By.xpath(elem[0]);
       } else if (elem[1].equals("id")) {
           elementLocation = By.id(app_package_name + ":id/" + elem[0]);
       } else if (elem[1].equals("class")) {
           elementLocation = By.className(elem[0]);
       } else if (elem[1].equals("id_android")) {
           elementLocation = By.id("android:id/" + elem[0]);
       } else if (elem[1].equals("none")) {
           elementLocation = By.id(elem[0]);
       }
       //logger.info(elementLocation.toString());
       return elementLocation;
   }
   /** Returns the element identifier and its type from the elements.xml file
   *
   * Example:
   * --> getElementXML("home","login_button","MAC")
   */
  public String[] getElementXML(String page, String attributeName, String current_platform) {

      String fileName = new File("").getAbsolutePath().replace("\\", "/")+"/"+route_elements;
      String nameFound = "";
      String type = "";

      File xmlFile = new File(fileName);

      try {
          //Create a document builder
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          //Create a Document from a file
          Document doc = builder.parse(xmlFile);
          Element docEle = doc.getDocumentElement();
          //Get a NodeList of elements
          NodeList nl = docEle.getElementsByTagName(page);
          if (nl != null && nl.getLength() > 0) {
              NodeList nl2 = docEle.getElementsByTagName("string");
              //Iterate until finding the element
              for (int i = 0; i < nl2.getLength(); i++) {
                  Element el = (Element) nl2.item(i);
                  if (el.getAttribute("name").equals(attributeName) & el.getAttribute("platform").equals(current_platform)) {
                      nameFound = el.getTextContent();
                      type = el.getAttribute("type");
                  }
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }

      logger.debug("Attribute found (type=" + type + "): " + nameFound);
      return new String[]{nameFound, type};

  }

  /** Generate file for performance logs **/
  public void generatePerformanceLogsFile() throws IOException {

      System.out.println("COPYING FILES TO PERFORMANCE FILE");
      File source = new File(logsPath);
      File dest = new File(logsPerformancePath);

      BufferedReader reader = new BufferedReader(new FileReader(source));
      BufferedWriter writer = new BufferedWriter(new FileWriter(dest));

      String currentLine;

      while((currentLine = reader.readLine()) != null) {
          // trim newline when comparing with lineToRemove
          String trimmedLine = currentLine.trim();
          if(!trimmedLine.contains("performanceLogger")) continue;
          writer.write(currentLine + System.getProperty("line.separator"));
      }
      writer.close();
      reader.close();

  }
	
}
