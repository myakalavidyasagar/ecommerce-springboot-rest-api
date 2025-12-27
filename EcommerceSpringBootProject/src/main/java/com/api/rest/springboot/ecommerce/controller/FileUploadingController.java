package com.api.rest.springboot.ecommerce.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.rest.springboot.ecommerce.helper.FileUploadHelper;


@RestController
public class FileUploadingController {
    @Autowired private FileUploadHelper uploadHelper;

    @PostMapping("/upload-file") 
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) { 
        try { if (file.isEmpty()) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file"); 
            } 
            // String type = file.getContentType(); 
            // if (type == null || 
            // !(type.equals("image/jpeg") || 
            // type.equals("image/jpg") || 
            // type.equals("image/png") || 
            // type.equals("image/gif"))) { 
                // return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE) 
                // .body("Only JPEG, JPG, PNG, GIF formats are allowed!"); // } 
                // or 
                List<String> allowedTypes = Arrays.asList("image/jpeg", "image/jpeg", "image/gif", "image/png");
                 if (!allowedTypes.contains(file.getContentType())) {
                      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("Only JPEG,JPG,PNG,GIF Content type IMAGES are allowed"); 
                      } 
                      boolean f = uploadHelper.uploadFile(file); 
                      if (f) {
                         //return ResponseEntity.ok(" file is succesfully Uploaded"); 
                         return ResponseEntity.status(HttpStatus.OK).body(" file is succesfully Uploaded"); 
                        } 
                    } catch (Exception e) {
                         e.printStackTrace(); 
                        } 
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong! try again !"); 
                    } 
                }