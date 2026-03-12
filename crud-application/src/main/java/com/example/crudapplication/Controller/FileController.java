package com.example.crudapplication.Controller;

import com.example.crudapplication.Entities.Students;
import com.example.crudapplication.Interface.ExcelDataService;
import com.example.crudapplication.Interface.FileUploaderService;
import com.example.crudapplication.Service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileUploaderService fileService;

    @Autowired
    ExcelDataService excelDataService;

    @Autowired
    StudentsService studentsService;
    @Autowired
    private FileUploaderService fileUploaderService;

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model, Principal principal){
        String username = principal.getName(); // currently logged-in user
        fileUploaderService.uploadFile(file, username); // pass username
        model.addAttribute("username", username);
        model.addAttribute("message", "File uploaded successfully");
        return "redirect:/list";
    }
//    @GetMapping("/students")
//    public String showStudents(Model model, Principal principal){
//
//        String username = principal.getName();
//        List<Students> students = studentsService.getStudentsByUsername(username);
//
//        model.addAttribute("students", students);
//        return "list";
//    }
    @GetMapping("/list")
    public String showStudentsFromList(Model model, Principal principal){

        String username = principal.getName();
        List<Students> students = studentsService.getStudentsByUsername(username);

        model.addAttribute("students", students);
        return "list";
    }
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadExcel(@RequestParam String username) {

        ByteArrayInputStream excel =
                excelDataService.exportStudentsToExcel(username);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=students.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(excel));
    }
}

