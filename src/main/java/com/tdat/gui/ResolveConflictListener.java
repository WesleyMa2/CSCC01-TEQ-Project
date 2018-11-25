package com.tdat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.tdat.data.ConflictIdentifier;

public class ResolveConflictListener implements ActionListener {
	
	public static ConflictWindow conflictWindowInstance;
	
	public ResolveConflictListener(ConflictWindow specificInstance){
		this.conflictWindowInstance = specificInstance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String currentConflict = (String)conflictWindowInstance.manualConflictsDropdown.getSelectedItem();
		if (currentConflict != null){
			new ManualConflictResolvePanel(ConflictIdentifier.manualConflictData.get(currentConflict), currentConflict);
		} 
	}

}
