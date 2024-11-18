package com.pms.project.controllers;

import java.util.Optional;

import com.pms.project.MainApp;
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
    private ThemeView themeView = new ThemeView(primaryStage);
    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void onTargetGameButtonPressed() {

    }

    // temporary method for testing, please delete at the end
    public void onTestButtonPressed() {
        Scene scene = new Scene(new BaseSceneView(primaryStage), MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }
    
    
    public void onQuitButtonPressed() {
    	Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Exit Application");
        confirmationAlert.setContentText("Are you sure you want to quit?");

    
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

       
        Text aboutUsText = new Text("Lorem ipsum odor amet, consectetuer adipiscing elit. Tempus eu massa scelerisque augue libero nisl maecenas. Litora fringilla inceptos turpis tristique sit ut. Euismod per lacus, ante lacus tempor cubilia. Lobortis mi risus volutpat egestas suspendisse sem dis dis. Nostra sapien euismod integer volutpat egestas. Malesuada vivamus libero parturient blandit class egestas est mattis.\r\n"
        		+ "\r\n"
        		+ "Commodo facilisis porta quisque ridiculus netus rutrum suspendisse risus. Posuere metus felis justo quis nascetur natoque potenti urna. Volutpat tristique ut cubilia fermentum accumsan tincidunt laoreet hendrerit. Tempor suscipit elit ullamcorper luctus ornare senectus. Pulvinar tristique blandit curabitur proin semper efficitur congue consectetur! Placerat dis feugiat lorem sodales neque mauris dis. Mus fames dis ante sollicitudin justo justo vulputate aenean finibus. Adipiscing suscipit morbi felis fringilla feugiat aliquet magnis dis platea. Dapibus id facilisi purus dapibus nascetur et quam posuere.\r\n"
        		+ "\r\n"
        		+ "Laoreet leo eleifend etiam vivamus quam faucibus ex. Et bibendum adipiscing imperdiet nullam semper curae eleifend eros. Ullamcorper magna est mollis commodo cras primis justo curabitur. Gravida netus metus conubia malesuada leo odio rhoncus. Rhoncus potenti maximus sodales nisi aliquet elit feugiat dolor. In sapien ornare non amet inceptos dolor; habitasse vitae. Pellentesque egestas suspendisse volutpat neque rhoncus adipiscing. Ipsum purus bibendum vitae ridiculus integer.\r\n"
        		+ "\r\n"
        		+ "Porttitor imperdiet phasellus ac, mi velit natoque euismod. Ex etiam fermentum sit semper rutrum metus feugiat taciti nostra. Bibendum placerat dignissim pellentesque viverra purus; litora rutrum curabitur. Erat nostra dictumst eleifend congue sociosqu ac laoreet tortor nunc? Dui metus feugiat felis phasellus suspendisse fringilla. Potenti imperdiet torquent vel inceptos, laoreet libero. Orci enim duis suspendisse purus himenaeos quam tempor netus inceptos. Sed nostra dis ullamcorper laoreet vehicula vivamus?\r\n"
        		+ "\r\n"
        		+ "Faucibus risus lectus feugiat et feugiat. Hac etiam velit sodales nibh fermentum congue blandit. Curae volutpat proin amet parturient posuere. Ipsum nisi porta aenean lacus mi. Lobortis ad tempor dignissim cras per auctor. Suspendisse natoque curabitur netus ullamcorper luctus est fringilla etiam. Viverra molestie quisque vivamus dolor, hac mattis. Consectetur facilisis sollicitudin turpis ligula ipsum mi neque ante. Erat odio nec enim nascetur venenatis augue. Penatibus magna maecenas consequat morbi urna rutrum magna ornare.");

        // Wrap the text within a TextFlow to handle line breaks automatically
        TextFlow textFlow = new TextFlow(aboutUsText);
        textFlow.setPrefWidth(MainView.stageWidth - 20); 

      
        Button backButton = new Button("Back");
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
    	ThemeView themeView = new ThemeView(primaryStage);
    	Scene scene = new Scene(themeView,MainView.stageWidth,MainView.stageHeight);
    	util.switchScene(primaryStage, scene);
    	
    }

    public void onAnimationButtonPressed(){
        AnimationView animationView = new AnimationView(primaryStage);
        Scene scene = new Scene(animationView,MainView.stageWidth,MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }

    public void onGeneralSettingButtonPressed() {
        GeneralSettingView generalSettingView = new GeneralSettingView(primaryStage);
        Scene scene = new Scene(generalSettingView, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, scene);
    }
}
