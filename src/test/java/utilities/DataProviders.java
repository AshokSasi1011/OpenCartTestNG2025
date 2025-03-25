package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String path = "testData\\OpenCartDataDriven.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalRows = xlutil.getRowCount("Sheet1"); //excluding headers
		int totalColumns = xlutil.getCellCount("Sheet1", 1);
		
		//Ensure the row is not empty
		
		if(totalRows==0 || totalColumns==0) {
			throw new IOException("Excel sheet is empty or incorrectly formatted");
		}
		
		String loginData[][] = new String [totalRows][totalColumns];
		
		for(int i=1;i<=totalRows;i++) { //since totalRows gives the index here, to skip the first row which is header
			for(int j=0;j<totalColumns;j++) { //totalColumns gives the total number of cells here, index starts from zero
				loginData [i-1][j] = xlutil.getCellData("Sheet1",i,j); 
			}
		}
		return loginData;
	}
	
	
	

}
