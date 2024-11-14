package controller.Flights;
import javafx.fxml.FXML;
import model.Flight;
import model.Flights;
import au.edu.uts.ap.javafx.Controller;
import javafx.scene.control.*;

public class DisplayFlightsController extends Controller<Flights>{

    public Flights getFlights(){
        return model;
    }

    @FXML private TableView<Flight> tableView;
    @FXML private TableColumn<Flight,String> costColumn;
    @FXML private TextField countryTf;

    @FXML public void initialize(){
        costColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty().asString("$%.2f"));
        tableView.setItems(getFlights().getFlights());
    }

    @FXML public void filterFlights(){
        String country = countryTf.getText();
        tableView.setItems(getFlights().getFilteredFlights(country));
    }

    public void handleClose(){
        stage.close();
    }

}
