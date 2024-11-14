package controller.Flights;
import javafx.fxml.FXML;
import model.Flights;
import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;

public class ExploreFlightsController extends Controller<Flights>{

    public Flights getFlights(){
        return model;
    }
    
    @FXML private Label displayWelcome;

    @FXML public void initialize(){
        displayWelcome.setText("Hi " + getFlights().getAgency().getLoggedInUser().getName() + ", welcome to the Flights section");
    }

    @FXML public void viewFlights() throws IOException{
    Stage viewFlightsStage = new Stage();
    viewFlightsStage.getIcons().add(new Image("/image/flights_icon.png"));
    ViewLoader.showStage(getFlights(), "/view/Flights/DisplayFlightsView.fxml", "Display Flights", viewFlightsStage);
    }

    @FXML public void viewFlightsByCountry() throws IOException{
    Stage viewFlightsByCountryStage = new Stage();
    viewFlightsByCountryStage.getIcons().add(new Image("/image/flights_icon.png"));
    ViewLoader.showStage(getFlights(), "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Filtered Flights", viewFlightsByCountryStage);
    }

    @FXML public void addFlight() throws IOException{
    Stage addFlightStage = new Stage();
    addFlightStage.getIcons().add(new Image("/image/flights_icon.png"));
    ViewLoader.showStage(getFlights(), "/view/Flights/AddFlightView.fxml", "Add Flight", addFlightStage);
    }

    @FXML public void removeFlight() throws IOException{
    Stage removeFlightStage = new Stage();
    removeFlightStage.getIcons().add(new Image("/image/flights_icon.png"));
    ViewLoader.showStage(getFlights(), "/view/Flights/RemoveFlightView.fxml", "Remove Flight", removeFlightStage);
    }

    @FXML public void handleClose(){
        stage.close();
    }

}
