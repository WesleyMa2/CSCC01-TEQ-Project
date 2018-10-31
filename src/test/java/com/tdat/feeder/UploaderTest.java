package com.tdat.feeder;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.data.TableData;
import com.tdat.data.VisitData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Array;
import java.time.Year;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UploaderTest {
    File testICare1 = new File("./src/test/resources/Test 1.xlsx");
    File testICare2 = new File("./src/test/resources/Test 2.xlsx");


    @Test
    @DisplayName("Test uploading single iCare file")
    void uploadOneFile() {
        App.selectedFileType = ".xlsx";
        if (!Uploader.upload(Year.of(2018), testICare1)){
            System.out.println("File not found!");
        }
        TableData data = MasterData.getYearData(Year.of(2018));
        assertEquals(new HashSet<>(Arrays.asList("AA", "AB", "AC", "AD")), new HashSet<>(data.getColumnList()));
        assertEquals(new HashSet<>(Arrays.asList("1.0", "2.0")), new HashSet<>(data.getColumnEntries("AA")));
    }

    @Test
    @DisplayName("Test uploading two iCare files")
    void uploadTwoFiles() {
        App.selectedFileType = ".xlsx";
        if (!Uploader.upload(Year.of(2018), testICare1) || !Uploader.upload(Year.of(2018), testICare2)){
            System.out.println("File not found!");
        }
        TableData data = MasterData.getYearData(Year.of(2018));
        assertEquals(new HashSet<>(data.getColumnList()), new HashSet<>(Arrays.asList("AA", "AB", "AC", "AD", "BA", "BB", "BS", "BC")));
    }
}