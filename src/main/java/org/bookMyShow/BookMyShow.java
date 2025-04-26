package org.bookMyShow;

import org.bookMyShow.controller.MovieController;
import org.bookMyShow.controller.TheatreController;
import org.bookMyShow.enums.City;
import org.bookMyShow.enums.SeatCategory;
import org.bookMyShow.model.*;

import java.util.ArrayList;
import java.util.List;

public class BookMyShow {
    private MovieController movieController;
    private TheatreController theatreController;

    public BookMyShow() {
        this.movieController = new MovieController();
        this.theatreController = new TheatreController();
    }

   public void initialize() {
      createMovies();
      createTheatres();
   }

   private void createMovies() {
     Movie avenger = new Movie();
     avenger.setDuration(128);
     avenger.setMovieName("avenger");
     avenger.setId("1");

     Movie bahubali = new Movie();
     bahubali.setDuration(300);
     bahubali.setMovieName("bahubali");
     bahubali.setId("2");

     movieController.addMovie(avenger, City.DELHI);
     movieController.addMovie(avenger, City.BANGLORE);
     movieController.addMovie(bahubali, City.DELHI);
     movieController.addMovie(bahubali, City.BANGLORE);
   }

   public List<Seat> getSeats() {
       List<Seat> seats = new ArrayList<>();

       for (int i=1;i<=40;i++) {
           Seat seat = new Seat();
           seat.setSeatId(i);
           seat.setRow(0);
           seat.setSeatCategory(SeatCategory.SILVER);
       }

       for (int i=41;i<=70;i++) {
           Seat seat = new Seat();
           seat.setSeatId(i);
           seat.setRow(1);
           seat.setSeatCategory(SeatCategory.GOLD);
       }

       for (int i=71;i<=100;i++) {
           Seat seat = new Seat();
           seat.setSeatId(i);
           seat.setRow(2);
           seat.setSeatCategory(SeatCategory.PLATINUM);
       }

       return seats;
   }

   public List<Screen> createScreens() {
       List<Screen> screens = new ArrayList<>();
       Screen screen = new Screen();
       screen.setId("1");
       screen.setSeats(getSeats());
       screens.add(screen);
       return screens;
   }

   private Show createShow(int showId, Screen screen, Movie movie, int showStartTime) {
        Show show = new Show();
        show.setId(showId);
        show.setShowStartTime(showStartTime);
        show.setMovie(movie);
        show.setScreen(screen);
        return show;
   }

    private void createTheatres() {
        Movie avengerMovie = movieController.getMovieByName("avenger");
        Movie bahubaliMovie = movieController.getMovieByName("bahubali");

        Theatre inoxTheatre = new Theatre();
        inoxTheatre.setId("1");
        inoxTheatre.setScreens(createScreens());
        inoxTheatre.setCity(City.DELHI);
        Show show1 = createShow(1, inoxTheatre.getScreens().get(0), avengerMovie, 128);
        Show show2 = createShow(2, inoxTheatre.getScreens().get(0), bahubaliMovie, 240);
        inoxTheatre.setShows(List.of(show1, show2));

        Theatre pvrTheatre = new Theatre();
        pvrTheatre.setId("2");
        pvrTheatre.setScreens(createScreens());
        pvrTheatre.setCity(City.BANGLORE);
        Show show3 = createShow(3, pvrTheatre.getScreens().get(0), avengerMovie, 228);
        Show show4 = createShow(4, pvrTheatre.getScreens().get(0), bahubaliMovie, 340);
        pvrTheatre.setShows(List.of(show3, show4));
    }
}
