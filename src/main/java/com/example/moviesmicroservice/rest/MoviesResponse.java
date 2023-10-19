package com.example.moviesmicroservice.rest;

import com.example.moviesmicroservice.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class MoviesResponse {
    private List<Movie> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
