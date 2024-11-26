package com.pms.project.views;

import com.pms.project.controllers.AnimationController;
import com.pms.project.utils.Util;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AnimationView extends Pane {
    
    private Stage primaryStage;
  
    private AnimationController animationController;
    private Util util;

    public AnimationView(Stage primaryStage,Util util) {
        this.primaryStage = primaryStage;
        this.util = util;
        animationController = new AnimationController(primaryStage);

        // Create Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> util.goBack(primaryStage));

        // Create Confirm Button
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> animationController.goToBaseScene());

        // Create Zoom In Button
        Button zoomInButton = new Button("Zoom In");
        zoomInButton.setOnAction(event -> animationController.zoom(1.1));

        // Create Zoom Out Button
        Button zoomOutButton = new Button("Zoom Out");
        zoomOutButton.setOnAction(event -> animationController.zoom(0.9));

        // Create Rotate Button
        Button rotateButton = new Button("Rotate");
        rotateButton.setOnAction(event -> animationController.rotate());
        
        Button targetButton =new Button("Target");
        targetButton.setOnAction(e-> animationController.goToTargetScene());

       
        backButton.setLayoutX(20);
        backButton.setLayoutY(900);

        confirmButton.setLayoutX(1700);
        confirmButton.setLayoutY(900);
        
        targetButton.setLayoutX(1700);
        targetButton.setLayoutY(950);

        zoomInButton.setLayoutX(0);
        zoomInButton.setLayoutY(0);

        zoomOutButton.setLayoutX(70);
        zoomOutButton.setLayoutY(0);

        rotateButton.setLayoutX(150);
        rotateButton.setLayoutY(0);

        this.getChildren().addAll(backButton,confirmButton,zoomInButton,zoomOutButton,rotateButton,targetButton);
    }
}
