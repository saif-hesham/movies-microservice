package com.example.moviesmicroservice.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthError {
    private Integer status;
    private String message;
    private Long timeStamp;
}
