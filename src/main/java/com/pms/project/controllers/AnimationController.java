package com.pms.project.controllers;

import com.pms.project.utils.Util;
import com.pms.project.views.AnimationView;
import com.pms.project.views.MainView;
import com.pms.project.views.BaseSceneView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.Optional;

public class AnimationController {
    private Scale scaleTransform;
    private Rotate rotateTransform;
    private AnimationView animationView;
    private Util util = new Util();
    private Stage primaryStage;

    public AnimationController(AnimationView animationView, Stage primaryStage) {
        this.animationView = animationView;
        this.primaryStage = primaryStage;
        this.scaleTransform = new Scale(1, 1);
        this.rotateTransform = new Rotate(0);
    }

    public void zoom(double factor) {
        scaleTransform.setX(scaleTransform.getX() * factor);
        scaleTransform.setY(scaleTransform.getY() * factor);
        animationView.getTransforms().add(scaleTransform);
    }

    /*public void rotate(double angle) {
        rotateTransform.setAngle(rotateTransform.getAngle() + angle);
        animationView.getTransforms().add(rotateTransform);
    }*/

    public void goBack() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to the main scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            MainView mainView = new MainView(primaryStage);
            Scene scene = new Scene(mainView, MainView.stageWidth, MainView.stageHeight);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void goToBaseScene() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to the base scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            BaseSceneView baseSceneView = new BaseSceneView(primaryStage);
            Scene scene = new Scene(baseSceneView, MainView.stageWidth, MainView.stageHeight);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}

