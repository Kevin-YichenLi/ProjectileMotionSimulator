package com.pms.project.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaseSceneController {
    public void switchScene(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

}
