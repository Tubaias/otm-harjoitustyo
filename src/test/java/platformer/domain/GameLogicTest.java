
package platformer.domain;

import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import platformer.domain.entity.GameCharacter;

public class GameLogicTest {
    private GameLogic logic;
    private HashMap<KeyCode, Boolean> keys;
    private GameCharacter ch;
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
        ch = logic.getCharacter();
    }
    
    @Test
    public void canGetActiveKeys() {
        assertEquals(0, logic.getActiveKeys().size());
    }
}
