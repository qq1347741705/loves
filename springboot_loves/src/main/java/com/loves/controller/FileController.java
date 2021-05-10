package com.loves.controller;

import com.loves.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public Object uploadImage (@RequestParam("file") MultipartFile imgFile) {
        return fileService.uploadImage(imgFile);
    }
}
