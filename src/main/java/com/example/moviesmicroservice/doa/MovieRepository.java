package com.example.moviesmicroservice.doa;

import com.example.moviesmicroservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
