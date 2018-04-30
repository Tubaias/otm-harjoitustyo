package platformer.domain;

import java.util.ArrayList;
import platformer.domain.entity.GameCharacter;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import platformer.domain.entity.Coin;
import platformer.domain.entity.EndPoint;
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
    private GameStage currentStage;
    private Platform lastCollision;
    private ArrayList<Coin> hitCoins;
    private long chargeCountdown;
    private boolean firstCycle;
    
    public GameLogic(int windowX, int windowY) {
        this.windowX = windowX;
        this.windowY = windowY;

        lastCollision = new Platform(State.AIR, 0, 0, 0, 0, 0, 0, 0, 0);
        character = new GameCharacter((double) windowX / 10, (double) windowY * 0.7);
        hitCoins = new ArrayList<>();
    }

    public void setup() {
        chargeCountdown = 0;
        firstCycle = true;

        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (firstCycle) {
                    gameUI.setStartTime(now);
                    firstCycle = false;
                }
                
                gameUI.updateTimer(now);
                checkOutOfBounds();
                checkCharge(now);

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
                    handleJump();
                }

                if (activeKeys.getOrDefault(KeyCode.ESCAPE, false)) {
                    activeKeys.put(KeyCode.ESCAPE, false);
                    menuLogic.goToMain();
                }

                if (activeKeys.getOrDefault(KeyCode.R, false)) {
                    activeKeys.put(KeyCode.R, false);
                    reset();
                }

                character.update();

                if (currentStage != null) {
                    checkPlatformCollisions();

                    if (!currentStage.getCoins().isEmpty()) {
                        checkCoinCollisions();
                    } else {
                        checkGoalCollisions(now);
                    }
                }
            }
        };

        animationTimer.start();
    }

    private void checkOutOfBounds() {
        if (character.getX() > windowX + 20
                || character.getX() < -20
                || character.getY() > windowY + 20
                || character.getY() < -20) {
            reset();
        }
    }

    private void checkCharge(long now) {
        if (character.isCharged()) {
            if (chargeCountdown == 0) {
                chargeCountdown = now;
            } else if (now - chargeCountdown > 300_000_000) {
                character.unCharge();
                chargeCountdown = 0;
            }
        }
    }

    private void checkPlatformCollisions() {
        boolean fallAgain = true;

        for (Platform p : currentStage.getPlatforms()) {
            if (character.collision(p)) {
                if ((character.getState() == State.AIR
                        && !((p.getType() == State.LEFTWALL || p.getType() == State.RIGHTWALL)
                        && lastCollision == p))
                        && p.getType() != State.AIR) {
                    character.chargeUp();
                }

                character.setState(p.getType());

                lastCollision = p;
                fallAgain = false;
            }
        }

        if (fallAgain && character.getState() == State.GROUND) {
            character.setState(State.AIR);
        } else if (fallAgain && !character.isCharged() && (character.getState() == State.LEFTWALL || character.getState() == State.RIGHTWALL)) {
            character.setState(State.AIR);
        }
    }

    private void checkCoinCollisions() {
        for (Coin c : currentStage.getCoins()) {
            if (character.coinCollision(c)) {
                hitCoins.add(c);
            }
        }

        if (!hitCoins.isEmpty()) {
            for (Coin c : hitCoins) {
                currentStage.getCoins().remove(c);
                gameUI.removeShape(c.getShape());
            }

            hitCoins.clear();
        }
    }

    private void checkGoalCollisions(long now) {
        EndPoint goal = currentStage.getEndPoint();

        if (goal == null) {
            return;
        }

        if (character.goalCollision(goal)) {
            win(now);
        }
    }

    private void handleJump() {
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

    public void loadStage(StageNo number) {
        reset();
        gameUI.clear();

        GameStage gStage;

        if (number == StageNo.ONE) {
            gStage = new Stage1((double) windowX, (double) windowY);
        } else {
            gStage = new StageDebug((double) windowX, (double) windowY);
        }

        currentStage = gStage;

        for (Platform p : currentStage.getPlatforms()) {
            gameUI.addShape(p.getPoly());
        }

        this.addEntitiesToUI();
    }

    public void reset() {
        firstCycle = true;
        
        if (currentStage == null) {
            return;
        }

        currentStage.setupEntities();

        this.addEntitiesToUI();

        character = new GameCharacter((double) windowX / 10, (double) windowY * 0.7);
        gameUI.setCharacterPoly(character.getPoly());
        chargeCountdown = 0;
    }

    public void win(long now) {
        System.out.println("tuut");
        reset();
    }

    private void addEntitiesToUI() {
        if (currentStage == null) {
            return;
        }

        for (Coin c : currentStage.getCoins()) {
            gameUI.addShape(c.getShape());
        }

        EndPoint goal = currentStage.getEndPoint();

        if (goal != null) {
            gameUI.addShape(goal.getPoly());
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
