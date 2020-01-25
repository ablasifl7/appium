package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import helper.Excel;
import testExecutions.appInteraction.pagesTests.resources.Information;

public class TestExcel extends Information {
	private String testdata_path;
	private String results_path;
	protected TestExcel(){
		testdata_path = new File("").getAbsolutePath().replace("\\", "/")+"/"+readExcel_path;
		results_path  = new File("").getAbsolutePath().replace("\\", "/")+"/"+writeExcel_path;
	}
	
	protected void readExcel(){
		//List<ClassExcel> excelList = new ArrayList<ClassExcel>();
		Excel excel = new Excel(testdata_path, results_path,"login");
	
		for(int i=1;i<=excel.getLastRow("login");i++){
			// Key	Standar	Accion	Value
			System.out.println("Key :"+ excel.readCell(i, 0));
		}

		 
		
	}
	


}
