package com.example.crudapplication.Controller;

import com.example.crudapplication.Interface.ExcelDataService;
import com.example.crudapplication.Interface.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileUploaderService fileService;

    @Autowired
    ExcelDataService excelDataService;
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
        return "list";
    }
}

