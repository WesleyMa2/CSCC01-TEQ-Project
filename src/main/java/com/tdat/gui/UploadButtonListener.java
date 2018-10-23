package com.tdat.gui;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.feeder.Uploader;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.Year;

/**
 * When upload is clicked, performs sends info back to main.
 */
public class UploadButtonListener implements ActionListener {
    private JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private String selectedYear;
    private String selectedFileType;
    private File selectedFile;

    public UploadButtonListener(String selectedYear, String selectedFileType){
        this.selectedFileType = selectedFileType;
        this.selectedYear = selectedYear;
    }

    public void actionPerformed(ActionEvent e) {
        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = jfc.getSelectedFile();
            App.selectedFile = selectedFile;
            App.selectedFileType = this.selectedFileType;
            App.selectedYear = (Year.of(Integer.parseInt(this.selectedYear.substring(0,4))));
            System.out.println("Year:\t\t" + App.selectedYear);
            System.out.println("FileType:\t" + App.selectedFileType);
            System.out.println("File:\t\t" + App.selectedFile.getName());
            System.out.println("FilePath:\t" + selectedFile.getAbsolutePath());

            Uploader.upload(App.selectedYear, App.selectedFile);
            MasterData.printYearData(App.selectedYear);

        }
    }

}
