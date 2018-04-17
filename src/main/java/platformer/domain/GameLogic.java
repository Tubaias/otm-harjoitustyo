package platformer.domain;

import platformer.domain.entity.GameCharacter;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import platformer.ui.GameUI;

public class GameLogic {
    private Stage stage;
    private int windowX;
    private int windowY;
    private HashMap<KeyCode, Boolean> activeKeys;
    private GameCharacter character;
    private MenuLogic menuLogic;
    private GameUI gameUI;
    private GameLogic self;
    private AnimationTimer animationTimer;
    
    public GameLogic(int windowX, int windowY) {
        this.windowX = windowX;
        this.windowY = windowY;
        
        this.self = this;
        
        this.setup();
    }

    private void setup() {
        character = new GameCharacter((double) windowX / 2, (double) windowY / 2);
        
        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long currentTime) {
                if (activeKeys.getOrDefault(KeyCode.LEFT, false)) {
                    character.moveLeft(1);
                }

                if (activeKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    character.moveRight(1);
                }
                
                if (activeKeys.getOrDefault(KeyCode.A, false)) {
                    character.moveUp(1);
                } 
                
                if (activeKeys.getOrDefault(KeyCode.DOWN, false)) {
                    character.moveDown(1);
                }
                
                if (activeKeys.getOrDefault(KeyCode.UP, false)) {
                    character.jump();
                }
                
                if (activeKeys.getOrDefault(KeyCode.ESCAPE, false)) {
                    activeKeys.put(KeyCode.ESCAPE, false);
                    menuLogic.goToMain();
                }
                
                if (activeKeys.getOrDefault(KeyCode.R, false)) {
                    activeKeys.put(KeyCode.R, false);
                    character = new GameCharacter((double) windowX / 2, (double) windowY / 2);
                    gameUI.setCharacterPoly(character.getPoly());
                }
                
                character.update();
            }
        };
        
        animationTimer.start();
    }
    
    public GameCharacter getCharacter() {
        return this.character;
    }
    
    public void setActiveKeys(HashMap<KeyCode, Boolean> activeKeys) {
        this.activeKeys = activeKeys;
    }

    public HashMap<KeyCode, Boolean> getActiveKeys() {
        return this.activeKeys;
    }
    
    public AnimationTimer getAnimationTimer() {
        return this.animationTimer;
    }
    
    public void setMenuLogic(MenuLogic menuLogic) {
        this.menuLogic = menuLogic;
    }
    
    public void setGameUI(GameUI gameUI) {
        this.gameUI = gameUI;
    }
}
