package com.pms.project.controllers;

import java.util.Optional;

import com.pms.project.models.GeneralSetting;
import com.pms.project.views.BaseSceneView;
import com.pms.project.views.MainView;
import com.pms.project.views.SimulationView;
import com.pms.project.views.TargetGameView;
import com.pms.project.views.VectorView;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ThemeController {
    private Stage primaryStage;
    private String selectedBackground = "default-background";
    private String selectedFont = "default-font";
   

    public ThemeController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Preview theme changes
    public void previewBackground(String backgroundClass) {
        this.selectedBackground = backgroundClass;
       
    }

    public void previewFont(String fontClass) {
        this.selectedFont = fontClass;
        
    }

    

    public void goToSimulationScene(Stage primaryStage) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(GeneralSetting.getString("label.confirmationTitle"));
        confirmationAlert.setContentText(GeneralSetting.getString("text.confirmation.toSimulation"));
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            SimulationView simulationView = new SimulationView(primaryStage);
            Scene scene = new Scene(simulationView, MainView.stageWidth, 1010);

            // Apply the selected font and background
            scene.getRoot().getStyleClass().clear();
            scene.getRoot().getStyleClass().add(selectedFont);
            scene.getRoot().getStyleClass().add(selectedBackground);

            // Apply the theme stylesheet
            scene.getStylesheets().add(getClass().getResource("/css/theme.css").toExternalForm());

            // Set the scene for the primaryStage
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    public void goToTargetScene(Stage primaryStage) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(GeneralSetting.getString("label.confirmationTitle"));
        confirmationAlert.setContentText(GeneralSetting.getString("text.confirmation.toTargetGame"));
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            TargetGameView targetGameView = new TargetGameView(primaryStage);
            Scene scene = new Scene(targetGameView, MainView.stageWidth, 1010);

            // Apply the selected font and background
            scene.getRoot().getStyleClass().clear();
            scene.getRoot().getStyleClass().add(selectedFont);
            scene.getRoot().getStyleClass().add(selectedBackground);

            // Apply the theme stylesheet
            scene.getStylesheets().add(getClass().getResource("/css/theme.css").toExternalForm());

            // Set the scene for the primaryStage
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    
    public void goToVectorScene(Stage primaryStage) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(GeneralSetting.getString("label.confirmationTitle"));
        confirmationAlert.setContentText(GeneralSetting.getString("text.confirmation.toVector"));
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            VectorView vectorView = new VectorView(primaryStage);
            Scene scene = new Scene(vectorView, MainView.stageWidth, 1010);

            // Apply the selected font and background
            scene.getRoot().getStyleClass().clear();
            scene.getRoot().getStyleClass().add(selectedFont);
            scene.getRoot().getStyleClass().add(selectedBackground);

            // Apply the theme stylesheet
            scene.getStylesheets().add(getClass().getResource("/css/theme.css").toExternalForm());

            // Set the scene for the primaryStage
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

   
}
