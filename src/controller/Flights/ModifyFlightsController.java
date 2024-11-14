package controller.Flights;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import model.Flight;
import model.Flights;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.scene.control.*;


public class ModifyFlightsController extends Controller<Flights> {
    
    public Flights getFlights(){
        return model;
    }

    @FXML private TextField airlineTf;
    @FXML private TextField flightNoTf;
    @FXML private TextField takeOffTf;
    @FXML private TextField landingTf;
    @FXML private TextField costTf;
    @FXML private Button addButton;
    @FXML private Button removeButton;

    public void initialize(){
        if (addButton != null){
        BooleanBinding emptyTfsForAdd = airlineTf.textProperty().isEmpty().or(flightNoTf.textProperty().isEmpty()).or(takeOffTf.textProperty().isEmpty()).or(landingTf.textProperty().isEmpty()).or(costTf.textProperty().isEmpty());
        addButton.disableProperty().bind(emptyTfsForAdd);
        }
        if (removeButton != null){
        BooleanBinding emptyTfsForRemove = (takeOffTf.textProperty().isEmpty()).or(landingTf.textProperty().isEmpty());
        removeButton.disableProperty().bind(emptyTfsForRemove);
        }
    }

    public void addFlight() {
        try{
        String airline = airlineTf.getText();
        int flightNo = Integer.parseInt(flightNoTf.getText());
        String takeOff = takeOffTf.getText();
        String landing = landingTf.getText();
        double cost = Double.parseDouble(costTf.getText());
        Flight flight = new Flight(airline,flightNo,takeOff,landing,cost);
        getFlights().addFlight(flight);
        stage.close();
        }catch (DuplicateItemException e){
            ViewLoader.showErrorWindow(new ErrorModel(e,"This flight already exists"));
        } catch (NumberFormatException e){
            ViewLoader.showErrorWindow(new ErrorModel(e,"Enter a number"));
        }
    }

    public void removeFlight(){
        try{
        String takeOff = takeOffTf.getText();
        String landing = landingTf.getText();
        Flight flight  = getFlights().getFlight(takeOff, landing);
        getFlights().removeFlight(flight);
        stage.close();
        }catch(ItemNotFoundException e){
            ViewLoader.showErrorWindow(new ErrorModel(e,"This flight does not exist"));
        }
    }

    public void handleClose(){
        stage.close();
    }


}
