package org.bookMyShow.controller;

import org.bookMyShow.enums.City;
import org.bookMyShow.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    private Map<City, List<Movie>> cityVsMovies;
    List<Movie> allMovies;

    public MovieController() {
        this.cityVsMovies = new HashMap<>();
        allMovies = new ArrayList<>();
    }

    public void addMovie(Movie movie, City city) {
        List<Movie> movies = cityVsMovies.getOrDefault(city, new ArrayList<>());
        movies.add(movie);
        allMovies.add(movie);
        cityVsMovies.put(city, movies);
    }

    public List<Movie> getMoviesByCity(City city) {
        return cityVsMovies.get(city);
    }

    public Movie getMovieByName(String name) {
        for(Movie movie : allMovies) {
            if (movie.getMovieName().equals(name)) {
                return movie;
            }
        }

        return null;
    }
}
