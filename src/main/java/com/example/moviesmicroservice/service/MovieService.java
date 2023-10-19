package com.example.moviesmicroservice.service;

import com.example.moviesmicroservice.entity.Movie;
import com.example.moviesmicroservice.rest.MoviesResponse;

import java.util.List;

public interface MovieService {
    public MoviesResponse findAll(int pageNo, int pageSize, String token);

    public Movie findById(int id, String token);

//    public void populateDb(List<Movie> movies);

}
