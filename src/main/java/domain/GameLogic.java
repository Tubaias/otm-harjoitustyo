package domain;

import domain.entity.GameCharacter;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GameLogic {
    private Stage stage;
    private int windowX;
    private int windowY;
    private HashMap<KeyCode, Boolean> activeKeys;
    private GameCharacter character;
    private MenuLogic menuLogic;
    
    public GameLogic(Stage stage, int windowX, int windowY) {
        this.stage = stage;
        this.windowX = windowX;
        this.windowY = windowY;
        
        character = new GameCharacter(windowX / 2, windowY / 2);
        
        this.init();
    }

    private void init() {
        new AnimationTimer() {

            @Override
            public void handle(long nykyhetki) {
                if (activeKeys.getOrDefault(KeyCode.LEFT, false)) {
                    character.moveLeft(1);
                }

                if (activeKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    character.moveRight(1);
                }
                
                if (activeKeys.getOrDefault(KeyCode.UP, false)) {
                    character.moveUp(1);
                }
                
                if (activeKeys.getOrDefault(KeyCode.DOWN, false)) {
                    character.moveDown(1);
                }
                
                if (activeKeys.getOrDefault(KeyCode.ESCAPE, false)) {
                    activeKeys.put(KeyCode.ESCAPE, false);
                    menuLogic.goToMain();
                }
            }
        }.start();
    }
    
    public GameCharacter getCharacter() {
        return this.character;
    }
    
    public void setActiveKeys(HashMap<KeyCode, Boolean> activeKeys) {
        this.activeKeys = activeKeys;
    }
    
    public void setMenuLogic(MenuLogic menuLogic) {
        this.menuLogic = menuLogic;
    }
}
