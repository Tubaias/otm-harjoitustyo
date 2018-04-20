package platformer.domain;

import platformer.domain.entity.GameCharacter;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import platformer.domain.entity.Platform;
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
    private long chargeCountdown;

    public GameLogic(int windowX, int windowY) {
        this.windowX = windowX;
        this.windowY = windowY;

        this.self = this;
    }

    public void setup() {
        character = new GameCharacter((double) windowX / 2, (double) windowY / 2);
        chargeCountdown = 0;
        
        Platform platform = new Platform(0, 0, 0, 10, 10, 1000, 0, 1000);
        platform.setTranslateY(500d);
        gameUI.addShape(platform.getPoly());

        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (character.isCharged()) {
                    if (chargeCountdown == 0) {
                        chargeCountdown = now;
                    } else if (now - chargeCountdown > 300000000) {
                        character.unCharge();
                        chargeCountdown = 0;
                    }
                }
                
                if (activeKeys.getOrDefault(KeyCode.LEFT, false)) {
                    character.moveLeft();
                } else if (character.getState() == State.GROUND && character.getDeltaX() < 0) {
                    character.stopOnGround();
                }

                if (activeKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    character.moveRight();
                } else if (character.getState() == State.GROUND && character.getDeltaX() > 0) {
                    character.stopOnGround();
                }

                if (activeKeys.getOrDefault(KeyCode.UP, false)) {
                    if (character.isCharged()) {
                        chargeCountdown = 0;
                    }
                    
                    if (activeKeys.getOrDefault(KeyCode.RIGHT, false) && activeKeys.getOrDefault(KeyCode.LEFT, false)) {
                        character.jump(KeyCode.UP);
                    } else if (activeKeys.getOrDefault(KeyCode.RIGHT, false)) {
                        character.jump(KeyCode.RIGHT);
                    } else if (activeKeys.getOrDefault(KeyCode.LEFT, false)) {
                        character.jump(KeyCode.LEFT);
                    } else {
                        character.jump(KeyCode.UP);
                    }
                }

                if (activeKeys.getOrDefault(KeyCode.DOWN, false)) {
                    character.moveDown();
                }

                if (activeKeys.getOrDefault(KeyCode.S, false)) {
                    character.simpleJump();
                }

                if (activeKeys.getOrDefault(KeyCode.ESCAPE, false)) {
                    activeKeys.put(KeyCode.ESCAPE, false);
                    menuLogic.goToMain();
                }

                if (activeKeys.getOrDefault(KeyCode.R, false)) {
                    activeKeys.put(KeyCode.R, false);
                    character = new GameCharacter((double) windowX / 2, (double) windowY / 2);
                    gameUI.setCharacterPoly(character.getPoly());
                    chargeCountdown = 0;
                }

                character.update();
            }
        };

        animationTimer.start();
    }

    public GameCharacter getCharacter() {
        return this.character;
    }

    public HashMap<KeyCode, Boolean> getActiveKeys() {
        return this.activeKeys;
    }

    public AnimationTimer getAnimationTimer() {
        return this.animationTimer;
    }

    public void setActiveKeys(HashMap<KeyCode, Boolean> activeKeys) {
        this.activeKeys = activeKeys;
    }

    public void setMenuLogic(MenuLogic menuLogic) {
        this.menuLogic = menuLogic;
    }

    public void setGameUI(GameUI gameUI) {
        this.gameUI = gameUI;
    }
}
