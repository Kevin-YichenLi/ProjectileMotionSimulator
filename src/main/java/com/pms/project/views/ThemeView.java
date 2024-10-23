package com.pms.project.views;

import com.pms.project.controllers.ThemeController;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThemeView extends GridPane {
	
	 public ThemeView() {
	        // Set vertical and horizontal gaps for the grid
	        this.setVgap(10);
	        this.setHgap(10);

	        // Title labels
	        Label backgroundLabel = new Label("Change Background");
	        Label fontLabel = new Label("Change Font");
	        Label fontSizeLabel = new Label("Change Font Size");

	        // Add labels to the GridPane
	        this.add(backgroundLabel, 0, 0);
	        this.add(fontLabel, 0, 4);
	        this.add(fontSizeLabel, 0, 8);

	        // Create checkboxes for Background
	        CheckBox lightBlue = new CheckBox("Light blue");
	        CheckBox darkBlue = new CheckBox("Dark blue");
	        CheckBox sunset = new CheckBox("Sunset");

	        // Add Background checkboxes to the GridPane
	        this.add(lightBlue, 1, 1);
	        this.add(darkBlue, 1, 2);
	        this.add(sunset, 1, 3);

	        // Create checkboxes for Font
	        CheckBox times = new CheckBox("Times");
	        CheckBox calibri = new CheckBox("Calibri");
	        CheckBox comicSans = new CheckBox("Comic Sans");

	        // Add Font checkboxes to the GridPane
	        this.add(times, 1, 5);
	        this.add(calibri, 1, 6);
	        this.add(comicSans, 1, 7);

	        // Create checkboxes for Font Size
	        CheckBox size10 = new CheckBox("10");
	        CheckBox size12 = new CheckBox("12");
	        CheckBox size14 = new CheckBox("14");

	        // Add Font Size checkboxes to the GridPane
	        this.add(size10, 1, 9);
	        this.add(size12, 1, 10);
	        this.add(size14, 1, 11);

	        // Logic to allow only one checkbox selection for Background
	        lightBlue.setOnAction(event -> {
	            if (lightBlue.isSelected()) {
	                darkBlue.setSelected(false);
	                sunset.setSelected(false);
	            }
	        });

	        darkBlue.setOnAction(event -> {
	            if (darkBlue.isSelected()) {
	                lightBlue.setSelected(false);
	                sunset.setSelected(false);
	            }
	        });

	        sunset.setOnAction(event -> {
	            if (sunset.isSelected()) {
	                lightBlue.setSelected(false);
	                darkBlue.setSelected(false);
	            }
	        });

	        // Logic to allow only one checkbox selection for Font
	        times.setOnAction(event -> {
	            if (times.isSelected()) {
	                calibri.setSelected(false);
	                comicSans.setSelected(false);
	            }
	        });

	        calibri.setOnAction(event -> {
	            if (calibri.isSelected()) {
	                times.setSelected(false);
	                comicSans.setSelected(false);
	            }
	        });

	        comicSans.setOnAction(event -> {
	            if (comicSans.isSelected()) {
	                times.setSelected(false);
	                calibri.setSelected(false);
	            }
	        });

	        // Logic to allow only one checkbox selection for Font Size
	        size10.setOnAction(event -> {
	            if (size10.isSelected()) {
	                size12.setSelected(false);
	                size14.setSelected(false);
	            }
	        });

	        size12.setOnAction(event -> {
	            if (size12.isSelected()) {
	                size10.setSelected(false);
	                size14.setSelected(false);
	            }
	        });

	        size14.setOnAction(event -> {
	            if (size14.isSelected()) {
	                size10.setSelected(false);
	                size12.setSelected(false);
	            }
	        });
	    }}