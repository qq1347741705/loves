package com.loves.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileService {

    @Value("${filePath}")
    private String imgPath;
    @Value("${serviceAddress}")
    private String serviceAddress;

    public Object uploadImage (MultipartFile imgFile) {
        String imgName = String.valueOf(new Date().getTime()) + ".png";
        File file = new File(imgPath + imgName);
        try {
            OutputStream out = new FileOutputStream(file);
            out.write(imgFile.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String , Object> result = new HashMap<>();
        Map<String , String> data = new HashMap<>();
        ArrayList<Object> list = new ArrayList<>();
        data.put("url",serviceAddress + imgName);
        data.put("href","");
        data.put("alt","");
        list.add(data);
        result.put("errno",0);
        result.put("data",list);
        return result;
    }
}
