package com.example.crudapplication.Service;

import com.example.crudapplication.Entities.Students;
import com.example.crudapplication.Interface.ExcelDataService;
import com.example.crudapplication.Interface.FileUploaderService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class FileUploaderServiceImpl implements FileUploaderService {
    private final ExcelDataService excelDataService;

    public FileUploaderServiceImpl(ExcelDataService excelDataService){
        this.excelDataService = excelDataService;
    }

    @Override
    public void uploadFile(MultipartFile file, String username){
        try(InputStream inputStream = file.getInputStream()){
            List<Students> students = excelDataService.getExcelDataAsList(inputStream);
            excelDataService.saveExcelData(students,username);
        }catch(Exception e){
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }


}
