
import domain.entity.GameCharacter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameCharacterTest {
    
    public GameCharacterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void startLocationRight() {
        GameCharacter ch = new GameCharacter(15, 25);
        assertEquals("15.0:25.0", ch.toString());

    }

    @Test
    public void canMoveRightOnce() {
        GameCharacter ch = new GameCharacter(0, 0);
        ch.moveRight(50);
        assertEquals("50.0:0.0", ch.toString());
    }
}
