
package platformer.ui;

import platformer.domain.MenuLogic;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class MainMenu {
    private MenuLogic logic;
    private Scene scene;
    private int windowX;
    private int windowY;
    
    public MainMenu(MenuLogic logic, int windowX, int windowY) {
        this.logic = logic;
        this.windowX = windowX;
        this.windowY = windowY;
        
        this.setup();
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    private void setup() {
        VBox mainLayout = new VBox();
        mainLayout.setSpacing(30);
        mainLayout.setAlignment(Pos.CENTER);
        
        this.scene = new Scene(mainLayout, windowX, windowY);
        
        mainLayout.getChildren().add(new Label("SUPER PLATFORMER EXTREME 6000"));
        
        Button startButton = new Button("Start game");
        Button levelSelectButton = new Button("Level select");
        Button timesButton = new Button("Best times");
        Button optionsButton = new Button("Options");
        mainLayout.getChildren().addAll(startButton, levelSelectButton, timesButton, optionsButton);
        
        startButton.setOnAction((ActionEvent event) -> {
            logic.goToGame();
        });
        
        optionsButton.setOnAction((ActionEvent event) -> {
            logic.goToOptions();
        });
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                logic.exitDialog();
            }
        });
    }
}
