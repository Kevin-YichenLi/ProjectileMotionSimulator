package com.pms.project.models;

public class TargetGame extends BaseScene {
    private double targetX;  
    private double targetY;  
    private double targetRadius = 20;  
    private boolean isHit;  

    public TargetGame() {
        super(); 
        this.targetX = Math.random() * 1200;  
        this.targetY = 580;  
        this.isHit = false; 
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

    // Method to directly check if the projectile hit the target
    public boolean checkHit(double projectileX, double projectileY) {
        // Debugging: Print the target's center position
        System.out.println("Target Position: (" + targetX + ", " + targetY + ")");
        
        double distance = Math.sqrt(Math.pow(projectileX - targetX, 2) + Math.pow(projectileY - targetY, 2));
        isHit = distance <= (targetRadius + 5); 
        return isHit;
    }

}
