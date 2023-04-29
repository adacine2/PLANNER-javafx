package cz.vse.planner.main;
import cz.vse.planner.utils.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements Initializable{
    @FXML
    private Button MenuButtonHome;
    @FXML
    private Button LoginButtonNext;
    @FXML
    private Button MenuButtonLogin;
    @FXML
    private Button MenuButtonAdmin;
    @FXML
    private Button MenuButtonEvents;
    @FXML
    private Button MenuButtonNews;


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

    /* ALERT - EMAIL is not valid */
    private static Alert alert = new Alert(Alert.AlertType.ERROR);

    /* stored email as a text */
    private String email;

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
        changeTheImage.changeButtonImageOnHover(LoginButtonNext, "next");
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

    /**
     * Stores the email as a text into variable email
     */
    private void storeLoginEmail() {
        email = LoginEmail.getText();
    }

    /**
     * Returns the email as a text
     * @return email
     */
    public String getLoginEmail() {
        return email;
    }
    /**
     * Setter for the email as a text
     * @param email
     * @return "new" email
     */
    public String setLoginEmail(String email) {
        return this.email = email;
    }


    /**
     * Checks if the email is valid
     * @param email
     * @return
     */
    private static boolean checkLoginEmail(String email) {
        // ^(?:(?:[^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((?:[a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}|(\d{1,3}\.){3}\d{1,3}|\[(\d{1,3}\.){3}\d{1,3}])$
        String regex = "^(?:(?:[^<>\\[\\]()\\\\.,;:\\s@\"]+(\\.[^<>\\[\\]()\\\\.,;:\\s@\"]+)*)|(\\\".+\\\"))@((?:[a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}|(\\d{1,3}\\.){3}\\d{1,3}|\\[(\\d{1,3}\\.){3}\\d{1,3}])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /**
     * Shows the login password scene
     */
    private void showLoginPassword() {
        String email = LoginEmail.getText();
        if (checkLoginEmail(email)) {
            try {
                // Connect to the SQL database
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");

                // Prepare the INSERT statement
                String sql = "INSERT INTO users (email) VALUES (?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);

                // Execute the INSERT statement
                pstmt.executeUpdate();

                // Close the connection and statement
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/login_password.fxml"));
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
        else {
            alert.setTitle("Error");
            alert.setHeaderText("Invalid email");
            alert.setContentText("The email entered is not valid.");
            alert.showAndWait();
        }
    }

}
