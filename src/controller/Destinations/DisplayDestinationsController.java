package controller.Destinations;
import javafx.fxml.FXML;
import model.Destination;
import model.Destinations;
import au.edu.uts.ap.javafx.Controller;
import javafx.scene.control.*;

public class DisplayDestinationsController extends Controller<Destinations>{
    
    public Destinations getDestinations(){
        return model;
    }

    @FXML private TableView<Destination> tableView;
    @FXML private TextField nameTf;
    @FXML private TextField countryTf;


    @FXML public void initialize(){
        tableView.setItems(getDestinations().getDestinations());
    }

    @FXML public void filterDestinations(){
        String country = countryTf.getText();
        tableView.setItems(getDestinations().getFilteredDestinations(country));
    }

    public void handleClose(){
        stage.close();
    }

}
