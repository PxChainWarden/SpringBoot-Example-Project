package com.example.movieApp.actors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorsService {
    private final ActorsRepo actorsRepo;

    @Autowired
    public ActorsService(ActorsRepo actorsRepo) {
        this.actorsRepo = actorsRepo;
    }

    public List<Actors> findAllActors(){
        return actorsRepo.findAll();
    }
    public Page<Actors> findAllActors(Pageable pageable){
        return actorsRepo.findAll(pageable);
    }
    public void saveActor(Actors actors){
        actorsRepo.save(actors);
    }
    public void updateActor(Long id,Actors actors){
        Actors actorToUpdate = actorsRepo.getById(id);
        actorToUpdate.setName(actors.getName());
        actorToUpdate.setSurname(actors.getSurname());
        actorsRepo.save(actorToUpdate);
    }
    public void deleteActor(Long id){
        actorsRepo.delete(actorsRepo.getById(id));
    }
    public Optional<Actors> findById(Long id){
        return actorsRepo.findById(id);
    }

}
