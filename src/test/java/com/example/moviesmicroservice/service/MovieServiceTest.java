package com.example.moviesmicroservice.service;

import com.example.moviesmicroservice.doa.MovieRepository;
import com.example.moviesmicroservice.entity.Movie;
import com.example.moviesmicroservice.exceptions.MovieNotFoundException;
import com.example.moviesmicroservice.rest.MoviesResponse;
import com.example.moviesmicroservice.security.JwtService;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private MovieServiceImpl movieService;


    @Test
    void findAll_Returns_PageOfMovies(){
        doNothing().when(jwtService).checkToken(anyString());
        List<Movie> movies = new ArrayList<>(List.of(new Movie(), new Movie()));
        Page<Movie> moviesPage = new PageImpl<>(movies);
        when(movieRepository.findAll(any(Pageable.class))).thenReturn(moviesPage);
        MoviesResponse result = movieService.findAll(0, 10, "valid_token");
        assertEquals(result.getTotalElements(), 2);
        assertEquals(result.getPageSize(), 10);
        assertEquals(result.getPageNo(), 0);
        assertEquals(result.getTotalPages(), 1);

    }

    @Test
    void findValidMovie_ReturnsMovie() throws MovieNotFoundException {
        doNothing().when(jwtService).checkToken(anyString());
        int movieId = 2;
        Movie movie = Movie.builder().id(movieId).vote_average(9)
                .original_title("good movie")
                .overview("good Movie").build();
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        Movie result = movieService.findById(movieId, "any string");
        assertEquals(result, movie);

    }

    @Test
    void findInvalidMovie_ThrowsException(){
        doNothing().when(jwtService).checkToken(anyString());
        when(movieRepository.findById(any(int.class))).thenReturn(Optional.empty());
        assertThrows(MovieNotFoundException.class, () -> movieService.findById(9, "any string"));

    }


}
