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
    private JFileChooser jfc = new JFileChooser();
    private String selectedYear;
    private String selectedFileType;
    private File selectedFile;

    public UploadButtonListener(){
        jfc.setPreferredSize(new Dimension(800, 500));
    }

    public void actionPerformed(ActionEvent e) {
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = jfc.getSelectedFile();
            App.selectedFile = selectedFile;
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            String historyEntry = "Date: " + timeStamp + "   Filename: " + selectedFile.getName() + "   Filetype: " + App.selectedFileType +
            					"   Fiscal year: " + App.selectedYear;
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
