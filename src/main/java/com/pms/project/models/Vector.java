package com.pms.project.models;

public class Vector extends BaseScene {
    private String sceneName;

    public Vector() {
        super(); // Calls the constructor of BaseScene
    }

    public Vector(String sceneName) {
        super(); // Calls the constructor of BaseScene
        this.sceneName = sceneName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    @Override
    public String toString() {
        return "AdvancedScene{" +
                "sceneName='" + sceneName + '\'' +
                ", " + super.toString() +
                '}';
    }
}
