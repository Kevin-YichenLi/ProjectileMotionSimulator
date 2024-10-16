package com.pms.project;

import com.pms.project.views.MainView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new MainView(primaryStage), 1800, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
