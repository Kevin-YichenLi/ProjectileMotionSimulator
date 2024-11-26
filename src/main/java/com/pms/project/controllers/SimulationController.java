package com.pms.project.controllers;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class SimulationController {
    private double mouseX; // the initial x position of cursor when the mouse click the detector relative to the detector
    private double mouseY; // the initial y position of cursor when the mouse click the detector relative to the detector

    public void enableDetectorDragAndDrop(Group group, double initialX, double initialY) {
        // Mouse pressed: calculate the initial offset
        group.setOnMousePressed(event -> {
            mouseX = event.getSceneX() - group.getLayoutX();
            mouseY = event.getSceneY() - group.getLayoutY();
        });

        // Mouse dragged: update the position while maintaining the offset
        group.setOnMouseDragged(event -> {
            group.setLayoutX(event.getSceneX() - mouseX);
            group.setLayoutY(event.getSceneY() - mouseY);
        });

        group.setOnMouseReleased(event -> {
            group.setLayoutX(initialX);
            group.setLayoutY(initialY);
        });
    }
}
