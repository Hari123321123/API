package CommonMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	public static File createFolder(String folderName) {
		
		// Step 1 : Fetch the current Java project
		
		String projectFolder = System.getProperty("user.dir");
		System.out.println(projectFolder);
		
		// Step 2 : Check if folder name coming in variable folderName already exists in 
		//projectFolder and create folderName accordingly
		
		File folder = new File(projectFolder + "\\APILogs\\" + folderName);
		
		if(folder.exists())
		{
			System.out.println(folder + " , Already exists in Java Project :" + projectFolder);
		}
		else
		{
			System.out.println(folder + " , Doesn't exists in Java Project :" +projectFolder + ", " + "hence creating it");
			folder.mkdir();
			System.out.println(folder + " , Created in Java Project :" + projectFolder);
		}
		
		return folder;
		
	}
	
	public static void createLogFile (String Filename, File Filelocation, String endpoint, String requestbody, 
			String responseHeader, String responsebody) throws IOException {
		
		// Step 1 : Create and open a text file
		
		File newTextFile = new File(Filelocation + "\\" + Filename + ".txt");
		System.out.println("File create with name :" + newTextFile.getName());
		
		// Step 2 : Write data into the file
		
		FileWriter writedata = new FileWriter(newTextFile);
		writedata.write("Endpoint is :\n" + endpoint + "\n\n");
		writedata.write("Request body is :\n" + requestbody + "\n\n");
		writedata.write("Response header is :\n" + responseHeader + "\n\n");
		writedata.write("Response body is :\n" + responsebody );
		
		// Step 3 : Save and close the file
		
		writedata.close();
		
	}
	
	public static ArrayList<String> ReadExcelData (String sheetName, String testCase) throws IOException {
		
		ArrayList<String> arraydata = new ArrayList<String>();
		
		// Step 1 : Find the location and name of java project
		
		String projectdir = System.getProperty("user.dir");
		
		// Step 2 : Read name of the file using class File Input Stream
		
		FileInputStream fis = new FileInputStream(projectdir + "\\DataFiles\\InputData.xlsx");
		
		// Step 3 : Open the file using XSSF workbook object
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		// Step 4 : Find the count of sheets
		
		int countofSheets = wb.getNumberOfSheets();
		System.out.println(countofSheets);
		
		// Step 5 : Read the name of the each sheet in excel file and print the sheet I want
		
		for (int i=0; i<countofSheets; i++)
		{
			if (wb.getSheetName(i).equals(sheetName))
			{
				System.out.println(wb.getSheetName(i));
				
				// Step 5.1 : Capturing the whole sheet and save it into local variable
				
				XSSFSheet sheet = wb.getSheetAt(i);
				
				// Step 5.2 : Going through Rows one by one/ It will help you to iterate through rows
				
				Iterator<Row> rows = sheet.iterator();
				
				// Step 5.3 : Go to next row
				
				while(rows.hasNext())
				{
					Row datarows = rows.next();
					
					String testCaseName = datarows.getCell(0).getStringCellValue(); // to go first test case we need to set value of cell zero
					System.out.println(testCaseName);
					
					if (testCaseName.equals(testCase))
					{
						// Step 5.4 : Read all cell values. Iterate through the cells
						
						Iterator<Cell> cellvalues = datarows.iterator(); //because cell is the child of row
						
						while(cellvalues.hasNext()) // To read cell
						{
							String testData = "";
							Cell cell = cellvalues.next();
							CellType datatype = cell.getCellType();
							
							if(datatype.toString().equals("STRING"))
							{
								testData = cell.getStringCellValue();
							}
							else if(datatype.toString().equals("NUMERIC"))
							{
								double numTestData = cell.getNumericCellValue();
								testData = String.valueOf(numTestData);
							}
							
							System.out.print("\n" + testData);
							arraydata.add(testData);
						}
						break;
					}
				}
				break;
			}
			else
			{
				System.out.println("No sheet found of Name :" + sheetName + " In current iteration :" + i);
			}
		}
		
		wb.close();
		
		return arraydata;
		
	}

}
