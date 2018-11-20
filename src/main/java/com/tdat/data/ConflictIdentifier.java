package com.tdat.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdat.app.App;
import com.tdat.gui.ConflictWindow;

public class ConflictIdentifier {
	public static int numManualConflicts = 0;
	public static int numAutomaticallyResolvedConflicts = 0;
	public static HashMap<String, ArrayList<Object>> manualConflictData = new HashMap<String, ArrayList<Object>>();
	public ConflictIdentifier(){
	}
	
	public void checkForAutomaticConflicts(List<Map<String, String>> potentialUpload){
		
		//Check for trailing whitespace
		for(Map<String,String> currentMap: potentialUpload){
			List<String> listOfValues = new ArrayList<String>(currentMap.values());
			for(String currentString : listOfValues){
				trailingWhitespaceCheck(currentMap, currentString);
			}
		}
	}
	
	public void trailingWhitespaceCheck(Map<String,String> currentMap, String currentString){
		if(currentString.trim() != null && currentString != "N/A"){
			numAutomaticallyResolvedConflicts++;
			//add to list of automatically resolved conflict messages
			ConflictWindow.automaticConflictsArrayList.add(this.generateAutomaticallyResolvedConflictMessage(App.selectedFile, "TrailingWhitespaceConflict", currentString, currentString.trim()));
			currentMap.replace(currentMap.get(currentString), currentString.trim());
		}
	}
	
	public String generateAutomaticallyResolvedConflictMessage(File selectedFile, String conflictType, String oldValue, String newValue){
		return "Upload: " + selectedFile.getName() + ", Conflict Type: " +  conflictType + ", Old Value: " + oldValue + ", New Value: " + newValue;
		
	}
	
	public void checkForManualConflicts(List<Map<String, String>> potentialUpload){
		checkForTypeDifferences(potentialUpload);
	}
	
	public void checkForTypeDifferences(List<Map<String, String>> potentialUpload){
		HashMap<String, Object> typeReferenceDict = new HashMap<String, Object>();
		for(String columnName: potentialUpload.get(0).keySet()){
			//populate the type reference dictionary declared above
			if(this.isNumeric(potentialUpload.get(0).get(columnName))){
				typeReferenceDict.put(columnName, Integer.class.getName());
			} else {
				typeReferenceDict.put(columnName, potentialUpload.get(0).get(columnName).getClass().getName());
			}
		}
		//System.out.println(typeReferenceDict);
		for(Map<String, String> rowEntry: potentialUpload){
			int rowMapIndex = potentialUpload.indexOf(rowEntry);
			for(String key: rowEntry.keySet()){
				//if value is numeric but reference dictionary says that column shouldn't have numeric values, do this
				if(isNumeric(rowEntry.get(key)) && typeReferenceDict.get(key) != Integer.class.getName()){
						numManualConflicts++;
						String conflictMessage = generateManualResolveConflictMessage(App.selectedFile, "TypeConflict", rowEntry.get(key));
						ConflictWindow.manualConflictsArrayList.add(conflictMessage);
						//System.out.println(ConflictWindow.manualConflictsArrayList);
						ArrayList<Object> conflictData = new ArrayList<Object>();
						conflictData.add(rowMapIndex);
						conflictData.add(key);
						conflictData.add(rowEntry.get(key));
						manualConflictData.put(conflictMessage, conflictData);
						//System.out.println(manualConflictData);
				}
			}
		}

	}
	
	public static boolean isNumeric(String columnName){  
	  try {  
	    int d = Integer.parseInt(columnName);  
	  }  
	  catch(NumberFormatException nfe){  
	    return false;  
	  }  
	  return true;  
	}
	
	public String generateManualResolveConflictMessage(File selectedFile, String conflictType, String oldValue){
		return "Upload: " + selectedFile.getName() + ", Conflict Type: " +  conflictType + ", Conflicting Value: " + oldValue;
	}
	
}
