package cz.vse.planner.controls;
import cz.vse.planner.utils.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Login_Controller implements Initializable{
    @FXML
    private Button MenuButtonHome, MenuButtonAdmin, MenuButtonLogin, MenuButtonNotif, MenuButtonEvents, MenuButtonNews;
    @FXML
    private Button LoginButtonNext;
    @FXML
    private TextField LoginEmail;
    @FXML
    private void handleLoginButtonNext() {
        showLoginPassword();
    }
    @FXML
    private void handleMenuButtonHome() {
        showHomeScene();
    }
    @FXML
    private void HandleLoginEmail() {
        showLoginPassword();
    }
    @Autowired
    private EmailManager emailManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private SceneManager sceneManager;
    @Autowired
    private LayoutManager layoutManager;
    /** IMAGE CHANGE ON HOVER
     * This part of code is used to change the image on hover
     * necesarry to import the class ChangeTheImage in utils
     * /utils/*
     * requires class to implements Initializable
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        layoutManager.changeButtonIconOnHover(MenuButtonHome, "home");
        layoutManager.changeButtonIconOnHover(MenuButtonAdmin, "admin_gear");
        layoutManager.changeButtonIconOnHover(LoginButtonNext, "next");
        layoutManager.changeButtonIconOnHover(MenuButtonNotif, "bell");
    }

    /**
     * Shows the Home scene
     */
    public void showHomeScene() {
        // Get the current stage from any control
        Stage primaryStage = (Stage) MenuButtonHome.getScene().getWindow();
        sceneManager.changeScene(primaryStage, "/cz/vse/planner/gui/home.fxml");
    }

    /**
     * Shows the login password scene
     */
    private void showLoginPassword() {
        String email = LoginEmail.getText();
        if (emailManager.isEmailValid(email)) {
            if (!userManager.userExists(email)) {
                try {
                    userManager.addUser(email);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Inform user that user-email was sent
                AlertsController.showEmailAlert("Succes","ACCOUNT CREATED","Mail with password was sent to your email.","AMAZING!","/cz/vse/planner/icons/sent_email_w.png");
            }
            emailManager.setLoginEmail(email);
            Stage primaryStage = (Stage) LoginButtonNext.getScene().getWindow();
            sceneManager.changeScene(primaryStage, "/cz/vse/planner/gui/login_password.fxml");
        } else {
            //Email is not valid ERROR
            AlertsController.showErrorAlert("Error", "INVALID EMAIL ADDRESS","Entered email address is not valid!", "OH NO!","/cz/vse/planner/icons/invalid_email_w.png");
        }
    }
}
