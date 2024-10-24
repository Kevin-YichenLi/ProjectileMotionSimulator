package com.pms.project.controllers;

import com.pms.project.MainApp;
import com.pms.project.utils.Util;
import com.pms.project.views.BaseSceneView;
import com.pms.project.views.MainView;
import com.pms.project.views.ThemeView;
import com.sun.tools.javac.Main;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {
    private Util util = new Util();
    private Stage primaryStage;
    private ThemeController themeController;
    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onTargetGameButtonPressed() {

    }

    // temporary method for testing, please delete at the end
    public void onTestButtonPressed() {
        Scene scene = new Scene(new BaseSceneView(primaryStage), MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }
    
    
    public void onQuitButtonPressed() {
    	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Exit Application");
        confirmationAlert.setContentText("Are you sure you want to quit?");

        // Show the dialog and wait for the user's response
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Close the application
                primaryStage.close();
            }
            // No action needed for ButtonType.CANCEL, just let the dialog close
        });
    }
    
    
    
    public void onThemeButtonPressed() {
    	ThemeView themeView = new ThemeView(primaryStage);
    	Scene scene = new Scene(themeView,MainView.stageWidth,MainView.stageHeight);
    	util.switchScene(primaryStage, scene);
    	
    }
}
