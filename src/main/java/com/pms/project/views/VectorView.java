package com.pms.project.views;

import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class VectorView extends BaseSceneView {
    
    private String customProperty;

    public VectorView(Stage primaryStage) {
        super(primaryStage); // Call the parent constructor
    }

    public VectorView(Stage primaryStage, String customProperty) {
        super(primaryStage); // Call the parent constructor
        this.customProperty = customProperty;
    }

    public String getCustomProperty() {
        return customProperty;
    }

    public void setCustomProperty(String customProperty) {
        this.customProperty = customProperty;
    }

    @Override
    protected Region createInitialHeightComponent() {
        // Extend or customize the behavior of an existing method
        Region baseComponent = super.createInitialHeightComponent();
        // Add custom logic if needed
        return baseComponent;
    }

    @Override
    protected Region createBottom() {
        // Customize the bottom component
        Region baseBottom = super.createBottom();
     
        return baseBottom;
    }

    @Override
    public String toString() {
        return "AdvancedSceneView{" +
                "customProperty='" + customProperty + '\'' +
                ", " + super.toString() +
                '}';
    }
}
