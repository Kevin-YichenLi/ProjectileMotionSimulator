package com.pms.project.controllers;

import com.pms.project.models.TargetGame;
import com.pms.project.utils.Util;
import com.pms.project.views.TargetGameView;
import com.pms.project.views.MainView;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class TargetGameController extends BaseSceneController {
    private TargetGame targetGame;
    private TargetGameView targetGameView;
    private Util util;

    public TargetGameController(TargetGame targetGame, TargetGameView targetGameView) {
        super(targetGameView.getPrimaryStage(), targetGame, 800, 600, new Circle[5]);
        this.targetGame = targetGame;
        this.targetGameView = targetGameView;
        this.util = util;

        // Set up event listener for the projectile
        targetGameView.getProjectile().setOnMouseClicked(this::onProjectileShot);
    }

    public TargetGame getTargetGame() {
        return targetGame;
    }

    public void onNewTargetButtonPressed() {
        double newX = Math.random() * 1200;
        targetGame.setTargetX(newX);
        targetGameView.getTarget().setCenterX(newX);
    }

    public void onProjectileShot(MouseEvent event) {
        double projectileX = targetGameView.getProjectile().getCenterX();
        double projectileY = targetGameView.getProjectile().getCenterY();
        boolean hit = targetGame.checkHit(projectileX, projectileY);

        if (hit) {
            System.out.println("Congratulations! You've hit the target.");
        } else {
            System.out.println("Try again!");
        }
    }

    // Override the refresh method here to use the TargetGameController's logic
    @Override
    public void onRefreshButtonPressed() {
        // Create a new TargetGameView (this is where the target scene is created)
        TargetGameView newTargetGameView = new TargetGameView(primaryStage);

        // Create the scene with the new TargetGameView
        Scene targetScene = new Scene(newTargetGameView, MainView.stageWidth, MainView.stageHeight);

        // Switch to the new target scene
        util.switchScene(primaryStage, targetScene);
        System.out.println("New target scene created and switched.");
    }
}
