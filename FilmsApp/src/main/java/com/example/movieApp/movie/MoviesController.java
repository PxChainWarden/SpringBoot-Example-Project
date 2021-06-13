package com.example.movieApp.movie;

import com.example.movieApp.actors.Actors;
import com.example.movieApp.actors.ActorsRepo;
import com.example.movieApp.actors.ActorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class MoviesController {
    private final MoviesService moviesService;
    private final ActorsService actorsService;

    @Autowired
    public MoviesController(MoviesService moviesService,ActorsService actorsService){
        this.moviesService = moviesService;
        this.actorsService = actorsService;
    }



    @GetMapping("/")
    String getIndex(Model model, @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "") String q,
                    @RequestParam(defaultValue = "") String s,@RequestParam(defaultValue = "asc") String sd,
                    @RequestParam(defaultValue = "0") Long a, @RequestParam(defaultValue = "") String t){
        model.addAttribute("carousalMovies",moviesService.getAllMovies(0,5));
        model.addAttribute("actors",actorsService.findAllActors());
        model.addAttribute("movies",moviesService.getMovies(page,9,q,s,sd,a,t));
        model.addAttribute("currPage",page);
        model.addAttribute("currSortField",s);
        model.addAttribute("currSortDirection",sd);
        model.addAttribute("searchQuery",q);
        model.addAttribute("searchType",t);
        model.addAttribute("actorSearch",a);
        return "index";
    }
    @GetMapping("/movie/")
    String getMovie(Model model, @RequestParam Long id){
        model.addAttribute("movies",moviesService.findMovieById(id));
        model.addAttribute("actors",moviesService.findMovieById(id).getActors());
        return "moviePage";
    }
    //ADMIN PAGE
    @GetMapping("/admin")
    String adminPage(){
        return "adminPage";
    }
    //ADMIN PAGE TO LIST AND EDIT MOVIES
    @GetMapping("/admin/movies")
    String getMovies(Model model,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "") String q,
                     @RequestParam(defaultValue = "") String s,@RequestParam(defaultValue = "asc") String sd,
                     @RequestParam(defaultValue = "0") Long a,@RequestParam(defaultValue = "") String t){
        model.addAttribute("actors",actorsService.findAllActors());
        model.addAttribute("movies",moviesService.getMovies(page,9,q,s,sd,a,t));
        model.addAttribute("currPage",page);
        model.addAttribute("currSortField",s);
        model.addAttribute("currSortDirection",sd);
        model.addAttribute("searchQuery",q);
        model.addAttribute("searchType",t);
        model.addAttribute("actorSearch",a);
        return "MoviesAddForm";
    }
    //ADMIN PAGE TO LIST AND EDIT ACTORS
    @GetMapping("admin/actors")
    String getActors(Model model,@RequestParam(defaultValue = "0") int page){
        model.addAttribute("Something","This is coming from controller");
        model.addAttribute("actors", actorsService.findAllActors(PageRequest.of(page,5)));
        model.addAttribute("currPage",page);
        return "Actors";
    }
    //ACTORS API
    @PostMapping("api/actor/add")
    String addActor(Model model,@ModelAttribute("actor") Actors actor){
        actorsService.saveActor(actor);
        return "redirect:/admin/actors";
    }
    @PostMapping("api/actor/update")
    String updateActor(Model model,@ModelAttribute("actor") Actors actor,@RequestParam Long id){
        actorsService.updateActor(id,actor);
        return "redirect:/admin/actors";
    }
    @PostMapping("api/actor/delete")
    String deleteActor(Model model,@RequestParam Long id){
        actorsService.deleteActor(id);
        return "redirect:/admin/actors";
    }
    @GetMapping("api/actor/find")
    @ResponseBody
    Optional<Actors> findActor(@RequestParam Long id){
        return actorsService.findById(id);
    }

    //MOVIES API
    @GetMapping("api/movieadd/find")
    @ResponseBody
    Movies findMovie(@RequestParam Long id){
        return moviesService.findMovieById(id);
    }
    @PostMapping("api/movieadd/add")
    String adddMovie(Model model, @ModelAttribute("movie") Movies movie){
        System.out.println(movie.toString());
        moviesService.saveMovie(movie);
        return "redirect:/admin/movies";
    }
    @PostMapping("api/movieadd/update")
    String updateMovie(Model model,@ModelAttribute("movie") Movies movie,@RequestParam("id") Long id){
        moviesService.update(id,movie);
        return "redirect:/admin/movies";
    }
    @PostMapping("api/movieadd/delete")
    String deleteMovie(Model model,@RequestParam("id") Long id){
        moviesService.delete(id);
        model.addAttribute("Something","This is coming from controller");
        model.addAttribute("actors", moviesService.getAllMovies());
        return "redirect:/admin/movies";
    }

}
