package com.pms.project.controllers;

import com.pms.project.models.TargetGame;
import com.pms.project.views.TargetGameView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class TargetGameController extends BaseSceneController {
    private TargetGame targetGame;
    private TargetGameView targetGameView;

    public TargetGameController(TargetGame targetGame, TargetGameView targetGameView) {
        super(targetGameView.getPrimaryStage(), targetGame, 800, 600, new Circle[5]);
        this.targetGame = targetGame;
        this.targetGameView = targetGameView;

       
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
}
