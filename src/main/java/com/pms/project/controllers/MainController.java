package com.pms.project.controllers;

import com.pms.project.MainApp;
import com.pms.project.utils.Util;
import com.pms.project.views.BaseSceneView;
import com.pms.project.views.MainView;
import com.pms.project.views.ThemeView;
import com.sun.tools.javac.Main;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {
    private Util util = new Util();
    private Stage primaryStage;
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
    
    public void onThemeButtonPressed() {
    	ThemeView themeView = new ThemeView();
    	Scene scene = new Scene(themeView,MainView.stageWidth,MainView.stageHeight);
    	util.switchScene(primaryStage, scene);
    	
    }
}
