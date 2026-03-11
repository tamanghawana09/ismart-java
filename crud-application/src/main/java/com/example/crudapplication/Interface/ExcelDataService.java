package com.example.crudapplication.Interface;

import com.example.crudapplication.Entities.Students;

import java.io.InputStream;
import java.util.List;

public interface ExcelDataService {
    List<Students> getExcelDataAsList(InputStream inputStream);
    int saveExcelData(List<Students> students, String username);
}
