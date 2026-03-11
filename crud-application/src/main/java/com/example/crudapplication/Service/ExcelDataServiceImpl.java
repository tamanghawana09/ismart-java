package com.example.crudapplication.Service;

import com.example.crudapplication.Entities.Students;
import com.example.crudapplication.Entities.Users;
import com.example.crudapplication.Interface.ExcelDataService;
import com.example.crudapplication.Repository.FileRepository;
import com.example.crudapplication.Repository.StudentRepository;
import com.example.crudapplication.Repository.UserRepository;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelDataServiceImpl implements ExcelDataService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public ExcelDataServiceImpl(StudentRepository studentRepository, UserRepository userRepository){
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Students> getExcelDataAsList(InputStream inputStream){
        List<Students> students = new ArrayList<>();


        try{
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            for(Row row : sheet){
                if(row.getRowNum() == 0) continue;

                Students student = new Students();

                student.setFname(formatter.formatCellValue(row.getCell(1)));
                student.setLname(formatter.formatCellValue(row.getCell(2)));
                student.setEmail(formatter.formatCellValue(row.getCell(3)));
                student.setNumber(formatter.formatCellValue(row.getCell(4)));
                student.setBirthdate(formatter.formatCellValue(row.getCell(5)));
                student.setMarkssee(Float.parseFloat(formatter.formatCellValue(row.getCell(6))));
                student.setMarksPlus2(Float.parseFloat(formatter.formatCellValue(row.getCell(7))));
                student.setCourse(formatter.formatCellValue(row.getCell(8)));
                student.setGender(formatter.formatCellValue(row.getCell(9)));
                students.add(student);
            }

            workbook.close();

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return students;
    }

    @Override
    public int saveExcelData(List<Students> students, String username){
        Users currentUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        students.forEach(s -> s.setUser(currentUser));
        studentRepository.saveAll(students);
        return students.size();
    }
}
