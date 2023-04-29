package cz.vse.planner.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;


public class Login_PasswordController {
    @FXML
    private Button MenuButtonHome;
    @FXML
    private Button LoginButtonNext;
    @FXML
    private Button LoginButtonBack;
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
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/my_events.fxml"));
            Parent root1 = fxmlLoader1.load();

            Stage primaryStage1 = (Stage) LoginButtonNext.getScene().getWindow();
            double currentWidth = primaryStage1.getWidth();
            double currentHeight = primaryStage1.getHeight();
            Scene passwordScene = new Scene(root1, currentWidth, currentHeight);

            primaryStage1.setScene(passwordScene);
        }
        catch (IOException exception){
            exception.printStackTrace();
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
