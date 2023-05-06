package cz.vse.planner.main;
import cz.vse.planner.utils.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    private Button MenuButtonHome;
    @FXML
    private Button MenuButtonLogin;
    @FXML
    private Button MenuButtonAdmin;
    @FXML
    private Button MenuButtonNotif;
    @FXML
    private Button MenuButtonEvents;
    @FXML
    private Button MenuButtonNews;

    @FXML
    private void handleMenuButtonHome() {
        showHomeScene();
    }
    @FXML
    private void handleMenuButtonLogin() {
        showLoginScene();
    }


    /** IMAGE CHANGE ON HOVER
     * This part of code is used to change the image on hover
     * necesarry to import the class ChangeTheImage in utils
     * /utils/*
     * requires class to implements Initializable
     */
    private ChangeTheImage changeTheImage;
    public void initialize(URL location, ResourceBundle resources) {
        changeTheImage = new ChangeTheImage();
        changeTheImage.changeButtonImageOnHover(MenuButtonHome, "home");
        changeTheImage.changeButtonImageOnHover(MenuButtonAdmin, "admin_gear");
        changeTheImage.changeButtonImageOnHover(MenuButtonNotif, "bell");
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

    private void showLoginScene() {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/login.fxml"));
            Parent root1 = fxmlLoader1.load();

            Stage primaryStage1 = (Stage) MenuButtonLogin.getScene().getWindow();
            double currentWidth = primaryStage1.getWidth();
            double currentHeight = primaryStage1.getHeight();
            Scene passwordScene = new Scene(root1, currentWidth, currentHeight);

            primaryStage1.setScene(passwordScene);
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

}
