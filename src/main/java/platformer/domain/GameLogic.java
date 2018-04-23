package platformer.domain;

import platformer.domain.entity.GameCharacter;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import platformer.domain.entity.Platform;
import platformer.domain.stage.GameStage;
import platformer.domain.stage.Stage1;
import platformer.domain.stage.StageDebug;
import platformer.ui.GameUI;

public class GameLogic {

    private Stage stage;
    private int windowX;
    private int windowY;
    private HashMap<KeyCode, Boolean> activeKeys;
    private GameCharacter character;
    private MenuLogic menuLogic;
    private GameUI gameUI;
    private AnimationTimer animationTimer;
    private long chargeCountdown;
    private GameStage currentStage;

    public GameLogic(int windowX, int windowY) {
        this.windowX = windowX;
        this.windowY = windowY;

        character = new GameCharacter((double) windowX / 10, (double) windowY * 0.8);
    }

    public void setup() {
        chargeCountdown = 0;

//        StageDebug testStage = new StageDebug((double) windowX, (double) windowY);
//        Stage1 stage1 = new Stage1((double) windowX, (double) windowY);
//        this.loadStage(StageNo.DEBUG);
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
                    character = new GameCharacter((double) windowX / 10, (double) windowY * 0.8);
                    gameUI.setCharacterPoly(character.getPoly());
                    chargeCountdown = 0;
                }

                character.update();

                if (currentStage != null) {
                    for (Platform p : currentStage.getPlatforms()) {
                        if (character.collision(p)) {
                            p.toggleColor();
                        }
                    }
                }
            }
        };

        animationTimer.start();
    }

    public void loadStage(StageNo number) {
        gameUI.clear();

        GameStage stage;

        if (number == StageNo.ONE) {
            stage = new Stage1((double) windowX, (double) windowY);
        } else {
            stage = new StageDebug((double) windowX, (double) windowY);
        }
        
        currentStage = stage;

        for (Platform p : stage.getPlatforms()) {
            gameUI.addShape(p.getPoly());
        }
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
