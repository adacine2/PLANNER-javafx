package cz.vse.planner.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController {
    @FXML
    private Button buttonHOME;
    @FXML
    private Button buttonLOGIN;
    @FXML
    private Button buttonEVENTS;
    @FXML
    private Button buttonNEWS;
    @FXML
    private Button buttonADMIN;
    @FXML
    private ImageView iconHOME;
    @FXML
    private ImageView iconADMIN;


    /**
     * --- STORAGE ---
     * PREVIOUS STYLES AND ICONS
     */
    private Button previousButton;
    private ImageView previousImageView;
    private String previousIconPath;


    public void initialize() {
        initializeButton(buttonHOME, iconHOME, "/cz/vse/planner/icons/home_w.png", "/cz/vse/planner/icons/home_b.png");
        initializeButton(buttonLOGIN, null, null, null);
        initializeButton(buttonEVENTS, null, null, null);
        initializeButton(buttonNEWS, null, null, null);
        initializeButton(buttonADMIN, iconADMIN, "/cz/vse/planner/icons/admin_gear_w.png", "/cz/vse/planner/icons/admin_gear_b.png");
    }
    private void initializeButton(Button button, ImageView imageView, String iconPath, String originalIconPath) {
        button.setOnAction(event -> {
            if (previousButton != null && previousButton != button) {
                previousButton.getStyleClass().remove("pressed");

                if (previousImageView != null && previousIconPath != null) {
                    Image originalImage = new Image(getClass().getResourceAsStream(previousIconPath));
                    previousImageView.setImage(originalImage);
                }
            }

            if (previousButton != button) {
                if (imageView != null && iconPath != null) {
                    Image newImage = new Image(getClass().getResourceAsStream(iconPath));
                    imageView.setImage(newImage);
                }

                button.getStyleClass().add("pressed");
                previousButton = button;
                previousImageView = imageView;
                previousIconPath = originalIconPath;
            }
        });
    }


}
