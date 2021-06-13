package com.example.movieApp.movie;

import com.example.movieApp.actors.Actors;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date release;
    private String url = "/img/placeholder2.jpg";
    @ElementCollection
    private List<String> type;
    private String description;
    @ElementCollection
    private List<String> language;
    @ElementCollection
    private List<String> media;
    @ElementCollection
    private List<String> comments;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="movie_actors",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name="actor_id")}
        )
    private List<Actors> actors = new ArrayList<>();

    protected Movies(){}

    public Movies(String name, Date release, String url, List<String> type, String description, List<String> language, List<String> media, List<Actors> actors){
        this.name = name;
        this.release = release;
        if(!url.isEmpty()){
            System.out.println(url);
            this.url = url;
        }
        this.type = type;
        this.description = description;
        this.language = language;
        this.media = media;
        this.actors = actors;
    }
    public Movies(String name, Date release, List<String> type, String description, List<String> language, List<String> media, List<Actors> actors){
        this.name = name;
        this.release = release;
        this.type = type;
        this.description = description;
        this.language = language;
        this.media = media;
        this.actors = actors;
    }

    public String getName() {
        return name;
    }
    public List<Actors> getActors() {  return actors; }
    public void setActors(List<Actors> actors) {
        this.actors = actors;
    }
    public List<String> getMedia() { return media; }
    public void setMedia(List<String> media) { this.media = media; }
    public List<String> getLanguage() {
        return language;
    }
    public void setLanguage(List<String> language) { this.language = language; }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<String> getType() { return type; }
    public String getTypeString() { return String.join(", ", type); }
    public String getMediaString() { return String.join(", ", media); }
    public String getLanguageString() { return String.join(", ", language); }
    public void setType(List<String> type) {
        this.type = type;
    }
    public void addActors(List<Actors> actors){
        this.actors.addAll(actors);
    }
    public Date getRelease() {
        return release;
    }
    public void setRelease(Date release) {
        this.release = release;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() { return id; }
    public void setUrl(String url) {
        this.url = url;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getComments() {
        return comments;
    }



    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release=" + release +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", language=" + language +
                ", media=" + media +
                ", actors=" + actors +
                '}';
    }
}
