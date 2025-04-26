package org.bookMyShow.controller;

import org.bookMyShow.enums.City;
import org.bookMyShow.model.Theatre;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
    private Map<City, List<Theatre>> cityVsTheatres;

    public TheatreController() {
        this.cityVsTheatres = new HashMap<>();
    }

    public void addTheatres(City city, Theatre theatre) {
        List<Theatre> theatres = cityVsTheatres.getOrDefault(city, new ArrayList<>());
        theatres.add(theatre);
        cityVsTheatres.put(city, theatres);
    }

    public List<Theatre> getTheatreByCity(City city) {
        return cityVsTheatres.get(city);
    }
}
