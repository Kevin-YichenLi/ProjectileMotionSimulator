package com.pms.project.views;

import com.pms.project.controllers.AnimationController;
import com.pms.project.models.GeneralSetting;
import com.pms.project.utils.Util;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AnimationView extends GridPane {
    
    private Stage primaryStage;
  
    private AnimationController animationController;
    private Util util;

    public AnimationView(Stage primaryStage,Util util) {
        this.primaryStage = primaryStage;
        this.util = util;
        animationController = new AnimationController(primaryStage);

        // Create Back Button
        Button backButton = new Button(GeneralSetting.getString("button.back"));
        backButton.setOnAction(event -> util.goBack(primaryStage));

        // Create Confirm Button
        Button simulationButton = new Button(GeneralSetting.getString("button.simulation"));
        simulationButton.setOnAction(event -> animationController.goToSimulation());

        // Create Zoom In Button
        Button zoomInButton = new Button(GeneralSetting.getString("button.zoomIn"));
        zoomInButton.setOnAction(event -> animationController.zoom(1.1));

        // Create Zoom Out Button
        Button zoomOutButton = new Button(GeneralSetting.getString("button.zoomOut"));
        zoomOutButton.setOnAction(event -> animationController.zoom(0.9));

        // Create Rotate Button
        Button rotateButton = new Button(GeneralSetting.getString("button.changeTheWidth"));
        rotateButton.setOnAction(event -> animationController.rotate());
        
        Button targetButton =new Button(GeneralSetting.getString("button.targetGame"));
        targetButton.setOnAction(e-> animationController.goToTargetScene());
        
        Button vectorButton = new Button (GeneralSetting.getString("button.vector"));
        vectorButton.setOnAction(e -> animationController.goToVector());
              
        this.setVgap(10);
        this.setHgap(10);
        
       this.add(zoomInButton, 0, 0);
       this.add(zoomOutButton, 1, 0);
       this.add(rotateButton, 2, 0);
       
       this.add(backButton, 0, 4);
       
       this.add(targetButton, 2, 4);
       this.add(simulationButton, 2, 5);
       this.add(vectorButton, 2, 6);
    }
}
