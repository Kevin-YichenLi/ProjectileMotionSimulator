package com.pms.project.controllers;

import com.pms.project.utils.Util;
import com.pms.project.views.MainView;
import com.pms.project.views.BaseSceneView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class AnimationController {
    private Stage primaryStage;
    private Util util;

    public AnimationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.util = new Util(primaryStage); // Pass Stage to Util
    }

    /**
     * Adjusts the global zoom level dynamically for the current scene.
     * @param factor The zoom factor. Values > 1 zoom in, values < 1 zoom out.
     */
    public void zoom(double factor) {
        util.zoom(factor);
    }

    /**
     * Rotates the current scene by a specified angle.
     * @param angle The angle to rotate the scene by, in degrees.
     */
    public void rotate( ) {
        util.rotate();
    }

 

    public void goToBaseScene() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to the base scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            BaseSceneView baseSceneView = new BaseSceneView(primaryStage);
            Scene scene = new Scene(baseSceneView, MainView.stageWidth, MainView.stageHeight);
            util.switchScene(primaryStage, scene);
        }
    }
}
