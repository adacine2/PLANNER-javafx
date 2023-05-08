package cz.vse.planner.controls;
import cz.vse.planner.utils.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class Login_Password_Controller implements Initializable {
    @FXML
    private Button MenuButtonHome, MenuButtonAdmin, MenuButtonLogin, MenuButtonNotif, MenuButtonEvents, MenuButtonNews;
    @FXML
    private Button LoginButtonNext, LoginButtonBack;
    @FXML
    private PasswordField LoginPassword;
    @FXML
    private void handleLoginButtonNext() {
        logIn();
    }
    @FXML
    private void HandleLoginPassword() {
        logIn();
    }
    @FXML
    private void handleLoginButtonBack() {
        showLoginEmail();
    }
    @FXML
    private void handleMenuButtonHome() {
        showHomeScene();
    }
    @FXML
    private void handleMenuButtonLogin() {
        showLoginEmail();
    }
    @Autowired
    private PasswordManager passwordManager;
    @Autowired
    private SceneManager sceneManager;
    @Autowired
    private UserManager userManager;
    /** IMAGE CHANGE ON HOVER
     * This part of code is used to change the image on hover
     */
    private LayoutManager layoutManager;
    public void initialize(URL location, ResourceBundle resources) {
        layoutManager = new LayoutManager();

        Button[] buttons = {MenuButtonHome, MenuButtonAdmin, LoginButtonNext, LoginButtonBack, MenuButtonNotif};
        String[] imageNames = {"home", "admin_gear", "next", "back", "bell"};
        for (int i = 0; i < buttons.length; i++) {
            layoutManager.changeButtonIconOnHover(buttons[i], imageNames[i]);
        }
    }

    public void showHomeScene() {
        Stage primaryStage = (Stage) MenuButtonHome.getScene().getWindow();
        sceneManager.changeScene(primaryStage, "/cz/vse/planner/gui/home.fxml");
    }

    private void logIn() {
        String enteredPassword = LoginPassword.getText();
        String storedPassword = passwordManager.getPasswordFromDB();
        boolean passwordMatches = PasswordManager.checkInsertedPassword(enteredPassword, storedPassword);
        //controlling the actual values
        System.out.println("Entered password: " + enteredPassword);
        System.out.println("Stored password: " + storedPassword);
        System.out.println("Password matches: " + passwordMatches);
        if (passwordMatches) {
            userManager.setLoggedIn(true);
            Stage primaryStage = (Stage) LoginButtonNext.getScene().getWindow();
            sceneManager.changeScene(primaryStage, "/cz/vse/planner/gui/planner.fxml");
        } else {
            //Password doesn't match ERROR
            AlertsController.showErrorAlert("Error","INCORRECT PASSWORD", "The entered password is incorrect. Please try again.", "Let's try again!","/cz/vse/planner/icons/invalid_email_w.png");
        }
    }

    private void showLoginEmail() {
        Stage primaryStage = (Stage) MenuButtonLogin.getScene().getWindow();
        sceneManager.changeScene(primaryStage, "/cz/vse/planner/gui/login.fxml");
    }
}
