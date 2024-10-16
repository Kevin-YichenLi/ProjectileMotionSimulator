package com.pms.project.views;

import javafx.animation.Animation;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class BaseSceneView extends BorderPane {
    private Stage primaryStage;
    public BaseSceneView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    protected Region createContent() {
        return null;
    }
    protected Region createTop() {
        return null;
    }
    protected Animation animate() {
        return null;
    }

    protected Region createBottom() {
        return null;
    }

}
