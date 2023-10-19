package com.example.moviesmicroservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "original_title")
    private String original_title;

    @Column(name = "vote_average")
    private float vote_average;

    @Column(name = "overview")
    private String overview;



}
