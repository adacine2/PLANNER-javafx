package cz.vse.planner.controls;
import cz.vse.planner.utils.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
@Component
public class PlannerController implements Initializable {
    @FXML
    private Button MenuButtonHome, MenuButtonUser, MenuButtonPlanner, MenuButtonNotif, MenuButtonEvents, MenuButtonNews;
    @FXML
    private void handleMenuButtonHome() {
        showHomeScene();
    }
    @FXML
    private void handleMenuButtonPlanner() {
        showHomeScene();
    }
    @Autowired
    private SceneManager sceneManager;
    /** IMAGE CHANGE ON HOVER
     * requires class to implements Initializable
     */
    private LayoutManager layoutManager;
    public void initialize(URL location, ResourceBundle resources) {
        layoutManager = new LayoutManager();
        Button[] buttons = {MenuButtonHome, MenuButtonUser, MenuButtonNotif};
        String[] imageNames = {"home", "user", "bell"};
        for (int i = 0; i < buttons.length; i++) {
            layoutManager.changeButtonIconOnHover(buttons[i], imageNames[i]);
        }
    }
    public void showHomeScene() {
        Stage primaryStage = (Stage) MenuButtonHome.getScene().getWindow();
        sceneManager.changeScene(primaryStage, "/cz/vse/planner/gui/home.fxml");
    }
}
