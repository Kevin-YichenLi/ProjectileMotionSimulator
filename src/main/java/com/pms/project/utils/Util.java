package com.pms.project.utils;

import com.pms.project.views.MainView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Util {
    public void switchScene(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public void goBack(Stage primaryStage) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to the main scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            MainView mainView = new MainView(primaryStage);
            Scene scene = new Scene(mainView, MainView.stageWidth, MainView.stageHeight);
            switchScene(primaryStage, scene);
        }
    }
}
