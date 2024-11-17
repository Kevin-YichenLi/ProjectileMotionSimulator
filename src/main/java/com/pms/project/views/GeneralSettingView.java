package com.pms.project.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GeneralSettingView extends VBox {
    private Stage primaryStage;
    public GeneralSettingView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.getChildren().addAll(createResourcesPathComponent(), createLanguageComponent(), createButtons()[0], createButtons()[1]);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(50);
    }

    private Region createResourcesPathComponent() {
        Label label = new Label("Resources Path: ");
        TextField pathTxt = new TextField();
        Label example = new Label("Example: /resources/images/");

        HBox container = new HBox(20, label, pathTxt, example);
        container.setAlignment(Pos.TOP_CENTER);
        return container;
    }

    private Region createLanguageComponent() {
        Label languageLbl = new Label("Language:");

        ComboBox<String> languageComboBox = new ComboBox<>();
        languageComboBox.setVisibleRowCount(2);
        languageComboBox.getItems().addAll("English", "French");

        HBox container = new HBox(20, languageLbl, languageComboBox);
        container.setAlignment(Pos.CENTER);
        return container;
    }

    private Button[] createButtons() {
        Button saveButton = new Button("Save");

        Button backButton = new Button("Back");
        Button[] buttons = {saveButton, backButton};
        return buttons;
    }
}
