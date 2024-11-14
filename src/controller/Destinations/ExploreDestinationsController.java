package controller.Destinations;
import javafx.fxml.FXML;
import model.Destinations;
import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;

public class ExploreDestinationsController extends Controller<Destinations>{

       public Destinations getDestinations(){
        return model;
    }

    @FXML private Label displayWelcome;

    @FXML public void initialize(){
        displayWelcome.setText("Hi " + getDestinations().getAgency().getLoggedInUser().getName() + ", welcome to the Destinations section");
    }

    @FXML public void viewDestinations() throws IOException{
        Stage viewDestinationsStage = new Stage();
        viewDestinationsStage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(getDestinations(), "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", viewDestinationsStage);
        }

    
    @FXML public void viewDestinationsByCountry() throws IOException{
        Stage viewDestinationsByCountryStage = new Stage();
        viewDestinationsByCountryStage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(getDestinations(), "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Destinations Filtered", viewDestinationsByCountryStage);
    }

    @FXML public void addDestination() throws IOException{
        Stage addDestinationStage = new Stage();
        addDestinationStage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination", addDestinationStage);
    }

    @FXML public void removeDestination() throws IOException{
        Stage removeDestinationStage = new Stage();
        removeDestinationStage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", removeDestinationStage);
}

    @FXML public void handleClose(){
        stage.close();
    }

}
