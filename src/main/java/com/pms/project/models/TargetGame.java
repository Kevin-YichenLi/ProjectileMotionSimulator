package com.pms.project.models;

public class TargetGame extends BaseScene {
    private double targetX;  // X coordinate of the target
    private double targetY;  // Y coordinate of the target (constant)
    private double targetRadius = 20;  // Target's radius
    private boolean isHit;  // Boolean to check if the projectile hits the target

    public TargetGame() {
        super(); // Calls the constructor of BaseScene
        this.targetX = Math.random() * 1200;  // Randomize X position of target
        this.targetY = 580;  // Fixed Y position (horizontal)
        this.isHit = false;  // Initially not hit
    }

    // Getter and Setter for targetX and targetY
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

    // Method to directly check if the projectile hit the target
    public boolean checkHit(double projectileX, double projectileY) {
        // Debugging: Print the target's center position
        System.out.println("Target Position: (" + targetX + ", " + targetY + ")");
        
        double distance = Math.sqrt(Math.pow(projectileX - targetX, 2) + Math.pow(projectileY - targetY, 2));
        isHit = distance <= (targetRadius + 5);  // Assuming projectile radius is 5
        return isHit;
    }

}
