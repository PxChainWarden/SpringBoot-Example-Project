package com.example.movieApp.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MoviesService {
//    @Autowired
//    private MoviesRepo moviesRepo;


    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }


    //    @GetMapping("Movies/")
    public List<Movies> getAllMovies(){
//        model.addAttribute("Something","This is coming from controller");
//        model.addAttribute("movies", moviesRepository.findAll());
        return moviesRepository.findAll();
//        return moviesRepository.findAll().toString();
    }
    public Page<Movies> getAllMovies(int page, int size){
        return moviesRepository.findAll(PageRequest.of(page,size));
    }
    public Page<Movies> getMovies(int page, int size, String q,String sortField,String sortDirection,Long actorId,String type){
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        Sort.Direction a = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        if(!sortField.isEmpty()) {
            Sort.Order sortOrder = new Sort.Order(a,sortField);
            orders.add(sortOrder);
        }
        Sort.Order nameOrder = new Sort.Order(Sort.Direction.ASC,"id");
        orders.add(nameOrder);
        Sort sort = Sort.by(orders);
        if(actorId > 0 && !type.isEmpty()) {
            return moviesRepository.findByTypeStartingWithAndNameStartingWithAndActorId(type.toUpperCase(Locale.ROOT),q.toUpperCase(Locale.ROOT),
                    actorId, PageRequest.of(page, size,sort));
        } else if(actorId > 0){
            return moviesRepository.findByNameStartingWithAndActorId(q.toUpperCase(Locale.ROOT),actorId,PageRequest.of(page, size,sort));
        } else if (!type.isEmpty()){
            return moviesRepository.findByTypeStartingWithAndNameStartingWith(type.toUpperCase(Locale.ROOT), q.toUpperCase(Locale.ROOT), PageRequest.of(page, size,sort));
        }
        return moviesRepository.findByNameStartingWith(q.toUpperCase(Locale.ROOT), PageRequest.of(page, size,sort));
    }
    public String setMoviesnew(Model model,@ModelAttribute("movie") Movies movie){
        if(movie!=null){
            movie = moviesRepository.save(movie);
        }
        model.addAttribute("Something","This is coming from controller");
        model.addAttribute("actors", moviesRepository.findAll());
        return "MovieAddForm";
    }
    public String setMovies(Model model,@ModelAttribute("movie") Movies movie){
        if(movie!=null){
            movie = moviesRepository.save(movie);
        }
        model.addAttribute("Something","This is coming from controller");
        model.addAttribute("actors", moviesRepository.findAll());
        return "redirect:../";
    }
    public Movies saveMovie(Movies movie){
        if(movie!=null){
            return moviesRepository.save(movie);
        }
        return null;
    }

    public Movies findMovieById(Long id){
        return moviesRepository.findMoviesById(id);
    }

    public void delete(Long id) {
        Movies movieToDelete = moviesRepository.findMoviesById(id);
        if(movieToDelete != null)
            moviesRepository.delete(movieToDelete);
        return;
        // TODO exception

    }

    public void update(Long id, Movies newMovie) {
        Movies movie = moviesRepository.findMoviesById(id);
        movie.setName(newMovie.getName());
        movie.setUrl(newMovie.getUrl());
        movie.setActors(newMovie.getActors());
        movie.setDescription(newMovie.getDescription());
        movie.setLanguage(newMovie.getLanguage());
        movie.setMedia(newMovie.getMedia());
        movie.setType(newMovie.getType());
        movie.setRelease(newMovie.getRelease());
        moviesRepository.save(movie);
    }
}
