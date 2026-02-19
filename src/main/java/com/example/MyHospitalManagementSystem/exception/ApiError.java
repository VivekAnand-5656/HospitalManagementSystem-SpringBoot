package com.example.MyHospitalManagementSystem.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String error;
    private HttpStatus httpStatus;

    public ApiError(){
        this.timestamp = LocalDateTime.now();
    }
    public ApiError(String error,HttpStatus statusCode){
        this();
        this.error = error;
        this.httpStatus = statusCode;
    }

}
