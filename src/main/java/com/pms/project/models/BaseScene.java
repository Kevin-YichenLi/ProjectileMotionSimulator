package com.pms.project.models;

import com.pms.project.controllers.BaseSceneController;

public class BaseScene {
    double initialVelocity;
    double initialAngle;
    double time;
    double initialHeight;
    double gravity;
    double distance;
    double changeInHeight;
    double initialX;
    double initialY;
    double finalX;
    double finalY;
    double mass;
    double maxHeight;
    double initialXVelocity;
    double initialYVelocity;
    double finalXVelocity;
    double finalYVelocity;

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

    public BaseScene() {
        gravity = 9.8; // default gravity
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
}
