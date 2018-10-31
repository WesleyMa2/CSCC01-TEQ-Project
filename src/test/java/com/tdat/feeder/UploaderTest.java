package com.tdat.feeder;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.data.TableData;
import com.tdat.data.VisitData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UploaderTest {
    File testICare = new File("./src/test/resources/Test 1.xlsx");


    @Test
    void upload() {
        System.out.println(testICare.getAbsolutePath());
        App.selectedFileType = ".xlsx";
        if (!Uploader.upload(Year.of(2018), testICare)){
            System.out.println("File not found!");
        }
        TableData data = MasterData.getYearData(Year.of(2018));
        List<VisitData> visits = data.getVisitsData();
        Set<String> columnName = visits.get(0).getData().keySet();
        assertTrue(columnName.containsAll(Arrays.asList("AA", "AB", "AC", "AD")));


    }
}