package cz.vse.planner.main;
import cz.vse.planner.utils.*;
import cz.vse.planner.repo.*;

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
import java.sql.*;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;



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

    /* ALERT - EMAIL is not valid */

    private static Alert alertError = new Alert(Alert.AlertType.ERROR);
    private ImageView alertErrorIcon = new ImageView(new Image(getClass().getResourceAsStream("/cz/vse/planner/icons/invalid_email_w.png")));
    /* ALERT - PASSWORD has been sent */
    private static Alert alertEmail = new Alert(Alert.AlertType.ERROR);
    private ImageView alertEmailIcon = new ImageView(new Image(getClass().getResourceAsStream("/cz/vse/planner/icons/sent_email_w.png")));
    private EmailManager emailManager = new EmailManager();

    @Autowired
    private UserRepo userRepository;






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
     * Checks if the useremail is valid
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
        emailManager.setLoginEmail(email);
        if (checkLoginEmail(email)) {
            try {

                Connection conn = DBManager.getConnection();
                String sql = "SELECT email FROM users WHERE email=?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();
                System.out.println("Lokal email: "+email);
                if (!rs.next()){
                    // Generate a random password
                    String plainTextPassword = PasswordManager.generateRandomPassword(4);

                    // Hash the password using bcrypt
                    String hashedPassword = BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

                    // Prepare the INSERT statement
                    String insertSql = "INSERT INTO users (email, password) VALUES (?, ?)";
                    PreparedStatement insertPstmt = conn.prepareStatement(insertSql);
                    insertPstmt.setString(1, email);
                    insertPstmt.setString(2, hashedPassword);
                    // Execute the INSERT statement
                    insertPstmt.executeUpdate();
                    // Close the statement
                    insertPstmt.close();
                    // Send the plain text password to the user's email
                    EmailManager.sendEmail(email,"Your password is:", plainTextPassword);



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
                }

                // Close the connection and statement
                rs.close();
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
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {

            alertErrorIcon.setFitWidth(50);
            alertErrorIcon.setFitHeight(50);
            alertErrorIcon.setPreserveRatio(true);

            alertError.setTitle("Error");
            alertError.setHeaderText("INVALID EMAIL ADDRESS");
            alertError.setContentText("Entered email address is not valid!");
            ((Button) alertEmail.getDialogPane().lookupButton(ButtonType.OK)).setText("OH NO!");
            alertError.getDialogPane().getStylesheets().add(getClass().getResource("/cz/vse/planner/styles/Style_Dark.css").toExternalForm());
            alertError.getDialogPane().getStyleClass().add("alert-error");
            alertError.getDialogPane().setGraphic(alertErrorIcon);
            alertError.show();




        }
    }

}
