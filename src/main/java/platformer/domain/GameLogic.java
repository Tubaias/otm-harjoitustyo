package platformer.domain;

import java.util.ArrayList;
import platformer.domain.entity.GameCharacter;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import platformer.domain.entity.Coin;
import platformer.domain.entity.EndPoint;
import platformer.domain.entity.Platform;
import platformer.domain.stage.GameStage;
import platformer.domain.stage.Stage1;
import platformer.domain.stage.Stage0;
import platformer.domain.stage.Stage2;
import platformer.ui.GameUI;

public class GameLogic {

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
    private long startTime;
    private boolean firstCycle;
    private boolean playThroughMode;

    /**
     * GameLogic class constructor. Sets up private values that don't depend on
     * external classes.
     *
     * @param windowX Width of the game window
     * @param windowY Height of the game window
     */
    public GameLogic(int windowX, int windowY) {
        this.windowX = windowX;
        this.windowY = windowY;

        lastCollision = new Platform(State.AIR, 0, 0, 0, 0, 0, 0, 0, 0);
        
        if (currentStage == null) {
            character = new GameCharacter((double) windowX / 10, (double) windowY * 0.7);
        } else {
            character = new GameCharacter(currentStage.characterX, currentStage.characterY);
        }
        
        
        hitCoins = new ArrayList<>();
        playThroughMode = false;
    }

    /**
     * Sets up values that depend on external classes and defines the
     * animationtimer object that handles all frame-by-frame game logic.
     */
    public void setup() {
        chargeCountdown = 0;
        firstCycle = true;

        animationTimer = new AnimationTimer() {

            /**
             * Handles all real-time game logic like checking inputs and
             * collisions.
             *
             * @param now Current system time in nanoseconds
             */
            @Override
            public void handle(long now) {
                if (firstCycle) {
                    if (!playThroughMode) {
                        startTime = now;
                        gameUI.setStartTime(now);
                    }

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
            stageClear(now);
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

    /**
     * Starts a new timed playthrough of all levels.
     */
    public void startGame() {
        playThroughMode = true;
        loadStage(StageNum.ZERO);
    }

    private void nextStage(long now) {
        StageNum currentNumber = currentStage.getNumber();
        
        switch (currentNumber) {
            case ZERO:
                loadStage(StageNum.ONE);
                break;
            case ONE:
                loadStage(StageNum.TWO);
                break;
            case TWO:
                loadStage(StageNum.THREE);
                break;
            default:
                gameClear(now);
                break;
        }
    }

    /**
     * Resets the game and loads up a stage based on the given stage enum.
     *
     * @param number Enum that defines which stage will be loaded up.
     */
    public void loadStage(StageNum number) {
        reset();
        gameUI.clear();

        GameStage gStage;

        if (number == StageNum.ONE) {
            gStage = new Stage1((double) windowX, (double) windowY);
        } else if (number == StageNum.TWO) { 
            gStage = new Stage2((double) windowX, (double) windowY);
        } else {
            gStage = new Stage0((double) windowX, (double) windowY);
        }

        currentStage = gStage;

        for (Platform p : currentStage.getPlatforms()) {
            gameUI.addShape(p.getPoly());
        }

        addEntitiesToUI();
    }

    /**
     * Resets the game state to default based on the current stage.
     */
    public void reset() {
        firstCycle = true;

        if (currentStage == null) {
            return;
        }

        currentStage.setupEntities();
        addEntitiesToUI();

        character = new GameCharacter(currentStage.characterX, currentStage.characterY);
        gameUI.setCharacterPoly(character.getPoly());
        chargeCountdown = 0;
    }

    /**
     * Handles procedures to be taken when a stage is beaten.
     *
     * @param now Current system time in nanoseconds
     */
    public void stageClear(long now) {
        ClearTime clear = new ClearTime(1, menuLogic.getUsername(), currentStage.getNumber(), now - startTime);
        boolean saved = menuLogic.assessAndSave(clear);

        if (saved) {
            System.out.println("SAVED");
        }

        reset();
    }

    /**
     * Handles procedures to be taken when the game is beaten.
     *
     * @param now Current system time in nanoseconds
     */
    public void gameClear(long now) {
        ClearTime clear = new ClearTime(1, menuLogic.getUsername(), StageNum.GAME, now - startTime);
        boolean saved = menuLogic.assessAndSave(clear);

        if (saved) {
            System.out.println("SAVED");
        }

        playThroughMode = false;
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

    public GameStage getCurrentStage() {
        return this.currentStage;
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
