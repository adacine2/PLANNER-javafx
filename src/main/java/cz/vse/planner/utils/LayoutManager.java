package cz.vse.planner.utils;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LayoutManager {
    /**
     * Changes the image of the button on hover
     * When the image is "pressed" then it won't change on hover
     * @param button
     * @param imageName
     */
    public void changeButtonIconOnHover(Button button, String imageName) {
        ImageView imageView = (ImageView)button.getGraphic();
        Image inactiveImage = createImage(imageName + "_b.png", imageView.getBoundsInParent().getWidth(), imageView.getBoundsInParent().getHeight());
        Image activeImage = createImage(imageName + "_w.png", imageView.getBoundsInParent().getWidth(), imageView.getBoundsInParent().getHeight());

        if (!button.getStyleClass().contains("pressed") && button.isVisible()) {
            button.setOnMouseEntered(e -> imageView.setImage(activeImage));
            button.setOnMouseExited(e -> imageView.setImage(inactiveImage));
        }
    }
    /**
     * Creates an image with the specified name and dimensions
     * @param imageName
     * @param width
     * @param height
     * @return
     */
    private Image createImage(String imageName, double width, double height) {
        return new Image(getClass().getResourceAsStream("/cz/vse/planner/icons/" + imageName), width, height, true, false);
    }
    /**
     * Updates the visibility of buttons based on the logged-in status.
     * @param showButtons A list of buttons to show.
     * @param hideButtons A list of buttons to hide.
     */
    public void updateMenuButtonVisibility(List<Button> showButtons, List<Button> hideButtons) {
        for (Button button : showButtons) {
            if (!button.isVisible()) {
                int buttonIndex = button.getParent().getChildrenUnmodifiable().indexOf(button);
                ((HBox) button.getParent()).getChildren().add(buttonIndex, button);
                button.setVisible(true);
            }
        }
        for (Button button : hideButtons) {
            if (button.isVisible()) {
                ((HBox) button.getParent()).getChildren().remove(button);
                button.setVisible(false);
            }
        }
    }

}
