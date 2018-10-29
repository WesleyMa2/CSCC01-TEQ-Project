package com.tdat.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Year;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TableDataCreatorTest {
	private static MasterData master = new MasterData();
	private static TableData tableData1 = new TableData();
	private static TableData tableData2 = new TableData();
	private static TableData tableData3 = new TableData();
	private static TableData tableData4 = new TableData();
	private static VisitData visit1 = new VisitData();
	private static VisitData visit2 = new VisitData();
	private static VisitData visit3 = new VisitData();
	private static VisitData visit4 = new VisitData();
	private static VisitData visit5 = new VisitData();
	private static VisitData visit6 = new VisitData();
	private static VisitData visit7 = new VisitData();
	private static VisitData visit8 = new VisitData();
	public static int year1int = 2018;
	public static int year2int = 2016;
	private static Year year1 = Year.of(year1int);
	private static Year year2 = Year.of(year2int);
	    
	@BeforeAll
	public static void setUp(){
		// First visit
		visit1.addColumnData("Col1", "firstvisit-col1");
		visit1.addColumnData("Col2", "firstvisit-col2");
		visit1.addColumnData("Col3", "firstvisit-col3");
		visit1.addColumnData("Col4", "firstvisit-col4");
		
		// Second visit
		visit2.addColumnData("Col1", "secondvisit-col1");
		visit2.addColumnData("Col2", "secondvisit-col2");
		visit2.addColumnData("Col3", "secondvisit-col3");
		visit2.addColumnData("Col4", "secondvisit-col4");
        
		// Third visit
		visit3.addColumnData("Col1", "thirdvisit-col1");
		visit3.addColumnData("Col2", "thirdvisit-col2");
		visit3.addColumnData("Col4", "thirdvisit-col4");
        
		// Fourth visit
		visit4.addColumnData("Col1", "fourthvisit-col1");
		visit4.addColumnData("Col2", "fourthvisit-col2");
		visit4.addColumnData("Col4", "fourthvisit-col4");
        
		visit5.addColumnData("Col1", "firstvisit-col1");
		visit5.addColumnData("Col2", "firstvisit-col2");
		visit5.addColumnData("Col3", "firstvisit-col3");
		visit6.addColumnData("Col1", "secondvisit-col1");
		visit6.addColumnData("Col2", "secondvisit-col2");
		visit6.addColumnData("Col3", "secondvisit-col3");
		visit7.addColumnData("Col1", "thirdvisit-col1");
		visit7.addColumnData("Col2", "thirdvisit-col2");
		visit8.addColumnData("Col1", "fourthvisit-col1");
		visit8.addColumnData("Col2", "fourthvisit-col2");
		
		
		tableData1.addVisitData(visit1);
		tableData1.addVisitData(visit2);
        
		tableData2.addVisitData(visit3);
		tableData2.addVisitData(visit4);
        
		tableData3.addVisitData(visit5);
		tableData3.addVisitData(visit6);
		tableData3.addVisitData(visit7);
		tableData3.addVisitData(visit8);
		
		MasterData.setYearData(year1, tableData1);
		MasterData.setYearData(year2, tableData2);
	}
    
    
	@Test
	@DisplayName("Creating a custom DataTable object with one year")
	void oneYear() throws YearNotFoundException {
		setUp();
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("Col1");
		columns.add("Col2");
		columns.add("Col3");
		ArrayList<Year> years = new ArrayList<Year>();
		years.add(year1);
		assertEquals(tableData3, TableDataCreator.customize(master, columns, years));
	}
    
}
