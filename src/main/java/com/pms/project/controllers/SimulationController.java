package com.pms.project.controllers;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class SimulationController {
    private double mouseX; // the initial x position of cursor when the mouse click the detector
    private double mouseY; // the initial y position of cursor when the mouse click the detector

    public void onDetectorClicked(MouseEvent event, Group group) {
        mouseX = event.getSceneX() - group.getLayoutX();
        mouseY = event.getSceneY() - group.getLayoutY();
    }

    public void onDetectorDragged(MouseEvent event, Group group) {
        double deltaX = event.getSceneX() - mouseX;
        double deltaY = event.getSceneY() - mouseY;

        group.setLayoutX(deltaX);
        group.setLayoutY(deltaY);
    }
}
