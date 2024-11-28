package com.pms.project.controllers;

import com.pms.project.AnimationStatus;
import com.pms.project.models.BaseScene;
import com.pms.project.models.TargetGame;
import com.pms.project.utils.Util;
import com.pms.project.views.TargetGameView;
import com.pms.project.views.MainView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;

import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TargetGameController extends BaseSceneController {
	
    private  TargetGame targetGame = new TargetGame();

    public TargetGameController(Stage primaryStage, BaseScene baseScene, int animationPaneWidth, int animationPaneHeight, Circle[] trails, SimpleObjectProperty<AnimationStatus> status) {
    	super(primaryStage, baseScene, animationPaneWidth, animationPaneHeight, trails, status);
       
        
    }
  

    public TargetGame getTargetGame() {
        return targetGame;
    }

    public void onNewTargetButtonPressed() {
        double newX = Math.random() * 1200;         
        targetGame.setTargetX(newX);  
        targetGame.getTarget().setCenterX(newX);  

        
       
    }

    @Override
    public void onStartButtonPressed() {
        if (status.get() == AnimationStatus.PLAYED) {
            return;
        }

        // Print out the current values (optional)
        System.out.println(baseScene.getInitialVelocity());
        System.out.println(baseScene.getMass());
        System.out.println(baseScene.getInitialAngle());
        System.out.println("Target X Position: " + targetGame.getTargetX());

        // Set the animation status to "played"
        status.set(AnimationStatus.PLAYED);
        calculateAndSetPhysicalProperties();

        if (hasMotion) {
            // Create and play animation if there's motion
            System.out.println("has motion");
            System.out.println(baseScene);
            createAnimation();
            timeline.play();
        } else {
            // Tell the user there's no motion
            System.out.println("no motion");
        }

    
        if (targetGame.checkHit()) {
      
            System.out.println("Congratulations! You've hit the target!");
        } else {
          
            System.out.println("Try again. The target wasn't hit.");
        }
    }

    @Override
    public void onRefreshButtonPressed() {
        TargetGameView newTargetGameView = new TargetGameView(primaryStage);
        Scene targetScene = new Scene(newTargetGameView, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, targetScene);
    }

    
}
