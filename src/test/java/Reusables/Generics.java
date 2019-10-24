package Reusables;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Generics {

	public ArrayList<String> TestResultsList= new ArrayList<String>();
	  public int Result_increment=0;
	  
	
	public void takescreenshot(WebDriver driver, String filename)
	{
		File screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	
	try {
		FileUtils.copyFile(screenshot, new File("Results/"+filename+".jpg") );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Error in taking screenshot");
		e.printStackTrace();
	}
	}
	
	public String[][] readtextfile(String filename) throws IOException
	
	{
	    String [][] matrix = null;

	    // If included in an Eclipse project.
	    //InputStream stream = ClassLoader.getSystemResourceAsStream(filename);
	    //BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));

	    // If in the same directory - Probably in your case...
	    // Just comment out the 2 lines above this and uncomment the line
	    // that follows.
	    BufferedReader buffer = new BufferedReader(new FileReader(filename));

	    String line;
	    int rowIndex = 0 , rowsize=0;
	    int size = 0;
	    while ((line = buffer.readLine()) != null)
	    {
	    	rowsize++;
	    }
	    System.out.println("Row size: "+ rowsize);
	    
	    
	    buffer = new BufferedReader(new FileReader(filename));
	    while ((line = buffer.readLine()) != null) {
	        String[] vals = line.trim().split("\\t");  // for tab \\t  , for space \\s , for others use that perticular character  

	        // Lazy instantiation.
	        if (matrix == null) {
	            size = vals.length;
	            matrix = new String[rowsize][size];
	           // displayResult("vals = "+vals.toString()); 
	        }

	        for (int col = 0; col < size; col++) {
	            matrix[rowIndex][col] = vals[col];
	           // displayResult("matrix["+rowIndex+"]["+col+"] "+matrix[rowIndex][col]); 
	        }

	        rowIndex++;
	    }

	    return matrix;
	}
	
	public boolean compareStrings(String stringone, String stringtwo)
	{
		
		if(stringone.equals(stringtwo))
		{
			System.out.println("String 1 :"+stringone +" and String 2 :"+stringtwo +"  are matching");
		return true;
		}
		else
		{	System.out.println("String 1 :"+stringone +" and String 2 :"+stringtwo +"  are NOT matching");
			return false;	
		}
	}
	
	public void GatherTestStepResult(String text)
	{
		if(Result_increment==0) 
		{
	//	TestResultsList.add("Period	      Date        Price         Returns");
			
		}
		Result_increment=Result_increment+1;
		TestResultsList.add( "	"+text);
		System.out.println(text);
	}
	
	public void writeToTextfile(String filename) throws IOException
	{
		//my results.. to an array
		//create a file on specified location - delete the file it already exists
		File OutputFile=new File("Results\\"+filename);
		BufferedWriter bw=new BufferedWriter(new FileWriter(OutputFile));
		
		// write array to file
		for (int rowIndex=0; rowIndex<TestResultsList.size();rowIndex++)
		{
			bw.write(TestResultsList.get(rowIndex)+"\r\n");
			bw.write("\r \n");
		}
		
		
		bw.close();
	}

	public String [][] readEXCELfile(String filename, String Sheetname) throws IOException
	{
		File InputFile=new File(filename);	
		FileInputStream steam=new FileInputStream(InputFile);
		XSSFWorkbook myworkbook1=new XSSFWorkbook(steam);
		XSSFSheet mysheet1=myworkbook1.getSheet(Sheetname);
		int SheetActiveRows=mysheet1.getLastRowNum()+1;
		int SheetActiveColumns=mysheet1.getRow(0).getLastCellNum();
		
		//displayResult("Excel file's row count: "+SheetActiveRows+ "   Column count : "+SheetActiveColumns);
		
		String ExcelDataArray[][]= new String[SheetActiveRows][SheetActiveColumns];
		
		for(int rowIndex=0; rowIndex<SheetActiveRows; rowIndex++)
		{
			XSSFRow row=mysheet1.getRow(rowIndex);
			for (int ColumnIndex=0; ColumnIndex<SheetActiveColumns;ColumnIndex++)
			{
				XSSFCell cell=row.getCell(ColumnIndex);
				//ExcelDataArray[rowIndex][ColumnIndex]=CellToString(cell);
				ExcelDataArray[rowIndex][ColumnIndex]=getCellValue(cell);
			}
		}
		
		for(int rowIndex=0; rowIndex<ExcelDataArray.length; rowIndex++)
		{
			
			for (int ColumnIndex=0; ColumnIndex<ExcelDataArray[0].length;ColumnIndex++)
			{
				
				System.out.print(ExcelDataArray[rowIndex][ColumnIndex]+ "   ");
			}
			System.out.println("\n");
		}
		return ExcelDataArray;
	}
	
	private static String getCellValue( Cell cell)
{
  DataFormatter formatter = new DataFormatter();
  String formattedCellValue = formatter.formatCellValue(cell);
  return formattedCellValue; 
}
	
	
	
	public void writeToExcel(ArrayList<String> MyTestResultsList, String filename) throws IOException
	{
		//System.out.println("Ready to begin EXcel file generation..Results\\"+filename+".xlsx");

	FileOutputStream stream=new FileOutputStream("Results\\"+filename+".xlsx");
	XSSFWorkbook wb=new XSSFWorkbook();
	XSSFSheet sheet1=wb.createSheet("Results");
	

	
	int length=MyTestResultsList.size();
	//System.out.println(length);
	XSSFRow row1 = sheet1.createRow(0);
	row1.createCell(0).setCellValue("Ex/Eff Date");
	row1.createCell(1).setCellValue("CashAmount");
	row1.createCell(2).setCellValue("Declaration Date");
	row1.createCell(3).setCellValue("Record Date");
	row1.createCell(4).setCellValue("Payment Date");
	int rowNum = 1;
		for(int i=0;i<10;i++)
		{
		XSSFRow row = sheet1.createRow(rowNum++);
		row.createCell(0).setCellValue(MyTestResultsList.get(i));
		row.createCell(1).setCellValue(MyTestResultsList.get(i+10));
		row.createCell(2).setCellValue(MyTestResultsList.get(i+20));
		row.createCell(3).setCellValue(MyTestResultsList.get(i+30));
		row.createCell(4).setCellValue(MyTestResultsList.get(i+40));
		}
	

	    
	wb.write(stream);
	stream.flush();
	stream.close();
	System.out.println("EXcel file is generated");
	}
}