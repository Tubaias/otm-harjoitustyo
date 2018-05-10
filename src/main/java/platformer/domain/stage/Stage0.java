
package platformer.domain.stage;

import platformer.domain.StageNum;
import platformer.domain.State;
import platformer.domain.entity.Coin;
import platformer.domain.entity.EndPoint;
import platformer.domain.entity.Platform;

public class Stage0 extends GameStage {
    
    public Stage0(Double windowX, Double windowY) {
        super(windowX, windowY);
        
        number = StageNum.ZERO;
        
        this.setupGround();
        this.setupBounds();
        this.setupEntities();
    }
    
    private void setupGround() {
        Platform ground = new Platform(State.GROUND, 0, 0, windowX.intValue(), 0, windowX.intValue(), 50, 0, 50);
        
        ground.setTranslateY(windowY - 1);
        
        platforms.add(ground);
    }
    
    private void setupBounds() {
        Platform leftBound = new Platform(State.RIGHTWALL, 0, 0, 50, 0, 50, windowY.intValue(), 0, windowY.intValue());
        Platform rightBound = new Platform(State.LEFTWALL, 0, 0, 50, 0, 50, windowY.intValue(), 0, windowY.intValue());
        Platform ceiling = new Platform(State.AIR, 0, 0, windowX.intValue(), 0, windowX.intValue(), 50, 0, 50);
        
        leftBound.setTranslateX(-50d);
        
        rightBound.setTranslateX(windowX);
        
        ceiling.setTranslateY(-50d);
        
        platforms.add(leftBound);
        platforms.add(rightBound);
        platforms.add(ceiling);
    }
    
    @Override
    public void setupEntities() {
        goal = new EndPoint(0, 0, 20, 0, 20, 20, 0, 20);
        
        goal.setTranslateX(windowX - 100);
        goal.setTranslateY(windowY - 100);
        
        Coin coin1 = new Coin(windowX / 2, windowY / 2 - 150);
        Coin coin2 = new Coin(200d, windowY - 150);
        
        coins.add(coin1);
        coins.add(coin2);
    }
}
