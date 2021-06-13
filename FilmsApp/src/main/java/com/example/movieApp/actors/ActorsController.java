package com.example.movieApp.actors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/actors")
public class ActorsController {
    private final ActorsService actorsService;

    @Autowired
    public ActorsController(ActorsService actorsService) {
        this.actorsService = actorsService;
    }


    @PostMapping("/add")
    String actorSave(Model model, @ModelAttribute("actor") Actors actor){
            actorsService.saveActor(actor);
            model.addAttribute("actors", actorsService.findAllActors());
            return "redirect:/admin/actors";
    }
    @GetMapping("/getAll")
    @ResponseBody
    List<Actors> actorGetAll(){
        return actorsService.findAllActors();
    }
}
