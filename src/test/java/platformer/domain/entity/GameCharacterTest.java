package platformer.domain.entity;


import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import platformer.domain.State;

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
        ch.update(10_000_000);
        
        assertTrue(ch.getDeltaX() > dX);
    }
    
    @Test
    public void moveLeftDecreasesDeltaX() {
        Double dX = ch.getDeltaX();
        
        ch.moveLeft();
        ch.update(10_000_000);
        
        assertTrue(ch.getDeltaX() < dX);
    }
    
    @Test
    public void beingInAirMakesCharacterFall() {
        ch.setState(State.AIR);
        double oldDY = ch.getDeltaY();
        
        ch.update(10_000_000);
        assertTrue(oldDY < ch.getDeltaY());
    }
    
    @Test
    public void beingOnGroundDoesntMakeCharacterFall() {
        ch.setState(State.GROUND);
        double oldDY = ch.getDeltaY();
        
        ch.update(10_000_000);
        assertTrue(oldDY == ch.getDeltaY());
    }
    
    @Test
    public void collisionReturnsTrueWhenCollides() {
        Platform plat = new Platform(State.GROUND, 0, 0, 500, 0, 500, 500, 0, 500);
        
        ch.update(10_000_000);
        assertTrue(ch.collision(plat));
    }
    
    @Test
    public void collisionReturnsFalseWhenDoesntCollide() {
        Platform plat = new Platform(State.GROUND, 0, 0, 1, 0, 1, 1, 0, 1);
        
        ch.update(10_000_000);
        assertFalse(ch.collision(plat));
    }
    
    @Test
    public void coinCollisionReturnsTrueWhenCollides() {
        Coin coin = new Coin(15d, 25d);
        
        ch.update(10_000_000);
        assertTrue(ch.coinCollision(coin));
    }
    
    @Test
    public void coinCollisionReturnsFalseWhenDoesntCollide() {
        Coin coin = new Coin(0d, 0d);
        
        ch.update(10_000_000);
        assertFalse(ch.coinCollision(coin));
    }
    
    @Test
    public void goalCollisionReturnsTrueWhenCollides() {
        EndPoint goal = new EndPoint(0, 0, 500, 0, 500, 500, 0, 500);
        
        ch.update(10_000_000);
        assertTrue(ch.goalCollision(goal));
    }
    
    @Test
    public void goalCollisionReturnsFalseWhenDoesntCollide() {
        EndPoint goal = new EndPoint(0, 0, 1, 0, 1, 1, 0, 1);
        
        ch.update(10_000_000);
        assertFalse(ch.goalCollision(goal));
    }
    
    @Test
    public void chargedJumpUpDecreasesDeltaY() {
        ch.moveRight();
        ch.update(10_000_000);
        ch.setState(State.GROUND);
        ch.chargeUp();
        
        Double oldDeltaY = ch.getDeltaY();
        
        ch.jump(KeyCode.UP);
        assertTrue(ch.getDeltaY() < oldDeltaY);
    }
    
    @Test
    public void chargedJumpRightDecreasesDeltaY() {
        ch.moveRight();
        ch.update(10_000_000);
        ch.setState(State.GROUND);
        ch.chargeUp();
        
        Double oldDeltaY = ch.getDeltaY();
        
        ch.jump(KeyCode.RIGHT);
        assertTrue(ch.getDeltaY() < oldDeltaY);
    }
    
    @Test
    public void chargedJumpLeftDecreasesDeltaY() {
        ch.moveRight();
        ch.update(10_000_000);
        ch.setState(State.GROUND);
        ch.chargeUp();
        
        Double oldDeltaY = ch.getDeltaY();
        
        ch.jump(KeyCode.LEFT);
        assertTrue(ch.getDeltaY() < oldDeltaY);
    }
    
    @Test
    public void chargedJumpRightIncreasesDeltaX() {
        ch.moveRight();
        ch.update(10_000_000);
        ch.setState(State.GROUND);
        ch.chargeUp();
        
        Double oldDeltaX = ch.getDeltaX();
        
        ch.jump(KeyCode.RIGHT);
        assertTrue(ch.getDeltaX() > oldDeltaX);
    }
    
    @Test
    public void chargedJumpLeftDecreasesDeltaX() {
        ch.moveRight();
        ch.update(10_000_000);
        ch.setState(State.GROUND);
        ch.chargeUp();
        
        Double oldDeltaX = ch.getDeltaX();
        
        ch.jump(KeyCode.LEFT);
        assertTrue(ch.getDeltaX() < oldDeltaX);
    }
    
    @Test
    public void leftWallStickStopsXMovement() {
        ch.moveRight();
        ch.update(10_000_000);
        ch.setState(State.LEFTWALL);
        ch.chargeUp();
        
        ch.update(10_000_000);
        assertTrue(ch.getDeltaX() == 0);
    }
    
    @Test
    public void leftWallStickStopsYMovement() {
        ch.moveRight();
        ch.update(10_000_000);
        ch.setState(State.LEFTWALL);
        ch.chargeUp();
        
        ch.update(10_000_000);
        assertTrue(ch.getDeltaY() == 0);
    }
    
    @Test
    public void rightWallStickStopsXMovement() {
        ch.moveRight();
        ch.update(10_000_000);
        ch.setState(State.RIGHTWALL);
        ch.chargeUp();
        
        ch.update(10_000_000);
        assertTrue(ch.getDeltaX() == 0);
    }
    
    @Test
    public void rightWallStickStopsYMovement() {
        ch.moveRight();
        ch.update(10_000_000);
        ch.setState(State.RIGHTWALL);
        ch.chargeUp();
        
        ch.update(10_000_000);
        assertTrue(ch.getDeltaY() == 0);
    }
}
