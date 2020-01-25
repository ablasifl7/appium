package testExecutions.tests.testBuilder;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import testExecutions.appInteraction.pagesTests.Apps.AND_LoginTest;
import testExecutions.appInteraction.pagesTests.resources.Information;

public class TestsCreator extends Information {
    
	private String testName;
    private int OS;
    private  AppiumDriver<?> app_driver;
    private WebDriver driver;
    private boolean fail;
    private boolean externalFail;
    
    public TestsCreator(AppiumDriver<?> app_driver, int OS, String testName) {

        this.app_driver = app_driver;
        this.OS = OS;
        this.testName = testName;
    }
    
    /**
     * Auxiliary methods
     **/
    public void prepare_testCase(int index, String path, String sheet_name) throws IOException, InvalidFormatException {
     /*
         XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(new FileInputStream(path)));
        XSSFSheet sheet = workbook.getSheet(sheet_name);
        XSSFRow row = sheet.getRow(index);
        XSSFCell[] cell_array = new XSSFCell[row.getLastCellNum()];
        for(int i=0; i<row.getLastCellNum(); i++) {
            cell_array[i] = row.getCell(i);
        }
        return cell_array;
      */
    }
    /**
     * Method that executes a test case depending on the inputed testName
     **/
    public boolean[] testExecutor(int index) throws InterruptedException, IOException, InvalidFormatException {
    	
          switch (testName) {
            case ("login"):
                return correctLogin(index);
            default:
                logger.error("The introduced test does not exist!");
                return new boolean[]{true, true};

        }

    }
    public boolean[] correctLogin(int index) throws IOException, InvalidFormatException {
    	
    	String testdata_path = new File("").getAbsolutePath().replace("\\", "/")+"/"+readExcel_path;
    	prepare_testCase(index,testdata_path,"Login");// metodode per llegir l'excel
		
		AND_LoginTest lt = new AND_LoginTest(app_driver);
		String user = "ablasifl7@gmail.com";
		String password = "password";
		lt.login(user,password);
		return new boolean[] { false, false, false };
       // return new boolean[]{fail,externalFail};
    }
}
