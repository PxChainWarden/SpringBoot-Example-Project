package com.example.movieApp.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {
    public Movies findMoviesById(Long id);
    //FIND MOVIES BY NAME STARTS WITH
    @Query("select m from Movies m where upper(m.name) like ?1%")
    Page<Movies> findByNameStartingWith(String movieName, Pageable pageable);
    @Query("select m from Movies m join m.actors a where upper(m.name) like ?1% and a.id = ?2")
    Page<Movies> findByNameStartingWithAndActorId(String movieName, Long actorId, Pageable pageable);
    @Query("select m from Movies m join m.type t where upper(t) like ?1% and upper(m.name) like ?2%")
    Page<Movies> findByTypeStartingWithAndNameStartingWith(String movieType, String movieName, Pageable pageable);
    @Query("select m from Movies m join m.actors a join m.type t where upper(t) like ?1% and a.id = ?2")
    Page<Movies> findByTypeStartingWithAndActorId(String movieType,Long id, Pageable pageable);
    @Query("select m from Movies m join m.actors a join m.type t where upper(t) like ?1% and upper(m.name) like ?2% and a.id = ?3")
    Page<Movies> findByTypeStartingWithAndNameStartingWithAndActorId(String movieType,String movieName,Long id, Pageable pageable);
    //FIND MOVIES BY NAME STARTS WITH AND WITH GIVEN ACTOR
    Page<Movies> findMoviesByActorsIn(List<Long> id,Pageable pageable);
}
