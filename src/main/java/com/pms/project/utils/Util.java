package com.pms.project.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

import com.pms.project.views.BaseSceneView;
import com.pms.project.views.MainView;
import com.pms.project.views.TargetGameView;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Util {
    private Scale globalScaleTransform = new Scale(1, 1);
    private Stage primaryStage;
	private String selectedFont;
	private String selectedBackground;
	 private boolean isRotated = false;
	 
    public Util(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    
    public void zoom(double factor) {
        globalScaleTransform.setX(globalScaleTransform.getX() * factor);
        globalScaleTransform.setY(globalScaleTransform.getY() * factor);

        Scene currentScene = primaryStage.getScene();
        if (currentScene != null) {
            Pane root = (Pane) currentScene.getRoot();
            if (!root.getTransforms().contains(globalScaleTransform)) {
                root.getTransforms().add(globalScaleTransform);
            }
        }
    }

   
    public void rotate() {
        Scene currentScene = primaryStage.getScene();
        if (currentScene == null) return;
        if (isRotated) {
            currentScene.getWindow().setWidth(1800);
            currentScene.getWindow().setHeight(1038);
            isRotated = false; 
        } else {
         
            currentScene.getWindow().setWidth(1468);
            currentScene.getWindow().setHeight(1038);
            isRotated = true;
        }
    
    }

   
   


   
    public void switchScene(Stage primaryStage, Scene scene) {
        Pane root = (Pane) scene.getRoot();

        if (!root.getTransforms().contains(globalScaleTransform)) {
            root.getTransforms().add(globalScaleTransform);
        }

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
    
   

    public void goToBaseScene(Stage primaryStage) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to  the base scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            BaseSceneView baseSceneView = new BaseSceneView(primaryStage);
            Scene scene = new Scene(baseSceneView, MainView.stageWidth, MainView.stageHeight);

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
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to  the target scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            TargetGameView targetGameView = new TargetGameView(primaryStage);
            Scene scene = new Scene(targetGameView, MainView.stageWidth, MainView.stageHeight);

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