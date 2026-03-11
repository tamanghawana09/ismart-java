package com.example.crudapplication.Interface;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploaderService {
     void uploadFile(MultipartFile file, String username);
}
