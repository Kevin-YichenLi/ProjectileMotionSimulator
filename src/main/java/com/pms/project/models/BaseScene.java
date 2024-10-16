package com.pms.project.models;

public abstract class BaseScene {
    double initialVelocity;
    double initialAngle;
    double time;
    double initialHeight;
    double gravity;
    double distance;
    double changeInHeight;

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
}
