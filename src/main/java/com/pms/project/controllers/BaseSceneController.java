package com.pms.project.controllers;

import com.pms.project.models.BaseScene;
import com.pms.project.utils.Util;
import com.pms.project.views.MainView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class BaseSceneController {
    private BaseScene baseScene;
    private Util util = new Util();
    private Stage primaryStage;
    public BaseSceneController(Stage primaryStage, BaseScene baseScene) {
        this.primaryStage = primaryStage;
        this.baseScene = baseScene;
    }
    public void onBackToMainPressed() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to main scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            util.switchScene(primaryStage, new Scene(new MainView(primaryStage), MainView.stageWidth, MainView.stageHeight));
        }
    }

    public void updateMass(double newValue) {
        baseScene.setMass(newValue);
    }

    public void updateInitialAngle(double newValue) {
        baseScene.setInitialAngle(newValue);
    }

    public void updateInitialHeight(double newValue) {
        baseScene.setInitialHeight(newValue);
    }

    public void updateInitialSpeed(double newValue) {
        baseScene.setInitialVelocity(newValue);
    }

    public void onEarthButtonPressed() {
        baseScene.setGravity(9.8);
    }

    public void onMoonButtonPressed() {
        baseScene.setGravity(1.62);
    }

    public void onMarsButtonPressed() {
        baseScene.setGravity(3.73);
    }

    public void onJupiterButtonPressed() {
        baseScene.setGravity(24.79);
    }
}
