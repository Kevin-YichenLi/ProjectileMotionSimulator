package com.pms.project.controllers;

import java.util.Optional;

import com.pms.project.views.BaseSceneView;
import com.pms.project.views.MainView;
import com.pms.project.views.ThemeView;
import com.pms.project.utils.Util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

public class ThemeController {
    private BaseSceneView baseSceneView;
    private ThemeView themeView;


    public ThemeController(BaseSceneView baseSceneView, ThemeView themeView) {
        this.baseSceneView = baseSceneView;
        this.themeView = themeView;

        // Initialize listeners for font checkboxes
        initializeFontCheckboxListeners();
    }

    private void initializeFontCheckboxListeners() {
        CheckBox times = (CheckBox) themeView.lookup("#times");
        CheckBox calibri = (CheckBox) themeView.lookup("#calibri");
        CheckBox comicSans = (CheckBox) themeView.lookup("#comicSans");

        // Add listeners to the font checkboxes
        times.setOnAction(event -> updateFont("Times New Roman", times.isSelected()));
        calibri.setOnAction(event -> updateFont("Calibri", calibri.isSelected()));
        comicSans.setOnAction(event -> updateFont("Comic Sans MS", comicSans.isSelected()));
    }

    private void updateFont(String font, boolean isSelected) {
        if (isSelected) {
            // Clear other selections
            if (font.equals("Times New Roman")) {
                ((CheckBox) themeView.lookup("#calibri")).setSelected(false);
                ((CheckBox) themeView.lookup("#comicSans")).setSelected(false);
            } else if (font.equals("Calibri")) {
                ((CheckBox) themeView.lookup("#times")).setSelected(false);
                ((CheckBox) themeView.lookup("#comicSans")).setSelected(false);
            } else if (font.equals("Comic Sans MS")) {
                ((CheckBox) themeView.lookup("#times")).setSelected(false);
                ((CheckBox) themeView.lookup("#calibri")).setSelected(false);
            }

            // Apply the selected font to all labels in BaseSceneView
            for (Label label : baseSceneView.getLabels()) {
                label.setStyle("-fx-font-family: '" + font + "';");
            }
        }
    }
}


    

