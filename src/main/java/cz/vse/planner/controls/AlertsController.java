package cz.vse.planner.controls;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlertsController {

    public static void showErrorAlert(String title,String header, String content, String button, String iconAddress) {
        //ALERT SETUP
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle(title);
        alertError.setHeaderText(header);
        alertError.setContentText(content);
        alertError.showAndWait();
        ((Button) alertError.getDialogPane().lookupButton(ButtonType.OK)).setText(button);
        alertError.getDialogPane().getStylesheets().add(AlertsController.class.getResource("/cz/vse/planner/styles/Style_Dark.css").toExternalForm());
        alertError.getDialogPane().getStyleClass().add("alert-error");
        //ICON SETUP
        ImageView alertErrorIcon = new ImageView(new Image(AlertsController.class.getResourceAsStream(iconAddress)));
        alertErrorIcon.setFitWidth(50);
        alertErrorIcon.setFitHeight(50);
        alertErrorIcon.setPreserveRatio(true);
        alertError.getDialogPane().setGraphic(alertErrorIcon);
    }
    public static void showEmailAlert(String title,String header, String content, String button, String iconAddress) {
        //ALERT SETUP
        Alert alertEmail = new Alert(Alert.AlertType.ERROR);
        alertEmail.setTitle(title);
        alertEmail.setHeaderText(header);
        alertEmail.setContentText(content);
        alertEmail.showAndWait();
        ((Button) alertEmail.getDialogPane().lookupButton(ButtonType.OK)).setText(button);
        alertEmail.getDialogPane().getStylesheets().add(AlertsController.class.getResource("/cz/vse/planner/styles/Style_Dark.css").toExternalForm());
        alertEmail.getDialogPane().getStyleClass().add("alert-success");
        //ICON SETUP
        ImageView alertEmailIcon = new ImageView(new Image(AlertsController.class.getResourceAsStream("/cz/vse/planner/icons/sent_email_w.png")));
        alertEmailIcon.setFitWidth(50);
        alertEmailIcon.setFitHeight(50);
        alertEmailIcon.setPreserveRatio(true);
        alertEmail.getDialogPane().setGraphic(alertEmailIcon);

    }
}