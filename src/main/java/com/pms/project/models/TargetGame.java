package com.pms.project.models;

import com.pms.project.controllers.BaseSceneController;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TargetGame {
    double initialVelocity;
    double initialAngle;
    double time;
    double initialHeight;
    double gravity;
    double distance;
    double changeInHeight;
    double initialX;
    double initialY;
    public double finalX;
    double finalY;
    double mass;
    double maxHeight;
    double initialXVelocity;
    double initialYVelocity;
    double finalXVelocity;
    double finalYVelocity;
    private Circle target= new Circle(20, Color.web("#F4CCCC"));
    private double targetX;  
    private double targetY;  
    private double targetRadius = 20;
    private boolean isHit;

 	public TargetGame() {
 	    gravity = 9.8; // default gravity
        initialVelocity = 20; // default velocity
        initialX = 8; // default initial x position
        this.targetX = Math.random() * 1200 + 50;
        this.targetY = 580;  
        this.isHit = false; 
       
        target.setStroke(Color.web("#b45454"));
      
    }

    public double getFinalXVelocity() {
        return finalXVelocity;
    }

    public void setFinalXVelocity(double finalXVelocity) {
        this.finalXVelocity = finalXVelocity;
    }

    public double getFinalYVelocity() {
        return finalYVelocity;
    }

    public void setFinalYVelocity(double finalYVelocity) {
        this.finalYVelocity = finalYVelocity;
    }

    

    public double getInitialXVelocity() {
        return initialXVelocity;
    }

    public void setInitialXVelocity(double initialXVelocity) {
        this.initialXVelocity = initialXVelocity;
    }

    public double getInitialYVelocity() {
        return initialYVelocity;
    }

    public void setInitialYVelocity(double initialYVelocity) {
        this.initialYVelocity = initialYVelocity;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getInitialVelocity() {
        return initialVelocity;
    }

    public void setInitialVelocity(double initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public double getInitialAngle() {
        return initialAngle;
    }

    public void setInitialAngle(double initialAngle) {
        this.initialAngle = initialAngle;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getInitialHeight() {
        return initialHeight;
    }

    public double getFinalY() {
        return finalY;
    }

    public void setFinalY(double finalY) {
        this.finalY = finalY;
    }

    public double getFinalX() {
        return finalX;
    }

    public void setFinalX(double finalX) {
        this.finalX = finalX;
    }

    public double getInitialY() {
        return initialY;
    }

    public void setInitialY(double initialY) {
        this.initialY = initialY;
    }

    public void setInitialHeight(double initialHeight) {
        this.initialHeight = initialHeight;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getChangeInHeight() {
        return changeInHeight;
    }

    public void setChangeInHeight(double changeInHeight) {
        this.changeInHeight = changeInHeight;
    }

    public double getInitialX() {
        return initialX;
    }

    public void setInitialX(double initialX) {
        this.initialX = initialX;
    }

    public double getTargetX() {
        return targetX;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public double getTargetRadius() {
        return targetRadius;
    }

    public void setTargetRadius(double targetRadius) {
        this.targetRadius = targetRadius;
    }
   
    

    public boolean isHit() {
        return isHit;
    }
    public void setTarget(Circle target) {
    	this.target = target;
    }
    
    public Circle getTarget() {
        return target;
    }
    // Method to check if the projectile has hit the target based only on horizontal position
    public boolean checkHit() {
        // Check if the final X position of the projectile is within the range of the target's X position
        // Target's hit range is from targetX - targetRadius to targetX + targetRadius
        if (finalX >= targetX - targetRadius && finalX <= targetX + targetRadius) {
            isHit = true;  // If within range, it's a hit
            return true;
        }
        isHit = false;  // If no hit, reset the hit status
        return false;
    }

    
    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    @Override
    public String toString() {
        return "TargetGame{" +
                "target=" + target +
                ", targetX=" + targetX +
                ", targetY=" + targetY +
                ", targetRadius=" + targetRadius +
                ", isHit=" + isHit +
                ", initialVelocity=" + initialVelocity +
                ", initialAngle=" + initialAngle +
                ", time=" + time +
                ", initialHeight=" + initialHeight +
                ", gravity=" + gravity +
                ", distance=" + distance +
                ", changeInHeight=" + changeInHeight +
                ", initialX=" + initialX +
                ", initialY=" + initialY +
                ", finalX=" + finalX +
                ", finalY=" + finalY +
                ", mass=" + mass +
                ", maxHeight=" + maxHeight +
                ", initialXVelocity=" + initialXVelocity +
                ", finalXVelocity=" + finalXVelocity +
                ", initialYVelocity=" + initialYVelocity +
                ", finalYVelocity=" + finalYVelocity +
                '}';
    }
}