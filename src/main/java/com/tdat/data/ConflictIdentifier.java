package com.tdat.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tdat.app.App;
import com.tdat.gui.ConflictWindow;

public class ConflictIdentifier {
	public static int numManualConflicts = 0;
	public static int numAutomaticallyResolvedConflicts = 0;
	
	public ConflictIdentifier(){
	}
	
	public void checkForAutomaticConflicts(List<Map<String, String>> potentialUpload){
		
		//Check for trailing whitespace
		for(Map<String,String> currentMap: potentialUpload){
			List<String> listOfValues = new ArrayList<String>(currentMap.values());
			for(String currentString : listOfValues){
				if(currentString.trim() != null){
					numAutomaticallyResolvedConflicts++;
					//add to list of automatically resolved conflict messages
					ConflictWindow.automaticConflictsArrayList.add(this.generateAutomaticallyResolvedConflictMessage(App.selectedFile, "TrailingWhitespaceConflict", currentString, currentString.trim()));
					currentMap.replace(currentMap.get(currentString), currentString.trim());
				}
			}
			
		}
		
	}
	
	public String generateAutomaticallyResolvedConflictMessage(File selectedFile, String conflictType, String oldValue, String newValue){
		return "Upload: " + selectedFile.getName() + ", Conflict Type: " +  conflictType + ", Old Value: " + oldValue + ", New Value: " + newValue;
		
	}
	
	

}
