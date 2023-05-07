package cz.vse.planner.main;

import cz.vse.planner.controls.Login_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class JavaFxApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Login_Controller loginController = Main.getSpringContext().getBean(Login_Controller.class);
        loginController.startJavaFXThread(); // Call startJavaFXThread() method here

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cz/vse/planner/gui/home.fxml"));
        loader.setControllerFactory(Main.getSpringContext()::getBean);
        Parent root = loader.load();

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("PLANNER v0.1.21");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/cz/vse/planner/icons/diary_small_b.png")));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
