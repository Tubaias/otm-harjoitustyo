package platformer.domain.entity;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameCharacterTest {
    private GameCharacter ch;
    
    public GameCharacterTest() {
    }
    
    @Before
    public void setUp() {
        ch = new GameCharacter(15d, 25d);
    }
    
    @Test
    public void startLocationRight() {
        assertEquals("15.0:25.0", ch.toString());
    }

    @Test
    public void moveRightIncreasesDeltaX() {
        Double dX = ch.getDeltaX();
        
        ch.moveRight();
        assertTrue(ch.getDeltaX() > dX);
    }
    
    @Test
    public void moveLeftDecreasesDeltaX() {
        Double dX = ch.getDeltaX();
        
        ch.moveLeft();
        assertTrue(ch.getDeltaX() < dX);
    }
}
