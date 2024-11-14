package controller.Trip;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.InputMismatchException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import model.*;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class DisplayTripController extends Controller<Trip> {

    public Trip getTrips(){
        return model;
    }

    @FXML private ListView<Itinery> destinationList;

    @FXML public void initialize(){
        destinationList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        destinationList.setItems(getTrips().getItinery());
        getTrips().getDestinations().getDestinations().addListener((ListChangeListener.Change<? extends Itinery> c)->{
            destinationList.setItems(getTrips().getItinery());
        });
         getTrips().getFlights().getFlights().addListener((ListChangeListener.Change<? extends Itinery> c)->{
            destinationList.setItems(getTrips().getItinery());
        });
        
    }

    @FXML public void viewIndividual() throws IOException, DuplicateItemException{
        try{
        ObservableList<Itinery> selectedItems = destinationList.getSelectionModel().getSelectedItems();
        Flights flightsToDisplay = new Flights(getTrips().getAgency());
        Destinations destinationsToDisplay = new Destinations(getTrips().getAgency());
        for (Itinery item : selectedItems){
            if (item instanceof Flight){
                Flight flight = (Flight) item;
                flightsToDisplay.addFlight(flight);
            }
            else if (item instanceof Destination){
            Destination destination = (Destination) item;
              destinationsToDisplay.addDestination(destination);
            }
        }
            // if (!destinationsToDisplay.getDestinations().isEmpty() && !flightsToDisplay.getFlights().isEmpty()){
            //     throw new InputMismatchException("You can't select destinations and flights together");
            // }
        if (!flightsToDisplay.getFlights().isEmpty()) {
            Stage viewFlightsStage = new Stage();
            viewFlightsStage.getIcons().add(new Image("/image/flights_icon.png"));
            ViewLoader.showStage(flightsToDisplay, "/view/Flights/DisplayFlightsView.fxml", "Display Flights", viewFlightsStage);
        }
        else if (!destinationsToDisplay.getDestinations().isEmpty()) {
            Stage viewDestinationsStage = new Stage();
            viewDestinationsStage.getIcons().add(new Image("/image/destinations_icon.png"));
            ViewLoader.showStage(destinationsToDisplay,"/view/Destinations/DisplayDestinationsView.fxml","Display Destinations",viewDestinationsStage);
        }
    }catch(InputMismatchException e){
        ViewLoader.showErrorWindow(new ErrorModel(e,"You can't select destinations and flights together"));
    }

    }

    @FXML public void handleClose(){
        stage.close();
    }
}