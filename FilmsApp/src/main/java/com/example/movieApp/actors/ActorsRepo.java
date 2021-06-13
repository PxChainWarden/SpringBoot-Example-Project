package com.example.movieApp.actors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("ActorsRepo")
public interface ActorsRepo extends JpaRepository<Actors, Long> {
    Actors findByfname(String fname);
}
