package com.example.movieApp.actors;

import com.example.movieApp.movie.Movies;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Actors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String surname;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "actors")
//    @JoinColumn(name="movie_id")
    private List<Movies> movies;
    protected Actors(){}
    public Actors(String fname, String surname){
        this.fname = fname;
        this.surname = surname;
    }
    public String getName() {
        return fname;
    }
    public String getSurname() {
        return surname;
    }
    public void setName(String name) {
        this.fname = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return getName()+" "+getSurname();
    }
}
