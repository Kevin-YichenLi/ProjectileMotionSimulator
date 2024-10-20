package com.pms.project.utils;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Util {
    public void switchScene(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
