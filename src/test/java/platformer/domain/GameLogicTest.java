
package platformer.domain;

import java.util.HashMap;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import platformer.domain.entity.GameCharacter;

public class GameLogicTest {
    private GameLogic logic;
    private HashMap<KeyCode, Boolean> keys;
    
    public GameLogicTest() {
    }
    
    @Before
    public void setUp() {
        logic = new GameLogic(1280, 720);
        
        keys = new HashMap<>();
        logic.setActiveKeys(keys);
    }
    
    @Test
    public void getCharacterReturnsCharacter() {
        GameCharacter comparison = new GameCharacter(0.0, 0.0);
        assertTrue(comparison.getClass().equals(logic.getCharacter().getClass()));
    }
    
    @Test
    public void canGetActiveKeys() {
        assertEquals(0, logic.getActiveKeys().size());
    }
    
    @Test
    public void upMakesCharacterJump() {
        GameCharacter character = logic.getCharacter();
        Double dY = character.getDeltaY();
     
        keys.put(KeyCode.UP, true);
        logic.getAnimationTimer().handle(0);
        assertTrue(dY > character.getDeltaY());
    }
}
