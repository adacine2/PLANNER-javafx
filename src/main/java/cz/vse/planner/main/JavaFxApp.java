package cz.vse.planner.main;
import cz.vse.planner.utils.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
@Component
public class JavaFxApp extends Application {
    @Autowired
    private ConfigurableApplicationContext springContext;
    @Autowired
    private SceneManager sceneManager;

    @Override
    public void init() {
        Main.getSpringContext().getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage stage) {
        sceneManager.initializeStage(stage, "/cz/vse/planner/gui/home.fxml");
    }

    @Override
    public void stop() {
        springContext.close();
    }
}
