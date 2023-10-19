package com.example.moviesmicroservice.rest;

import com.example.moviesmicroservice.entity.Movie;
import com.example.moviesmicroservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MoviesController {
    private final MovieService movieService;

    @Autowired
    public MoviesController(MovieService theMovieService) {
        movieService = theMovieService;
    }

    @GetMapping("/movies")
    public MoviesResponse getMovies(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
        @RequestHeader(name = "Authorization", required = false) String token
    ) {
        return movieService.findAll(pageNo, pageSize, token);
    }

    @GetMapping("/movies/{movieId}")
    public Movie getMovie(
            @RequestHeader(name = "Authorization", required = false) String token,
            @PathVariable int movieId
    ) {
        return movieService.findById(movieId, token);
    }

//    @PostMapping("/movies")
//    public void insertMovies(@RequestBody List<Movie> movieList) {
//        movieService.populateDb(movieList);
//    }
}
