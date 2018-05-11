
package platformer.domain.stage;

import java.util.ArrayList;
import platformer.domain.StageNum;
import platformer.domain.entity.Coin;
import platformer.domain.entity.EndPoint;
import platformer.domain.entity.Platform;

/**
 * Abstraction for the different stages in the game.
 * @author tote
 */
public class GameStage {
    ArrayList<Platform> platforms;
    ArrayList<Coin> coins;
    Double windowX;
    Double windowY;
    EndPoint goal;
    StageNum number;
    public double characterX;
    public double characterY;
    
    /**
     * Constructor.
     * @param windowX Width of the game window.
     * @param windowY Height of the game window.
     */
    public GameStage(Double windowX, Double windowY) {
        this.platforms = new ArrayList<>();
        this.coins = new ArrayList<>();
        
        this.windowX = windowX;
        this.windowY = windowY;
        
        characterX = (double) windowX / 10;
        characterY = (double) windowY * 0.7;
    }
    
    public StageNum getNumber() {
        return this.number;
    }
    
    public ArrayList<Platform> getPlatforms() {
        return this.platforms;
    }
    
    public ArrayList<Coin> getCoins() {
        return this.coins;
    }
    
    public EndPoint getEndPoint() {
        return this.goal;
    }
    
    /**
     * Sets up coins and the EndPoint of the stage to the default state.
     */
    public void setupEntities() {
    }
}
