package cz.vse.planner.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/home.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("PLANNER v0.1.05");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/cz/vse/planner/icons/diary_small_b.png")));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
