package com.tdat.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.feeder.Uploader;

/**
 * When upload is clicked, performs sends info back to main.
 */
public class UploadButtonListener implements ActionListener {
    private JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private String selectedYear;
    private String selectedFileType;
    private File selectedFile;

    public UploadButtonListener(String selectedYear, String selectedFileType){
        jfc.setPreferredSize(new Dimension(800, 500));
        
    	this.selectedFileType = selectedFileType;
        this.selectedYear = selectedYear;
    }

    public void actionPerformed(ActionEvent e) {
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = jfc.getSelectedFile();
            App.selectedFile = selectedFile;
            App.selectedFileType = this.selectedFileType;
            App.selectedYear = (Year.of(Integer.parseInt(this.selectedYear.substring(0,4))));
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            String historyEntry = "Date: " + timeStamp + "   Filename: " + selectedFile.getName() + "   Filetype: " + this.selectedFileType + 
            					"   Fiscal year: " + this.selectedYear;
            GUI.DLM.addElement(historyEntry);
            GUI.fileUploadDict.put(historyEntry, this.selectedFile);
            System.out.println("Year:\t\t" + App.selectedYear);
            System.out.println("FileType:\t" + App.selectedFileType);
            System.out.println("File:\t\t" + App.selectedFile.getName());
            System.out.println("FilePath:\t" + selectedFile.getAbsolutePath());

            if (!Uploader.upload(App.selectedYear, App.selectedFile)){
                System.out.println("File Not Found!");
            };
            MasterData.printYearData(App.selectedYear);

        }
    }

}
