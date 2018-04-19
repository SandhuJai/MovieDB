package com.se_project.moviedb;

import java.util.ArrayList;

public class Movie {
    private String title;   // Title of the Movie
    private String releaseDate;
    private ArrayList<String> genres;
    private String rating;
    // TODO : Add poster, trailer, reviews, comments


    public Movie(String title, String releaseDate, ArrayList<String> genres, String rating) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
