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
import platformer.domain.stage.Stage0;
import platformer.domain.stage.Stage1;
import platformer.domain.stage.Stage2;
import platformer.domain.stage.Stage3;
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
    private long stageStartTime;
    private long lastFrame;
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

        chargeCountdown = 0;
        firstCycle = true;
    }

    /**
     * Defines the animationtimer-object that handles all frame-by-frame game
     * logic.
     */
    public void setup() {
        animationTimer = new AnimationTimer() {

            /**
             * Handles all real-time game logic like checking inputs and
             * collisions.
             *
             * @param now Current system time in nanoseconds
             */
            @Override
            public void handle(long now) {
                handlingProtocol(now);
            }
        };

        animationTimer.start();
    }

    private void handlingProtocol(long now) {
        handleVariables(now);

        gameUI.updateTimer(now);
        checkOutOfBounds();
        checkCharge(now);

        handleMovement();
        handleSpecialInputs(now);

        character.update(now - lastFrame);

        handleCollisions(now);

        lastFrame = now;
    }

    private void handleCollisions(long now) {
        if (currentStage != null) {
            checkPlatformCollisions();

            if (!currentStage.getCoins().isEmpty()) {
                checkCoinCollisions();
            } else {
                checkGoalCollisions(now);
            }
        }
    }

    private void handleVariables(long now) {
        if (firstCycle) {
            if (!playThroughMode) {
                startTime = now;
                gameUI.setStartTime(now);
            } else {
                stageStartTime = now;
            }

            firstCycle = false;
        }
    }

    private void handleMovement() {
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
    }

    private void handleSpecialInputs(long now) {
        if (activeKeys.getOrDefault(KeyCode.ESCAPE, false)) {
            activeKeys.put(KeyCode.ESCAPE, false);
            fullReset();
            menuLogic.goToMain();
        }

        if (activeKeys.getOrDefault(KeyCode.R, false)) {
            activeKeys.put(KeyCode.R, false);
            reset();
        }

        if (activeKeys.getOrDefault(KeyCode.T, false)) {
            if (playThroughMode) {
                activeKeys.put(KeyCode.T, false);
                resetPlaythrough(now);
            }
        }
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
                handlePlatformCollision(p);
                fallAgain = false;
            }
        }

        if (fallAgain && character.getState() == State.GROUND) {
            character.setState(State.AIR);
        } else if (fallAgain && !character.isCharged() && (character.getState() == State.LEFTWALL || character.getState() == State.RIGHTWALL)) {
            character.setState(State.AIR);
        }
    }

    private void handlePlatformCollision(Platform p) {
        if ((character.getState() == State.AIR
                && !((p.getType() == State.LEFTWALL || p.getType() == State.RIGHTWALL)
                && lastCollision == p))
                && p.getType() != State.AIR) {
            character.chargeUp();
        }

        if (p.getType() == State.CORNER) {
            character.setState(State.GROUND);
        } else {
            character.setState(p.getType());
        }

        if (p.getType() == State.GROUND) {
            character.setY(p.getPoly().getTranslateY() - 9);
        }

        lastCollision = p;
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

    /**
     * Resets the game and loads up a stage based on the given stage enum.
     *
     * @param number Enum that defines which stage will be loaded up.
     */
    public void loadStage(StageNum number) {
        gameUI.clear();

        GameStage gStage;

        switch (number) {
            case ONE:
                gStage = new Stage1((double) windowX, (double) windowY);
                break;
            case TWO:
                gStage = new Stage2((double) windowX, (double) windowY);
                break;
            case THREE:
                gStage = new Stage3((double) windowX, (double) windowY);
                break;
            default:
                gStage = new Stage0((double) windowX, (double) windowY);
                break;
        }

        currentStage = gStage;

        loadStageElements();
    }

    private void loadStageElements() {
        for (Platform p : currentStage.getPlatforms()) {
            gameUI.addShape(p.getPoly());
        }

        addEntitiesToUI();
        reset();
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
     * Resets the game, even when in playthrough mode, and clears active keys.
     */
    public void fullReset() {
        activeKeys.clear();
        playThroughMode = false;
        reset();
    }

    private void resetPlaythrough(long now) {
        startTime = now;
        gameUI.setStartTime(now);

        loadStage(StageNum.ZERO);
    }

    /**
     * Handles procedures to be taken when a stage is beaten.
     *
     * @param now Current system time in nanoseconds
     */
    public void stageClear(long now) {
        ClearTime clear = null;

        if (playThroughMode) {
            clear = new ClearTime(1, menuLogic.getUsername(), currentStage.getNumber(), now - stageStartTime);
        } else {
            clear = new ClearTime(1, menuLogic.getUsername(), currentStage.getNumber(), now - startTime);
        }

        boolean saved = menuLogic.assessAndSave(clear);

        if (saved) {
            System.out.println("time saved");
        }

        if (playThroughMode) {
            advancePlayThrough(now);
        }

        reset();
    }

    private void advancePlayThrough(long now) {
        if (currentStage.getNumber() == StageNum.THREE) {
            gameClear(now);
        } else {
            StageNum nextStage = StageNum.fromInt(currentStage.getNumber().getIntValue() + 1);
            loadStage(nextStage);
        }
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
        menuLogic.goToMain();
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
    
    public boolean getPlaythroughMode() {
        return this.playThroughMode;
    }
    
    public long getStartTime() {
        return this.startTime;
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
