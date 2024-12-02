package com.pms.project.controllers;

import com.pms.project.models.GeneralSetting;
import com.pms.project.utils.Util;
import com.pms.project.views.MainView;
import com.pms.project.views.SimulationView;
import com.pms.project.views.TargetGameView;
import com.pms.project.views.VectorView;
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

 

    public void goToSimulation() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(GeneralSetting.getString("label.confirmationTitle"));
        confirmationAlert.setContentText(GeneralSetting.getString("text.confirmation.toSimulation"));
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            SimulationView simulationView = new SimulationView(primaryStage);
            Scene scene = new Scene(simulationView, MainView.stageWidth, MainView.stageHeight);
            util.switchScene(primaryStage, scene);
        }
    }
    public void goToTargetScene() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(GeneralSetting.getString("label.confirmationTitle"));
        confirmationAlert.setContentText(GeneralSetting.getString("text.confirmation.toTargetGame"));
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
        	TargetGameView targetGameView = new TargetGameView(primaryStage);
            Scene scene = new Scene(targetGameView, MainView.stageWidth, MainView.stageHeight);
            util.switchScene(primaryStage, scene);
        }
    }
    
    public void goToVector() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(GeneralSetting.getString("label.confirmationTitle"));
        confirmationAlert.setContentText(GeneralSetting.getString("text.confirmation.toVector"));
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
        	VectorView vectorView = new VectorView(primaryStage);
            Scene scene = new Scene(vectorView, MainView.stageWidth, MainView.stageHeight);
            util.switchScene(primaryStage, scene);
        }
    }
    
    
}
