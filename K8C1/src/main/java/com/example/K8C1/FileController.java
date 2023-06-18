package com.example.K8C1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@RestController
public class FileController{

    @Autowired
    private FileService fileService;

    @PostMapping("/store-file")
    public ResponseEntity<?> storeFile(@RequestBody FileRequest fileRequest) {
        if (fileRequest.getFile() == null || fileRequest.getFile().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(null, "Invalid JSON input."));
        }
        boolean stored = fileService.storeFile(fileRequest.getFile(), fileRequest.getData());

        if(stored) {
            return ResponseEntity.ok(new SuccessResponse(fileRequest.getFile(), "Success."));
        } else  {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(fileRequest.getFile(), "Error while storing the file to the storage."));
        }
    }

    public static class FileRequest {
        private String file;
        private String data;

        // Getters and setters

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static class SuccessResponse {
        private String file;
        private String message;

        // Getters and setters

        public SuccessResponse(String file, String message) {
            this.file = file;
            this.message = message;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ErrorResponse {
        private String file;
        private String error;

        // Getters and setters

        public ErrorResponse(String file, String error) {
            this.file = file;
            this.error = error;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }



}




