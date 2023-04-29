package cz.vse.planner.utils;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChangeTheImage {

    /**
     * Changes the image of the button on hover
     * When the image is "pressed" then it won't change on hover
     * @param button
     * @param imageName
     */
    public void changeButtonImageOnHover(Button button, String imageName) {
        ImageView imageView = (ImageView) button.getGraphic();
        Image inactiveImage = createImage(imageName + "_b.png", imageView.getBoundsInParent().getWidth(), imageView.getBoundsInParent().getHeight());
        Image activeImage = createImage(imageName + "_w.png", imageView.getBoundsInParent().getWidth(), imageView.getBoundsInParent().getHeight());

        if(!button.getStyleClass().contains("pressed")) {
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
}
