package com.example.moviesmicroservice.dao;

import com.example.moviesmicroservice.doa.MovieRepository;
import com.example.moviesmicroservice.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    @Test
    void movieRepoFindAll_ReturnsListOfMovies(){
        List<Movie> movies = new ArrayList<>(List.of(Movie.builder().id(1).build(),Movie.builder().id(2).build()));
        movieRepository.saveAll(movies);
        List<Movie> foundMovies = movieRepository.findAll();
        assertNotNull(foundMovies);
    }

    @Test
    void movieRepoFindById_ReturnsAMovie(){
       Movie movie = Movie.builder().id(33).build();
        movieRepository.save(movie);
        Movie foundMovie = movieRepository.findById(33).get();
        assertEquals(foundMovie.getId(), 33);
    }
}
