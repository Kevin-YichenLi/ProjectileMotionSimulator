package com.pms.project.controllers;

import com.pms.project.MainApp;
import com.pms.project.views.BaseSceneView;
import com.pms.project.views.MainView;
import com.sun.tools.javac.Main;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
    private BaseSceneController baseSceneController = new BaseSceneController();
    private Stage primaryStage;
    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onTargetGameButtonPressed() {

    }

    // temporary method for testing, please delete at the end
    public void onTestButtonPressed() {
        Scene scene = new Scene(new BaseSceneView(primaryStage), MainView.stageWidth, MainView.stageHeight);
        baseSceneController.switchScene(primaryStage, scene);
    }
}
