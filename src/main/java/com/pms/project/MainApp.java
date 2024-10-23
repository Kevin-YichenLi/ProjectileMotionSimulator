package com.pms.project;

import com.pms.project.views.MainView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Locale;

public class MainApp extends Application {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new MainView(primaryStage), MainView.stageWidth, MainView.stageHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Projectile Motion Simulator");
        primaryStage.show();
    }
}
