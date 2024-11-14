package controller.Trip;
import javafx.fxml.FXML;
import model.Trip;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.InsufficientDestinationsException;
import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;

public class BookTripController extends Controller<Trip>{

    public Trip getTrips(){
        return model;
    }

    @FXML private Label displayWelcome;

    @FXML public void initialize(){
        displayWelcome.setText("Hi " + getTrips().getAgency().getLoggedInUser().getName() + ", welcome to the Trip section");
    }

    @FXML public void handleAddDestination() throws IOException{

            Stage addDestinationStage = new Stage();
            addDestinationStage.getIcons().add(new Image("/image/destinations_icon.png"));
            ViewLoader.showStage(getTrips().getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination To Trip", addDestinationStage);
    }
    
    @FXML public void handleRemoveDestination() throws IOException{
            Stage removeDestinationStage = new Stage();
            removeDestinationStage.getIcons().add(new Image("/image/destinations_icon.png"));
            ViewLoader.showStage(getTrips().getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination From Trip", removeDestinationStage);
    }

    public void handleTripDisplay() throws IOException{
        Stage handleTripDisplayStage = new Stage();
        handleTripDisplayStage.getIcons().add(new Image("/image/trip_icon.png"));
        ViewLoader.showStage(getTrips(), "/view/Trip/DisplayTripView.fxml", "Display Trip", handleTripDisplayStage);
    }

     @FXML public void addConnectingFlights() throws InsufficientDestinationsException{
        try{
        getTrips().addConnectingFlights();
        }catch(DuplicateItemException e){
            ViewLoader.showErrorWindow(new ErrorModel(e,"There are two duplicate flights"));
        }catch(InsufficientDestinationsException e){
            ViewLoader.showErrorWindow(new ErrorModel(e,"Not enough destinations to connect"));
        }
    }

    public void handleClose(){
        stage.close();
    }

}
