package com.tdat.feeder;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXDataFileReader implements DataFileReader {

    /**
     * A
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
	public List<Map<String, String>> converter(File file) throws FileNotFoundException, IOException {
		
		// To be returned: a list of all visits with each visit having a HashMap of information of that visit
		List<Map<String, String>> allVisits = new ArrayList<>();
		// Holds all the keys for the HashMap (first row data)
		ArrayList<String> keys = new ArrayList<String>();
		
		// Cited Sources:
		// http://java67.com/2014/09/how-to-read-write-xlsx-file-in-java-apache-poi-example.html
		// https://poi.apache.org/apidocs/
		// https://www.callicoder.com/java-read-excel-file-apache-poi/
		
		FileInputStream xlsxFile = new FileInputStream(file);
		// Gets the workbook of the xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(xlsxFile);
		// Gets the first sheet of the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);
		// Gets iterator to all the rows of the sheet
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		// Gather third row information and put it in a list
		rowIterator.next();
		rowIterator.next();
        Row firstRow = rowIterator.next();
		// Loops through each cell in this first row
		Iterator<Cell> cellIteratorForFirst = firstRow.cellIterator();
		while(cellIteratorForFirst.hasNext()) {		
			// Gets the cell object for this cell
			Cell cell = cellIteratorForFirst.next();
			keys.add(cell.getStringCellValue());
		}
		
		// Traverse each row of the XLSX file
		while(rowIterator.hasNext()) {
			// New HashMap
			HashMap<String, String> currentVisit = new HashMap<String, String>();
			
			// New counter
			int counter = 0;
			
			// Gets the row object for this loop
			Row row = rowIterator.next();
			
			// Loops through each cell in this row
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {
					
				// Gets the cell object for this cell
				Cell cell = cellIterator.next();
				
				// Puts the current cell information into its row HashMap
				currentVisit.put(keys.get(counter), cell.getStringCellValue());
				
				// Increase counter by 1
				counter++;
				
				/*
				For different types.
				switch(cell.getCellType()) {
					case STRING:
						System.out.print(cell.getStringCellValue() + "\t"); 
						break; 
					case NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t");
						break;
					default:	
				}
				*/
			}
			
			// Adds the current row into the list
			allVisits.add(currentVisit);
		}
		
		// Close the workbook
		workbook.close();
        
		return allVisits;
	}

}
