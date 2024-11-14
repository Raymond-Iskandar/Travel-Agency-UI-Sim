package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Administrators;
import model.Agency;
import model.Exceptions.ErrorModel;
import model.Exceptions.InvalidCredentialsException;
import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.beans.binding.BooleanBinding;

public class LoginController extends Controller<Agency>{
    
    @FXML private TextField usernameTf;
    @FXML private PasswordField passwordTf;
    @FXML private Button loginButton;

    public Agency getAgency(){
        return model;
    }

    @FXML public void initialize(){
        if (loginButton != null){
            BooleanBinding emptyTfsForLogin = (usernameTf.textProperty().isEmpty()).or(passwordTf.textProperty().isEmpty());
            loginButton.disableProperty().bind(emptyTfsForLogin);
            }
    }

    @FXML private void handleLogin() throws IOException{
        String username = usernameTf.getText();
        String password = passwordTf.getText();
        Administrators admins = getAgency().getAdministrators();

        try{
            if (admins.hasAdministrator(username, password)){
            getAgency().setLoggedInUser(admins.getAdministrator(username, password));
            stage.close();
            Stage agencyStage = new Stage();
            agencyStage.getIcons().add(new Image("/image/agency_icon.png"));
            ViewLoader.showStage(getAgency(), "/view/AgencyView.fxml", "Prog2 Travel Agency", agencyStage);
        }
        }catch (InvalidCredentialsException e){
        ViewLoader.showErrorWindow(new ErrorModel(e,"Invalid Credentials! Try again!"));
    }
}

    @FXML public void handleClose(){
       Platform.exit();
    }

}
