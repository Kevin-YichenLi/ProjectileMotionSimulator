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
}
