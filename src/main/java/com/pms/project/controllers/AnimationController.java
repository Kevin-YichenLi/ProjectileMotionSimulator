package com.pms.project.controllers;

import com.pms.project.utils.Util;
import com.pms.project.views.MainView;
import com.pms.project.views.TargetGameView;
import com.pms.project.views.BaseSceneView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.Optional;

public class AnimationController {
    private Stage primaryStage;
    private Util util;
    private Scale globalScaleTransform = new Scale(1, 1);

    public AnimationController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.util = new Util(primaryStage);
    }

   
    public void zoom(double factor) {
        util.zoom(factor);
    }

    
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
    public void goToTargetScene() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to the base scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
        	TargetGameView targetGameView = new TargetGameView(primaryStage);
            Scene scene = new Scene(targetGameView, MainView.stageWidth, MainView.stageHeight);
            util.switchScene(primaryStage, scene);
        }
    }
}
