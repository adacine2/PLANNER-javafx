package cz.vse.planner.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class SceneManager {
    @Autowired
    private ConfigurableApplicationContext springContext;
    @Autowired
    private UserManager userManager;
    /**
     * This method is used to change the scene
     * @param stage
     * @param fxmlResourcePath
     */
    public void changeScene(Stage stage, String fxmlResourcePath) {
        try {
            userManager.isUserLogged();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlResourcePath));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();
            // Save the current dimensions of the window
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            Scene newScene = new Scene(root, currentWidth, currentHeight);
            //set the new scene to the stage
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initializeStage(Stage stage, String fxmlResourcePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlResourcePath));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();
            Scene scene = new Scene(root, 320, 240);
            stage.setTitle("PLANNER v0.2.0");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/cz/vse/planner/icons/diary_small_b.png")));
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
