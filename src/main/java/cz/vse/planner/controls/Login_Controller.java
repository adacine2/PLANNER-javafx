package cz.vse.planner.controls;

import cz.vse.planner.utils.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;


@Controller
public class LoginController implements Initializable{
    @FXML
    private Button MenuButtonHome;
    @FXML
    private Button LoginButtonNext;
    @FXML
    private Button MenuButtonLogin;
    @FXML
    private Button MenuButtonNotif;
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
    private static EmailManager emailManager = new EmailManager();






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
        changeTheImage.changeButtonImageOnHover(MenuButtonNotif, "bell");
    }

    /**
     * Start initialization JavaFX thread.
     */
    public void startJavaFXThread() {
        initializeJavaFXThread();
    }

    /**
     * Initialize JavaFX thread.
     */
    private void initializeJavaFXThread() {
        Platform.startup(() -> {});
        Platform.setImplicitExit(false);
    }

    @Autowired
    private UserService userService;



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
     * Shows the login password scene
     */
    private void showLoginPassword() {
        String email = LoginEmail.getText();
        emailManager.setLoginEmail(email);
        if (emailManager.isEmailValid(email)) {
            if (!userService.userExists(email)) {
                try {
                    userService.addUser(email);
                } catch (MessagingException e) {
                    // handle the exception
                }
            }
                Platform.runLater(() -> {
                    /* ALERT - PASSWORD has been sent */
                    Alert alertEmail = new Alert(Alert.AlertType.ERROR);
                    ImageView alertEmailIcon = new ImageView(new Image(getClass().getResourceAsStream("/cz/vse/planner/icons/sent_email_w.png")));

                    //Inform user that useremail was sent
                    alertEmailIcon.setFitWidth(50);
                    alertEmailIcon.setFitHeight(50);
                    alertEmailIcon.setPreserveRatio(true);

                    alertEmail.setTitle("Succes");
                    alertEmail.setHeaderText("ACCOUNT CREATED");
                    alertEmail.setContentText("Mail with password was sent to your email.");
                    ((Button) alertEmail.getDialogPane().lookupButton(ButtonType.OK)).setText("AMAZING!");
                    alertEmail.getDialogPane().getStylesheets().add(getClass().getResource("/cz/vse/planner/styles/Style_Dark.css").toExternalForm());
                    alertEmail.getDialogPane().getStyleClass().add("alert-success");
                    alertEmail.getDialogPane().setGraphic(alertEmailIcon);
                    alertEmail.showAndWait();
                });
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/login_password.fxml"));
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
            Platform.runLater(() -> {
            /* ALERT - EMAIL is not valid */
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            ImageView alertErrorIcon = new ImageView(new Image(getClass().getResourceAsStream("/cz/vse/planner/icons/invalid_email_w.png")));

            alertErrorIcon.setFitWidth(50);
            alertErrorIcon.setFitHeight(50);
            alertErrorIcon.setPreserveRatio(true);

            alertError.setTitle("Error");
            alertError.setHeaderText("INVALID EMAIL ADDRESS");
            alertError.setContentText("Entered email address is not valid!");
            ((Button) alertError.getDialogPane().lookupButton(ButtonType.OK)).setText("OH NO!");
            alertError.getDialogPane().getStylesheets().add(getClass().getResource("/cz/vse/planner/styles/Style_Dark.css").toExternalForm());
            alertError.getDialogPane().getStyleClass().add("alert-error");
            alertError.getDialogPane().setGraphic(alertErrorIcon);
            alertError.show();
            });
        }
    }
}
