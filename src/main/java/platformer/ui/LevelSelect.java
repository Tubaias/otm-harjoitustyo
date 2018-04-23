
package platformer.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import platformer.domain.GameLogic;
import platformer.domain.MenuLogic;
import platformer.domain.StageNo;

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
        
        Button stageDebug = new Button("Stage Debug");
        Button stageOne = new Button("Stage 1");
        
        levels.getChildren().addAll(stageDebug, stageOne);
        
        stageDebug.setOnAction((event) -> {
            gameLogic.loadStage(StageNo.DEBUG);
            logic.goToGame();
        });
        
        stageOne.setOnAction((event) -> {
            gameLogic.loadStage(StageNo.ONE);
            logic.goToGame();
        });
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                logic.goToMain();
            }
        });
    }
}
