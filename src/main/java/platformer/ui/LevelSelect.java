
package platformer.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import platformer.domain.GameLogic;
import platformer.domain.MenuLogic;
import platformer.domain.StageNum;

public class LevelSelect {
    private MenuLogic logic;
    private GameLogic gameLogic;
    private Scene scene;
    private int windowX;
    private int windowY;
    
    public LevelSelect(MenuLogic logic, GameLogic gameLogic, int windowX, int windowY) {
        this.logic = logic;
        this.gameLogic = gameLogic;
        this.windowX = windowX;
        this.windowY = windowY;
        
        this.setup();
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    private void setup() {
        VBox levels = new VBox();
        this.scene = new Scene(levels, windowX, windowY);
        
        levels.setSpacing(10);
        levels.setAlignment(Pos.CENTER);
        levels.setPadding(new Insets(10, 0, 10, 0));
        
        Button stageZero = new Button("Stage 0");
        Button stageOne = new Button("Stage 1");
        Button stageTwo = new Button("Stage 2");
        Button stageThree = new Button("Stage 3");
        
        levels.getChildren().addAll(stageZero, stageOne, stageTwo, stageThree);
        
        stageZero.setOnAction((event) -> {
            gameLogic.loadStage(StageNum.ZERO);
            logic.goToGame();
        });
        
        stageOne.setOnAction((event) -> {
            gameLogic.loadStage(StageNum.ONE);
            logic.goToGame();
        });
        
        stageTwo.setOnAction((event) -> {
            gameLogic.loadStage(StageNum.TWO);
            logic.goToGame();
        });
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                logic.goToMain();
            }
        });
    }
}
