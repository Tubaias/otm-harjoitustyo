
package platformer.domain;

import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import platformer.domain.entity.GameCharacter;
import platformer.ui.FakeGameUI;
import platformer.ui.GameUI;

public class GameLogicTest {
    private GameLogic logic;
    private HashMap<KeyCode, Boolean> keys;
    private AnimationTimer timer;
    
    public GameLogicTest() {
    }
    
    @Before
    public void setUp() {
        logic = new GameLogic(1280, 720);
        logic.setup();
        
        keys = new HashMap<>();
        logic.setActiveKeys(keys);
        
        timer = logic.getAnimationTimer();
        
        MenuLogic menuLogic = new FakeMenuLogic();
        GameUI gameUI = new FakeGameUI();
        
        logic.setMenuLogic(menuLogic);
        logic.setGameUI(gameUI);
    }
    
    @Test
    public void canLoadStage0() {
        logic.loadStage(StageNum.ZERO);
        StageNum stage = logic.getCurrentStage().getNumber();
        
        assertEquals(StageNum.ZERO, stage);
    }
    
    @Test
    public void canLoadStage1() {
        logic.loadStage(StageNum.ONE);
        StageNum stage = logic.getCurrentStage().getNumber();
        
        assertEquals(StageNum.ONE, stage);
    }
    
    @Test
    public void canLoadStage2() {
        logic.loadStage(StageNum.TWO);
        StageNum stage = logic.getCurrentStage().getNumber();
        
        assertEquals(StageNum.TWO, stage);
    }
    
    @Test
    public void canLoadStage3() {
        logic.loadStage(StageNum.THREE);
        StageNum stage = logic.getCurrentStage().getNumber();
        
        assertEquals(StageNum.THREE, stage);
    }
    
    @Test
    public void loadingGameDefaultsToZero() {
        logic.loadStage(StageNum.GAME);
        StageNum stage = logic.getCurrentStage().getNumber();
        
        assertEquals(StageNum.ZERO, stage);
    }
    
    @Test
    public void winningPlaythroughEndsPlaythroughMode() {
        logic.startGame();
        
        logic.loadStage(StageNum.THREE);
        logic.getCurrentStage().getCoins().clear();
        
        logic.getCharacter().setX(logic.getCurrentStage().getEndPoint().getPoly().getTranslateX());
        logic.getCharacter().setY(logic.getCurrentStage().getEndPoint().getPoly().getTranslateY());
        
        timer.handle(1_000_000);
        
        assertFalse(logic.getPlaythroughMode());
    }
    
    @Test
    public void inputUpMakesCharacterJump() {
        logic.loadStage(StageNum.ZERO);
        
        GameCharacter ch = logic.getCharacter();
        ch.setState(State.GROUND);
        
        double oldY = ch.getY();
        
        keys.put(KeyCode.UP, true);
        timer.handle(1_000_000);
        
        assertTrue(oldY > ch.getY());
    }
    
    @Test
    public void inputUpRightMakesCharacterJumpRight() {
        logic.loadStage(StageNum.ZERO);
        
        GameCharacter ch = logic.getCharacter();
        ch.setState(State.GROUND);
        
        double oldY = ch.getY();
        double oldX = ch.getX();
        
        keys.put(KeyCode.UP, true);
        keys.put(KeyCode.RIGHT, true);
        timer.handle(1_000_000);
        
        assertTrue(oldY > ch.getY() && oldX < ch.getX());
    }
    
    @Test
    public void inputUpLeftMakesCharacterJumpLeft() {
        logic.loadStage(StageNum.ZERO);
        
        GameCharacter ch = logic.getCharacter();
        ch.setState(State.GROUND);
        
        double oldY = ch.getY();
        double oldX = ch.getX();
        
        keys.put(KeyCode.UP, true);
        keys.put(KeyCode.LEFT, true);
        timer.handle(1_000_000);
        
        assertTrue(oldY > ch.getY() && oldX > ch.getX());
    }
    
    @Test
    public void hittingGroundSetsCharge() {
        logic.loadStage(StageNum.ZERO);
        
        GameCharacter ch = logic.getCharacter();
        ch.setX(1280 / 2);
        ch.setY(logic.getCurrentStage().getPlatforms().get(0).getPoly().getTranslateY());
        
        timer.handle(1_000_000);
        assertTrue(ch.isCharged());
    }
    
    @Test
    public void inputTinPlaythroughGoesToStage0() {
        logic.startGame();
        logic.loadStage(StageNum.TWO);
        
        keys.put(KeyCode.T, true);
        
        timer.handle(1);
        assertEquals(StageNum.ZERO, logic.getCurrentStage().getNumber());
    }
    
    @Test
    public void inputTinPlaythroughResetsTimer() {
        logic.startGame();
        
        timer.handle(0);
        timer.handle(100);
        
        keys.put(KeyCode.T, true);
        
        timer.handle(1);
        assertEquals(1, logic.getStartTime());
    }
    
    @Test
    public void inputRinSingleStageResetsTimer() {
        logic.loadStage(StageNum.ZERO);
        
        timer.handle(0);
        timer.handle(100);
        
        keys.put(KeyCode.R, true);
        
        timer.handle(1);
        timer.handle(10);
        
        assertEquals(10, logic.getStartTime());
    }
    
    @Test
    public void inputRinPlaythroughDoesntResetTimer() {
        logic.startGame();
        
        timer.handle(0);
        timer.handle(100);
        
        keys.put(KeyCode.R, true);
        
        timer.handle(1);
        timer.handle(10);
        
        assertEquals(0, logic.getStartTime());
    }
}
