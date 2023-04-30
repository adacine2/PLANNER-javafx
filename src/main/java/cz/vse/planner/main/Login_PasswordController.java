package cz.vse.planner.main;
import cz.vse.planner.utils.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;





public class Login_PasswordController implements Initializable {
    @FXML
    private Button MenuButtonHome;
    @FXML
    private Button LoginButtonNext;
    @FXML
    private Button LoginButtonBack;
    @FXML
    private Button MenuButtonLogin;
    @FXML
    private Button MenuButtonAdmin;
    @FXML
    private PasswordField LoginPassword;

    @FXML
    private void handleLoginButtonNext() {
        showMyEvents();
    }
    @FXML
    private void HandleLoginPassword() {
        showMyEvents();
    }
    @FXML
    private void handleLoginButtonBack() {
        showLoginEmail();
    }
    @FXML
    private void handleMenuButtonHome() {
        showHomeScene();
    }

    private EmailManager emailManager;
    private String userEmail;
    private static Alert alertError = new Alert(Alert.AlertType.ERROR);


    public Login_PasswordController() {
        emailManager = new EmailManager();
        userEmail = emailManager.getLoginEmail();
    }


    /** IMAGE CHANGE ON HOVER
     * This part of code is used to change the image on hover
     * necesarry to import the class ChangeTheImage in utils
     * /utils/*
     * requires class to implements Initializable
     */
    private ChangeTheImage changeTheImage;
    public void initialize(URL location, ResourceBundle resources) {
        emailManager = new EmailManager();
        userEmail = emailManager.getLoginEmail();
        changeTheImage = new ChangeTheImage();
        changeTheImage.changeButtonImageOnHover(MenuButtonHome, "home");
        changeTheImage.changeButtonImageOnHover(MenuButtonAdmin, "admin_gear");
        changeTheImage.changeButtonImageOnHover(LoginButtonNext, "next");
        changeTheImage.changeButtonImageOnHover(LoginButtonBack, "back");
    }



    public void showHomeScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/home.fxml"));
            Parent root = fxmlLoader.load();

            // Get the current stage from any control (in this case, the HOME button)
            Stage primaryStage = (Stage) MenuButtonHome.getScene().getWindow();
            // Save the current dimensions of the window
            double currentWidth = primaryStage.getWidth();
            double currentHeight = primaryStage.getHeight();
            Scene homeScene = new Scene(root, currentWidth, currentHeight);

            primaryStage.setScene(homeScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMyEvents() {
        String enteredPassword = LoginPassword.getText();
        String storedPassword = PasswordManager.getPasswordFromDB("adam.eger@seznam.cz");
        boolean passwordMatches = PasswordManager.checkInsertedPassword(enteredPassword, storedPassword);


        System.out.println("Entered password: " + enteredPassword);
        System.out.println("Stored password: " + storedPassword);
        System.out.println("Password matches: " + passwordMatches);
        if (passwordMatches) {
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/my_events.fxml"));
                Parent root1 = fxmlLoader1.load();

                Stage primaryStage1 = (Stage) LoginButtonNext.getScene().getWindow();
                double currentWidth = primaryStage1.getWidth();
                double currentHeight = primaryStage1.getHeight();
                Scene passwordScene = new Scene(root1, currentWidth, currentHeight);

                primaryStage1.setScene(passwordScene);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            // Show an alert if the password is incorrect
            alertError.setTitle("Incorrect Password");
            alertError.setHeaderText(null);
            alertError.setContentText("The entered password is incorrect. Please try again.");
            alertError.showAndWait();
        }
    }

    private void showLoginEmail() {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/login.fxml"));
            Parent root1 = fxmlLoader1.load();

            Stage primaryStage1 = (Stage) LoginButtonBack.getScene().getWindow();
            double currentWidth = primaryStage1.getWidth();
            double currentHeight = primaryStage1.getHeight();
            Scene passwordScene = new Scene(root1, currentWidth, currentHeight);

            primaryStage1.setScene(passwordScene);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

}
