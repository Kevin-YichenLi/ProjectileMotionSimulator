package com.pms.project.controllers;

import java.util.Optional;

import com.pms.project.MainApp;
import com.pms.project.models.GeneralSetting;
import com.pms.project.models.TargetGame;
import com.pms.project.utils.Util;
import com.pms.project.views.*;
import com.sun.tools.javac.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MainController {
    private Stage primaryStage;
    private Util util = new Util(primaryStage);
    private ThemeController themeController = new ThemeController(primaryStage);
    private ThemeView themeView = new ThemeView(primaryStage,util, themeController);

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onTargetGameButtonPressed() {
    	  TargetGameView targetGameView = new TargetGameView(primaryStage);
          Scene scene = new Scene(targetGameView, MainView.stageWidth, MainView.stageHeight);
          util.switchScene(primaryStage, scene);
    }
    
    public void onVectorButtonPressed() {
  	  VectorView vectorView = new VectorView(primaryStage);
        Scene scene = new Scene(vectorView, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, scene);
  }

    // temporary method for testing, please delete at the end
    public void onTestButtonPressed() {
        Scene scene = new Scene(new BaseSceneView(primaryStage), MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }
    
    public void onQuitButtonPressed() {
    	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle(GeneralSetting.getString("label.confirmationTitle"));
        confirmationAlert.setHeaderText(GeneralSetting.getString("text.confirmation.exitHeaderText"));
        confirmationAlert.setContentText(GeneralSetting.getString("text.confirmation.exit"));

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
               
                primaryStage.close();
            }
            
        });
    }

    public void onAboutUsPressed() {
        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(10)); 
        vbox1.setSpacing(10); 

       
        Text aboutUsText = new Text(GeneralSetting.getString("menuItem.aboutUs.content"));

        // Wrap the text within a TextFlow to handle line breaks automatically
        TextFlow textFlow = new TextFlow(aboutUsText);
        textFlow.setPrefWidth(MainView.stageWidth - 20); 

      
        Button backButton = new Button(GeneralSetting.getString("button.back"));
        backButton.setOnAction(e -> util.goBack(primaryStage));
        
        VBox.setMargin(backButton, new Insets(10, 0, 10, 0)); 

        
        HBox textBox = new HBox(textFlow);
        textBox.setAlignment(Pos.CENTER); 

        
        vbox1.getChildren().addAll(textBox, backButton);
        
        
        vbox1.setAlignment(Pos.BOTTOM_LEFT);

        
        VBox.setMargin(textBox, new Insets(0, 0, 800, 0)); 

        Scene scene = new Scene(vbox1, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }
    
    public void onHelpPressed() {
       
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10)); 
        vbox.setSpacing(10); 

        
        Image formulas = new Image(this.getClass().getResource("/images/formulas.png").toExternalForm());
        ImageView formulasImageView = new ImageView(formulas);

        
        HBox imageBox = new HBox(formulasImageView);
        imageBox.setAlignment(Pos.CENTER); 

        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> util.goBack(primaryStage));

       
        VBox.setMargin(backButton, new Insets(10, 0, 10, 0)); 
        vbox.getChildren().addAll(imageBox, backButton);
        
       
        vbox.setAlignment(Pos.BOTTOM_LEFT);

        
        Scene scene = new Scene(vbox, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }

    public void onThemeButtonPressed() {
    	ThemeView themeView = new ThemeView(primaryStage,util,themeController);
    	Scene scene = new Scene(themeView,MainView.stageWidth,MainView.stageHeight);
    	util.switchScene(primaryStage, scene);
    	
    }

    public void onAnimationButtonPressed(){
        AnimationView animationView = new AnimationView(primaryStage,util);
        Scene scene = new Scene(animationView,MainView.stageWidth,MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }

    public void onGeneralSettingButtonPressed() {
        GeneralSettingView generalSettingView = new GeneralSettingView(primaryStage);
        Scene scene = new Scene(generalSettingView, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }

    public void onSimulationButtonPressed() {
        SimulationView simulationView = new SimulationView(primaryStage);
        Scene scene = new Scene(simulationView, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }
}
