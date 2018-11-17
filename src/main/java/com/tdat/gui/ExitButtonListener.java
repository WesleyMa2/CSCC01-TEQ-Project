package com.tdat.gui;

import com.tdat.data.template.InitialVisitType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.tdat.app.App;
import com.tdat.data.ConflictIdentifier;
import com.tdat.data.MasterData;

public class ExitButtonListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    if (ConflictIdentifier.numManualConflicts == 0) {
      this.uploadEditedFile();
      String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
          .format(Calendar.getInstance().getTime());
      String historyEntry =
          "Date: " + timeStamp + "   Filename: " + App.selectedFile.getName() + "   Filetype: "
              + App.selectedFileType +
              "   Fiscal year: " + App.selectedYear;
      UploadPanel.DLM.addElement(historyEntry);
      App.fileUploadDict.put(historyEntry, App.selectedFile);
      ConflictWindow.f.dispose();
    } else {
      System.out.println("Please resolve all manual conflicts before exiting");
    }
  }

  public void uploadEditedFile() {
    System.out.println(UploadButtonListener.newUpload.get(0).get("Template"));
    System.out.println(InitialVisitType.NARS.getTemplateName());
    if (InitialVisitType.contains(UploadButtonListener.newUpload.get(0).get("Template"))) {
      MasterData.setInitialVisitData(UploadButtonListener.newUpload);
    } else {
      MasterData.setServiceProvidedData(App.selectedYear, UploadButtonListener.newUpload);
    }
    System.out.println("\n[File Uploaded]");
    System.out.println(" File:\t\t" + App.selectedFile.getName());
    System.out.println(" Type:\t" + App.selectedFileType);
    System.out.println(" Path:\t" + App.selectedFile.getAbsolutePath());
    System.out.println(" Year:\t" + App.selectedYear);
    MasterData.printInitialVisitsData();
    MasterData.printServiceProvidedData(App.selectedYear);
  }

}
