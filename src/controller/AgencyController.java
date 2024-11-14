package controller;
import model.Agency;
import model.Trip;
import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.*;


public class AgencyController extends Controller<Agency>{
    
    public Agency getAgency(){
        return model;
    }

    public Trip getTrips(){
        Trip trip = new Trip(getAgency());
        return trip;
    }

    @FXML private Label displayWelcome;

    @FXML public void initialize(){
        displayWelcome.setText("Hi " + getAgency().getLoggedInUser().getName() + ", welcome to the Prog2 Travel Agency");
    }
    
    @FXML private void handleFlights() throws IOException{
    Stage flightsStage = new Stage();
    flightsStage.getIcons().add(new Image("/image/flights_icon.png"));
    ViewLoader.showStage(getAgency().getFlights(), "/view/Flights/ExploreFlightsView.fxml", "Explore Flights", flightsStage);
    }

    @FXML private void handleDestinations() throws IOException{
    Stage destinationsStage = new Stage();
    destinationsStage.getIcons().add(new Image("/image/destinations_icon.png"));
    ViewLoader.showStage(getAgency().getDestinations(), "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destinations", destinationsStage);
    }

    @FXML private void handleTrips() throws IOException{
    Stage tripsStage = new Stage();
    tripsStage.getIcons().add(new Image("/image/trip_icon.png"));
    ViewLoader.showStage(getTrips(), "/view/Trip/BookTripView.fxml", "Book a Trip", tripsStage);
    }

    @FXML public void handleClose(){
       Platform.exit();
    }


}
