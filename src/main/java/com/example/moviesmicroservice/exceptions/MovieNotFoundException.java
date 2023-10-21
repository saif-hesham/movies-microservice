package com.example.moviesmicroservice.exceptions;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(String s) {
        super(s);
    }
}
