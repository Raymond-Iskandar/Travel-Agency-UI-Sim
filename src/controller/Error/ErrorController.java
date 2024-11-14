package controller.Error;
import au.edu.uts.ap.javafx.Controller;
import model.Exceptions.ErrorModel;
import javafx.scene.control.*;
import javafx.fxml.*;

public class ErrorController extends Controller<ErrorModel> {
    
    @FXML private Label errorType;
    @FXML private Label errorMessage;

    public ErrorModel getErrorModel(){
        return model;
    }

    @FXML public void initialize(){
        errorType.setText(getErrorModel().getException().getClass().getSimpleName());
        errorMessage.setText(getErrorModel().getMessage());
    }


    @FXML public void handleClose(){
        stage.close();
    }
}
