package cz.vse.planner.controls;
import cz.vse.planner.utils.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@Component
public class Main_Controller implements Initializable {
    @FXML
    private Button MenuButtonHome, MenuButtonLogin, MenuButtonPlanner, MenuButtonNotif, MenuButtonAdmin, MenuButtonUser , MenuButtonEvents, MenuButtonNews;
    @FXML
    private void handleMenuButtonHome() {
        showHomeScene();
    }
    @FXML
    private void handleMenuButtonLogin() {
        showLoginScene();
    }
    @FXML
    private void handleMenuButtonPlanner() {
        showPlannerScene();
    }
    @Autowired
    private SceneManager sceneManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private LayoutManager layoutManager;

    /** IMAGE CHANGE ON HOVER
     * This part of code is used to change the image on hover
     * necesarry to import the class ChangeTheImage in utils
     * /utils/*
     * requires class to implements Initializable
     */
    public void initialize(URL location, ResourceBundle resources) {
        updateMenuButtonVisibility();
        changeButtonIconsOnHover();
    }
    private void changeButtonIconsOnHover() {
        layoutManager.changeButtonIconOnHover(MenuButtonHome, "home");
        layoutManager.changeButtonIconOnHover(MenuButtonNotif, "bell");
        layoutManager.changeButtonIconOnHover(MenuButtonUser, "user");
        layoutManager.changeButtonIconOnHover(MenuButtonAdmin, "admin_gear");
    }
    private void updateMenuButtonVisibility() {
        if (userManager.isUserLogged()) {
            layoutManager.updateMenuButtonVisibility(
                    Arrays.asList(MenuButtonPlanner,MenuButtonUser), // Show when logged in
                    Arrays.asList(MenuButtonLogin,MenuButtonAdmin) // Hide when logged in
            );
        } else {
            layoutManager.updateMenuButtonVisibility(
                    Arrays.asList(MenuButtonLogin,MenuButtonAdmin), // Show when not logged in
                    Arrays.asList(MenuButtonPlanner,MenuButtonUser) // Hide when not logged in
            );
        }
    }

    public void showHomeScene() {
        Stage primaryStage = (Stage) MenuButtonHome.getScene().getWindow();
        sceneManager.changeScene(primaryStage, "/cz/vse/planner/gui/home.fxml");
    }

    private void showLoginScene() {
        Stage primaryStage = (Stage) MenuButtonLogin.getScene().getWindow();
        sceneManager.changeScene(primaryStage, "/cz/vse/planner/gui/login.fxml");
    }
    private void showPlannerScene() {
        Stage primaryStage = (Stage) MenuButtonPlanner.getScene().getWindow();
        sceneManager.changeScene(primaryStage, "/cz/vse/planner/gui/planner.fxml");
    }
}
