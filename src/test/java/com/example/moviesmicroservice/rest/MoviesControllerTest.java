package com.example.moviesmicroservice.rest;

import com.example.moviesmicroservice.entity.Movie;
import com.example.moviesmicroservice.exceptions.MovieNotFoundException;
import com.example.moviesmicroservice.service.MovieService;
import com.example.moviesmicroservice.service.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MoviesControllerTest {
    @Mock
    private MovieServiceImpl movieService;

    @InjectMocks
    private MoviesController moviesController;

    @Test
    void getMovies_ReturnsMovieResponse(){
        List<Movie> movies = new ArrayList<>(List.of(new Movie(), new Movie()));
        Page<Movie> moviesPage = new PageImpl<>(movies);
        MoviesResponse moviesResponse = new MoviesResponse();
        moviesResponse.setContent(movies);
        when(movieService.findAll(any(int.class), any(int.class), any(String.class))).thenReturn(moviesResponse);
        MoviesResponse finalResponse = moviesController.getMovies(1,1,"any string");
        assertEquals(finalResponse.getContent().size(), 2);
    }

    @Test
    void getMovie_ReturnsAMovie() throws MovieNotFoundException {
        Movie movie = Movie.builder().overview("great movie")
                .vote_average(9)
                .original_title("nice one").build();
        when(movieService.findById(any(int.class), any(String.class))).thenReturn(movie);
        Movie finalResponse = moviesController.getMovie("any string", 1);
        assertEquals(finalResponse.getOverview(), "great movie");
        assertEquals(finalResponse.getOriginal_title(), "nice one");
        assertEquals(finalResponse.getVote_average(), 9);
    }
}
