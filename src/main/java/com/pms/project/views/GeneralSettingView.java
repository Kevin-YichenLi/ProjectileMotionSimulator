package com.pms.project.views;

import com.pms.project.controllers.GeneralSettingController;
import com.pms.project.models.GeneralSetting;
import com.pms.project.utils.Util;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneralSettingView extends VBox {
    private Stage primaryStage;
    private GeneralSetting generalSetting = new GeneralSetting();
    private final GeneralSettingController generalSettingController = new GeneralSettingController(generalSetting, primaryStage);
    private Util util;

    public GeneralSettingView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        util = new Util(primaryStage);
        this.getChildren().addAll(createResourcesPathComponent(), createVolumeComponent(), createLanguageComponent(), createBackButton());
        this.setAlignment(Pos.CENTER);
        this.setSpacing(50);
    }

    private Region createResourcesPathComponent() {

        HBox container = new HBox(20);
        container.setAlignment(Pos.TOP_CENTER);
        return container;
    }

    private Region createVolumeComponent() {
        Label volumeLabel = new Label("Volume");

        Slider volumeSlider = new Slider(0, 100, 50); // Min=0, Max=100, Default=50
        volumeSlider.setBlockIncrement(5); // Make step increments bigger if needed

        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setMajorTickUnit(20);
        volumeSlider.setMinorTickCount(4);

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            generalSetting.setVolume(newValue.doubleValue());
        });

        HBox volumeBox = new HBox(10, volumeLabel, volumeSlider);
        volumeBox.setAlignment(Pos.CENTER);
        return volumeBox;
    }

    private Region createLanguageComponent() {
        Label languageLabel = new Label("Language");

        ComboBox<String> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll("English", "French");

        languageComboBox.setValue("English");

        // Handle language change
        languageComboBox.setOnAction(event -> {
            String selectedLanguage = languageComboBox.getValue();
            System.out.println("Selected Language: " + selectedLanguage);
        });

        HBox languageBox = new HBox(10, languageLabel, languageComboBox);
        languageBox.setAlignment(Pos.CENTER);
        return languageBox;
    }

    private Region createBackButton() {
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> util.goBack(primaryStage));

        return backButton;
    }
}
