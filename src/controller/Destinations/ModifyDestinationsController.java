package controller.Destinations;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import model.Destinations;
import model.Destination;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.scene.control.*;


public class ModifyDestinationsController extends Controller<Destinations>{
    
    public Destinations getDestinations(){
        return model;
    }

    @FXML private TextField nameTf;
    @FXML private TextField countryTf;
    @FXML private Button addButton;

    public void initialize(){
        
        if(addButton != null) {
        BooleanBinding emptyTfsForAdd = nameTf.textProperty().isEmpty().or(countryTf.textProperty().isEmpty());
        addButton.disableProperty().bind(emptyTfsForAdd);
    }
}

    @FXML
    public void addDestination() throws DuplicateItemException, ItemNotFoundException{
        try{
        String name = nameTf.getText();
        String country = countryTf.getText();
        Destination destination = new Destination(name,country);
        getDestinations().addDestination(destination);
        stage.close();
        }catch (DuplicateItemException e){
            ViewLoader.showErrorWindow(new ErrorModel(e,"This destination already exists"));
        }
    }

    @FXML
    public void removeDestination(){
        String name = nameTf.getText();
        String country = countryTf.getText();
        try{
        Destination destination = getDestinations().destination(name,country);
        getDestinations().removeDestination(destination);
        stage.close();
        }catch(ItemNotFoundException e){
            ViewLoader.showErrorWindow(new ErrorModel(e,"This destination does not exist"));
        }
    }

    public void handleClose(){
        stage.close();
    }

}
