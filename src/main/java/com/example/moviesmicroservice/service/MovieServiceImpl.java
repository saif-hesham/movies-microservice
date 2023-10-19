package com.example.moviesmicroservice.service;

import com.example.moviesmicroservice.doa.MovieRepository;
import com.example.moviesmicroservice.entity.Movie;
import com.example.moviesmicroservice.rest.MoviesResponse;
import com.example.moviesmicroservice.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    private final JwtService jwtService;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepo, JwtService jwtService) {
        movieRepository = movieRepo;
        this.jwtService = jwtService;
    }

    @Override
    public MoviesResponse findAll(int pageNo, int pageSize, String token) {
        jwtService.checkToken(token);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Movie>  moviesPage = movieRepository.findAll(pageable);
        return new MoviesResponse(moviesPage.getContent(), pageNo, pageSize,
                moviesPage.getTotalElements(), moviesPage.getTotalPages(), moviesPage.isLast());

    }

    @Override
    public Movie findById(int id, String token) {
        jwtService.checkToken(token);
        Optional<Movie> result = movieRepository.findById(id);
        Movie curMovie = null;
        if(result.isPresent()) {
            curMovie = result.get();
        } else {
            throw new RuntimeException("Didn't find this movie of id" + id);
        }

        return curMovie;
    }

//    public void populateDb(List<Movie> movies) {
//        movieRepository.saveAll(movies);
//
//    }
}
