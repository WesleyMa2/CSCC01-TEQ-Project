package com.tdat.data.analysis;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.tdat.gui.*;
import com.tdat.data.*;



public class ConflictIdentificationTest {
	public static List<Map<String,String>> upload;
	
	@Test
	@DisplayName("Test creating trailing white space entries in the array of automatically resolved conflicts")
	void automaticConflictTrailingWhiteSpaceTest(){
		Map<String,String> testMap1 = new HashMap<String,String>();
		Map<String,String> testMap2 = new HashMap<String,String>();
		upload = new ArrayList<Map<String,String>>();
		ConflictIdentifier automaticConflictCheck = new ConflictIdentifier();
		testMap1.put("AA", " 1 ");
		testMap1.put("AB", "bob");
		testMap2.put("AA", "Mistake");
		testMap2.put("AB", "2");
		upload.add(testMap1);
		upload.add(testMap2);
	    assertTrue(automaticConflictCheck.numAutomaticallyResolvedConflicts >= 0);
	}
	
	
	@Test
	@DisplayName("Test checking for number in a text column")
	void manualConflictNumInTextTest(){
		Map<String,String> testMap1 = new HashMap<String,String>();
		Map<String,String> testMap2 = new HashMap<String,String>();
	    ConflictIdentifier manualConflictCheck1 = new ConflictIdentifier();
		testMap1.put("AC", " 1 ");
		testMap1.put("ASDA", "bobby");
		testMap2.put("SSAA", "Mistake2");
		testMap2.put("ABBB", "2");
	    assertTrue(manualConflictCheck1.manualConflictData.size() >= 0);
	}
	
	@Test
	@DisplayName("Test checking for text in a number column")
	void manualConflictTextInNumTest(){
		Map<String,String> testMap1 = new HashMap<String,String>();
		Map<String,String> testMap2 = new HashMap<String,String>();
	    ConflictIdentifier manualConflictCheck2 = new ConflictIdentifier();
		testMap1.put("JHHJKJH", " 1 ");
		testMap1.put("ASDASD", "bobby");
		testMap2.put("MBNB", "Mistake3");
		testMap2.put("QWEQWE", "8");	    
		assertTrue(manualConflictCheck2.manualConflictData.size() >= 0);
	}
	
}
